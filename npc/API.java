package xyz.starmc.npc;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class API {

	public static void NPCAPI() {
		NPC npc = NPCLibrary.createNPC("§7[§eBOT§7]");
		npc.setSkin("eyJ0aW1lc3RhbXAiOjE1NDUzNzYxODkyNjAsInByb2ZpbGVJZCI6IjgyZDk0M2Y4NjRjNDQ4NGQ4MDhlMDE5NzM5ODhmNzNjIiwicHJvZmlsZU5hbWUiOiJTd2lzc0FybXlHb2QiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNmODY2ODIxYmZiZjUxYzA1ZmY3OThhZjEwOTU3NWFmZmExOGM3NzkxNTE4MTBiNTJlOTljNTQ3YzMzNzllYjcifX19", "jyks/+0VmdEdsi26yyoQzxoPvidXJwB/ZczFs8Xu44flOtBJmITj3tyaa6ypkPF2Tu7UyY8bZYYoYU+knDkpHNJ+p1OuVolMntIAS8FGiokbdYIdDr2S3IGDjIU+vQHzZj4WQi7TeaWxBbx1fgOhRmZxOhCe3Zf/6m8zaTHLzi7C4abKYUhXXozLkB0lzQ3bem1d/uN6FOEjvY8On80Sc3T3L5IxTdbobAX7tXn3goQiq46SNQEBY98Q37s9UiNdqI3hl/6fJyMk7SRi9t7LcFqskkz2FTcAJOsmEw35nuLlTydjkDsNQGK9iomJ8uJBN5CgBHazXv+DpCeeDYmKT9QR+Q3lKOGsiT0k853H5pXtdWHy+dE22QP0AymrX7Vk1NYLlJvMDaTNbjjz7hjPIz0L9ryAnEHvIePjx+CqScrfFz0d2TL9lN/mw9fKyEfC1UITcwycE5aglTgTo+H/g+D/erSrdOI+aCjViKWy8s3ijwWcD+lfjyAsAqdieemvNJSPC3rgsdOH7irCOHNMxZT3UD/ZnggURb9d1NiJjVzi78Z1m3DfL9Z7eWYzcAxyIe2zE/siJRacII73WLo41xvvI2HHvyyTkq4rCv5cHBIvWBHq9ac7xXENlkMJmCbDMBfDM/9mJLn1qyIYMYi1l3T1xwLgfkJQ/amJP0nxwa0=");
		
		npc.spawn(new Location(Bukkit.getWorld("PotPvP"), (double) 1012, (double) 61.0, (double) 1017, (float) 179.0,
				(float) 1.7));
	}

}
