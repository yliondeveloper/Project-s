package xyz.starmc.system;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeamBase;

public class TagPacket {

    private PacketPlayOutScoreboardTeam packet;
    private ReflectionManager reflectionManager = new ReflectionManager();

    public TagPacket(String name, Collection<String> players, int updateType) {
        this.packet = new PacketPlayOutScoreboardTeam();
        if (players == null || players.isEmpty()) {
            players = new HashSet<>();
        }
        reflectionManager.begin(packet);
        reflectionManager.setString("a", name);
        reflectionManager.setInteger("h", updateType);
        reflectionManager.getCollection("g").addAll(players);
    }

    public TagPacket(String name, String prefix, String suffix, Collection<String> players, int updateType) {
        this.packet = new PacketPlayOutScoreboardTeam();
        reflectionManager.begin(packet);
        reflectionManager.setString("a", name);
        reflectionManager.setInteger("h", updateType);
        if (updateType == 0 || updateType == 2) {
            reflectionManager.setString("b", name);
            reflectionManager.setString("c", prefix);
            reflectionManager.setString("d", suffix);
            reflectionManager.setInteger("i", 1);
            if (!TagAPI.visible) {
                reflectionManager.setString("e", ScoreboardTeamBase.EnumNameTagVisibility.NEVER.e);
            }
        }
        if (updateType == 0) {
            reflectionManager.getCollection("g").addAll(players);
        }
    }

    public void sendPacket(Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
