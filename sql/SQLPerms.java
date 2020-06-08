package xyz.starmc.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class SQLPerms {

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
			Bukkit.getConsoleSender().sendMessage("§a§lSQLAPI §fConexão §eSQLPerms §finiciada com sucesso! ");
		} catch (SQLException e) {
			Bukkit.getConsoleSender()
					.sendMessage("§c§lSQLAPI §fOcorreu um erro ao efetuar a conexão MySQL (§aClass: SQLPerms§f)");

		}
	}

	public static void disconnect() {
		try {
			con.close();
			Bukkit.getConsoleSender().sendMessage("§a§lSQLAPI §fConexão §eSQLPerms §ffinalizada com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§c§lSQLAPI §fOcorreu um erro ao finalizar a conexão SQLPerms.");
		}
	}

	public static String getGroupConnection(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Groups WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String grupo = rs.getString("Grupo");
			rs.close();
			ps.close();

			return grupo;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "§cSem registros.";
	}

	public static String getUUID(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Groups WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String grupo = rs.getString("UUID");
			rs.close();
			ps.close();
			return grupo;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "§cSem registros.";
	}

	public static void updateGroup(String player, String group) {
		try {
			PreparedStatement ps = getStatement("UPDATE Groups SET Grupo= ? WHERE NICK= ?");
			ps.setString(1, group);
			ps.setString(2, player);
			ps.executeUpdate();
			ps.close();
		} catch (Exception localException) {
		}
	}

}
