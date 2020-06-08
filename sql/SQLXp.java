package xyz.starmc.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SQLXp {

	public static Map<String, Integer> cachexp = new HashMap<String, Integer>();

	public static Connection con;

	public static boolean checkConnection() {
		return con != null;
	}

	public static PreparedStatement getStatement(String sql) {
		if (checkConnection()) {
			try {
				return con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ResultSet getResult(String sql) {
		if (checkConnection()) {
			try {
				return getStatement(sql).executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String host = "127.0.0.1";
	public static String bank = "SyncServers";
	public static int port = 3306;

	public static void startConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + bank + "?autoReconnect=true",
					"root", "dhfNMCnshj@uu44");
			Bukkit.getConsoleSender().sendMessage("§a§lSQLAPI §fConexão §eSQLXp §finiciada com sucesso! ");
		} catch (SQLException e) {
			Bukkit.getConsoleSender()
					.sendMessage("§c§lSQLAPI §fOcorreu um erro ao efetuar a conexão MySQL (§aClass: SQLXp§f)");

		}
	}

	public static void disconnect() {
		try {
			con.close();
			Bukkit.getConsoleSender().sendMessage("§a§lSQLAPI §fConexão §eSQLXp §ffinalizada com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§c§lSQLAPI §fOcorreu um erro ao finalizar a conexão SQLXp.");
		}
	}

	public static void setXp(int valor, Player p) {
		cachexp.replace(p.getName(), valor);
	}

	public static String getRankComplete(int calc) {
		if (calc >= 50000) {
			return "§1Extreme";
		}
		if (calc >= 20000) {
			return "§4Legendary";
		}
		if (calc >= 15000) {
			return "§cMaster";
		}
		if (calc >= 10000) {
			return "§2Emerald";
		}
		if (calc >= 5000) {
			return "§bDiamond";
		}
		if (calc >= 3500) {
			return "§6Gold";
		}
		if (calc >= 2000) {
			return "§7Silver";
		}
		if (calc >= 1000) {
			return "§aPrimary";
		}
		if (calc <= 0) {
			return "§7Unranked";
		}

		return "§7Unranked";
	}

	public static String getRank(Player p) {
		if (cachexp.containsKey(p.getName())) {
			int xp = cachexp.get(p.getName());
			if (xp >= 50000) {
                return "§1✯";
		 	}
			if (xp >= 20000) {
				return "§4✪";
			}
			if (xp >= 15000) {
				return "§c✾";
			}
			if (xp >= 10000) {
				return "§2✽";
			}
			if (xp >= 5000) {
				return "§b❂";
			}
			if (xp >= 3500) {
				return "§6✴";
			}
			if (xp >= 2000) {
				return "✳";
			}
			if (xp >= 1000) {
				return "§a⚌";
			}
			if (xp >= 0) {
				return "§f-";
			}
		}
		return "§f-";
	}

	public static int getXp(Player p) {
		return cachexp.get(p.getName());

	}

	public static int getXpConnection(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Xp WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int xp = rs.getInt("EXP");
			rs.close();
			ps.close();
			return xp;
		} catch (Exception localException) {
		}
		return 0;
	}

	public static void updateData(Player p) {
		try {
			con.createStatement().executeUpdate("UPDATE `Xp` SET `NICK`='" + p.getName() + "',`EXP`='"
					+ cachexp.get(p.getName()) + "' WHERE `NICK`='" + p.getName() + "'");
		} catch (SQLException e) {
		} finally {
			cachexp.remove(p.getName());
		}
	}

	public static void createTable() {
		try {
			PreparedStatement ps = getStatement("CREATE TABLE IF NOT EXISTS Xp (NICK VARCHAR(100), EXP INT(100))");
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
