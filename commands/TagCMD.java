package xyz.starmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.starmc.sql.SQLXp;
import xyz.starmc.system.TagAPI;

public class TagCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cComando indisponivel para console.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length != 0) {
			if (args[0].equalsIgnoreCase("ceo")) {
				if (sender.getName().equalsIgnoreCase("yLionCodes")) {
					p.setDisplayName("§4§lCEO §4" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §4CEO §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§4§LCEO §4", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("dono")) {
				if (sender.getName().equalsIgnoreCase("yLionCodes")) {
					p.setDisplayName("§4§lDONO §4" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §4Dono §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§4§LDONO §4", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				} else if (sender.getName().equalsIgnoreCase("yNescauh)_")) {
					p.setDisplayName("§4§lDONO §4" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §4Dono §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§4§LDONO §4", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				} else if (sender.getName().equalsIgnoreCase("LegitExtreme")) {
					p.setDisplayName("§4§lDONO §4" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §4Dono §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§4§LDONO §4", " §7(" + SQLXp.getRank(p) + "§7)");
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("subdono")) {
				if (sender.getName().equalsIgnoreCase("lovelyMCPVP_") || sender.getName().equalsIgnoreCase("yLionCodes")

						|| sender.getName().equalsIgnoreCase("IloveLion_")) {
					p.setDisplayName("§4§lSUBDONO §4" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §4SUBDONO §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§4§lSUBDONO §4", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("diretor")) {
				if (sender.hasPermission("tag.diretor")) {
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §1Diretor §fcom sucesso!");
					p.setDisplayName("§1§lDIRETOR §1" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§1§lDIRETOR §1", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("coordenador")) {
				if (sender.hasPermission("tag.coordenador")) {
					p.setDisplayName("§9§lCOORD §9" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §9Coordenador §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§9§lCOORD §9", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("gerentegc")) {
				if (sender.hasPermission("tag.gerentegc")) {
					p.setDisplayName("§c§lGERENTEGC §c" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §cGerenteGC §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§c§lGERENTEGC §c", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("gerente")) {
				if (sender.hasPermission("tag.gerente")) {
					p.setDisplayName("§c§lGERENTE §c" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §cGerente §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§c§lGERENTE §c", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("admingc")) {
				if (sender.hasPermission("tag.admingc")) {
					p.setDisplayName("§c§lADMINGC §c" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §cAdminGC §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§c§lADMINGC §c", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("admin")) {
				if (sender.hasPermission("tag.admin")) {
					p.setDisplayName("§c§lADMIN §c" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §cAdmin §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§c§lADMIN §c", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("modgc")) {
				if (p.hasPermission("tag.modgc")) {
					p.setDisplayName("§5§lMODGC §5" + p.getName());
					p.sendMessage("§3§lTAG §fSua tag foi alterada para §5ModGC §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§5§lMODGC §5", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				p.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("mod")) {
				if (p.hasPermission("tag.mod")) {
					p.setDisplayName("§5§lMOD §5" + p.getName());
					p.sendMessage("§3§lTAG §fSua tag foi alterada para §5Mod §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§5§lMOD §5", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				p.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("trial")) {
				if (sender.hasPermission("tag.trial")) {
					p.setDisplayName("§5§lTRIAL §5" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §5Trial §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§5§lTRIAL§5 §5", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("trialgc")) {
				if (sender.hasPermission("tag.trialgc")) {
					p.setDisplayName("§5§lTRIALGC §5" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §5TrialGC §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§5§lTRIALGC§5 §5", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("builder")) {
				if (sender.hasPermission("tag.builder")) {
					p.setDisplayName("§e§lBUILDER §e" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §eBuilder §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§e§lBUILDER§a §e", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("ajudante+") || args[0].equalsIgnoreCase("helper+")) {
				if (sender.hasPermission("tag.ajudante+")) {
					p.setDisplayName("§9§lAJUDANTE+ §9" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §9Ajudante+ §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§9§lAJUDANTE+ §9", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("ajudante") || args[0].equalsIgnoreCase("helper")) {
				if (sender.hasPermission("tag.ajudante")) {
					p.setDisplayName("§9§lAJUDANTE §9" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §9Ajudante §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§9§lAJUDANTE §9", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("evento")) {
				if (sender.hasPermission("tag.evento")) {
					p.setDisplayName("§e§lEVENTO§f §e" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §eEvento §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§e§lEVENTO §e", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("crystal")) {
				if (p.hasPermission("tag.crystal")) {
					p.setDisplayName("§5§lCRYSTAL §5" + p.getName());
					p.sendMessage("§3§lTAG §fSua tag foi alterada para §5Crystal §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§5§lCRYSTAL §5", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				p.sendMessage("§c§lERRO §fVocê não possui essa tag.");

			}
			if (args[0].equalsIgnoreCase("ultimate")) {
				if (sender.hasPermission("tag.ultimate")) {
					p.setDisplayName("§d§lULTIMATE §d" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §dUltimate §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§d§lULTIMATE §d", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("ultra")) {
				if (sender.hasPermission("tag.ultra")) {
					p.setDisplayName("§e§lULTRA §e" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §eUltra §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§e§lULTRA §e", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}

			if (args[0].equalsIgnoreCase("youtuber+")) {
				if (sender.hasPermission("tag.youtuber+")) {
					p.setDisplayName("§3§lYT+ §3" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §3Youtuber+ §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§3§lYT+ §3", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}

			if (args[0].equalsIgnoreCase("youtubermanager")) {
				if (sender.hasPermission("tag.youtubermanager")) {
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §aYTMANAGER §fcom sucesso!");
					p.setDisplayName("§a§lYTMANAGER §a" + p.getName());
					TagAPI.setNameTag(p.getName(), "team", "§a§lYTMANAGER §a", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}

			if (args[0].equalsIgnoreCase("youtuber")) {
				if (sender.hasPermission("tag.youtuber")) {
					p.setDisplayName("§b§lYT §b" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §bYoutuber §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§b§lYT §b", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("pro")) {
				if (sender.hasPermission("tag.pro")) {
					p.setDisplayName("§6§lPRO §6" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §6Pro §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§6§lPRO §6", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("Spro")) {
				if (sender.hasPermission("tag.spro")) {
					p.setDisplayName("§e§lSPRO §e" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §eSPro §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§e§lSPRO §e", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("Discloser")) {
				if (sender.hasPermission("tag.discloser")) {
					p.setDisplayName("§3§lDISCLOSER §3" + p.getName());
					sender.sendMessage("§3§lTAG §fSua tag foi alterada para §3Discloser §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§3§lDISCLOSER §3", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				sender.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}
			if (args[0].equalsIgnoreCase("normal") || args[0].equalsIgnoreCase("membro")) {
				if (p.hasPermission("tag.normal")) {
					p.setDisplayName("§7" + p.getName());
					p.sendMessage("§3§lTAG §fSua tag foi alterada para §7Normal §fcom sucesso!");
					TagAPI.setNameTag(sender.getName(), "team", "§7", " §7(" + SQLXp.getRank(p) + "§7)");
					return true;
				}
				p.sendMessage("§c§lERRO §fVocê não possui essa tag.");
			}

		}

		p.sendMessage("§c§lERRO §fComando incorreto, utilize: /tag (tag)");
		return true;
	}

}
