package xyz.starmc.system;

import java.util.Collections;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.main.Main;

public class TagAPI implements Listener {

	public static int count;

	public static HashSet<Team> teams = new HashSet<>();

	public static boolean visible = true;

	private static void checkTeam(Team teamInfo) {
		if (teamInfo.getPlayers().isEmpty()) {
			TagPacket packetInfo = new TagPacket(teamInfo.getName(), null, null, null, 1);
			for (Player player : Bukkit.getOnlinePlayers()) {
				packetInfo.sendPacket(player);
			}
			TagAPI.teams.remove(teamInfo);
		}
	}

	private static Team getPlayerTeam(String player) {
		for (Team team : TagAPI.teams) {
			if (team.getPlayers().contains(player)) {
				return team;
			}
		}
		return null;
	}

	private static Team getTeam(String teamName, String prefix, String suffix) {
		for (Team team : TagAPI.teams) {
			if (team.getPrefix().equals(prefix) && team.getSuffix().equals(suffix)) {
				return team;
			}
		}
		Team teamInfo = new Team(teamName + TagAPI.count++);
		teamInfo.setPrefix(prefix);
		teamInfo.setSuffix(suffix);
		TagAPI.teams.add(teamInfo);
		TagPacket packetInfo = new TagPacket(teamInfo.getName(), prefix, suffix, teamInfo.getPlayers(), 0);
		for (Player player : Bukkit.getOnlinePlayers()) {
			packetInfo.sendPacket(player);
		}
		return teamInfo;
	}

	public static void removeTag(String playerName) {
		Team oldTeam = TagAPI.getPlayerTeam(playerName);
		if (oldTeam != null) {
			oldTeam.getPlayers().remove(playerName);
			TagPacket packetInfo = new TagPacket(oldTeam.getName(), Collections.singleton(playerName), 4);
			for (Player player : Bukkit.getOnlinePlayers()) {
				packetInfo.sendPacket(player);
			}
			TagAPI.checkTeam(oldTeam);
		}
	}

	public static void setNameTag(String playerName, String teamName, String prefix, String suffix) {
		new BukkitRunnable() {
			public void run() {
				TagAPI.removeTag(playerName);
				Team teamInfo = TagAPI.getTeam(teamName, prefix, suffix);
				if (teamInfo.getPlayers().contains(playerName)) {
					return;
				}
				teamInfo.getPlayers().add(playerName);
				TagPacket packetInfo = new TagPacket(teamInfo.getName(), Collections.singleton(playerName), 3);
				for (Player player : Bukkit.getOnlinePlayers()) {
					packetInfo.sendPacket(player);
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
	}

	public static void setNameTagVisible(boolean visible) {
		TagAPI.visible = visible;
		for (Team team : TagAPI.teams) {
			TagPacket tagPacket = new TagPacket(team.getName(), team.getPrefix(), team.getSuffix(), team.getPlayers(),
					2);
			for (Player player : Bukkit.getOnlinePlayers()) {
				tagPacket.sendPacket(player);
			}
		}
	}

	public static void setPrefix(String playerName, String prefix) {
		TagAPI.setNameTag(playerName, "team", prefix, "");
	}

	public static void setPrefix(String playerName, String teamName, String prefix) {
		TagAPI.setNameTag(playerName, teamName, prefix, "");
	}

	public static void setSuffix(String playerName, String suffix) {
		TagAPI.setNameTag(playerName, "team", "", suffix);
	}

	public static void setSuffix(String playerName, String teamName, String suffix) {
		TagAPI.setNameTag(playerName, teamName, "", suffix);
	}

	public static void setTag(String playerName, String teamName, String prefix, String suffix) {
		TagAPI.setNameTag(playerName, teamName, prefix, suffix);
	}
	
	@EventHandler
	public void join(PlayerJoinEvent event) {
		for (Team teamInfo : TagAPI.teams) {
			TagPacket packetInfo = new TagPacket(teamInfo.getName(), teamInfo.getPrefix(), teamInfo.getSuffix(),
					teamInfo.getPlayers(), 0);
			packetInfo.sendPacket(event.getPlayer());
		}
	}

	@EventHandler
	public void quit(PlayerQuitEvent event) {
		TagAPI.removeTag(event.getPlayer().getName());
	}

}
