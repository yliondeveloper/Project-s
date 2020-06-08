package xyz.starmc.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtList;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import xyz.starmc.anticheat.AntiCheatAPI;
import xyz.starmc.anticheat.AntiMacroAPI;
import xyz.starmc.anticheat.AntiRegenAPI;
import xyz.starmc.clan.ClanCommand;
import xyz.starmc.clan.MessageClan;
import xyz.starmc.clan.TopClans;
import xyz.starmc.commands.AccountCMD;
import xyz.starmc.commands.AlternateGroupCMD;
import xyz.starmc.commands.ApplyCMD;
import xyz.starmc.commands.BroadCastCMD;
import xyz.starmc.commands.BuildCMD;
import xyz.starmc.commands.CChatCMD;
import xyz.starmc.commands.CageCMD;
import xyz.starmc.commands.ChatCMD;
import xyz.starmc.commands.DemoteCMD;
import xyz.starmc.commands.EnsinarCMD;
import xyz.starmc.commands.FlyCMD;
import xyz.starmc.commands.GamemodeCMD;
import xyz.starmc.commands.GoCMD;
import xyz.starmc.commands.GroupCMD;
import xyz.starmc.commands.HorasCMD;
import xyz.starmc.commands.InfoCMD;
import xyz.starmc.commands.LobbyCMD;
import xyz.starmc.commands.PingCMD;
import xyz.starmc.commands.PricesCMD;
import xyz.starmc.commands.PvPCMD;
import xyz.starmc.commands.RanksCMD;
import xyz.starmc.commands.ReloadBotCMD;
import xyz.starmc.commands.RulesCMD;
import xyz.starmc.commands.ScoreCMD;
import xyz.starmc.commands.SpawnCMD;
import xyz.starmc.commands.SpectarCMD;
import xyz.starmc.commands.StopCMD;
import xyz.starmc.commands.TagCMD;
import xyz.starmc.commands.TellCMD;
import xyz.starmc.commands.TopClanCMD;
import xyz.starmc.commands.TopDerrotasCMD;
import xyz.starmc.commands.TopWinCMD;
import xyz.starmc.commands.TopXpCMD;
import xyz.starmc.commands.TpCMD;
import xyz.starmc.commands.TpallCMD;
import xyz.starmc.commands.TweetCMD;
import xyz.starmc.commands.TwitterCMD;
import xyz.starmc.commands.UncageCMD;
import xyz.starmc.commands.VanishCMD;
import xyz.starmc.commands.YoutuberCMD;
import xyz.starmc.extra.Config;
import xyz.starmc.extra.ExtraGUI;
import xyz.starmc.extra.InfoPlayer;
import xyz.starmc.extra.Title;
import xyz.starmc.fileapi.FileAPI;
import xyz.starmc.fix.FixEnder;
import xyz.starmc.gui.DiamondRanked;
import xyz.starmc.gui.DiamondUnRanked;
import xyz.starmc.gui.IronRanked;
import xyz.starmc.gui.IronUnRanked;
import xyz.starmc.gui.NullRanked;
import xyz.starmc.gui.NullUnRanked;
import xyz.starmc.gui.RankedSelection;
import xyz.starmc.gui.SelectionClassic;
import xyz.starmc.gui.UnRankedSelection;
import xyz.starmc.gui.YoutuberGUI;
import xyz.starmc.hologram.HologramAPI;
import xyz.starmc.hologram.HologramListeners;
import xyz.starmc.http.HttpAPI;
import xyz.starmc.listener.Death;
import xyz.starmc.listener.Listener;
import xyz.starmc.npc.API;
import xyz.starmc.permssions.PermEntry;
import xyz.starmc.sql.SQLCache;
import xyz.starmc.sql.SQLClan;
import xyz.starmc.sql.SQLPerms;
import xyz.starmc.sql.SQLTop;
import xyz.starmc.sql.SQLXp;
import xyz.starmc.sql.TopAPI;
import xyz.starmc.system.FlagProtection;
import xyz.starmc.system.LagAPI;
import xyz.starmc.system.TagAPI;

