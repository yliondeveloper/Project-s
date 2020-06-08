package xyz.starmc.hologram.H1_8;

import org.bukkit.*;
import com.google.common.collect.*;
import java.util.*;

public class Hologram {
	private boolean spawned;
	private Location location;
	private Map<Integer, HologramLine> lines;

	public Hologram(Location location, String... lines) {
		this.lines = new HashMap<Integer, HologramLine>();
		this.location = location;
		int index = 0;
		for (String line : lines) {
			this.lines.put(++index, new HologramLine(this, location.clone().add(0.0, 0.33 * index, 0.0), line));
		}
	}

	public void spawn() {
		if (this.spawned) {
			return;
		}
		this.spawned = true;
		this.lines.values().forEach(line -> line.spawn());
	}

	public void despawn() {
		if (!this.spawned) {
			return;
		}
		this.spawned = false;
		this.lines.values().forEach(line -> line.despawn());
	}

	public void addLine(String text) {
		int line;
		for (line = 1; this.lines.containsKey(line); ++line) {
		}
		HologramLine hl = new HologramLine(this, this.location.clone().add(0.0, -0.33 * line, 0.0), text);
		this.lines.put(line, hl);
		if (this.spawned) {
			hl.spawn();
		}
	}

	public void updateLine(int line, String text) {
		if (this.lines.containsKey(line)) {
			this.lines.get(line).setText(text);
		}
	}

	public boolean isSpawned() {
		return this.spawned;
	}

	public Location getInitLocation() {
		return this.location;
	}

	public List<HologramLine> getLines() {
		return (List<HologramLine>) ImmutableList.copyOf((Collection<HologramLine>) this.lines.values());
	}
}
