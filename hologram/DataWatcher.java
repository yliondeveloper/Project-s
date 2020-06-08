package xyz.starmc.hologram;

import javax.annotation.*;
import java.util.*;
import java.lang.reflect.*;

public class DataWatcher {

	static ClassResolver classResolver;
	static NMSClassResolver nmsClassResolver;
	static Class<?> ItemStack;
	static Class<?> ChunkCoordinates;
	static Class<?> BlockPosition;
	static Class<?> Vector3f;
	static Class<?> DataWatcher;
	static Class<?> Entity;
	static Class<?> TIntObjectMap;
	static ConstructorResolver DataWacherConstructorResolver;
	static FieldResolver DataWatcherFieldResolver;
	static MethodResolver TIntObjectMapMethodResolver;
	static MethodResolver DataWatcherMethodResolver;

	public static Object newDataWatcher(@Nullable Object entity) throws ReflectiveOperationException {
		return xyz.starmc.hologram.DataWatcher.DataWacherConstructorResolver
				.resolve((Class<?>[][]) new Class[][] { { xyz.starmc.hologram.DataWatcher.Entity } })
				.newInstance(entity);
	}

	public static Object setValue(Object dataWatcher, int index, Object dataWatcherObject, Object value)
			throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.setValue(dataWatcher, index, value);
		}
		return V1_9.setValue(dataWatcher, dataWatcherObject, value);
	}

	public static Object setValue(Object dataWatcher, int index, V1_9.ValueType type, Object value)
			throws ReflectiveOperationException {
		return setValue(dataWatcher, index, type.getType(), value);
	}

	public static Object setValue(Object dataWatcher, int index, Object value,
			FieldResolver dataWatcherObjectFieldResolver, String... dataWatcherObjectFieldNames)
			throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.setValue(dataWatcher, index, value);
		}
		Object dataWatcherObject = dataWatcherObjectFieldResolver.resolve(dataWatcherObjectFieldNames).get(null);
		return V1_9.setValue(dataWatcher, dataWatcherObject, value);
	}

	@Deprecated
	public static Object getValue(DataWatcher dataWatcher, int index) throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.getValue(dataWatcher, index);
		}
		return V1_9.getValue(dataWatcher, index);
	}

	public static Object getValue(Object dataWatcher, int index, V1_9.ValueType type)
			throws ReflectiveOperationException {
		return getValue(dataWatcher, index, type.getType());
	}

	public static Object getValue(Object dataWatcher, int index, Object dataWatcherObject)
			throws ReflectiveOperationException {
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			return V1_8.getWatchableObjectValue(V1_8.getValue(dataWatcher, index));
		}
		return V1_9.getValue(dataWatcher, dataWatcherObject);
	}

	public static int getValueType(Object value) {
		int type = 0;
		if (value instanceof Number) {
			if (value instanceof Byte) {
				type = 0;
			} else if (value instanceof Short) {
				type = 1;
			} else if (value instanceof Integer) {
				type = 2;
			} else if (value instanceof Float) {
				type = 3;
			}
		} else if (value instanceof String) {
			type = 4;
		} else if (value != null && value.getClass().equals(xyz.starmc.hologram.DataWatcher.ItemStack)) {
			type = 5;
		} else if (value != null && (value.getClass().equals(xyz.starmc.hologram.DataWatcher.ChunkCoordinates)
				|| value.getClass().equals(xyz.starmc.hologram.DataWatcher.BlockPosition))) {
			type = 6;
		} else if (value != null && value.getClass().equals(xyz.starmc.hologram.DataWatcher.Vector3f)) {
			type = 7;
		}
		return type;
	}

	static {
		xyz.starmc.hologram.DataWatcher.classResolver = new ClassResolver();
		xyz.starmc.hologram.DataWatcher.nmsClassResolver = new NMSClassResolver();
		xyz.starmc.hologram.DataWatcher.ItemStack = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
				.resolveSilent("ItemStack");
		xyz.starmc.hologram.DataWatcher.ChunkCoordinates = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
				.resolveSilent("ChunkCoordinates");
		xyz.starmc.hologram.DataWatcher.BlockPosition = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
				.resolveSilent("BlockPosition");
		xyz.starmc.hologram.DataWatcher.Vector3f = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
				.resolveSilent("Vector3f");
		xyz.starmc.hologram.DataWatcher.DataWatcher = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
				.resolveSilent("DataWatcher");
		xyz.starmc.hologram.DataWatcher.Entity = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
				.resolveSilent("Entity");
		xyz.starmc.hologram.DataWatcher.TIntObjectMap = (Class<?>) xyz.starmc.hologram.DataWatcher.classResolver
				.resolveSilent("gnu.trove.map.TIntObjectMap", "net.minecraft.util.gnu.trove.map.TIntObjectMap");
		xyz.starmc.hologram.DataWatcher.DataWacherConstructorResolver = new ConstructorResolver(
				xyz.starmc.hologram.DataWatcher.DataWatcher);
		xyz.starmc.hologram.DataWatcher.DataWatcherFieldResolver = new FieldResolver(
				xyz.starmc.hologram.DataWatcher.DataWatcher);
		xyz.starmc.hologram.DataWatcher.TIntObjectMapMethodResolver = new MethodResolver(
				xyz.starmc.hologram.DataWatcher.TIntObjectMap);
		xyz.starmc.hologram.DataWatcher.DataWatcherMethodResolver = new MethodResolver(
				xyz.starmc.hologram.DataWatcher.DataWatcher);
	}

	public static class V1_9 {
		static Class<?> DataWatcherItem;
		static Class<?> DataWatcherObject;
		static ConstructorResolver DataWatcherItemConstructorResolver;
		static FieldResolver DataWatcherItemFieldResolver;
		static FieldResolver DataWatcherObjectFieldResolver;

		public static Object newDataWatcherItem(Object dataWatcherObject, Object value)
				throws ReflectiveOperationException {
			if (V1_9.DataWatcherItemConstructorResolver == null) {
				V1_9.DataWatcherItemConstructorResolver = new ConstructorResolver(V1_9.DataWatcherItem);
			}
			return V1_9.DataWatcherItemConstructorResolver.resolveFirstConstructor().newInstance(dataWatcherObject,
					value);
		}

		public static Object setItem(Object dataWatcher, int index, Object dataWatcherObject, Object value)
				throws ReflectiveOperationException {
			return setItem(dataWatcher, index, newDataWatcherItem(dataWatcherObject, value));
		}

		@SuppressWarnings("unchecked")
		public static Object setItem(Object dataWatcher, int index, Object dataWatcherItem)
				throws ReflectiveOperationException {
			Map<Integer, Object> map = (Map<Integer, Object>) xyz.starmc.hologram.DataWatcher.DataWatcherFieldResolver
					.resolveByLastTypeSilent(Map.class).get(dataWatcher);
			map.put(index, dataWatcherItem);
			return dataWatcher;
		}

		public static Object setValue(Object dataWatcher, Object dataWatcherObject, Object value)
				throws ReflectiveOperationException {
			xyz.starmc.hologram.DataWatcher.DataWatcherMethodResolver.resolve("set").invoke(dataWatcher,
					dataWatcherObject, value);
			return dataWatcher;
		}

		public static Object getItem(Object dataWatcher, Object dataWatcherObject) throws ReflectiveOperationException {
			return xyz.starmc.hologram.DataWatcher.DataWatcherMethodResolver
					.resolve(new ResolverQuery("c", (Class<?>[]) new Class[] { V1_9.DataWatcherObject }))
					.invoke(dataWatcher, dataWatcherObject);
		}

		public static Object getValue(Object dataWatcher, Object dataWatcherObject)
				throws ReflectiveOperationException {
			return xyz.starmc.hologram.DataWatcher.DataWatcherMethodResolver.resolve("get").invoke(dataWatcher,
					dataWatcherObject);
		}

		public static Object getValue(Object dataWatcher, ValueType type) throws ReflectiveOperationException {
			return getValue(dataWatcher, type.getType());
		}

		public static Object getItemObject(Object item) throws ReflectiveOperationException {
			if (V1_9.DataWatcherItemFieldResolver == null) {
				V1_9.DataWatcherItemFieldResolver = new FieldResolver(V1_9.DataWatcherItem);
			}
			return V1_9.DataWatcherItemFieldResolver.resolve("a").get(item);
		}

		@SuppressWarnings("unchecked")
		public static int getItemIndex(Object dataWatcher, Object item) throws ReflectiveOperationException {
			int index = -1;
			Map<Integer, Object> map = (Map<Integer, Object>) xyz.starmc.hologram.DataWatcher.DataWatcherFieldResolver
					.resolveByLastTypeSilent(Map.class).get(dataWatcher);
			for (Map.Entry<Integer, Object> entry : map.entrySet()) {
				if (entry.getValue().equals(item)) {
					index = entry.getKey();
					break;
				}
			}
			return index;
		}

		public static Type getItemType(Object item) throws ReflectiveOperationException {
			if (V1_9.DataWatcherObjectFieldResolver == null) {
				V1_9.DataWatcherObjectFieldResolver = new FieldResolver(V1_9.DataWatcherObject);
			}
			Object object = getItemObject(item);
			Object serializer = V1_9.DataWatcherObjectFieldResolver.resolve("b").get(object);
			Type[] genericInterfaces = serializer.getClass().getGenericInterfaces();
			if (genericInterfaces.length > 0) {
				Type type = genericInterfaces[0];
				if (type instanceof ParameterizedType) {
					Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
					if (actualTypes.length > 0) {
						return actualTypes[0];
					}
				}
			}
			return null;
		}

		public static Object getItemValue(Object item) throws ReflectiveOperationException {
			if (V1_9.DataWatcherItemFieldResolver == null) {
				V1_9.DataWatcherItemFieldResolver = new FieldResolver(V1_9.DataWatcherItem);
			}
			return V1_9.DataWatcherItemFieldResolver.resolve("b").get(item);
		}

		public static void setItemValue(Object item, Object value) throws ReflectiveOperationException {
			V1_9.DataWatcherItemFieldResolver.resolve("b").set(item, value);
		}

		static {
			V1_9.DataWatcherItem = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
					.resolveSilent("DataWatcher$Item");
			V1_9.DataWatcherObject = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
					.resolveSilent("DataWatcherObject");
		}

		public enum ValueType {
			ENTITY_FLAG("Entity", 57, 0), ENTITY_AIR_TICKS("Entity", 58, 1), ENTITY_NAME("Entity", 59, 2),
			ENTITY_NAME_VISIBLE("Entity", 60, 3), ENTITY_SILENT("Entity", 61, 4), ENTITY_as("EntityLiving", 2, 0),
			ENTITY_LIVING_HEALTH("EntityLiving", new String[] { "HEALTH" }), ENTITY_LIVING_f("EntityLiving", 4, 2),
			ENTITY_LIVING_g("EntityLiving", 5, 3), ENTITY_LIVING_h("EntityLiving", 6, 4),
			ENTITY_INSENTIENT_FLAG("EntityInsentient", 0, 0), ENTITY_SLIME_SIZE("EntitySlime", 0, 0),
			ENTITY_WITHER_a("EntityWither", 0, 0), ENTITY_WIHER_b("EntityWither", 1, 1),
			ENTITY_WITHER_c("EntityWither", 2, 2), ENTITY_WITHER_bv("EntityWither", 3, 3),
			ENTITY_WITHER_bw("EntityWither", 4, 4), ENTITY_HUMAN_ABSORPTION_HEARTS("EntityHuman", 0, 0),
			ENTITY_HUMAN_SCORE("EntityHuman", 1, 1), ENTITY_HUMAN_SKIN_LAYERS("EntityHuman", 2, 2),
			ENTITY_HUMAN_MAIN_HAND("EntityHuman", 3, 3);

			private Object type;

			private ValueType(String className, String[] fieldNames) {
				try {
					this.type = new FieldResolver(xyz.starmc.hologram.DataWatcher.nmsClassResolver.resolve(className))
							.resolve(fieldNames).get(null);
				} catch (Exception e) {
					if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
						System.err.println("[ReflectionHelper] Failed to find DataWatcherObject for " + className + " "
								+ Arrays.toString(fieldNames));
					}
				}
			}

			private ValueType(String className, int index) {
				try {
					this.type = new FieldResolver(xyz.starmc.hologram.DataWatcher.nmsClassResolver.resolve(className))
							.resolveIndex(index).get(null);
				} catch (Exception e) {
					if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
						System.err.println(
								"[ReflectionHelper] Failed to find DataWatcherObject for " + className + " #" + index);
					}
				}
			}

			private ValueType(String className, int index, int offset) {
				int firstObject = 0;
				try {
					Class<?> clazz = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver.resolve(className);
					for (Field field : clazz.getDeclaredFields()) {
						if ("DataWatcherObject".equals(field.getType().getSimpleName())) {
							break;
						}
						++firstObject;
					}
					this.type = new FieldResolver(clazz).resolveIndex(firstObject + offset).get(null);
				} catch (Exception e) {
					if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) {
						System.err.println("[ReflectionHelper] Failed to find DataWatcherObject for " + className + " #"
								+ index + " (" + firstObject + "+" + offset + ")");
					}
				}
			}

			public boolean hasType() {
				return this.getType() != null;
			}

			public Object getType() {
				return this.type;
			}
		}
	}

	public static class V1_8 {
		static Class<?> WatchableObject;
		static ConstructorResolver WatchableObjectConstructorResolver;
		static FieldResolver WatchableObjectFieldResolver;

		public static Object newWatchableObject(int index, Object value) throws ReflectiveOperationException {
			return newWatchableObject(xyz.starmc.hologram.DataWatcher.getValueType(value), index, value);
		}

		public static Object newWatchableObject(int type, int index, Object value) throws ReflectiveOperationException {
			if (V1_8.WatchableObjectConstructorResolver == null) {
				V1_8.WatchableObjectConstructorResolver = new ConstructorResolver(V1_8.WatchableObject);
			}
			return V1_8.WatchableObjectConstructorResolver
					.resolve((Class<?>[][]) new Class[][] { { Integer.TYPE, Integer.TYPE, Object.class } })
					.newInstance(type, index, value);
		}

		@SuppressWarnings("unchecked")
		public static Object setValue(Object dataWatcher, int index, Object value) throws ReflectiveOperationException {
			int type = xyz.starmc.hologram.DataWatcher.getValueType(value);
			Map<Integer, Object> map = (Map<Integer, Object>) xyz.starmc.hologram.DataWatcher.DataWatcherFieldResolver
					.resolveByLastType(Map.class).get(dataWatcher);
			map.put(index, newWatchableObject(type, index, value));
			return dataWatcher;
		}

		public static Object getValue(Object dataWatcher, int index) throws ReflectiveOperationException {
			Map<?, ?> map = (Map<?, ?>) xyz.starmc.hologram.DataWatcher.DataWatcherFieldResolver
					.resolveByLastType(Map.class).get(dataWatcher);
			return map.get(index);
		}

		public static int getWatchableObjectIndex(Object object) throws ReflectiveOperationException {
			if (V1_8.WatchableObjectFieldResolver == null) {
				V1_8.WatchableObjectFieldResolver = new FieldResolver(V1_8.WatchableObject);
			}
			return V1_8.WatchableObjectFieldResolver.resolve("b").getInt(object);
		}

		public static int getWatchableObjectType(Object object) throws ReflectiveOperationException {
			if (V1_8.WatchableObjectFieldResolver == null) {
				V1_8.WatchableObjectFieldResolver = new FieldResolver(V1_8.WatchableObject);
			}
			return V1_8.WatchableObjectFieldResolver.resolve("a").getInt(object);
		}

		public static Object getWatchableObjectValue(Object object) throws ReflectiveOperationException {
			if (V1_8.WatchableObjectFieldResolver == null) {
				V1_8.WatchableObjectFieldResolver = new FieldResolver(V1_8.WatchableObject);
			}
			return V1_8.WatchableObjectFieldResolver.resolve("c").get(object);
		}

		static {
			V1_8.WatchableObject = (Class<?>) xyz.starmc.hologram.DataWatcher.nmsClassResolver
					.resolveSilent("WatchableObject", "DataWatcher$WatchableObject");
		}
	}
}
