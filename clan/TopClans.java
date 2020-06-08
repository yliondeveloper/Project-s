package xyz.starmc.clan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import xyz.starmc.main.Main;
import xyz.starmc.sql.SQLClan;

public class TopClans {

	public static List<String> TopClan = new LinkedList<String>();

	public static void updateClan() {
		try {
			PreparedStatement ps = SQLClan.con.prepareStatement("SELECT * FROM ClanRegistry ORDER BY XP DESC LIMIT 10");
			ResultSet rs = ps.executeQuery();
			int index = 1;
			TopClan.clear();
			Main.topclan.clear();
			while (rs.next()) {
				if (TopClan.size() >= 10)
					break;
				TopClan.add("§a" + index + "§7. §f" + rs.getString("Nome") + " §8(" + rs.getString("Tag")
						+ ") §7| §fXP: §e" + rs.getInt("Xp"));
				index++;
			}
		} catch (SQLException localSQLException) {
			localSQLException.printStackTrace();
		}
		Main.topclan = TopClan;
	}

}
