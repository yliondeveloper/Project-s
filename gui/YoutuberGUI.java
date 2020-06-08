package xyz.starmc.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YoutuberGUI implements Listener {

	public static void YoutubeActive(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§fRequisitos (Tags)");

		ItemStack Ciano = new ItemStack(Material.WOOL, 1, (short) 9);
		ItemMeta Cianoo = Ciano.getItemMeta();
		ArrayList<String> Cianooo = new ArrayList<>();
		Cianooo.add("");
		Cianooo.add("§7-  20 inscritos");
		Cianooo.add("§7-  30 visualizações");
		Cianooo.add("");
		Cianoo.setLore(Cianooo);
		Cianoo.setDisplayName("§3§lDISCLOSER");
		Ciano.setItemMeta(Cianoo);

		ItemStack Yellow = new ItemStack(Material.WOOL, 1, (short) 4);
		ItemMeta Yelloww = Yellow.getItemMeta();
		ArrayList<String> Yellowwww = new ArrayList<>();
		Yellowwww.add("");
		Yellowwww.add("§7- 50 inscritos");
		Yellowwww.add("§7- 80 visualizações");
		Yellowwww.add("");
		Yelloww.setLore(Yellowwww);
		Yelloww.setDisplayName("§e§lS-PRO");
		Yellow.setItemMeta(Yelloww);

		ItemStack Laranja = new ItemStack(Material.WOOL, 1, (short) 1);
		ItemMeta Laranjaa = Laranja.getItemMeta();
		ArrayList<String> Laranjaaa = new ArrayList<>();
		Laranjaaa.add("");
		Laranjaaa.add("§7-  100 inscritos");
		Laranjaaa.add("§7-  150 visualizações");
		Laranjaaa.add("");
		Laranjaa.setLore(Laranjaaa);
		Laranjaa.setDisplayName("§6§lPRO");
		Laranja.setItemMeta(Laranjaa);

		ItemStack Azul = new ItemStack(Material.WOOL, 1, (short) 3);
		ItemMeta Azull = Azul.getItemMeta();
		ArrayList<String> Azulll = new ArrayList<>();
		Azulll.add("");
		Azulll.add("§bYoutuber");
		Azulll.add("");
		Azulll.add("§7- 500 inscritos");
		Azulll.add("§7- 300 visualizações");
		Azulll.add("");
		Azulll.add("§3Youtuber+");
		Azulll.add("");
		Azulll.add("§7- 2000 inscritos");
		Azulll.add("§7- 600 visualizações");
		Azulll.add("");
		Azull.setLore(Azulll);
		Azull.setDisplayName("§b§lYOUTUBER §f/ §3§lYOUTUBER+");
		Azul.setItemMeta(Azull);

		ItemStack Paper1 = new ItemStack(Material.PAPER);
		ItemMeta Paper11 = Paper1.getItemMeta();
		ArrayList<String> Paper111 = new ArrayList<>();
		Paper111.add("");
		Paper111.add("§7Sua tag você pode requisitar tanto aqui no");
		Paper111.add("§7servidor como em nosso discord (discord.me/teckmcofc).");
		Paper111.add("§7Para requisitar no servidor basta encontrar um admin");
		Paper111.add("§7(responsáveis por isso) e requisitar sua tag ou pelo chat ou");
		Paper111.add("§7pelo tell. Para requisitar em nosso discord, basta acessar e usar");
		Paper111.add("§7o chat #requisite-sua-tag e seguir o módulo que se");
		Paper111.add("§7pede para requisitar.");
		Paper111.add("");
		Paper11.setLore(Paper111);
		Paper11.setDisplayName("§b§lAONDE EU REQUISITO?");
		Paper1.setItemMeta(Paper11);

		ItemStack Paper2 = new ItemStack(Material.PAPER);
		ItemMeta Paper22 = Paper2.getItemMeta();
		ArrayList<String> Paper222 = new ArrayList<>();
		Paper222.add("");
		Paper222.add("§7Os responsáveis por setar tags, analisar vídeos, etc...");
		Paper222.add("§7São os §cAdministradores§f/§9Ajudante+§f/§aYT-MANAGER");
		Paper222.add("§7da rede, sempre que precisar");
		Paper222.add("§7de ajuda em algo relacionado ao Youtube, basta");
		Paper222.add("§7chama-los no discord ou no servidor.");
		Paper222.add("");
		Paper22.setLore(Paper222);
		Paper22.setDisplayName("§3§lCOM QUEM EU REQUISITO?");
		Paper2.setItemMeta(Paper22);

		ItemStack Paper3 = new ItemStack(Material.PAPER);
		ItemMeta Paper33 = Paper3.getItemMeta();
		ArrayList<String> Paper333 = new ArrayList<>();
		Paper333.add("");
		Paper333.add("§7Procure sempre manter a calma, não fique");
		Paper333.add("§7floodando ou pedindo a todo tempo, isso além ");
		Paper333.add("§7de atrapalhar outros jogadores que estão tentando");
		Paper333.add("§7requisitar tag, atrapalha o administrador.");
		Paper333.add("§7(A quantia de visualizações devem ser a do vídeo que");
		Paper333.add("§7você fez divulgando nossa rede, não em seu canal)!");
		Paper333.add("");
		Paper33.setLore(Paper333);
		Paper33.setDisplayName("§6§lCOMO EU REQUISITO?");
		Paper3.setItemMeta(Paper33);

		inv.setItem(1, Ciano);

		inv.setItem(3, Yellow);

		inv.setItem(5, Laranja);

		inv.setItem(7, Azul);

		inv.setItem(20, Paper1);

		inv.setItem(22, Paper2);

		inv.setItem(24, Paper3);
		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCLickInventry(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§fRequisitos (Tags)") && e.getCurrentItem() != null
				&& e.getCurrentItem().getTypeId() != 0) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.getWhoClicked().closeInventory();
				return;
			} else {
				e.getWhoClicked().closeInventory();
				return;
			}

		}
	}

}
