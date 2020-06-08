package xyz.starmc.hologram;

import org.bukkit.*;
import org.bukkit.scheduler.*;

import xyz.starmc.main.Main;

import javax.annotation.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import java.util.*;

public class DefaultHologram extends CraftHologram {
	private Location location;
	private String text;
	private boolean touchable;
	private boolean spawned;
	private boolean isAttached;
	private Entity attachedTo;
	private List<TouchHandler> touchHandlers;
	private List<ViewHandler> viewHandlers;
	private Hologram lineBelow;
	private Hologram lineAbove;
	private BukkitRunnable updater;

	protected DefaultHologram(@Nonnull Location loc, String text) {
		this.touchHandlers = new ArrayList<TouchHandler>();
		this.viewHandlers = new ArrayList<ViewHandler>();
		if (loc == null) {
			throw new IllegalArgumentException("location cannot be null");
		}
		this.location = loc;
		this.text = text;
	}

	@Override
	public boolean isSpawned() {
		return this.spawned;
	}

	@Override
	public void spawn(long ticks) {
		if (ticks < 1L) {
			throw new IllegalArgumentException("ticks must be at least 1");
		}
		this.spawn();
		new BukkitRunnable() {
			public void run() {
				DefaultHologram.this.despawn();
			}
		}.runTaskLater((Plugin) Main.getInstance(), ticks);
	}

