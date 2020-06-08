package xyz.starmc.hologram;

import java.lang.reflect.Field;

public abstract class NMUClass {

	public static Class<?> gnu_trove_map_TIntObjectMap;
	public static Class<?> gnu_trove_map_hash_TIntObjectHashMap;
	public static Class<?> gnu_trove_impl_hash_THash;
	public static Class<?> io_netty_channel_Channel;

	static {
		Field[] arrayOfField;
		int j = (arrayOfField = NMUClass.class.getDeclaredFields()).length;
		for (int i = 0; i < j; i++) {
			Field f = arrayOfField[i];
			if (f.getType().equals(Class.class)) {
				try {
					String name = f.getName().replace("_", ".");
					if (Reflection.getVersion().contains("1_8")) {
						f.set(null, Class.forName(name));
					} else {
						f.set(null, Class.forName("net.minecraft.util." + name));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
