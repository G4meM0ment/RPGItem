package me.G4meM0ment.RPGItem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class RPGItem extends JavaPlugin {
	
	private EventListener eventlistener;
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdf = getDescription();

		eventlistener = new EventListener(this);
		getServer().getPluginManager().registerEvents(eventlistener, this);
		
		//Config creation
		getConfig().options().copyDefaults(true);
		saveConfig();

		//Initialize messages on server start
		getLogger().info("Version: " + pdf.getVersion());
		if(getConfig().getBoolean("permissions"))
			getLogger().info("Permissions are enabled!");
		
		if(!getConfig().getBoolean("permissions"))
			getLogger().info("Permissions are disabled!");
		
		if(getConfig().getBoolean("items"))
			getLogger().info("Items are enabled!");
		
		if(!getConfig().getBoolean("items"))
			getLogger().info("Items are disabled!");
		
		if(getConfig().getBoolean("armor"))
			getLogger().info("Armor is enabled!");
		
		if(!getConfig().getBoolean("armor"))
			getLogger().info("Armor is disabled!");
		
		getLogger().info("RPGItem enabled v" + pdf.getVersion() + " (by G4meM0ment)!");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("rpgitem") && args.length > 0 && args[0].equals("reload") && checkReloadPerms(player) && sender instanceof Player) {
			reloadConfig();
			saveConfig();
			player.sendMessage("RPGItem reloaded!");
			getLogger().info("Reloaded config!");
		    return true;
		}
		if(command.getName().equalsIgnoreCase("rpgitem") && args.length > 0 && args[0].equals("reload") && checkReloadPerms(player) && !(sender instanceof Player)) {
			reloadConfig();
			saveConfig();
			getLogger().info("Reloaded config!");
		}
		return false;
	}
	
	public void repair(Player p)
	{
		if(p != null) {
			PlayerInventory inv = p.getInventory();
			ItemStack[] arm = inv.getArmorContents();
			List<String> sItems = getConfig().getStringList("repair_list");
			List<Material> materials = new ArrayList<Material>();
			short repaired;
			
			if(getConfig().getBoolean("use_default_list"))
				materials = defaultList();
			else {
				for(String sItem : sItems) {
					materials.add(getMaterial(sItem));
				}
			}

			//Sets the repair value
			if(getConfig().getBoolean("default_durability_bar"))
				repaired = 1;
			else
				repaired = 0;
			
			//Check for permissions
			if(checkRepairPerms(p)) {
				//Checks if items are enabled
				if(getConfig().getBoolean("items")) {
					for(ItemStack item : inv) {
						//checks if the item was damaged and it is an item
						if(item != null && item.getType() != Material.AIR && materials.contains(item.getType()))
							try{
								item.setDurability(repaired);
							}catch(NullPointerException e) {
								
							}
					}
				}
				//checks if armor is enabled
				if(!getConfig().getBoolean("armor")) {
					for(ItemStack item : arm) {
						if(item != null && item.getType() != Material.AIR && materials.contains(item.getType()))
							item.setDurability(repaired);
					}
				}			
			}
		}
	}
	
	private boolean checkRepairPerms(Player p) {
		if(getConfig().getBoolean("permissions")) {
			if((p.hasPermission("rpgitem.repairs") || p.hasPermission("rpgitem.admin")))
				return true;
			else
				return false;				
		}
		else
			return true;
	}
	
	private boolean checkReloadPerms(Player p) {
		if(getConfig().getBoolean("permissions")) {
			if((p.hasPermission("rpgitem.reload") || p.hasPermission("rpgitem.admin")))
				return true;
			else
				return false;				
		}
		else
			if(p.isOp())
				return true;
			else
				return false;
	}
	
	//gets the id or the name of the item from config and returns it as Material
	private Material getMaterial(String id)
	{
		Material get = Material.getMaterial(id);
		if(get != null) return get;
	try
	{
		get = Material.getMaterial(Integer.valueOf(id));
	}
	catch(NumberFormatException e)
	{
	}
	   return get;
	}
	
	private List<Material> defaultList() {
		List<Material> list = new ArrayList<Material>();
		list.add(Material.BOW);
		list.add(Material.CHAINMAIL_BOOTS);
		list.add(Material.CHAINMAIL_CHESTPLATE);
		list.add(Material.CHAINMAIL_HELMET);
		list.add(Material.CHAINMAIL_LEGGINGS);
		list.add(Material.DIAMOND_AXE);
		list.add(Material.DIAMOND_BOOTS);
		list.add(Material.DIAMOND_CHESTPLATE);
		list.add(Material.DIAMOND_HELMET);
		list.add(Material.DIAMOND_LEGGINGS);
		list.add(Material.DIAMOND_HOE);
		list.add(Material.DIAMOND_PICKAXE);
		list.add(Material.DIAMOND_SPADE);
		list.add(Material.DIAMOND_SWORD);
		list.add(Material.FISHING_ROD);
		list.add(Material.FLINT_AND_STEEL);
		list.add(Material.GOLD_AXE);
		list.add(Material.GOLD_BOOTS);
		list.add(Material.GOLD_CHESTPLATE);
		list.add(Material.GOLD_HELMET);
		list.add(Material.GOLD_HOE);
		list.add(Material.GOLD_LEGGINGS);
		list.add(Material.GOLD_SPADE);
		list.add(Material.GOLD_SWORD);
		list.add(Material.IRON_AXE);
		list.add(Material.IRON_BOOTS);
		list.add(Material.IRON_CHESTPLATE);
		list.add(Material.IRON_HELMET);
		list.add(Material.IRON_HOE);
		list.add(Material.IRON_LEGGINGS);
		list.add(Material.IRON_PICKAXE);
		list.add(Material.IRON_SPADE);
		list.add(Material.IRON_SWORD);
		list.add(Material.LEATHER_BOOTS);
		list.add(Material.LEATHER_CHESTPLATE);
		list.add(Material.LEATHER_HELMET);
		list.add(Material.LEATHER_LEGGINGS);
		list.add(Material.SHEARS);
		list.add(Material.STONE_AXE);
		list.add(Material.STONE_HOE);
		list.add(Material.STONE_PICKAXE);
		list.add(Material.STONE_SPADE);
		list.add(Material.STONE_SWORD);
		list.add(Material.WOOD_HOE);
		list.add(Material.WOOD_PICKAXE);
		list.add(Material.WOOD_SPADE);
		list.add(Material.WOOD_AXE);
		list.add(Material.WOOD_SWORD);
		return list;
	} 
	
	@Override
	public void onDisable()
	{		
		getLogger().info("RPGItem disabled!");
	}

}
