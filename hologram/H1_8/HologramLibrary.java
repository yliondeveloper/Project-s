package xyz.starmc.hologram.H1_8;

import net.minecraft.server.v1_8_R3.*;
import java.lang.reflect.*;
import org.bukkit.*;
import com.google.common.collect.*;
import java.util.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class HologramLibrary {
	private static List<Hologram> holograms;

	static {
		HologramLibrary.holograms = new ArrayList<Hologram>();
		try {
			Field classToString = EntityTypes.class.getDeclaredField("d");
			classToString.setAccessible(true);
			Map<Class<EntityHologramStand>, Comparable> map = (Map) classToString.get(null);
			map.put(EntityHologramStand.class, "ArmorStand");
			Field classToId = EntityTypes.class.getDeclaredField("f");
			classToId.setAccessible(true);
			map = (Map) classToId.get(null);
			map.put(EntityHologramStand.class, 30);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	public static Hologram createHologram(Location location, String... lines) {
		Hologram hologram = new Hologram(location, lines);
		HologramLibrary.holograms.add(hologram);
		return hologram;
	}

	public static void removeHologram(Hologram hologram) {
		HologramLibrary.holograms.remove(hologram);
		hologram.despawn();
	}

	public static List<Hologram> listHolograms() {
		return (List<Hologram>) ImmutableList.copyOf((Collection) HologramLibrary.holograms);
	}
}
