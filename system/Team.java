package xyz.starmc.system;

import java.util.HashSet;
import java.util.Set;

public class Team {

	private String name;
	private Set<String> players = new HashSet<>();
	private String prefix;
	private String suffix;

	public Team(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<String> getPlayers() {
		return players;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
