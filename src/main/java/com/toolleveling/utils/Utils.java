package com.toolleveling.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import net.md_5.bungee.api.ChatColor;

public class Utils {
	private static String divider = "-";
	
	public static int calcMaxXp(int level) {
		return (int) (10 * Math.log(level * 100));
	}
	
	public static String getExpLevel(int lvl, int xp, int maxXp) {
		return color("&rLvl: " + lvl + " " + divider + " Exp: " + xp + " / " + maxXp);
	}
	
	public static void addLevel(List<String> lore) {
		resetXp(lore);
		
		if (lore != null) {
			for (String line : lore) {
				if (line.contains("Lvl")) {
					int newLvl = getLvl(lore) + 1;
					int curXp = getXp(lore);
					int curMaxXP = calcMaxXp(newLvl);
					lore.remove(line);
					lore.add(getExpLevel(newLvl, curXp, curMaxXP));
				}
			}
		}
	}
	
	public static int getLvl(List<String> lore) {
		if (lore != null) {
			for (String line : lore) {
				if (line.contains("Lvl")) {
					String level = line.substring(line.indexOf("Lvl: ") + 5, line.indexOf(divider) - 1);
					return Integer.parseInt(level);
				}
					
			}
		}
		
		return -1;
	}
	
	public static void resetXp(List<String> lore) {
		if (lore != null) {
			for (String line : lore) {
				if (line.contains("Lvl")) {
					int newXp = 0;
					int curLvl = getLvl(lore);
					int curMaxXP = calcMaxXp(curLvl);
					lore.remove(line);
					lore.add(getExpLevel(curLvl, newXp, curMaxXP));
				}
			}
		}
	}
	
	public static void addXp(List<String> lore) {
		if (lore != null) {
			for (String line : lore) {
				if (line.contains("Lvl")) {
					int newXp = getXp(lore) + 1;
					int curLvl = getLvl(lore);
					int curMaxXP = calcMaxXp(curLvl);
					lore.remove(line);
					lore.add(getExpLevel(curLvl, newXp, curMaxXP));
				}
			}
		}
	}
	
	public static boolean checkForNewLevel(List<String> lore) {
		return Utils.getXp(lore) >= Utils.calcMaxXp(Utils.getLvl(lore));
	}
	
	public static int getXp(List<String> lore) {
		if (lore != null) {
			for (String line : lore) {
				if (line.contains("Lvl")) {
					String xp = line.substring(line.indexOf("Exp: ") + 5, line.indexOf("/") - 1);
					return Integer.parseInt(xp);
				}
					
			}
		}
		
		return -1;
	}
	
	public static void register(List<String> lore) {
		if (!isRegistered(lore)) {
			newRegister(lore);
		}
	}
	
	private static void newRegister(List<String> lore) {
		if (lore != null) {
			lore.add("");
			lore.add(getExpLevel(1, 0, 10));
		} else {
			lore = new ArrayList<String>();
			lore.add("");
			lore.add(getExpLevel(1, 0, 10));
		}
	}
	
	private static boolean isRegistered(List<String> lore) {
		if (lore != null) {
			for (String line : lore) {
				if (line.contains("Lvl"))
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean isTool(Material tool) {
		switch (tool) {
		case DIAMOND_PICKAXE:
		case GOLDEN_PICKAXE:
		case IRON_PICKAXE:
		case STONE_PICKAXE:
		case WOODEN_PICKAXE:
		case DIAMOND_SHOVEL:
		case GOLDEN_SHOVEL:
		case IRON_SHOVEL:
		case STONE_SHOVEL:
		case WOODEN_SHOVEL:
		case DIAMOND_AXE:
		case GOLDEN_AXE:
		case IRON_AXE:
		case STONE_AXE:
		case WOODEN_AXE:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isSword(Material tool) {
		switch(tool) {
		case DIAMOND_SWORD:
		case GOLDEN_SWORD:
		case IRON_SWORD:
		case STONE_SWORD:
		case WOODEN_SWORD:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isBow(Material tool) {
		return tool == Material.BOW;
	}
	
	public static String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static boolean isMonster(EntityType type) {
		switch (type) {
		case BLAZE:
		case CAVE_SPIDER:
		case CREEPER:
		case ELDER_GUARDIAN:
		case ENDER_DRAGON:
		case ENDERMAN:
		case ENDERMITE:
		case EVOKER:
		case GHAST:
		case GIANT:
		case GUARDIAN:
		case HUSK:
		case MAGMA_CUBE:
		case PHANTOM:
		case PIG_ZOMBIE:
		case SHULKER:
		case SILVERFISH:
		case SKELETON:
		case SLIME:
		case SPIDER:
		case STRAY:
		case VEX:
		case VINDICATOR:
		case WITCH:
		case WITHER:
		case WITHER_SKELETON:
		case ZOMBIE:
		case ZOMBIE_VILLAGER:
			return true;
		default:
			return false;
		}
	}
}
