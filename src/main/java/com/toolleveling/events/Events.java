package com.toolleveling.events;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.toolleveling.utils.Utils;

public class Events implements Listener {
	@EventHandler
	private void blockBreakEvent(BlockBreakEvent e) {
		ItemStack tool = e.getPlayer().getEquipment().getItemInMainHand();
		Material type = tool.getType();
		if (Utils.isTool(type)) {
			ItemMeta im = tool.getItemMeta();
			List<String> lore = im.getLore();
			Utils.register(lore);
			
			Utils.addXp(lore);
			
			if (Utils.checkForNewLevel(lore)) {
				Utils.addLevel(lore);
				im.setLore(lore);
				tool.setItemMeta(im);
				tool.addUnsafeEnchantment(Enchantment.DIG_SPEED, Utils.getLvl(lore));
			} else {
				im.setLore(lore);
				tool.setItemMeta(im);
			}
		}
	}
	
	@EventHandler
	private void entityHitEntityEvent(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && Utils.isMonster(e.getEntityType())) {
			Player player = (Player) e.getDamager();
			ItemStack tool = player.getEquipment().getItemInMainHand();
			Material type = tool.getType();
			if (Utils.isSword(type) || Utils.isBow(type)) {
				ItemMeta im = tool.getItemMeta();
				List<String> lore = im.getLore();
				Utils.register(lore);
				
				Utils.addXp(lore);
				
				if (Utils.checkForNewLevel(lore)) {
					Utils.addLevel(lore);
					im.setLore(lore);
					tool.setItemMeta(im);
					
					if (Utils.isSword(type)) {
						tool.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, Utils.getLvl(lore));
					} else {
						tool.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, Utils.getLvl(lore));
					}
				} else {
					im.setLore(lore);
					tool.setItemMeta(im);
				}
			}
		}
	}
}
