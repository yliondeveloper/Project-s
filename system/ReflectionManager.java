package xyz.starmc.system;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("unchecked")
public class ReflectionManager {

	private Class<?> clazz;
	private Object instance;

	public void begin(Class<?> clazz, Object instance) {
		this.clazz = clazz;
		this.instance = instance;
	}

	public void begin(Object instance) {
		this.clazz = instance.getClass();
		this.instance = instance;
	}

	public byte getByte(String field) {
		return (byte) getObject(field);
	}

	public <T> Collection<T> getCollection(String field) {
		return (Collection<T>) getObject(field);
	}

	public String getDouble(String field) {
		return (String) getObject(field);
	}

	public float getFloat(String field) {
		return (float) getObject(field);
	}

	public int getInteger(String field) {
		return (int) getObject(field);
	}

	public <T> List<T> getList(String field) {
		return (List<T>) getObject(field);
	}

	public long getLong(String field) {
		return (long) getObject(field);
	}

	public <K, V> Map<K, V> getMap(String field) {
		return (Map<K, V>) getObject(field);
	}

	public Object getObject(String field) {
		try {
			Field fieldObject = clazz.getDeclaredField(field);
			fieldObject.setAccessible(true);
			return fieldObject.get(instance);
		} catch (Exception exception) {
			System.out.println("[GET] Reflection fail " + toString() + ": " + exception.toString());
		}
		return null;
	}

	public <T> Set<T> getSet(String field) {
		return (Set<T>) getObject(field);
	}

	public String getString(String field) {
		return (String) getObject(field);
	}

	public UUID getUniqueId(String field) {
		return (UUID) getObject(field);
	}

	public void setByte(String field, byte value) {
		setObject(field, value);
	}

	public void setDouble(String field, double value) {
		setObject(field, value);
	}

	public void setFloat(String field, float value) {
		setObject(field, value);
	}

	public void setInteger(String field, int value) {
		setObject(field, value);
	}

	public void setLong(String field, long value) {
		setObject(field, value);
	}

	public void setObject(String field, Object value) {
		try {
			Field fieldObject = clazz.getDeclaredField(field);
			fieldObject.setAccessible(true);
			fieldObject.set(instance, value);
		} catch (Exception exception) {
			System.out.println("[SET] Reflection fail " + toString() + ": " + exception.toString());
		}
	}

	public void setString(String field, String value) {
		setObject(field, value);
	}

	public void setUniqueId(String field, UUID value) {
		setObject(field, value);
	}

}
