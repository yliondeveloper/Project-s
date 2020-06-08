package xyz.starmc.hologram;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;

public abstract class Reflection {

	public static String getVersion() {
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		return version;
	}

	public static Class<?> getNMSClass(String className) {
		String fullName = "net.minecraft.server." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public static Class<?> getNMSClassWithException(String className) throws Exception {
		String fullName = "net.minecraft.server." + getVersion() + className;
		Class<?> clazz = Class.forName(fullName);
		return clazz;
	}

	public static Class<?> getOBCClass(String className) {
		String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public static Object getHandle(Object obj) {
		try {
			return getMethod(obj.getClass(), "getHandle", new Class[0]).invoke(obj, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field getField(Class<?> clazz, String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field getFieldWithException(Class<?> clazz, String name) throws Exception {
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		return field;
	}

	public static Method getMethod(Class<?> clazz, String name, Class<?>... args) {
		Method[] arrayOfMethod;
		int j = (arrayOfMethod = clazz.getMethods()).length;
		for (int i = 0; i < j; i++) {
			Method m = arrayOfMethod[i];
			if ((m.getName().equals(name)) && ((args.length == 0) || (ClassListEqual(args, m.getParameterTypes())))) {
				m.setAccessible(true);
				return m;
			}
		}
		return null;
	}

	public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length) {
			return false;
		}
		for (int i = 0; i < l1.length; i++) {
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
}