public class Main extends JavaPlugin {

	public static Main instance;
	public static Main plugin;
	public static Main login;

	public static Main getInstance() {
		return instance;
	}

	public static Plugin getPlugin() {
		return (Plugin) plugin;
	}

	public void onLoad() {
		instance = this;
		plugin = this;
		login = this;
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fIniciando plugin...");
	}

	public static int HologramP;
	public static List<String> topwin = new ArrayList<String>();
	public static List<String> topxp = new ArrayList<String>();
	public static List<String> topclan = new ArrayList<String>();
	public static List<String> toplose = new ArrayList<String>();

	public static String[] animation = new String[] { "§F§LP§6§LOTPVP", "§6§LP§F§LO§6§LTPVP", "§6§LPO§F§LT§6§LPVP",
			"§6§LPOT§F§LP§6§LVP", "§6§LPOTP§F§LV§6§LP", "§6§LPOTPV§F§LP" };;
	public static Integer animationI = 0;

	public void onEnable() {
		GoCMD.onTeleport = false;
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fIniciando APIMySQL...");
		SQLCache.startConnection();
		SQLClan.startConnection();
		SQLPerms.startConnection();
		SQLXp.startConnection();
		Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fAPIMySQL iniciada com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fCarregando eventos...");
		Bukkit.getPluginManager().registerEvents(new IronUnRanked(), this);
		Bukkit.getPluginManager().registerEvents(new UnRankedSelection(), this);
		Bukkit.getPluginManager().registerEvents(new NullUnRanked(), this);
		Bukkit.getPluginManager().registerEvents(new DiamondUnRanked(), this);
		Bukkit.getPluginManager().registerEvents(new IronRanked(), this);
		Bukkit.getPluginManager().registerEvents(new RankedSelection(), this);
		Bukkit.getPluginManager().registerEvents(new NullRanked(), this);
		Bukkit.getPluginManager().registerEvents(new SelectionClassic(), this);
		Bukkit.getPluginManager().registerEvents(new DiamondRanked(), this);
		Bukkit.getPluginManager().registerEvents(new Death(), this);
		Bukkit.getPluginManager().registerEvents(new TagAPI(), this);
		Bukkit.getPluginManager().registerEvents(new VanishCMD(), this);
		Bukkit.getPluginManager().registerEvents(new Listener(), this);
		Bukkit.getPluginManager().registerEvents(new Config(), this);
		Bukkit.getPluginManager().registerEvents(new ExtraGUI(), this);
		Bukkit.getPluginManager().registerEvents(new InfoPlayer(), this);
		Bukkit.getPluginManager().registerEvents(new Title(), this);
		Bukkit.getPluginManager().registerEvents(new YoutuberGUI(), this);
		Bukkit.getPluginManager().registerEvents(new FlagProtection(), this);
		Bukkit.getPluginManager().registerEvents(new PermEntry(), this);
		Bukkit.getPluginManager().registerEvents(new FixEnder(this), this);
		Bukkit.getPluginManager().registerEvents(new AntiCheatAPI(), this);
		Bukkit.getPluginManager().registerEvents(new AntiMacroAPI(), this);
		Bukkit.getPluginManager().registerEvents(new AntiRegenAPI(), this);
		Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fEventos carregados com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fIniciando TwitterAPI...");
		HttpAPI.APIStarter();
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fIniciando canal BungeeCord...");
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fCanal BungeeCord iniciado com sucesso!");
		API.NPCAPI();
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fIniciando APIFile...");
		try {
			FileAPI.Converter();
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§c§lERRO §fNão foi possível encontrar a pasta do Bot.");
		}
		new BukkitRunnable() {
			public void run() {
				SQLTop.updatewin();
				SQLTop.updatexp();
				SQLTop.updatelose();
				TopClans.updateClan();
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(), 0, 20 * 300);
		Bukkit.getConsoleSender().sendMessage("§ePLUGIN §fCarregando comandos...");
		getCommand("c").setExecutor(new MessageClan());
		getCommand("clan").setExecutor(new ClanCommand());
		getCommand("ranks").setExecutor(new RanksCMD());
		getCommand("reloadbot").setExecutor(new ReloadBotCMD());
		getCommand("demote").setExecutor(new DemoteCMD());
		getCommand("tweet").setExecutor(new TweetCMD());
		getCommand("account").setExecutor(new AccountCMD());
		getCommand("ensinar").setExecutor(new EnsinarCMD());
		getCommand("topwins").setExecutor(new TopWinCMD());
		getCommand("uncage").setExecutor(new UncageCMD());
		getCommand("topvitorias").setExecutor(new TopWinCMD());
		getCommand("topxp").setExecutor(new TopXpCMD());
		getCommand("spectar").setExecutor(new SpectarCMD());
		getCommand("spec").setExecutor(new SpectarCMD());
		getCommand("espectar").setExecutor(new SpectarCMD());
		getCommand("score").setExecutor(new ScoreCMD());
		getCommand("aplicar").setExecutor(new ApplyCMD());
		getCommand("form").setExecutor(new ApplyCMD());
		getCommand("apply").setExecutor(new ApplyCMD());
		getCommand("bc").setExecutor(new BroadCastCMD());
		getCommand("build").setExecutor(new BuildCMD());
		getCommand("cage").setExecutor(new CageCMD());
		getCommand("chat").setExecutor(new ChatCMD());
		getCommand("cc").setExecutor(new CChatCMD());
		getCommand("clearchat").setExecutor(new CChatCMD());
		getCommand("fly").setExecutor(new FlyCMD());
		getCommand("gamemode").setExecutor(new GamemodeCMD());
		getCommand("gm").setExecutor(new GamemodeCMD());
		getCommand("go").setExecutor(new GoCMD());
		getCommand("stop").setExecutor(new StopCMD());
		getCommand("reload").setExecutor(new StopCMD());
		getCommand("tag").setExecutor(new TagCMD());
		getCommand("tell").setExecutor(new TellCMD());
		getCommand("Tp").setExecutor(new TpCMD());
		getCommand("hub").setExecutor(new LobbyCMD());
		getCommand("lobby").setExecutor(new LobbyCMD());
		getCommand("ping").setExecutor(new PingCMD());
		getCommand("topderrotas").setExecutor(new TopDerrotasCMD());
		getCommand("topdefeats").setExecutor(new TopDerrotasCMD());
		getCommand("prices").setExecutor(new PricesCMD());
		getCommand("precos").setExecutor(new PricesCMD());
		getCommand("pvp").setExecutor(new PvPCMD());
		getCommand("rules").setExecutor(new RulesCMD());
		getCommand("reg").setExecutor(new RulesCMD());
		getCommand("regras").setExecutor(new RulesCMD());
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("twitter").setExecutor(new TwitterCMD());
		getCommand("tt").setExecutor(new TwitterCMD());
		VanishCMD vanish = new VanishCMD();
		getCommand("v").setExecutor(vanish);
		getCommand("admin").setExecutor(vanish);
		getCommand("youtuber").setExecutor(new YoutuberCMD());
		getCommand("pro").setExecutor(new YoutuberCMD());
		getCommand("tpall").setExecutor(new TpallCMD());
		getCommand("groupset").setExecutor(new AlternateGroupCMD());
		getCommand("setgroup").setExecutor(new AlternateGroupCMD());
		getCommand("group").setExecutor(new GroupCMD());
		getCommand("checkgroup").setExecutor(new GroupCMD());
		getCommand("topclan").setExecutor(new TopClanCMD());
		getCommand("topclans").setExecutor(new TopClanCMD());
		getCommand("info").setExecutor(new InfoCMD());
		getCommand("horas").setExecutor(new HorasCMD());
		getCommand("horario").setExecutor(new HorasCMD());
		getCommand("dia").setExecutor(new HorasCMD());
		getCommand("data").setExecutor(new HorasCMD());
		getCommand("ano").setExecutor(new HorasCMD());
		Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fComandos carregados com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§e§lPLUGIN §fIniciando HologramAPI...");
		Bukkit.getPluginManager().registerEvents(new HologramListeners(), (Plugin) Main.getPlugin());
		HologramAPI.enablePacketListener();
		HologramAPI.packetsEnabled = true;
		HologramAPI.enableProtocolSupport();
		LagAPI.IntelicLag();
		Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fHologramAPI iniciada com sucesso!");
		ScoreAPI();
		AutoMessage();
		TopAPI.HologramAPI();
		Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fPlugin iniciado com sucesso!");
		ProtocolLibrary.getProtocolManager()
				.addPacketListener((com.comphenix.protocol.events.PacketListener) new PacketAdapter(this,
						ListenerPriority.LOWEST, new PacketType[] { PacketType.Play.Client.BLOCK_PLACE }) {
					public void onPacketReceiving(final PacketEvent event) {
						if (event.getPlayer() == null) {
							event.setCancelled(true);
							return;
						}
						ItemStack stack = (ItemStack) event.getPacket().getItemModifier().readSafely(0);
						if (stack == null || stack.getType() != Material.WRITTEN_BOOK) {
							return;
						}
						ItemStack inHand = event.getPlayer().getItemInHand();
						if (inHand == null || inHand.getType() != Material.WRITTEN_BOOK) {
							event.setCancelled(true);
							new BukkitRunnable() {
								public void run() {
									event.getPlayer().kickPlayer("Kicado por enviar varios packets ao servidor.");
								}
							}.runTask(Main.instance);

							return;
						}
						try {
							checkNbtTags(stack);
						} catch (IOException e) {
							event.setCancelled(true);
							new BukkitRunnable() {
								public void run() {
									event.getPlayer().kickPlayer("Kicado por enviar varios packets ao servidor.");
								}
							}.runTask(Main.instance);
							return;
						}

					}
				});

	}

	public static void checkNbtTags(ItemStack itemStack) throws IOException {
		NbtCompound root = (NbtCompound) NbtFactory.fromItemTag(itemStack);
		if (root == null) {
			throw new IOException("NBT etiketi yok");
		}
		if (!root.containsKey("pages")) {
			throw new IOException("NBT bile\u015fik bulunamad\u0131");
		}
		NbtList<String> pages = root.getList("pages");
		if (pages.size() > 50) {
			throw new IOException("\u00c7ok fazla sayfa");
		}
		for (final String page : pages) {
			if (page.length() > 320) {
				throw new IOException("\u00c7ok uzun bir sayfa");
			}
		}
	}

	public void ScoreAPI() {
		new BukkitRunnable() {
			public void run() {
				if (animationI < animation.length - 1) {
					animationI = animationI + 1;
				} else {
					animationI = 0;
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					final Scoreboard score = p.getScoreboard();
					if (score.getObjective("Scoreboard") != null & !ScoreCMD.Scoreboard.contains(p.getName())) {
						score.getObjective("Scoreboard").setDisplayName(animation[animationI]);
					}
				}
			}
		}.runTaskTimer(this, 0, 3);
	}

	public static double getTps() {
		return MinecraftServer.getServer().recentTps[0];
	}

	public void onDisable() {
		SQLCache.disconnect();
		SQLClan.disconnect();
		SQLPerms.disconnect();
		SQLXp.disconnect();
		Bukkit.getConsoleSender().sendMessage("§c§lPLUGIN §fDesativando o plugin...");
		for (Player todos : Bukkit.getOnlinePlayers()) {
			GoCMD.sendPlayer(todos, "lobby");
		}
		Bukkit.shutdown();
		Bukkit.getConsoleSender().sendMessage("§c§lPLUGIN §fMétodos de encerramento concluidos com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§c§lPLUGIN §fPlugin desativado com sucesso!");

	}

}
