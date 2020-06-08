package xyz.starmc.hologram;

import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public abstract class ClassBuilder {

	static NMSClassResolver nmsClassResolver;
	static FieldResolver EntitySlimeFieldResolver;
	static FieldResolver EntityFieldResolver;
	static FieldResolver PacketPlayOutSpawnEntityLivingFieldResolver;
	static FieldResolver DataWatcherFieldResolver;

	public static Object buildEntityWitherSkull(Object world, Location loc) throws Exception {
		Object witherSkull = NMSClass.EntityWitherSkull.getConstructor(NMSClass.World).newInstance(world);
		updateEntityLocation(witherSkull, loc);
		return witherSkull;
	}

	public static Object buildEntityHorse_1_7(Object world, Location loc, String name) throws Exception {
		Object horse_1_7 = NMSClass.EntityHorse.getConstructor(NMSClass.World).newInstance(world);
		updateEntityLocation(horse_1_7, loc);
		if (HologramAPI.is1_8()) {
			if (name != null) {
				NMSClass.Entity.getDeclaredMethod("setCustomName", String.class).invoke(horse_1_7, name);
			}
			NMSClass.Entity.getDeclaredMethod("setCustomNameVisible", Boolean.TYPE).invoke(horse_1_7,
					name != null && !name.isEmpty());
		} else {
			if (name != null) {
				NMSClass.EntityInsentient.getDeclaredMethod("setCustomName", String.class).invoke(horse_1_7, name);
			}
			NMSClass.EntityInsentient.getDeclaredMethod("setCustomNameVisible", Boolean.TYPE).invoke(horse_1_7,
					name != null && !name.isEmpty());
		}
		Object horseDataWatcher = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("datawatcher"))
				.get(horse_1_7);
		NMSClass.EntityAgeable.getDeclaredMethod("setAge", Integer.TYPE).invoke(horse_1_7, -1700000);
		DataWatcher.setValue(horseDataWatcher, 12, DataWatcher.V1_9.ValueType.ENTITY_FLAG, (byte) 96);
		return horse_1_7;
	}

	public static Object buildEntityHorse_1_8(Object world, Location loc, String name) throws Exception {
		Object horse_1_8 = NMSClass.EntityHorse.getConstructor(NMSClass.World).newInstance(world);
		updateEntityLocation(horse_1_8, loc);
		if (HologramAPI.is1_8()) {
			if (name != null) {
				NMSClass.Entity.getDeclaredMethod("setCustomName", String.class).invoke(horse_1_8, name);
			}
			NMSClass.Entity.getDeclaredMethod("setCustomNameVisible", Boolean.TYPE).invoke(horse_1_8, true);
		} else {
			NMSClass.EntityInsentient.getDeclaredMethod("setCustomName", String.class).invoke(horse_1_8, name);
			NMSClass.EntityInsentient.getDeclaredMethod("setCustomNameVisible", Boolean.TYPE).invoke(horse_1_8,
					name != null && !name.isEmpty());
		}
		return horse_1_8;
	}

	public static Object buildEntityArmorStand(Object world, Location loc, String name) throws Exception {
		Object armorStand = NMSClass.EntityArmorStand.getConstructor(NMSClass.World).newInstance(world);
		updateEntityLocation(armorStand, loc);
		if (name != null) {
			NMSClass.Entity.getDeclaredMethod("setCustomName", String.class).invoke(armorStand, name);
		}
		NMSClass.Entity.getDeclaredMethod("setCustomNameVisible", Boolean.TYPE).invoke(armorStand,
				name != null && !name.isEmpty());
		return armorStand;
	}

	public static Object setupArmorStand(Object armorStand) throws Exception {
		NMSClass.EntityArmorStand.getDeclaredMethod("setInvisible", Boolean.TYPE).invoke(armorStand, true);
		NMSClass.EntityArmorStand.getDeclaredMethod("setSmall", Boolean.TYPE).invoke(armorStand, true);
		NMSClass.EntityArmorStand.getDeclaredMethod("setArms", Boolean.TYPE).invoke(armorStand, false);
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_10_R1)) {
			NMSClass.EntityArmorStand.getDeclaredMethod("setGravity", Boolean.TYPE).invoke(armorStand, false);
		} else {
			NMSClass.Entity.getDeclaredMethod("setNoGravity", Boolean.TYPE).invoke(armorStand, true);
		}
		NMSClass.EntityArmorStand.getDeclaredMethod("setBasePlate", Boolean.TYPE).invoke(armorStand, false);
		return armorStand;
	}

	public static Object buildEntitySlime(Object world, Location loc, int size) throws Exception {
		Object slime = NMSClass.EntitySlime.getConstructor(NMSClass.World).newInstance(world);
		updateEntityLocation(slime, loc);
		Object dataWatcher = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("datawatcher")).get(slime);
		DataWatcher.setValue(dataWatcher, 0, DataWatcher.V1_9.ValueType.ENTITY_FLAG, (byte) 32);
		DataWatcher.setValue(dataWatcher, 16, DataWatcher.V1_9.ValueType.ENTITY_SLIME_SIZE,
				(size < 1) ? 1 : ((size > 100) ? 100 : size));
		return slime;
	}

	@SuppressWarnings("deprecation")
	public static Object buildWitherSkullSpawnPacket(Object skull) throws Exception {
		Object spawnPacketSkull = NMSClass.PacketPlayOutSpawnEntity.getConstructor(NMSClass.Entity, Integer.TYPE)
				.newInstance(skull, EntityType.WITHER_SKULL.getTypeId());
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			AccessUtil.setAccessible(NMSClass.PacketPlayOutSpawnEntity.getDeclaredField("j")).set(spawnPacketSkull, 66);
		} else {
			AccessUtil.setAccessible(NMSClass.PacketPlayOutSpawnEntity.getDeclaredField("k")).set(spawnPacketSkull, 66);
		}
		return spawnPacketSkull;
	}

	public static Object buildSkullMetaPacket(int id, Object dataWatcher) throws Exception {
		DataWatcher.setValue(dataWatcher, 0, DataWatcher.V1_9.ValueType.ENTITY_FLAG, (byte) 32);
		Object packet = NMSClass.PacketPlayOutEntityMetadata
				.getConstructor(Integer.TYPE, NMSClass.DataWatcher, Boolean.TYPE).newInstance(id, dataWatcher, true);
		return packet;
	}

	public static Object buildHorseSpawnPacket_1_7(Object horse, String name) throws Exception {
		if (horse == null) {
			throw new IllegalArgumentException("horse cannot be null");
		}
		Object spawnPacketHorse_1_7 = NMSClass.PacketPlayOutSpawnEntityLiving.getConstructor(NMSClass.EntityLiving)
				.newInstance(horse);
		Object dataWatcher_1_7 = AccessUtil.setAccessible(
				ClassBuilder.PacketPlayOutSpawnEntityLivingFieldResolver.resolveByFirstType(NMSClass.DataWatcher))
				.get(spawnPacketHorse_1_7);
		if (name != null) {
			DataWatcher.setValue(dataWatcher_1_7, 10, DataWatcher.V1_9.ValueType.ENTITY_NAME, name);
		}
		DataWatcher.setValue(dataWatcher_1_7, 11, DataWatcher.V1_9.ValueType.ENTITY_NAME_VISIBLE,
				(byte) (byte) ((name != null && !name.isEmpty()) ? 1 : 0));
		DataWatcher.setValue(dataWatcher_1_7, 12, DataWatcher.V1_9.ValueType.ENTITY_FLAG, -1700000);
		return spawnPacketHorse_1_7;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object buildHorseSpawnPacket_1_8(Object horse, String name) throws Exception {
		if (horse == null) {
			throw new IllegalArgumentException("horse cannot be null");
		}
		Object spawnPacketHorse_1_8 = NMSClass.PacketPlayOutSpawnEntityLiving.getConstructor(NMSClass.EntityLiving)
				.newInstance(horse);
		AccessUtil.setAccessible(NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("b"))
				.setInt(spawnPacketHorse_1_8, 30);
		Object dataWatcher_1_8 = AccessUtil.setAccessible(
				ClassBuilder.PacketPlayOutSpawnEntityLivingFieldResolver.resolveByFirstType(NMSClass.DataWatcher))
				.get(spawnPacketHorse_1_8);
		Map<Integer, Object> map_1_8 = (Map<Integer, Object>) ClassBuilder.DataWatcherFieldResolver
				.resolveByLastType(Map.class).get(dataWatcher_1_8);
		map_1_8.put(10, NMSClass.WatchableObject.getConstructor(Integer.TYPE, Integer.TYPE, Object.class).newInstance(0,
				10, (byte) 1));
		List<Integer> toRemove = new ArrayList<Integer>();
		for (Map.Entry entry : map_1_8.entrySet()) {
			Object current = entry.getValue();
			if (current == null) {
				continue;
			}
			int index = AccessUtil.setAccessible(NMSClass.WatchableObject.getDeclaredField("b")).getInt(current);
			if (index == 2) {
				AccessUtil.setAccessible(NMSClass.WatchableObject.getDeclaredField("c")).set(current, name);
			} else {
				if (index == 3) {
					continue;
				}
				toRemove.add(index);
			}
		}
		for (Integer i : toRemove) {
			map_1_8.remove(i);
		}
		map_1_8.put(0, NMSClass.WatchableObject.getConstructor(Integer.TYPE, Integer.TYPE, Object.class).newInstance(0,
				0, (byte) 32));
		return spawnPacketHorse_1_8;
	}

	public static Object buildSlimeSpawnPacket(Object slime) throws Exception {
		Object packet = NMSClass.PacketPlayOutSpawnEntityLiving.getConstructor(NMSClass.EntityLiving)
				.newInstance(slime);
		return packet;
	}

	public static Object buildNameMetadataPacket(int id, Object dataWatcher, int nameIndex, int visibilityIndex,
			String name) throws Exception {
		DataWatcher.setValue(dataWatcher, nameIndex, DataWatcher.V1_9.ValueType.ENTITY_NAME,
				(name != null) ? name : "");
		DataWatcher.setValue(dataWatcher, visibilityIndex, DataWatcher.V1_9.ValueType.ENTITY_NAME_VISIBLE,
				Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)
						? ((byte) ((name != null && !name.isEmpty()) ? 1 : 0))
						: (name != null && !name.isEmpty()));
		Object metaPacket = NMSClass.PacketPlayOutEntityMetadata
				.getConstructor(Integer.TYPE, NMSClass.DataWatcher, Boolean.TYPE).newInstance(id, dataWatcher, true);
		return metaPacket;
	}

	public static Object updateEntityLocation(Object entity, Location loc) throws Exception {
		NMSClass.Entity.getDeclaredField("locX").set(entity, loc.getX());
		NMSClass.Entity.getDeclaredField("locY").set(entity, loc.getY());
		NMSClass.Entity.getDeclaredField("locZ").set(entity, loc.getZ());
		return entity;
	}

	public static Object buildArmorStandSpawnPacket(Object armorStand) throws Exception {
		Object spawnPacket = NMSClass.PacketPlayOutSpawnEntityLiving.getConstructor(NMSClass.EntityLiving)
				.newInstance(armorStand);
		AccessUtil.setAccessible(Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)
				? NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("b")
				: NMSClass.PacketPlayOutSpawnEntityLiving.getDeclaredField("c")).setInt(spawnPacket, 30);
		return spawnPacket;
	}

	public static Object buildTeleportPacket(int id, Location loc, boolean onGround, boolean heightCorrection)
			throws Exception {
		Object packet = NMSClass.PacketPlayOutEntityTeleport.newInstance();
		AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("a")).set(packet, id);
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("b")).set(packet,
					(int) (loc.getX() * 32.0));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("c")).set(packet,
					(int) (loc.getY() * 32.0));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("d")).set(packet,
					(int) (loc.getZ() * 32.0));
		} else {
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("b")).set(packet,
					loc.getX());
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("c")).set(packet,
					loc.getY());
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("d")).set(packet,
					loc.getZ());
			AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("g")).set(packet, onGround);
		}
		AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("e")).set(packet,
				(byte) (loc.getYaw() * 256.0f / 360.0f));
		AccessUtil.setAccessible(NMSClass.PacketPlayOutEntityTeleport.getDeclaredField("f")).set(packet,
				(byte) (loc.getPitch() * 256.0f / 360.0f));
		return packet;
	}

	static {
		ClassBuilder.nmsClassResolver = new NMSClassResolver();
		ClassBuilder.EntitySlimeFieldResolver = new FieldResolver(NMSClass.EntitySlime);
		ClassBuilder.EntityFieldResolver = new FieldResolver(NMSClass.Entity);
		ClassBuilder.PacketPlayOutSpawnEntityLivingFieldResolver = new FieldResolver(
				NMSClass.PacketPlayOutSpawnEntityLiving);
		ClassBuilder.DataWatcherFieldResolver = new FieldResolver(NMSClass.DataWatcher);
	}
}
