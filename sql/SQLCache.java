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

public class SQLCache {

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
	public static String bank = "PotPvP";
	public static int port = 3306;

	public static void startConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + bank + "?autoReconnect=true",
					"root", "dhfNMCnshj@uu44");
			Bukkit.getConsoleSender().sendMessage("§a§lSQLAPI §fConexão §eSQLPotPvP §finiciada com sucesso! ");
		} catch (SQLException e) {
			Bukkit.getConsoleSender()
					.sendMessage("§c§lSQLAPI §fOcorreu um erro ao efetuar a conexão MySQL (§aClass: SQLPotPvP§f)");

		}
	}

	public static void disconnect() {
		try {
			con.close();
			Bukkit.getConsoleSender().sendMessage("§a§lSQLAPI §fConexão §eSQLPotPvP §ffinalizada com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§c§lSQLAPI §fOcorreu um erro ao finalizar a conexão SQLPotPvP.");
		}
	}

	public static Map<String, Integer> Victory = new HashMap<>();
	public static Map<String, Integer> Defeats = new HashMap<>();

	public static int getVictoryConnection(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PotPvP WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Victory = rs.getInt("Victory");
			rs.close();
			ps.close();
			return Victory;
		} catch (Exception localException) {
		}
		return 0;
	}

	public static int getDefeatsConnection(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PotPvP WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Defeats = rs.getInt("Defeats");
			rs.close();
			ps.close();
			return Defeats;
		} catch (Exception localException) {
		}
		return 0;
	}

	public static void loadCache(String name) {
		Victory.put(name, getVictoryConnection(name));
		Defeats.put(name, getDefeatsConnection(name));
	}

	public static Integer getDefeats(Player p) {
		return Defeats.get(p.getName());
	}

	public static Integer getVictory(Player p) {
		return Victory.get(p.getName());
	}

	public static void addVictorys(Player p) {
		Victory.replace(p.getName(), Victory.get(p.getName()) + 1);
	}

	public static void addDeaths(Player p) {
		Defeats.replace(p.getName(), Defeats.get(p.getName()) + 1);
	}

	public static void updateData(Player p) {
		try {
			con.createStatement()
					.executeUpdate("UPDATE `PotPvP` SET `UUID`='" + p.getUniqueId() + "',`Victory`='"
							+ SQLCache.getVictory(p) + "',`Defeats`='" + SQLCache.getDefeats(p) + "',`NICK`='"
							+ p.getName() + "' WHERE `NICK`='" + p.getName() + "'");
		} catch (SQLException e) {
		} finally {
			SQLCache.Defeats.remove(p.getName());
			SQLCache.Victory.remove(p.getName());
		}
	}

}
