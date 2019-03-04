package com.toolleveling.configs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoadPlayerFiles implements Listener {
	@EventHandler
	public void loadFiles(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		PlayerFiles cm = PlayerFiles.getConfig(p.getUniqueId());
		if (!p.hasPlayedBefore()) {
			FileConfiguration f = cm.getConfig();
			f.set("name", p.getName());
			f.set("uuid", p.getUniqueId().toString());
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
			f.set("join_date", format.format(now));
			f.set("last_join", format.format(now));
			cm.saveConfig();
		} else {
			FileConfiguration f = cm.getConfig();
			f.set("name", p.getName());
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
			f.set("last_join", format.format(now));
			cm.saveConfig();
			
			p.setWhitelisted(true);
		}
	}
}