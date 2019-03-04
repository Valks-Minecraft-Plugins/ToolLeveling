package com.toolleveling;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.toolleveling.configs.LoadPlayerFiles;
import com.toolleveling.events.Events;

public class ToolLeveling extends JavaPlugin {
	@Override
	public void onEnable() {
		registerListeners();
	}
	
	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new LoadPlayerFiles(), this);
		pm.registerEvents(new Events(), this);
	}
}
