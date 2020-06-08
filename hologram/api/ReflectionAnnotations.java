package xyz.starmc.hologram.api;

import java.lang.annotation.*;
import java.util.*;
import java.util.regex.*;

import xyz.starmc.hologram.ClassResolver;
import xyz.starmc.hologram.ClassWrapper;
import xyz.starmc.hologram.FieldResolver;
import xyz.starmc.hologram.FieldWrapper;
import xyz.starmc.hologram.MethodResolver;
import xyz.starmc.hologram.MethodWrapper;
import xyz.starmc.hologram.Minecraft;

public class ReflectionAnnotations {

	public static ReflectionAnnotations INSTANCE;
	static Pattern classRefPattern;

	public void load(Object toLoad) {
		if (toLoad == null) {
			throw new IllegalArgumentException("toLoad cannot be null");
		}
		ClassResolver classResolver = new ClassResolver();
		for (java.lang.reflect.Field field : toLoad.getClass().getDeclaredFields()) {
			Class classAnnotation = field.getAnnotation(Class.class);
			Field fieldAnnotation = field.getAnnotation(Field.class);
			Method methodAnnotation = field.getAnnotation(Method.class);
			if (classAnnotation != null || fieldAnnotation != null || methodAnnotation != null) {
				field.setAccessible(true);
				if (classAnnotation != null) {
					List<String> nameList = this.parseAnnotationVersions(Class.class, classAnnotation);
					if (nameList.isEmpty()) {
						throw new IllegalArgumentException("@Class names cannot be empty");
					}
					String[] names = nameList.toArray(new String[nameList.size()]);
					for (int i = 0; i < names.length; ++i) {
						names[i] = names[i].replace("{nms}", "net.minecraft.server." + Minecraft.VERSION.name())
								.replace("{obc}", "org.bukkit.craftbukkit." + Minecraft.VERSION.name());
					}
					try {
						if (ClassWrapper.class.isAssignableFrom(field.getType())) {
							field.set(toLoad, classResolver.resolveWrapper(names));
						} else {
							if (!java.lang.Class.class.isAssignableFrom(field.getType())) {
								this.throwInvalidFieldType(field, toLoad, "Class or ClassWrapper");
								return;
							}
							field.set(toLoad, classResolver.resolve(names));
						}
					} catch (ReflectiveOperationException e) {
						if (!classAnnotation.ignoreExceptions()) {
							this.throwReflectionException("@Class", field, toLoad, e);
							return;
						}
					}
				} else if (fieldAnnotation != null) {
					List<String> nameList = this.parseAnnotationVersions(Field.class, fieldAnnotation);
					if (nameList.isEmpty()) {
						throw new IllegalArgumentException("@Field names cannot be empty");
					}
					String[] names = nameList.toArray(new String[nameList.size()]);
					try {
						FieldResolver fieldResolver = new FieldResolver(
								this.parseClass(Field.class, fieldAnnotation, toLoad));
						if (FieldWrapper.class.isAssignableFrom(field.getType())) {
							field.set(toLoad, fieldResolver.resolveWrapper(names));
						} else {
							if (!java.lang.reflect.Field.class.isAssignableFrom(field.getType())) {
								this.throwInvalidFieldType(field, toLoad, "Field or FieldWrapper");
								return;
							}
							field.set(toLoad, fieldResolver.resolve(names));
						}
					} catch (ReflectiveOperationException e) {
						if (!fieldAnnotation.ignoreExceptions()) {
							this.throwReflectionException("@Field", field, toLoad, e);
							return;
						}
					}
				} else if (methodAnnotation != null) {
					List<String> nameList = this.parseAnnotationVersions(Method.class, methodAnnotation);
					if (nameList.isEmpty()) {
						throw new IllegalArgumentException("@Method names cannot be empty");
					}
					String[] names = nameList.toArray(new String[nameList.size()]);
					boolean isSignature = names[0].contains(" ");
					for (String s : names) {
						if (s.contains(" ") != isSignature) {
							throw new IllegalArgumentException(
									"Inconsistent method names: Cannot have mixed signatures/names");
						}
					}
					try {
						MethodResolver methodResolver = new MethodResolver(
								this.parseClass(Method.class, methodAnnotation, toLoad));
						if (MethodWrapper.class.isAssignableFrom(field.getType())) {
							if (isSignature) {
								field.set(toLoad, methodResolver.resolveSignatureWrapper(names));
							} else {
								field.set(toLoad, methodResolver.resolveWrapper(names));
							}
						} else {
							if (!java.lang.reflect.Method.class.isAssignableFrom(field.getType())) {
								this.throwInvalidFieldType(field, toLoad, "Method or MethodWrapper");
								return;
							}
							if (isSignature) {
								field.set(toLoad, methodResolver.resolveSignature(names));
							} else {
								field.set(toLoad, methodResolver.resolve(names));
							}
						}
					} catch (ReflectiveOperationException e2) {
						if (!methodAnnotation.ignoreExceptions()) {
							this.throwReflectionException("@Method", field, toLoad, e2);
							return;
						}
					}
				}
			}
		}
	}

