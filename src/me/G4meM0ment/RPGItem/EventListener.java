package me.G4meM0ment.RPGItem;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
	
	private RPGItem plugin;
	
	public EventListener(RPGItem plugin) {
	    this.plugin = plugin;
	}
	
	//Listeners to repair on Events which damages the items
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onEntityDamage(EntityDamageEvent event) {
		//checks if its a player
		if((event.getEntity() instanceof Player)) {
			Player p = (Player) event.getEntity();
			//sends the player which inventory should be repaired to the repair method
			plugin.repair(p);
		} 
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onBlockDamage(BlockDamageEvent event) {
		Player p = (Player) event.getPlayer();
		plugin.repair(p);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player p = (Player) event.getEntity();
		plugin.repair(p);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onInventoryOpen(InventoryOpenEvent event) {
		Player p = (Player) event.getPlayer();
		plugin.repair(p);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL) 
	public void onInventoryClose(InventoryCloseEvent event) {
		Player p = (Player) event.getPlayer();
		plugin.repair(p);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		plugin.repair(p);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack[] armor = player.getInventory().getArmorContents();
			int health = player.getHealth();
			
			ItemStack helmet = armor[0];
			ItemStack chest = armor[1];
			ItemStack leg = armor[2];
			ItemStack boot = armor[3];
			if(helmet != null && helmet.getType() != Material.AIR) {
				Material material = helmet.getType();
				//if()
			}
		}
		
	}
	//TODO add support for plugins which damages items, add more listeners which repairs
}
