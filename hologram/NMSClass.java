package xyz.starmc.hologram;

import java.lang.reflect.Field;

public abstract class NMSClass {

	public static Class<?> Entity;
	public static Class<?> EntityLiving;
	public static Class<?> EntityInsentient;
	public static Class<?> EntityAgeable;
	public static Class<?> EntityHorse;
	public static Class<?> EntityArmorStand;
	public static Class<?> EntityWitherSkull;
	public static Class<?> EntitySlime;
	public static Class<?> World;
	public static Class<?> PacketPlayOutSpawnEntityLiving;
	public static Class<?> PacketPlayOutSpawnEntity;
	public static Class<?> PacketPlayOutEntityDestroy;
	public static Class<?> PacketPlayOutAttachEntity;
	public static Class<?> PacketPlayOutMount;
	public static Class<?> PacketPlayOutEntityTeleport;
	public static Class<?> PacketPlayOutEntityMetadata;
	public static Class<?> DataWatcher;
	public static Class<?> WatchableObject;
	public static Class<?> ItemStack;
	public static Class<?> ChunkCoordinates;
	public static Class<?> BlockPosition;
	public static Class<?> Vector3f;
	public static Class<?> EnumEntityUseAction;

	static {
		Field[] arrayOfField;
		int j = (arrayOfField = NMSClass.class.getDeclaredFields()).length;
		for (int i = 0; i < j; i++) {
			Field f = arrayOfField[i];
			if (f.getType().equals(Class.class)) {
				try {
					f.set(null, Reflection.getNMSClassWithException(f.getName()));
				} catch (Exception e) {
					if (f.getName().equals("WatchableObject")) {
						try {
							f.set(null, Reflection.getNMSClassWithException("DataWatcher$WatchableObject"));
						} catch (Exception localException1) {
						}
					}
				}
			}
		}
	}
}
