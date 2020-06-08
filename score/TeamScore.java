package xyz.starmc.score;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Charsets;

import xyz.starmc.commands.ScoreCMD;
import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLClan;
import xyz.starmc.sql.SQLXp;

public class TeamScore implements OfflinePlayer {

	private final String playerName;

	@SuppressWarnings("deprecation")
	public static void addScore(Player p) {
		if (p.getScoreboard().getObjective("Scoreboard") == null) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();

			Objective objective = board.registerNewObjective("Scoreboard", "dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);

			TeamScore line11 = new TeamScore("    ");
			Team l11 = board.registerNewTeam("line11");
			l11.addPlayer(line11);
			objective.getScore(line11.getName()).setScore(11);

			TeamScore line10 = new TeamScore(" §fGrupo: ");
			Team l10 = board.registerNewTeam("line10");
			l10.setSuffix(getGroup(p));
			l10.addPlayer(line10);
			objective.getScore(line10.getName()).setScore(10);

			TeamScore line9 = new TeamScore(" §fClan: §7");
			Team l9 = board.registerNewTeam("line9");
			l9.setSuffix(SQLClan.getClan(p));
			l9.addPlayer(line9);
			objective.getScore(line9.getName()).setScore(9);

			TeamScore line8 = new TeamScore("   ");
			Team l8 = board.registerNewTeam("line8");
			l8.addPlayer(line8);
			objective.getScore(line8.getName()).setScore(8);

			TeamScore line7 = new TeamScore(" §fVitórias: §7");
			Team l7 = board.registerNewTeam("line7");
			l7.setSuffix(SQLCache.getVictory(p).toString());
			l7.addPlayer(line7);
			objective.getScore(line7.getName()).setScore(7);

			TeamScore line6 = new TeamScore(" §fDerrotas: §7");
			Team l6 = board.registerNewTeam("line6");
			l6.setSuffix(SQLCache.getDefeats(p).toString());
			l6.addPlayer(line6);
			objective.getScore(line6.getName()).setScore(6);

			TeamScore line5 = new TeamScore(" ");
			Team l5 = board.registerNewTeam("line5");
			l5.addPlayer(line5);
			objective.getScore(line5.getName()).setScore(5);

			TeamScore line4 = new TeamScore(" §fXP: §a");
			Team l4 = board.registerNewTeam("line4");
			l4.setSuffix(SQLXp.getXp(p) + "");
			l4.addPlayer(line4);
			objective.getScore(line4.getName()).setScore(4);

			TeamScore line3 = new TeamScore(" §fRank: §7(" + SQLXp.getRank(p));
			Team l3 = board.registerNewTeam("line3");
			l3.setSuffix("§7) " + SQLXp.getRankComplete(SQLXp.getXp(p)));
			l3.addPlayer(line3);
			objective.getScore(line3.getName()).setScore(3);

			TeamScore line2 = new TeamScore("");
			Team l2 = board.registerNewTeam("line2");
			l2.addPlayer(line2);
			objective.getScore(line2.getName()).setScore(2);

			TeamScore line1 = new TeamScore("  §7mc-starpvp");
			Team l1 = board.registerNewTeam("line1");
			l1.setSuffix(".com.br");
			l1.addPlayer(line1);
			objective.getScore(line1.getName()).setScore(1);
			p.setScoreboard(board);
		}
	}

	public static void updateScore(Player p) {
		if (p.getScoreboard().getObjective("Scoreboard") != null && !ScoreCMD.Scoreboard.contains(p.getName())) {
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			p.getScoreboard().getObjective("Scoreboard").unregister();
			addScore(p);
		}
	}

	public static String getGroup(Player p) {
		if (p.getName().equalsIgnoreCase("yLionCodes")) {
			return "§4CEO";
		}
		if (p.getName().equalsIgnoreCase("yNescauh")) {
			return "§4Dono";
		}
		if (p.getName().equalsIgnoreCase("LegitExtreme_")) {
			return "§4Dono";
		}
		if (p.getName().equalsIgnoreCase("LovelyMCPVP_")) {
			return "§4Subdono";
		}
		if (p.getName().equalsIgnoreCase("IloveLion_")) {
			return "§4Subdono";
		}
		if (p.hasPermission("tag.coordenador")) {
			return "§9Coordenador";
		}
		if (p.hasPermission("tag.gerentegc")) {
			return "§cGerenteGC";
		}
		if (p.hasPermission("tag.gerente")) {
			return "§cGerente";
		}
		if (p.hasPermission("tag.admingc")) {
			return "§cAdminGC";
		}
		if (p.hasPermission("tag.admin")) {
			return "§cAdmin";
		}
		if (p.hasPermission("tag.modgc")) {
			return "§5ModGC";
		}
		if (p.hasPermission("tag.mod")) {
			return "§5Mod";
		}
		if (p.hasPermission("tag.trialgc")) {
			return "§5TrialGC";
		}
		if (p.hasPermission("tag.youtubermanager")) {
			return "§aYTMANAGER";
		}
		if (p.hasPermission("tag.trial")) {
			return "§5Trial";
		}
		if (p.hasPermission("tag.builder")) {
			return "§eBuilder";
		}
		if (p.hasPermission("tag.ajudante")) {
			return "§9Ajudante";
		}
		if (p.hasPermission("tag.ajudante+")) {
			return "§9Ajudante+";
		}
		if (p.hasPermission("tag.evento")) {
			return "§eEvento";
		}
		if (p.hasPermission("tag.vendedor")) {
			return "§aVendedor";
		}
		if (p.hasPermission("tag.crystal")) {
			return "§5Crystal";
		}
		if (p.hasPermission("tag.ultimate")) {
			return "§dUltimate";
		}
		if (p.hasPermission("tag.ultra")) {
			return "§eUltra";
		}
		if (p.hasPermission("tag.youtuber+")) {
			return "§3Youtuber+";
		}
		if (p.hasPermission("tag.youtuber")) {
			return "§bYoutuber";
		}
		if (p.hasPermission("tag.spro")) {
			return "§eS-Pro";
		}
		if (p.hasPermission("tag.pro")) {
			return "§6Pro";
		}
		if (p.hasPermission("tag.discloser")) {
			return "§3Discloser";
		}
		if (p.hasPermission("tag.normal")) {
			return "§7Membro";
		}
		return "§7Membro";
	}

	public TeamScore(String playerName) {
		this.playerName = playerName;
	}

	public boolean isOnline() {
		return false;
	}

	public String getName() {
		return this.playerName;
	}

	public UUID getUniqueId() {
		return UUID.nameUUIDFromBytes(this.playerName.getBytes(Charsets.UTF_8));
	}

	public boolean isBanned() {
		return false;
	}

	public void setBanned(boolean banned) {
		throw new UnsupportedOperationException();
	}

	public boolean isWhitelisted() {
		return false;
	}

	public void setWhitelisted(boolean value) {
		throw new UnsupportedOperationException();
	}

	public Player getPlayer() {
		throw new UnsupportedOperationException();
	}

	public long getFirstPlayed() {
		return System.currentTimeMillis();
	}

	public long getLastPlayed() {
		return System.currentTimeMillis();
	}

	public boolean hasPlayedBefore() {
		return false;
	}

	public Location getBedSpawnLocation() {
		throw new UnsupportedOperationException();
	}

	public boolean isOp() {
		return false;
	}

	public void setOp(boolean value) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Object> serialize() {
		throw new UnsupportedOperationException();
	}

}
