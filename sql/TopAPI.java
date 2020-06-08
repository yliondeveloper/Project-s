package xyz.starmc.sql;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.starmc.hologram.Hologram;
import xyz.starmc.hologram.HologramAPI;
import xyz.starmc.main.Main;

public class TopAPI {

	public static String topx1;
	public static String topx2;
	public static String topx3;
	public static String topx4;
	public static String topx5;
	public static String topx6;
	public static String topx7;
	public static String topx8;
	public static String topx9;
	public static String topx10;

	public static String topw1;
	public static String topw2;
	public static String topw3;
	public static String topw4;
	public static String topw5;
	public static String topw6;
	public static String topw7;
	public static String topw8;
	public static String topw9;
	public static String topw10;

	public static String topl1;
	public static String topl2;
	public static String topl3;
	public static String topl4;
	public static String topl5;
	public static String topl6;
	public static String topl7;
	public static String topl8;
	public static String topl9;
	public static String topl10;

	public static void HologramAPI() {
		Main.HologramP = 0;
		new BukkitRunnable() {
			public void run() {
				Main.HologramP = 0;
				for (String jog : Main.topxp) {
					Main.HologramP = Main.HologramP + 1;
					if (Main.HologramP == 1) {
						topx1 = jog;
					} else {
						if (Main.HologramP == 2) {
							topx2 = jog;
						} else {
							if (Main.HologramP == 3) {
								topx3 = jog;
							} else {
								if (Main.HologramP == 4) {
									topx4 = jog;
								} else {
									if (Main.HologramP == 5) {
										topx5 = jog;
									} else {
										if (Main.HologramP == 6) {
											topx6 = jog;
										} else {
											if (Main.HologramP == 7) {
												topx7 = jog;
											} else {
												if (Main.HologramP == 8) {
													topx8 = jog;
												} else {
													if (Main.HologramP == 9) {
														topx9 = jog;
													} else {
														if (Main.HologramP == 10) {
															topx10 = jog;
															Main.HologramP = 0;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				for (String jog : Main.topwin) {
					Main.HologramP = Main.HologramP + 1;
					if (Main.HologramP == 1) {
						topw1 = jog;
					} else {
						if (Main.HologramP == 2) {
							topw2 = jog;
						} else {
							if (Main.HologramP == 3) {
								topw3 = jog;
							} else {
								if (Main.HologramP == 4) {
									topw4 = jog;
								} else {
									if (Main.HologramP == 5) {
										topw5 = jog;
									} else {
										if (Main.HologramP == 6) {
											topw6 = jog;
										} else {
											if (Main.HologramP == 7) {
												topw7 = jog;
											} else {
												if (Main.HologramP == 8) {
													topw8 = jog;
												} else {
													if (Main.HologramP == 9) {
														topw9 = jog;
													} else {
														if (Main.HologramP == 10) {
															topw10 = jog;
															Main.HologramP = 0;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				for (String jog : Main.toplose) {
					Main.HologramP = Main.HologramP + 1;
					if (Main.HologramP == 1) {
						topl1 = jog;
					} else {
						if (Main.HologramP == 2) {
							topl2 = jog;
						} else {
							if (Main.HologramP == 3) {
								topl3 = jog;
							} else {
								if (Main.HologramP == 4) {
									topl4 = jog;
								} else {
									if (Main.HologramP == 5) {
										topl5 = jog;
									} else {
										if (Main.HologramP == 6) {
											topl6 = jog;
										} else {
											if (Main.HologramP == 7) {
												topl7 = jog;
											} else {
												if (Main.HologramP == 8) {
													topl8 = jog;
												} else {
													if (Main.HologramP == 9) {
														topl9 = jog;
													} else {
														if (Main.HologramP == 10) {
															topl10 = jog;
															Main.HologramP = 0;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				for (Hologram h : HologramAPI.getHolograms()) {
					HologramAPI.removeHologram(h);
				}
				for (xyz.starmc.hologram.H1_8.Hologram h2 : xyz.starmc.hologram.H1_8.HologramLibrary.listHolograms()) {
					xyz.starmc.hologram.H1_8.HologramLibrary.removeHologram(h2);
				}

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013), "§a§lTOP XP")
						.spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -0.33, 0),
						TopAPI.topx1).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -0.66, 0),
						TopAPI.topx2).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -0.99, 0),
						TopAPI.topx3).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -1.32, 0),
						TopAPI.topx4).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -1.65, 0),
						TopAPI.topx5).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -1.98, 0),
						TopAPI.topx6).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -2.31, 0),
						TopAPI.topx7).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -2.64, 0),
						TopAPI.topx8).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -2.97, 0),
						TopAPI.topx9).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 64, 1013).add(0, -3.30, 0),
						TopAPI.topx10).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010), "§a§lTOP VITORIAS")
						.spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -0.33, 0),
						TopAPI.topw1).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -0.66, 0),
						TopAPI.topw2).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -0.99, 0),
						TopAPI.topw3).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -1.32, 0),
						TopAPI.topw4).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -1.65, 0),
						TopAPI.topw5).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 65, 1010).add(0, -1.98, 0),
						TopAPI.topw6).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -2.31, 0),
						TopAPI.topw7).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -2.64, 0),
						TopAPI.topw8).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -2.97, 0),
						TopAPI.topw9).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 64, 1010).add(0, -3.30, 0),
						TopAPI.topw10).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010), "§C§lTOP DERROTAS")
						.spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -0.33, 0),
						TopAPI.topl1).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -0.66, 0),
						TopAPI.topl2).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -0.99, 0),
						TopAPI.topl3).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -1.32, 0),
						TopAPI.topl4).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -1.65, 0),
						TopAPI.topl5).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -1.98, 0),
						TopAPI.topl6).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -2.31, 0),
						TopAPI.topl7).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -2.64, 0),
						TopAPI.topl8).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -2.97, 0),
						TopAPI.topl9).spawn();

				HologramAPI.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1006, 64, 1010).add(0, -3.30, 0),
						TopAPI.topl10).spawn();

				new BukkitRunnable() {
					public void run() {
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013), "§a§lTOP XP")
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -0.33, 0), TopAPI.topx1)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -0.66, 0), TopAPI.topx2)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -1.32, 0), TopAPI.topx4)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -0.99, 0), TopAPI.topx3)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -1.65, 0), TopAPI.topx5)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -1.98, 0), TopAPI.topx6)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -2.31, 0), TopAPI.topx7)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -2.64, 0), TopAPI.topx8)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -2.97, 0), TopAPI.topx9)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1000.54110, 63, 1013).add(0, -3.30, 0), TopAPI.topx10)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010), "§a§lTOP VITORIAS").spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -0.33, 0),
										TopAPI.topw1)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -0.66, 0),
										TopAPI.topw2)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -0.99, 0),
										TopAPI.topw3)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -1.32, 0),
										TopAPI.topw4)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -1.65, 0),
										TopAPI.topw5)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -1.98, 0),
										TopAPI.topw6)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -2.31, 0),
										TopAPI.topw7)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -2.64, 0),
										TopAPI.topw8)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -2.97, 0),
										TopAPI.topw9)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary
								.createHologram(new Location(Bukkit.getWorld("PotPvP"), 994, 63, 1010).add(0, -3.30, 0),
										TopAPI.topw10)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010), "§C§lTOP DERROTAS").spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -0.33, 0), TopAPI.topl1)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -0.66, 0), TopAPI.topl2)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -0.99, 0), TopAPI.topl3)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -1.32, 0), TopAPI.topl4)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -1.65, 0), TopAPI.topl5)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -1.98, 0), TopAPI.topl6)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -2.31, 0), TopAPI.topl7)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -2.64, 0), TopAPI.topl8)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -2.97, 0), TopAPI.topl9)
								.spawn();
						xyz.starmc.hologram.H1_8.HologramLibrary.createHologram(
								new Location(Bukkit.getWorld("PotPvP"), 1006, 63, 1010).add(0, -3.30, 0), TopAPI.topl10)
								.spawn();
					}
				}.runTaskLater(Main.getPlugin(), 15);
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 20, 20 * 3600);
	}

}
