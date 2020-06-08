package xyz.starmc.hologram;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.bukkit.plugin.Plugin;
import org.inventivetalent.packetlistener.handler.PacketHandler;
import org.inventivetalent.packetlistener.handler.PacketOptions;
import org.inventivetalent.packetlistener.handler.ReceivedPacket;
import org.inventivetalent.packetlistener.handler.SentPacket;

public class PacketListener extends PacketHandler {

	public PacketListener(Plugin pl) {
		super(pl);
		if (instance != null) {
			throw new IllegalStateException("Cannot instantiate PacketListener twice");
		}
		instance = this;
		addHandler(this);
	}

	public static void disable() {
		if (instance != null) {
			removeHandler(instance);
			instance = null;
		}
	}

	@SuppressWarnings("unchecked")
	@PacketOptions(forcePlayer = true)
	public void onSend(SentPacket packet) {
		if (!packet.hasPlayer()) {
			return;
		}
		int type = -1;
		if (packet.getPacketName().equals("PacketPlayOutSpawnEntityLiving")) {
			type = 0;
		}
		if (packet.getPacketName().equals("PacketPlayOutEntityMetadata")) {
			type = 1;
		}
		if (packet.getPacketName().equals("PacketPlayOutMapChunkBulk")) {
			type = 2;
		}
		if (packet.getPacketName().equals("PacketPlayOutMapChunk")) {
			type = 3;
		}
		String text;
		if ((type == 0) || (type == 1)) {
			int a = ((Integer) packet.getPacketValue("a")).intValue();
			Object dataWatcher = type == 0 ?

					packet.getPacketValue("m")
					: Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1) ? packet.getPacketValue("l") : null;
			if (dataWatcher != null) {
				try {
					dataWatcher = cloneDataWatcher(dataWatcher);
					AccessUtil.setAccessible(Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)
							? NMSClass.DataWatcher.getDeclaredField("a")
							: NMSClass.DataWatcher.getDeclaredField("b")).set(dataWatcher, null);
					if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
						packet.setPacketValue("l", dataWatcher);
					} else {
						packet.setPacketValue("m", dataWatcher);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
			List<Object> list = (List<Object>) (type == 1 ? packet.getPacketValue("b") : null);
			int listIndex = -1;
			text = null;
			int index;
			try {
				if (type == 0) {
					if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
						text = (String) DataWatcher.V1_8
								.getWatchableObjectValue(DataWatcher.V1_8.getValue(dataWatcher, 2));
					} else {
						Field dField = AccessUtil.setAccessible(NMSClass.DataWatcher.getDeclaredField("d"));
						Object dValue = dField.get(dataWatcher);
						if (dValue == null) {
							return;
						}
						if ((Map.class.isAssignableFrom(dValue.getClass())) && (((Map<?, ?>) dValue).isEmpty())) {
							return;
						}
						text = (String) DataWatcher.V1_9.getValue(dataWatcher, DataWatcher.V1_9.ValueType.ENTITY_NAME);
					}
				} else if ((type == 1) && (list != null)) {
					if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
						for (int i = 0; i < list.size(); i++) {
							index = DataWatcher.V1_8.getWatchableObjectIndex(list.get(i));
							if ((index == 2) && (DataWatcher.V1_8.getWatchableObjectType(list.get(i)) == 4)) {
								text = (String) DataWatcher.V1_8.getWatchableObjectValue(list.get(i));
								listIndex = i;
								break;
							}
						}
					} else if ((list.size() > 2) && (DataWatcher.V1_9.getItemType(list.get(2)) == String.class)) {
						text = (String) DataWatcher.V1_9.getItemValue(list.get(2));
						listIndex = 2;
					}
				}
			} catch (Exception e2) {
				if (!HologramAPI.useProtocolSupport) {
					e2.printStackTrace();
				}
			}
			if (text == null) {
				return;
			}
			for (Hologram h : HologramAPI.getHolograms()) {
				if (((CraftHologram) h).matchesHologramID(a)) {
					for (ViewHandler v : h.getViewHandlers()) {
						text = v.onView(h, packet.getPlayer(), text);
					}
				}
			}
			if (text == null) {
				packet.setCancelled(true);
				return;
			}
			try {
				if (type == 0) {
					DataWatcher.setValue(dataWatcher, 2, DataWatcher.V1_9.ValueType.ENTITY_NAME, text);
				} else if (type == 1) {
					if ((list == null) || (listIndex == -1)) {
						return;
					}
					Object object = Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)
							? DataWatcher.V1_8.newWatchableObject(2, text)
							: DataWatcher.V1_9.newDataWatcherItem(DataWatcher.V1_9.ValueType.ENTITY_NAME.getType(),
									text);
					list.set(listIndex, object);
					packet.setPacketValue("b", list);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (type == 2) {
			int[] a2 = (int[]) packet.getPacketValue("a");
			int[] b = (int[]) packet.getPacketValue("b");
			for (int j = 0; j < (a2.length + b.length) / 2; j++) {
				for (Hologram hologram : HologramAPI.getHolograms()) {
					if (hologram.isSpawned()) {
						int chunkX = hologram.getLocation().getBlockX() >> 4;
						int chunkZ = hologram.getLocation().getBlockZ() >> 4;
						if ((chunkX == a2[j]) && (chunkZ == b[j])) {
							try {
								HologramAPI.spawn(hologram, Collections.singletonList(packet.getPlayer()));
							} catch (Exception e3) {
								e3.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	@PacketOptions(forcePlayer = true)
	public void onReceive(ReceivedPacket packet) {
		if ((packet.hasPlayer()) && (packet.getPacketName().equals("PacketPlayInUseEntity"))) {
			int id = ((Integer) packet.getPacketValue("a")).intValue();
			Object useAction = packet.getPacketValue("action");
			TouchAction action = TouchAction.fromUseAction(useAction);
			if (action == TouchAction.UNKNOWN) {
				return;
			}
			for (Hologram h : HologramAPI.getHolograms()) {
				if (((DefaultHologram) h).matchesTouchID(id)) {
					for (TouchHandler t : h.getTouchHandlers()) {
						t.onTouch(h, packet.getPlayer(), action);
					}
				}
			}
		}
	}

	public Object cloneDataWatcher(Object original) throws Exception {
		if (original == null) {
			return null;
		}
		Object clone = DataWatcher.newDataWatcher(null);
		int index = 0;
		Object current = null;
		if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
			while ((current = DataWatcher.V1_8.getValue(original, index++)) != null) {
				DataWatcher.V1_8.setValue(clone, DataWatcher.V1_8.getWatchableObjectIndex(current),
						DataWatcher.V1_8.getWatchableObjectValue(current));
			}
		} else {
			Field mapField = DataWatcherFieldResolver.resolve(new String[] { "c" });
			mapField.set(clone, mapField.get(original));
		}
		return clone;
	}

	static FieldResolver DataWatcherFieldResolver = new FieldResolver(NMSClass.DataWatcher);
	static MethodResolver DataWatcherMethodResolver = new MethodResolver(NMSClass.DataWatcher);
	protected static PacketListener instance;
}
