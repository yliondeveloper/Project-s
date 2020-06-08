package xyz.starmc.hologram;

import java.lang.reflect.*;

public abstract class AccessUtil {

	public static Field setAccessible(Field field) throws ReflectiveOperationException {
		field.setAccessible(true);
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
		return field;
	}

	public static Method setAccessible(Method method) throws ReflectiveOperationException {
		method.setAccessible(true);
		return method;
	}

	public static Constructor<?> setAccessible(Constructor<?> constructor) throws ReflectiveOperationException {
		constructor.setAccessible(true);
		return constructor;
	}
}
