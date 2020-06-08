package xyz.starmc.hologram.H1_8;

import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.*;
import java.lang.reflect.*;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.craftbukkit.v1_8_R3.entity.*;

public class EntityHologramStand extends EntityArmorStand implements ArmorHologram {
	private HologramLine line;

	public EntityHologramStand(Location toSpawn, HologramLine line) {
		super((World) ((CraftWorld) toSpawn.getWorld()).getHandle());
		this.setArms(false);
		this.setBasePlate(true);
		this.setInvisible(true);
		this.setGravity(false);
		this.setSmall(true);
		this.line = line;
		this.setPosition(toSpawn.getX(), toSpawn.getY(), toSpawn.getZ());
		try {
			Field blockedSlots = EntityArmorStand.class.getDeclaredField("bi");
			blockedSlots.setAccessible(true);
			blockedSlots.set(this, Integer.MAX_VALUE);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		this.a((AxisAlignedBB) new NullBoundingBox());
	}

	public void t_() {
		this.ticksLived = 0;
		if (this.dead) {
			super.t_();
		}
	}

	public void a(NBTTagCompound nbttagcompound) {
	}

	public void b(NBTTagCompound nbttagcompound) {
	}

	public boolean c(NBTTagCompound nbttagcompound) {
		return false;
	}

	public void e(NBTTagCompound nbttagcompound) {
	}

	public void setCustomName(String s) {
	}

	public void setCustomNameVisible(boolean flag) {
	}

	public boolean a(EntityHuman entityhuman, Vec3D vec3d) {
		return true;
	}

	public void setText(String text) {
		if (text != null && text.length() > 300) {
			text = text.substring(0, 300);
		}
		super.setCustomName((text == null) ? "" : text);
		super.setCustomNameVisible(text != null && !text.isEmpty());
	}

	public void killEntity() {
		super.die();
	}

	public HologramLine getLine() {
		return this.line;
	}

	public Hologram getHologram() {
		return (this.line == null) ? null : this.line.getHologram();
	}

	public CraftEntity getBukkitEntity() {
		if (this.bukkitEntity == null) {
			this.bukkitEntity = (CraftEntity) new CraftHologramStand(this);
		}
		return super.getBukkitEntity();
	}

	static class CraftHologramStand extends CraftArmorStand {
		public CraftHologramStand(EntityHologramStand entity) {
			super(entity.world.getServer(), (EntityArmorStand) entity);
		}

		public void remove() {
		}
	}
}