	@Override
	public boolean spawn() {
		this.validateDespawned();
		if (!this.packetsBuilt) {
			try {
				this.buildPackets(false);
				this.packetsBuilt = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			this.spawned = HologramAPI.spawn(this, this.getLocation().getWorld().getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.spawned;
	}

	@Override
	public boolean despawn() {
		this.validateSpawned();
		try {
			this.spawned = !HologramAPI.despawn(this, this.getLocation().getWorld().getPlayers());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !this.spawned;
	}

	@Override
	public void setLocation(Location loc) {
		this.move(loc);
	}

	@Override
	public Location getLocation() {
		return this.location.clone();
	}

	@Override
	public void setText(String text) {
		this.text = text;
		if (this.isSpawned()) {
			try {
				this.buildPackets(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.sendNamePackets(this.getLocation().getWorld().getPlayers());
		}
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void update() {
		this.setText(this.getText());
	}

	@Override
	public void update(long interval) {
		if (interval == -1L) {
			if (this.updater == null) {
				throw new IllegalStateException("Not updating");
			}
			this.updater.cancel();
			this.updater = null;
		} else {
			if (this.updater != null) {
				throw new IllegalStateException("Already updating");
			}
			if (interval < 1L) {
				throw new IllegalArgumentException("Interval must be at least 1");
			}
			(this.updater = new BukkitRunnable() {
				public void run() {
					DefaultHologram.this.update();
				}
			}).runTaskTimer((Plugin) Main.getInstance(), interval, interval);
		}
	}

	@Override
	public void move(@Nonnull Location loc) {
		if (loc == null) {
			throw new IllegalArgumentException("location cannot be null");
		}
		if (this.location.equals((Object) loc)) {
			return;
		}
		if (!this.location.getWorld().equals(loc.getWorld())) {
			throw new IllegalArgumentException("cannot move to different world");
		}
		this.location = loc;
		if (this.isSpawned()) {
			try {
				this.buildPackets(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.sendTeleportPackets(this.getLocation().getWorld().getPlayers(), true, true);
		}
	}

	@Override
	public void setTouchable(boolean flag) {
		this.validateTouchEnabled();
		if (flag == this.isTouchable()) {
			return;
		}
		this.touchable = flag;
		if (this.isSpawned()) {
			try {
				this.buildPackets(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.sendSpawnPackets(this.getLocation().getWorld().getPlayers(), false, true);
		}
	}

	@Override
	public boolean isTouchable() {
		return this.touchable && HologramAPI.packetsEnabled;
	}

	@Override
	public void addTouchHandler(TouchHandler handler) {
		this.validateTouchEnabled();
		if (!this.isTouchable()) {
			throw new IllegalStateException("Hologram is not touchable");
		}
		this.touchHandlers.add(handler);
	}

	@Override
	public void removeTouchHandler(TouchHandler handler) {
		this.validateTouchEnabled();
		if (!this.isTouchable()) {
			throw new IllegalStateException("Hologram is not touchable");
		}
		this.touchHandlers.remove(handler);
	}

	@Override
	public Collection<TouchHandler> getTouchHandlers() {
		return new ArrayList<TouchHandler>(this.touchHandlers);
	}

	@Override
	public void clearTouchHandlers() {
		for (TouchHandler handler : this.getTouchHandlers()) {
			this.removeTouchHandler(handler);
		}
	}

	@Override
	public void addViewHandler(ViewHandler handler) {
		this.validateViewsEnabled();
		this.viewHandlers.add(handler);
	}

	@Override
	public void removeViewHandler(ViewHandler handler) {
		this.validateViewsEnabled();
		this.viewHandlers.remove(handler);
	}

	@Override
	public Collection<ViewHandler> getViewHandlers() {
		return new ArrayList<ViewHandler>(this.viewHandlers);
	}

	@Override
	public void clearViewHandlers() {
		for (ViewHandler handler : this.getViewHandlers()) {
			this.removeViewHandler(handler);
		}
	}

	@Override
	public Hologram addLineBelow(String text) {
		this.validateSpawned();
		Hologram hologram = HologramAPI.createHologram(this.getLocation().subtract(0.0, 0.25, 0.0), text);
		this.lineBelow = hologram;
		((DefaultHologram) hologram).lineAbove = this;
		hologram.spawn();
		return hologram;
	}

	@Override
	public Hologram getLineBelow() {
		this.validateSpawned();
		return this.lineBelow;
	}

	@Override
	public boolean removeLineBelow() {
		if (this.getLineBelow() != null) {
			if (this.getLineBelow().isSpawned()) {
				this.getLineBelow().despawn();
			}
			this.lineBelow = null;
			return true;
		}
		return false;
	}

	@Override
	public Collection<Hologram> getLinesBelow() {
		List<Hologram> list = new ArrayList<Hologram>();
		Hologram current = this;
		while ((current = ((DefaultHologram) current).lineBelow) != null) {
			list.add(current);
		}
		return list;
	}

	@Override
	public Hologram addLineAbove(String text) {
		this.validateSpawned();
		Hologram hologram = HologramAPI.createHologram(this.getLocation().add(0.0, 0.25, 0.0), text);
		this.lineAbove = hologram;
		((DefaultHologram) hologram).lineBelow = this;
		hologram.spawn();
		return hologram;
	}

	@Override
	public Hologram getLineAbove() {
		this.validateSpawned();
		return this.lineAbove;
	}

	@Override
	public boolean removeLineAbove() {
		if (this.getLineAbove() != null) {
			if (this.getLineAbove().isSpawned()) {
				this.getLineAbove().despawn();
			}
			this.lineAbove = null;
			return true;
		}
		return false;
	}

	@Override
	public Collection<Hologram> getLinesAbove() {
		List<Hologram> list = new ArrayList<Hologram>();
		Hologram current = this;
		while ((current = ((DefaultHologram) current).lineAbove) != null) {
			list.add(current);
		}
		return list;
	}

	@Override
	public Collection<Hologram> getLines() {
		List<Hologram> list = new ArrayList<Hologram>();
		list.addAll(this.getLinesAbove());
		list.add(this);
		list.addAll(this.getLinesBelow());
		return list;
	}

	@Override
	public void setAttachedTo(Entity attachedTo) {
		if (attachedTo == this.attachedTo) {
			return;
		}
		this.setAttached(attachedTo != null);
		if (attachedTo != null) {
			this.attachedTo = attachedTo;
		}
		if (this.isSpawned()) {
			try {
				this.buildPackets(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.sendAttachPacket(this.getLocation().getWorld().getPlayers());
		}
		this.attachedTo = attachedTo;
	}

	@Override
	public Entity getAttachedTo() {
		return this.attachedTo;
	}

	public boolean isAttached() {
		return this.isAttached;
	}

	public void setAttached(boolean isAttached) {
		this.isAttached = isAttached;
	}

	private void validateTouchEnabled() {
		if (!HologramAPI.packetsEnabled()) {
			throw new IllegalStateException("Touch-holograms are not enabled");
		}
	}

	private void validateViewsEnabled() {
		if (!HologramAPI.packetsEnabled()) {
			throw new IllegalStateException("ViewHandlers are not enabled");
		}
	}

	private void validateSpawned() {
		if (!this.spawned) {
			throw new IllegalStateException("Not spawned");
		}
	}

	private void validateDespawned() {
		if (this.spawned) {
			throw new IllegalStateException("Already spawned");
		}
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + ((this.location == null) ? 0 : this.location.hashCode());
		result = 31 * result + (this.spawned ? 1231 : 1237);
		result = 31 * result + ((this.text == null) ? 0 : this.text.hashCode());
		result = 31 * result + ((this.touchHandlers == null) ? 0 : this.touchHandlers.hashCode());
		result = 31 * result + (this.touchable ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		DefaultHologram other = (DefaultHologram) obj;
		if (this.location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!this.location.equals((Object) other.location)) {
			return false;
		}
		if (this.spawned != other.spawned) {
			return false;
		}
		if (this.text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!this.text.equals(other.text)) {
			return false;
		}
		if (this.touchHandlers == null) {
			if (other.touchHandlers != null) {
				return false;
			}
		} else if (!this.touchHandlers.equals(other.touchHandlers)) {
			return false;
		}
		return this.touchable == other.touchable;
	}

	@Override
	public String toString() {
		return "{\"location\":\"" + this.location + "\",\"text\":\"" + this.text + "\",\"touchable\":\""
				+ this.touchable + "\",\"spawned\":\"" + this.spawned + "\",\"touchHandlers\":\"" + this.touchHandlers
				+ "\"}";
	}
}
