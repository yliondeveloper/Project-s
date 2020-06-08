package xyz.starmc.http;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import xyz.starmc.main.Main;

public class HttpAPI {

	private static Twitter twitter;

	public Twitter getTwitter() {
		return HttpAPI.twitter;
	}

	public static Status TweetConsole(String tweet) {
		return tweet(tweet, null);
	}

	public static Main instance() {
		return Main.instance;
	}

	public static void APIStarter() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("hEXfwJ6tjALiaYA1ommZmydtQ")
				.setOAuthConsumerSecret("LJVsTozaufucprjDz5k2jqjN5j4T92AA04TczErQLYPz9JeI1Z")
				.setOAuthAccessToken("710511559085322242-w5Fg7PXQQ10AqSbWP30peJasi818XMx")
				.setOAuthAccessTokenSecret("vXq42EMjmszsCbiagmbhpklXllWmiEjxOIyy3pPGxFCeo");
		HttpAPI.twitter = new TwitterFactory(builder.build()).getInstance();
		try {
			HttpAPI.twitter.help().getAPIConfiguration();
			Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fTwitterAPI iniciada com sucesso.");
		} catch (TwitterException e) {
			if (e.getStatusCode() == 400) {
				Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fOcorreu um erro ao conectar ao twitter api.");
			} else {
				Bukkit.getConsoleSender().sendMessage("§a§lPLUGIN §fOcorreu um erro ao conectar ao twitter api.");
				e.printStackTrace();
			}
		}
	}

	public static Status tweet(String tweet, CommandSender sender) {
		if (sender != null && !(sender instanceof ConsoleCommandSender)) {
			Bukkit.getConsoleSender().sendMessage("§b§lTWITTER §fTweet postado com sucesso.");
		}
		try {
			Status status = HttpAPI.twitter
					.updateStatus(tweet.replace("\\r", "\r").replace("\r", "").replaceAll("\\\\n|\\|", "\n"));
			if (sender != null) {
				String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
				sender.sendMessage("§b§lTWITTER §f Tweet postado com sucesso. \n§fUsuario §b@"
						+ HttpAPI.twitter.getScreenName() + "\n§fLink §b" + url);
			}
			return status;
		} catch (TwitterException e) {
			String reply = "§c§lERRO §fOcorreu um erro ao postar o tweet.";
			if (sender != null && !(sender instanceof ConsoleCommandSender)) {
				sender.sendMessage(reply);
			}
			Bukkit.getConsoleSender().sendMessage(reply);
		}
		return null;

	}

}
