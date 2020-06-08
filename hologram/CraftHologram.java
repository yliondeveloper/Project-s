package xyz.starmc.hologram;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;

abstract class CraftHologram implements Hologram {

	protected boolean matchesTouchID(int id) {
		if ((!isTouchable()) || (this.touchIDs == null)) {
			return false;
		}
		int[] arrayOfInt;
		int j = (arrayOfInt = this.touchIDs).length;
		for (int i = 0; i < j; i++) {
			i = arrayOfInt[i];
			if (i == id) {
				return true;
			}
		}
		return false;
	}

	protected boolean matchesHologramID(int id) {
		if ((!HologramAPI.packetsEnabled()) || (this.hologramIDs == null)) {
			return false;
		}
		int[] arrayOfInt;
		int j = (arrayOfInt = this.hologramIDs).length;
		for (int i = 0; i < j; i++) {
			i = arrayOfInt[i];
			if (i == id) {
				return true;
			}
		}
		return false;
	}

	protected void buildPackets(boolean rebuild) throws Exception {
		if ((!rebuild) && (this.packetsBuilt)) {
			throw new IllegalStateException("packets already built");
		}
		if ((rebuild) && (!this.packetsBuilt)) {
			throw new IllegalStateException("cannot rebuild packets before building once");
		}
		Object world = Reflection.getHandle(getLocation().getWorld());
		if ((HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport)) {
			Object armorStand = ClassBuilder.buildEntityArmorStand(world, getLocation().add(0.0D, -1.25D, 0.0D),
					getText());
			ClassBuilder.setupArmorStand(armorStand);
			if (rebuild) {
				AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).set(armorStand,
						Integer.valueOf(this.hologramIDs[0]));
			} else {
				this.hologramIDs = new int[] {
						AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).getInt(armorStand) };
			}
			this.spawnPacketArmorStand = ClassBuilder.buildArmorStandSpawnPacket(armorStand);
			this.dataWatcherArmorStand = AccessUtil
					.setAccessible(PacketPlayOutSpawnEntityLivingFieldResolver.resolveByFirstType(NMSClass.DataWatcher))
					.get(this.spawnPacketArmorStand);
			this.teleportPacketArmorStand = ClassBuilder.buildTeleportPacket(this.hologramIDs[0],
					getLocation().add(0.0D, -1.25D, 0.0D), true, false);
		} else {
			Object horse_1_7 = ClassBuilder.buildEntityHorse_1_7(world, getLocation().add(0.0D, 54.56D, 0.0D),
					getText());
			Object horse_1_8 = ClassBuilder.buildEntityHorse_1_8(world, getLocation().add(0.0D, -2.25D, 0.0D),
					getText());
			Object witherSkull_1_7 = ClassBuilder.buildEntityWitherSkull(world, getLocation().add(0.0D, 54.56D, 0.0D));
			this.dataWatcherWitherSkull = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("datawatcher"))
					.get(witherSkull_1_7);
			if (rebuild) {
				AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).set(witherSkull_1_7,
						Integer.valueOf(this.hologramIDs[0]));
				AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).set(horse_1_7,
						Integer.valueOf(this.hologramIDs[1]));
				AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).set(horse_1_8,
						Integer.valueOf(this.hologramIDs[2]));
				Field entityCountField = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("entityCount"));
				entityCountField.set(null, Integer.valueOf(((Integer) entityCountField.get(null)).intValue() - 3));
			} else {
				this.hologramIDs = new int[] {
						AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).getInt(witherSkull_1_7),
						AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).getInt(horse_1_7),
						AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).getInt(horse_1_8) };
			}
			this.spawnPacketHorse_1_7 = ClassBuilder.buildHorseSpawnPacket_1_7(horse_1_7, getText());
			this.dataWatcherHorse_1_7 = AccessUtil
					.setAccessible(PacketPlayOutSpawnEntityLivingFieldResolver.resolveByFirstType(NMSClass.DataWatcher))
					.get(this.spawnPacketHorse_1_7);
			this.spawnPacketHorse_1_8 = ClassBuilder.buildHorseSpawnPacket_1_8(horse_1_8, getText());
			this.dataWatcherHorse_1_8 = AccessUtil
					.setAccessible(PacketPlayOutSpawnEntityLivingFieldResolver.resolveByFirstType(NMSClass.DataWatcher))
					.get(this.spawnPacketHorse_1_8);
			this.spawnPacketWitherSkull = ClassBuilder.buildWitherSkullSpawnPacket(witherSkull_1_7);
			if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
				this.attachPacket = NMSClass.PacketPlayOutAttachEntity
						.getConstructor(new Class[] { Integer.TYPE, NMSClass.Entity, NMSClass.Entity })
						.newInstance(new Object[] { Integer.valueOf(0), horse_1_7, witherSkull_1_7 });
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("b"))
						.set(this.attachPacket, Integer.valueOf(this.hologramIDs[1]));
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("c"))
						.set(this.attachPacket, Integer.valueOf(this.hologramIDs[0]));
			} else {
				this.attachPacket = NMSClass.PacketPlayOutAttachEntity.newInstance();
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("a"))
						.set(this.attachPacket, Integer.valueOf(this.hologramIDs[1]));
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("b"))
						.set(this.attachPacket, Integer.valueOf(this.hologramIDs[0]));
			}
			if ((!HologramAPI.is1_8) || (HologramAPI.useProtocolSupport)) {
				this.teleportPacketSkull = ClassBuilder.buildTeleportPacket(this.hologramIDs[0],
						getLocation().add(0.0D, 54.56D, 0.0D), true, false);
				this.teleportPacketHorse_1_7 = ClassBuilder.buildTeleportPacket(this.hologramIDs[1],
						getLocation().add(0.0D, 54.56D, 0.0D), true, false);
			}
			this.teleportPacketHorse_1_8 = ClassBuilder.buildTeleportPacket(this.hologramIDs[2],
					getLocation().add(0.0D, -2.25D, 0.0D), true, false);
		}
		if (isTouchable()) {
			int size = getText() == null ? 1 : getText().length() / 2 / 3;
			Object touchSlime = ClassBuilder.buildEntitySlime(world, getLocation().add(0.0D, -0.4D, 0.0D), size);
			this.dataWatcherTouchSlime = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("datawatcher"))
					.get(touchSlime);
			Object touchVehicle = null;
			if ((HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport)) {
				touchVehicle = ClassBuilder.buildEntityArmorStand(world, getLocation().add(0.0D, -1.85D, 0.0D), null);
				this.dataWatcherTouchVehicle = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("datawatcher"))
						.get(touchVehicle);
				ClassBuilder.setupArmorStand(touchVehicle);
			} else {
				touchVehicle = ClassBuilder.buildEntityWitherSkull(world, getLocation().add(0.0D, -0.4D, 0.0D));
				DataWatcher.setValue(
						this.dataWatcherTouchVehicle = AccessUtil
								.setAccessible(NMSClass.Entity.getDeclaredField("datawatcher")).get(touchVehicle),
						0, DataWatcher.V1_9.ValueType.ENTITY_FLAG, Byte.valueOf((byte) 32));
			}
			if (rebuild) {
				AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).set(touchSlime,
						Integer.valueOf(this.touchIDs[0]));
				AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).set(touchVehicle,
						Integer.valueOf(this.touchIDs[1]));
				Field entityCountField = AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("entityCount"));
				entityCountField.set(null, Integer.valueOf(((Integer) entityCountField.get(null)).intValue() - 2));
			} else {
				this.touchIDs = new int[] {
						AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).getInt(touchSlime),
						AccessUtil.setAccessible(NMSClass.Entity.getDeclaredField("id")).getInt(touchVehicle) };
			}
			this.spawnPacketTouchSlime = ClassBuilder.buildSlimeSpawnPacket(touchSlime);
			if ((HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport)) {
				this.spawnPacketTouchVehicle = ClassBuilder.buildArmorStandSpawnPacket(touchVehicle);
			} else {
				this.spawnPacketTouchVehicle = ClassBuilder.buildWitherSkullSpawnPacket(touchVehicle);
			}
			if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
				this.attachPacketTouch = NMSClass.PacketPlayOutAttachEntity
						.getConstructor(new Class[] { Integer.TYPE, NMSClass.Entity, NMSClass.Entity })
						.newInstance(new Object[] { Integer.valueOf(0), touchSlime, touchVehicle });
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("b"))
						.set(this.attachPacketTouch, Integer.valueOf(this.touchIDs[0]));
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("c"))
						.set(this.attachPacketTouch, Integer.valueOf(this.touchIDs[1]));
			} else {
				this.attachPacketTouch = NMSClass.PacketPlayOutAttachEntity.newInstance();
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("a"))
						.set(this.attachPacketTouch, Integer.valueOf(this.touchIDs[0]));
				AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("b"))
						.set(this.attachPacketTouch, Integer.valueOf(this.touchIDs[1]));
			}
			this.teleportPacketTouchSlime = ClassBuilder.buildTeleportPacket(this.touchIDs[0],
					getLocation().add(0.0D, -0.4D, 0.0D), true, false);
			if ((HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport)) {
				this.teleportPacketTouchVehicle = ClassBuilder.buildTeleportPacket(this.touchIDs[1],
						getLocation().add(0.0D, -1.85D, 0.0D), true, false);
			} else {
				this.teleportPacketTouchVehicle = ClassBuilder.buildTeleportPacket(this.touchIDs[1],
						getLocation().add(0.0D, -0.4D, 0.0D), true, false);
			}
			if (!rebuild) {
				this.destroyPacketTouch = NMSClass.PacketPlayOutEntityDestroy
						.getConstructor(new Class[] { int[].class }).newInstance(new Object[] { this.touchIDs });
			}
		}
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			this.ridingAttachPacket = NMSClass.PacketPlayOutAttachEntity.newInstance();
			this.ridingEjectPacket = NMSClass.PacketPlayOutAttachEntity.newInstance();
			AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("a"))
					.set(this.ridingAttachPacket, Integer.valueOf(0));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("a"))
					.set(this.ridingEjectPacket, Integer.valueOf(0));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("b"))
					.set(this.ridingAttachPacket, Integer.valueOf(this.hologramIDs[0]));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("b"))
					.set(this.ridingEjectPacket, Integer.valueOf(this.hologramIDs[0]));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("c")).set(
					this.ridingAttachPacket,
					Integer.valueOf(getAttachedTo() != null ? getAttachedTo().getEntityId() : -1));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutAttachEntity.getDeclaredField("c"))
					.set(this.ridingEjectPacket, Integer.valueOf(-1));
		} else {
			this.ridingAttachPacket = NMSClass.PacketPlayOutMount.newInstance();
			this.ridingEjectPacket = NMSClass.PacketPlayOutMount.newInstance();
			AccessUtil.setAccessible(NMSClass.PacketPlayOutMount.getDeclaredField("a")).set(this.ridingAttachPacket,
					Integer.valueOf((((DefaultHologram) this).isAttached()) && (getAttachedTo() != null)
							? getAttachedTo().getEntityId()
							: -1));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutMount.getDeclaredField("a")).set(this.ridingEjectPacket,
					Integer.valueOf((((DefaultHologram) this).isAttached()) && (getAttachedTo() != null)
							? getAttachedTo().getEntityId()
							: -1));
			AccessUtil.setAccessible(NMSClass.PacketPlayOutMount.getDeclaredField("b")).set(this.ridingAttachPacket,
					new int[] { this.hologramIDs[0] });
			AccessUtil.setAccessible(NMSClass.PacketPlayOutMount.getDeclaredField("b")).set(this.ridingEjectPacket,
					new int[0]);
		}
		if (!rebuild) {
			this.destroyPacket = NMSClass.PacketPlayOutEntityDestroy.getConstructor(new Class[] { int[].class })
					.newInstance(new Object[] { this.hologramIDs });
		}
	}

	protected void sendSpawnPackets(Collection<? extends Player> receivers, boolean holo, boolean touch) {
		if (holo) {
			if ((HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport)) {
				for (Player p : receivers) {
					HologramAPI.sendPacket(p, this.spawnPacketArmorStand);
				}
			} else {
				for (Player p : receivers) {
					if (HologramAPI.getVersion(p) <= 5) {
						HologramAPI.sendPacket(p, this.spawnPacketHorse_1_7);
						HologramAPI.sendPacket(p, this.spawnPacketWitherSkull);
						HologramAPI.sendPacket(p, this.attachPacket);
					}
				}
			}
		}
		if ((touch) && (isTouchable())) {
			for (Player p : receivers) {
				HologramAPI.sendPacket(p, this.spawnPacketTouchSlime);
				HologramAPI.sendPacket(p, this.spawnPacketTouchVehicle);
				HologramAPI.sendPacket(p, this.attachPacketTouch);
			}
		}
		if ((holo) || (touch)) {
			new BukkitRunnable() {
				public void run() {
					CraftHologram.this.sendTeleportPackets(receivers, holo, touch);
				}
			}.runTaskLater(Main.getInstance(), 1L);
		}
	}

	protected void sendTeleportPackets(Collection<? extends Player> receivers, boolean holo, boolean touch) {
		if ((!holo) && (!touch)) {
			return;
		}
		for (Player p : receivers) {
			if (holo) {
				if ((!HologramAPI.is1_8) || (HologramAPI.useProtocolSupport)) {
					if (HologramAPI.getVersion(p) > 5) {
						HologramAPI.sendPacket(p, this.teleportPacketHorse_1_8);
					} else {
						HologramAPI.sendPacket(p, this.teleportPacketHorse_1_7);
						HologramAPI.sendPacket(p, this.teleportPacketSkull);
					}
				} else {
					HologramAPI.sendPacket(p, this.teleportPacketArmorStand);
				}
			}
			if ((touch) && (isTouchable())) {
				HologramAPI.sendPacket(p, this.teleportPacketTouchSlime);
				HologramAPI.sendPacket(p, this.teleportPacketTouchVehicle);
			}
		}
	}

	protected void sendNamePackets(Collection<? extends Player> receivers) {
		for (Player p : receivers) {
			try {
				int id = HologramAPI.getVersion(p) > 5 ? this.hologramIDs[2]
						: (HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport) ? this.hologramIDs[0]
								: this.hologramIDs[1];
				Object dataWatcher =

						HologramAPI.getVersion(p) > 5 ? this.dataWatcherHorse_1_8
								: (HologramAPI.is1_8) && (!HologramAPI.useProtocolSupport) ? this.dataWatcherArmorStand
										: this.dataWatcherHorse_1_7;
				Object packet = ClassBuilder.buildNameMetadataPacket(id, dataWatcher, 2, 3, getText());
				HologramAPI.sendPacket(p, packet);
				if ((HologramAPI.getVersion(p) <= 5) && (this.hologramIDs.length > 1)) {
					HologramAPI.sendPacket(p, ClassBuilder.buildNameMetadataPacket(this.hologramIDs[1],
							this.dataWatcherHorse_1_7, 10, 11, getText()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void sendDestroyPackets(Collection<? extends Player> receivers) {
		for (Player p : receivers) {
			HologramAPI.sendPacket(p, this.destroyPacket);
			if (isTouchable()) {
				HologramAPI.sendPacket(p, this.destroyPacketTouch);
			}
		}
	}

	protected void sendAttachPacket(Collection<? extends Player> receivers) {
		for (Player p : receivers) {
			if (!((DefaultHologram) this).isAttached()) {
				HologramAPI.sendPacket(p, this.ridingEjectPacket);
			} else {
				HologramAPI.sendPacket(p, this.ridingAttachPacket);
			}
		}
	}

	public abstract void setLocation(Location paramLocation);

	public abstract Location getLocation();

	public abstract void setText(String paramString);

	public abstract String getText();

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.attachPacket == null ? 0 : this.attachPacket.hashCode());
		result = 31 * result + (this.attachPacketTouch == null ? 0 : this.attachPacketTouch.hashCode());
		result = 31 * result + (this.dataWatcherArmorStand == null ? 0 : this.dataWatcherArmorStand.hashCode());
		result = 31 * result + (this.dataWatcherHorse_1_7 == null ? 0 : this.dataWatcherHorse_1_7.hashCode());
		result = 31 * result + (this.dataWatcherHorse_1_8 == null ? 0 : this.dataWatcherHorse_1_8.hashCode());
		result = 31 * result + (this.dataWatcherTouchSlime == null ? 0 : this.dataWatcherTouchSlime.hashCode());
		result = 31 * result + (this.dataWatcherTouchVehicle == null ? 0 : this.dataWatcherTouchVehicle.hashCode());
		result = 31 * result + (this.dataWatcherWitherSkull == null ? 0 : this.dataWatcherWitherSkull.hashCode());
		result = 31 * result + (this.destroyPacket == null ? 0 : this.destroyPacket.hashCode());
		result = 31 * result + (this.destroyPacketTouch == null ? 0 : this.destroyPacketTouch.hashCode());
		result = 31 * result + Arrays.hashCode(this.hologramIDs);
		result = 31 * result + (this.packetsBuilt ? 1231 : 1237);
		result = 31 * result + (this.spawnPacketArmorStand == null ? 0 : this.spawnPacketArmorStand.hashCode());
		result = 31 * result + (this.spawnPacketHorse_1_7 == null ? 0 : this.spawnPacketHorse_1_7.hashCode());
		result = 31 * result + (this.spawnPacketHorse_1_8 == null ? 0 : this.spawnPacketHorse_1_8.hashCode());
		result = 31 * result + (this.spawnPacketTouchSlime == null ? 0 : this.spawnPacketTouchSlime.hashCode());
		result = 31 * result + (this.spawnPacketTouchVehicle == null ? 0 : this.spawnPacketTouchVehicle.hashCode());
		result = 31 * result + (this.spawnPacketWitherSkull == null ? 0 : this.spawnPacketWitherSkull.hashCode());
		result = 31 * result + (this.teleportPacketArmorStand == null ? 0 : this.teleportPacketArmorStand.hashCode());
		result = 31 * result + (this.teleportPacketHorse_1_7 == null ? 0 : this.teleportPacketHorse_1_7.hashCode());
		result = 31 * result + (this.teleportPacketHorse_1_8 == null ? 0 : this.teleportPacketHorse_1_8.hashCode());
		result = 31 * result + (this.teleportPacketSkull == null ? 0 : this.teleportPacketSkull.hashCode());
		result = 31 * result + (this.teleportPacketTouchSlime == null ? 0 : this.teleportPacketTouchSlime.hashCode());
		result = 31 * result
				+ (this.teleportPacketTouchVehicle == null ? 0 : this.teleportPacketTouchVehicle.hashCode());
		result = 31 * result + Arrays.hashCode(this.touchIDs);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CraftHologram other = (CraftHologram) obj;
		if (this.attachPacket == null) {
			if (other.attachPacket != null) {
				return false;
			}
		} else if (!this.attachPacket.equals(other.attachPacket)) {
			return false;
		}
		if (this.attachPacketTouch == null) {
			if (other.attachPacketTouch != null) {
				return false;
			}
		} else if (!this.attachPacketTouch.equals(other.attachPacketTouch)) {
			return false;
		}
		if (this.dataWatcherArmorStand == null) {
			if (other.dataWatcherArmorStand != null) {
				return false;
			}
		} else if (!this.dataWatcherArmorStand.equals(other.dataWatcherArmorStand)) {
			return false;
		}
		if (this.dataWatcherHorse_1_7 == null) {
			if (other.dataWatcherHorse_1_7 != null) {
				return false;
			}
		} else if (!this.dataWatcherHorse_1_7.equals(other.dataWatcherHorse_1_7)) {
			return false;
		}
		if (this.dataWatcherHorse_1_8 == null) {
			if (other.dataWatcherHorse_1_8 != null) {
				return false;
			}
		} else if (!this.dataWatcherHorse_1_8.equals(other.dataWatcherHorse_1_8)) {
			return false;
		}
		if (this.dataWatcherTouchSlime == null) {
			if (other.dataWatcherTouchSlime != null) {
				return false;
			}
		} else if (!this.dataWatcherTouchSlime.equals(other.dataWatcherTouchSlime)) {
			return false;
		}
		if (this.dataWatcherTouchVehicle == null) {
			if (other.dataWatcherTouchVehicle != null) {
				return false;
			}
		} else if (!this.dataWatcherTouchVehicle.equals(other.dataWatcherTouchVehicle)) {
			return false;
		}
		if (this.dataWatcherWitherSkull == null) {
			if (other.dataWatcherWitherSkull != null) {
				return false;
			}
		} else if (!this.dataWatcherWitherSkull.equals(other.dataWatcherWitherSkull)) {
			return false;
		}
		if (this.destroyPacket == null) {
			if (other.destroyPacket != null) {
				return false;
			}
		} else if (!this.destroyPacket.equals(other.destroyPacket)) {
			return false;
		}
		if (this.destroyPacketTouch == null) {
			if (other.destroyPacketTouch != null) {
				return false;
			}
		} else if (!this.destroyPacketTouch.equals(other.destroyPacketTouch)) {
			return false;
		}
		if (!Arrays.equals(this.hologramIDs, other.hologramIDs)) {
			return false;
		}
		if (this.packetsBuilt != other.packetsBuilt) {
			return false;
		}
		if (this.spawnPacketArmorStand == null) {
			if (other.spawnPacketArmorStand != null) {
				return false;
			}
		} else if (!this.spawnPacketArmorStand.equals(other.spawnPacketArmorStand)) {
			return false;
		}
		if (this.spawnPacketHorse_1_7 == null) {
			if (other.spawnPacketHorse_1_7 != null) {
				return false;
			}
		} else if (!this.spawnPacketHorse_1_7.equals(other.spawnPacketHorse_1_7)) {
			return false;
		}
		if (this.spawnPacketHorse_1_8 == null) {
			if (other.spawnPacketHorse_1_8 != null) {
				return false;
			}
		} else if (!this.spawnPacketHorse_1_8.equals(other.spawnPacketHorse_1_8)) {
			return false;
		}
		if (this.spawnPacketTouchSlime == null) {
			if (other.spawnPacketTouchSlime != null) {
				return false;
			}
		} else if (!this.spawnPacketTouchSlime.equals(other.spawnPacketTouchSlime)) {
			return false;
		}
		if (this.spawnPacketTouchVehicle == null) {
			if (other.spawnPacketTouchVehicle != null) {
				return false;
			}
		} else if (!this.spawnPacketTouchVehicle.equals(other.spawnPacketTouchVehicle)) {
			return false;
		}
		if (this.spawnPacketWitherSkull == null) {
			if (other.spawnPacketWitherSkull != null) {
				return false;
			}
		} else if (!this.spawnPacketWitherSkull.equals(other.spawnPacketWitherSkull)) {
			return false;
		}
		if (this.teleportPacketArmorStand == null) {
			if (other.teleportPacketArmorStand != null) {
				return false;
			}
		} else if (!this.teleportPacketArmorStand.equals(other.teleportPacketArmorStand)) {
			return false;
		}
		if (this.teleportPacketHorse_1_7 == null) {
			if (other.teleportPacketHorse_1_7 != null) {
				return false;
			}
		} else if (!this.teleportPacketHorse_1_7.equals(other.teleportPacketHorse_1_7)) {
			return false;
		}
		if (this.teleportPacketHorse_1_8 == null) {
			if (other.teleportPacketHorse_1_8 != null) {
				return false;
			}
		} else if (!this.teleportPacketHorse_1_8.equals(other.teleportPacketHorse_1_8)) {
			return false;
		}
		if (this.teleportPacketSkull == null) {
			if (other.teleportPacketSkull != null) {
				return false;
			}
		} else if (!this.teleportPacketSkull.equals(other.teleportPacketSkull)) {
			return false;
		}
		if (this.teleportPacketTouchSlime == null) {
			if (other.teleportPacketTouchSlime != null) {
				return false;
			}
		} else if (!this.teleportPacketTouchSlime.equals(other.teleportPacketTouchSlime)) {
			return false;
		}
		if (this.teleportPacketTouchVehicle == null) {
			if (other.teleportPacketTouchVehicle != null) {
				return false;
			}
		} else if (!this.teleportPacketTouchVehicle.equals(other.teleportPacketTouchVehicle)) {
			return false;
		}
		return Arrays.equals(this.touchIDs, other.touchIDs);
	}

	public String toString() {
		return

		"{\"hologramIDs\":\"" + Arrays.toString(this.hologramIDs) + "\",\"touchIDs\":\""
				+ Arrays.toString(this.touchIDs) + "\",\"packetsBuilt\":\"" + this.packetsBuilt
				+ "\",\"spawnPacketArmorStand\":\"" + this.spawnPacketArmorStand + "\",\"spawnPacketWitherSkull\":\""
				+ this.spawnPacketWitherSkull + "\",\"spawnPacketHorse_1_7\":\"" + this.spawnPacketHorse_1_7
				+ "\",\"spawnPacketHorse_1_8\":\"" + this.spawnPacketHorse_1_8 + "\",\"attachPacket\":\""
				+ this.attachPacket + "\",\"teleportPacketArmorStand\":\"" + this.teleportPacketArmorStand
				+ "\",\"teleportPacketSkull\":\"" + this.teleportPacketSkull + "\",\"teleportPacketHorse_1_7\":\""
				+ this.teleportPacketHorse_1_7 + "\",\"teleportPacketHorse_1_8\":\"" + this.teleportPacketHorse_1_8
				+ "\",\"destroyPacket\":\"" + this.destroyPacket + "\",\"spawnPacketTouchSlime\":\""
				+ this.spawnPacketTouchSlime + "\",\"spawnPacketTouchWitherSkull\":\"" + this.spawnPacketTouchVehicle
				+ "\",\"attachPacketTouch\":\"" + this.attachPacketTouch + "\",\"destroyPacketTouch\":\""
				+ this.destroyPacketTouch + "\",\"teleportPacketTouchSlime\":\"" + this.teleportPacketTouchSlime
				+ "\",\"teleportPacketTouchWitherSkull\":\"" + this.teleportPacketTouchVehicle
				+ "\",\"dataWatcherArmorStand\":\"" + this.dataWatcherArmorStand + "\",\"dataWatcherWitherSkull\":\""
				+ this.dataWatcherWitherSkull + "\",\"dataWatcherHorse_1_7\":\"" + this.dataWatcherHorse_1_7
				+ "\",\"dataWatcherHorse_1_8\":\"" + this.dataWatcherHorse_1_8 + "\",\"dataWatcherTouchSlime\":\""
				+ this.dataWatcherTouchSlime + "\",\"dataWatcherTouchWitherSkull\":\"" + this.dataWatcherTouchVehicle
				+ "\"}";
	}

	static FieldResolver PacketPlayOutSpawnEntityLivingFieldResolver = new FieldResolver(
			NMSClass.PacketPlayOutSpawnEntityLiving);
	protected int[] hologramIDs;
	protected int[] touchIDs;
	protected boolean packetsBuilt;
	protected Object spawnPacketArmorStand;
	protected Object spawnPacketWitherSkull;
	protected Object spawnPacketHorse_1_7;
	protected Object spawnPacketHorse_1_8;
	protected Object attachPacket;
	protected Object teleportPacketArmorStand;
	protected Object teleportPacketSkull;
	protected Object teleportPacketHorse_1_7;
	protected Object teleportPacketHorse_1_8;
	protected Object destroyPacket;
	protected Object ridingAttachPacket;
	protected Object ridingEjectPacket;
	protected Object spawnPacketTouchSlime;
	protected Object spawnPacketTouchVehicle;
	protected Object attachPacketTouch;
	protected Object destroyPacketTouch;
	protected Object teleportPacketTouchSlime;
	protected Object teleportPacketTouchVehicle;
	protected Object dataWatcherArmorStand;
	protected Object dataWatcherWitherSkull;
	protected Object dataWatcherHorse_1_7;
	protected Object dataWatcherHorse_1_8;
	protected Object dataWatcherTouchSlime;
	protected Object dataWatcherTouchVehicle;
}
