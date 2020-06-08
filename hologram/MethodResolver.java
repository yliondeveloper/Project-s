package xyz.starmc.hologram;

import java.lang.reflect.*;

public class MethodResolver extends MemberResolver<Method> {

	public MethodResolver(Class<?> clazz) {
		super(clazz);
	}

	public MethodResolver(String className) throws ClassNotFoundException {
		super(className);
	}

	public Method resolveSignature(String... signatures) throws ReflectiveOperationException {
		for (Method method : this.clazz.getDeclaredMethods()) {
			String methodSignature = MethodWrapper.getMethodSignature(method);
			for (String s : signatures) {
				if (s.equals(methodSignature)) {
					return AccessUtil.setAccessible(method);
				}
			}
		}
		return null;
	}

	public Method resolveSignatureSilent(String... signatures) {
		try {
			return this.resolveSignature(signatures);
		} catch (ReflectiveOperationException ignored) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public MethodWrapper<?> resolveSignatureWrapper(String... signatures) {
		return new MethodWrapper(this.resolveSignatureSilent(signatures));
	}

	@Override
	public Method resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
		return AccessUtil.setAccessible(this.clazz.getDeclaredMethods()[index]);
	}

	@Override
	public Method resolveIndexSilent(int index) {
		try {
			return this.resolveIndex(index);
		} catch (IndexOutOfBoundsException | ReflectiveOperationException ex2) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MethodWrapper<?> resolveIndexWrapper(int index) {
		return new MethodWrapper(this.resolveIndexSilent(index));
	}

	@SuppressWarnings("rawtypes")
	public MethodWrapper<?> resolveWrapper(String... names) {
		return new MethodWrapper(this.resolveSilent(names));
	}

	@SuppressWarnings("rawtypes")
	public MethodWrapper<?> resolveWrapper(ResolverQuery... queries) {
		return new MethodWrapper(this.resolveSilent(queries));
	}

	public Method resolveSilent(String... names) {
		try {
			return this.resolve(names);
		} catch (Exception e) {
			return null;
		}
	}

	public Method resolveSilent(ResolverQuery... queries) {
		return super.resolveSilent(queries);
	}

	public Method resolve(String... names) throws NoSuchMethodException {
		ResolverQuery.Builder builder = ResolverQuery.builder();
		for (String name : names) {
			builder.with(name);
		}
		return this.resolve(builder.build());
	}

	public Method resolve(ResolverQuery... queries) throws NoSuchMethodException {
		try {
			return super.resolve(queries);
		} catch (ReflectiveOperationException e) {
			throw (NoSuchMethodException) e;
		}
	}

	@Override
	protected Method resolveObject(ResolverQuery query) throws ReflectiveOperationException {
		for (Method method : this.clazz.getDeclaredMethods()) {
			if (method.getName().equals(query.getName())
					&& (query.getTypes().length == 0 || ClassListEqual(query.getTypes(), method.getParameterTypes()))) {
				return AccessUtil.setAccessible(method);
			}
		}
		throw new NoSuchMethodException();
	}

	@Override
	protected NoSuchMethodException notFoundException(String joinedNames) {
		return new NoSuchMethodException("Could not resolve method for " + joinedNames + " in class " + this.clazz);
	}

	static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length) {
			return false;
		}
		for (int i = 0; i < l1.length; ++i) {
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
}