	<A extends Annotation> List<String> parseAnnotationVersions(java.lang.Class<A> clazz, A annotation) {
		List<String> list = new ArrayList<String>();
		try {
			String[] names = (String[]) clazz.getMethod("value", (java.lang.Class<?>[]) new java.lang.Class[0])
					.invoke(annotation, new Object[0]);
			Minecraft.Version[] versions = (Minecraft.Version[]) clazz
					.getMethod("versions", (java.lang.Class<?>[]) new java.lang.Class[0])
					.invoke(annotation, new Object[0]);
			if (versions.length == 0) {
				for (String name : names) {
					list.add(name);
				}
			} else {
				if (versions.length > names.length) {
					throw new RuntimeException(
							"versions array cannot have more elements than the names (" + clazz + ")");
				}
				for (int i = 0; i < versions.length; ++i) {
					if (Minecraft.VERSION == versions[i]) {
						list.add(names[i]);
					} else if (names[i].startsWith(">") && Minecraft.VERSION.newerThan(versions[i])) {
						list.add(names[i].substring(1));
					} else if (names[i].startsWith("<") && Minecraft.VERSION.olderThan(versions[i])) {
						list.add(names[i].substring(1));
					}
				}
			}
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	<A extends Annotation> String parseClass(java.lang.Class<A> clazz, A annotation, Object toLoad) {
		try {
			String className = (String) clazz.getMethod("className", (java.lang.Class<?>[]) new java.lang.Class[0])
					.invoke(annotation, new Object[0]);
			Matcher matcher = ReflectionAnnotations.classRefPattern.matcher(className);
			while (matcher.find()) {
				if (matcher.groupCount() != 1) {
					continue;
				}
				String fieldName = matcher.group(1);
				java.lang.reflect.Field field = toLoad.getClass().getField(fieldName);
				if (ClassWrapper.class.isAssignableFrom(field.getType())) {
					return ((ClassWrapper<?>) field.get(toLoad)).getName();
				}
				if (java.lang.Class.class.isAssignableFrom(field.getType())) {
					return ((java.lang.Class) field.get(toLoad)).getName();
				}
			}
			return className;
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	void throwInvalidFieldType(java.lang.reflect.Field field, Object toLoad, String expected) {
		throw new IllegalArgumentException("Field " + field.getName() + " in " + toLoad.getClass() + " is not of type "
				+ expected + ", it's " + field.getType());
	}

	void throwReflectionException(String annotation, java.lang.reflect.Field field, Object toLoad,
			ReflectiveOperationException exception) {
		throw new RuntimeException(
				"Failed to set " + annotation + " field " + field.getName() + " in " + toLoad.getClass(), exception);
	}

	static {
		INSTANCE = new ReflectionAnnotations();
		classRefPattern = Pattern.compile("@Class\\((.*)\\)");
	}
}
