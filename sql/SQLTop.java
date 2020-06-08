package xyz.starmc.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import xyz.starmc.main.Main;

public class SQLTop {

	public static List<String> TopWin = new LinkedList<String>();
	public static List<String> TopLose = new LinkedList<String>();
	public static List<String> TopXP = new LinkedList<String>();

	public static void updatewin() {
		try {
			PreparedStatement ps = SQLCache.con
					.prepareStatement("SELECT * FROM PotPvP ORDER BY Victory DESC LIMIT 10");
			ResultSet rs = ps.executeQuery();
			int index = 1;
			TopWin.clear();
			Main.topwin.clear();
			while (rs.next()) {
				if (TopWin.size() >= 10)
					break;
				TopWin.add("§a" + index + "§7. " + rs.getString("NICK") + " §7| §fVitórias: §e" + rs.getInt("Victory"));
				index++;
			}
		} catch (SQLException localSQLException) {
			localSQLException.printStackTrace();
		}

		Main.topwin = TopWin;
	}
	
	public static void updatelose() {
		try {
			PreparedStatement ps = SQLCache.con
					.prepareStatement("SELECT * FROM PotPvP ORDER BY Defeats DESC LIMIT 10");
			ResultSet rs = ps.executeQuery();
			int index = 1;
			TopLose.clear();
			Main.toplose.clear();
			while (rs.next()) {
				if (TopLose.size() >= 10)
					break;
				TopLose.add("§a" + index + "§7. " + rs.getString("NICK") + " §7| §fDerrotas: §e" + rs.getInt("Defeats"));
				index++;
			}
		} catch (SQLException localSQLException) {
			localSQLException.printStackTrace();
		}

		Main.toplose = TopLose;
	}


	public static void updatexp() {
		try {
			PreparedStatement ps = SQLXp.con.prepareStatement("SELECT * FROM Xp ORDER BY EXP DESC LIMIT 10");
			ResultSet rs = ps.executeQuery();
			int index = 1;
			TopXP.clear();
			Main.topxp.clear();
			while (rs.next()) {
				if (TopXP.size() >= 10)
					break;
				TopXP.add("§a" + index + "§7. " + rs.getString("NICK") + " §7| §fXP: §e" + rs.getInt("EXP")
						+ "§7 | §fRank: " + SQLXp.getRankComplete(rs.getInt("EXP")));
				index++;
			}
		} catch (SQLException localSQLException) {
			localSQLException.printStackTrace();
		}
		Main.topxp = TopXP;
	}

}
