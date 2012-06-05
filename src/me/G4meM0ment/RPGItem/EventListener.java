package me.G4meM0ment.RPGItem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
	
	RPGItem plugin;
	public EventListener(RPGItem RPGItem)
	{
	    Bukkit.getServer().getPluginManager().registerEvents(this, RPGItem);
	    plugin = RPGItem;
	}
	
	//Listeners to repair on Events which damages the items
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onEntityDamage(EntityDamageEvent event)
	{
		//checks if its a player
		if((event.getEntity() instanceof Player))
		{
			Player damaged = (Player) event.getEntity();
			//sends the player which inventory should be repaired to the repair method
			plugin.repair(damaged);
		} 
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		Player killed = (Player) event.getEntity();
		plugin.repair(killed);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onInventoryOpen(InventoryOpenEvent event)
	{
		Player opener = (Player) event.getPlayer();
		plugin.repair(opener);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onInventoryClose(InventoryCloseEvent event)
	{
		Player closer = (Player) event.getPlayer();
		plugin.repair(closer);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player joiner = event.getPlayer();
		plugin.repair(joiner);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player interacter = event.getPlayer();
		plugin.repair(interacter);
	}
	
	//TODO add support for plugins which damages items, add more listeners which repairs
}
