package com.schiebros.explosions;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onTnTThrow(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("tnt.throw") || !p.isOp())
			return;
		ItemStack item = p.getItemInHand();
		if (!item.getType().equals(Material.TNT))
			return;
		if (p.getGameMode() != GameMode.CREATIVE)
			item.setAmount(item.getAmount() - 1);
		TNTPrimed tnt = p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
		tnt.setVelocity(p.getLocation().getDirection().multiply(2));
		p.setItemInHand(item);
	}
	
	
}
