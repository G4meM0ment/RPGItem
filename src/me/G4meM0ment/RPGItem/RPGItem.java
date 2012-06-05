package me.G4meM0ment.RPGItem;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class RPGItem extends JavaPlugin {
	
	RPGItem rpgi;
	
	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdf = getDescription();
		new EventListener(this);
		rpgi = this;
		
		//Config creation
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

		//Initialize messages on server start
		this.getLogger().info("Version: " + pdf.getVersion());
		if(this.getConfig().getBoolean("permissions") == true)
		{
			this.getLogger().info("Permissions are enabled!");
		}
		if(this.getConfig().getBoolean("permissions") == false)
		{
			this.getLogger().info("Permissions are disabled!");
		}
		if(this.getConfig().getBoolean("items") == true)
		{
			this.getLogger().info("Items are enabled!");
		}
		if(this.getConfig().getBoolean("items") == false)
		{
			this.getLogger().info("Items are disabled!");
		}
		if(this.getConfig().getBoolean("armor") == true)
		{
			this.getLogger().info("Armor is enabled!");
		}
		if(this.getConfig().getBoolean("armor") == false)
		{
			this.getLogger().info("Armor is disabled!");
		}
		this.getLogger().info("RPGItem enabled v" + pdf.getVersion() + " (by G4meM0ment)!");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("rpgitem") && args.length > 0 && args[0].equals("reload") && (player.hasPermission("rpgitem.reload") || player.hasPermission("rpgitem.admin") || player.isOp()))
		{
			this.reloadConfig();
			this.saveConfig();
			player.sendMessage("RPGItem reloaded!");
			this.getLogger().info("Reloaded config!");
		    return true;
		}
		return false;
	}
	
	public void repair(Player player)
	{
		if(player != null)
		{
			short repaired;
			//Checks if permissions are enabled
			if(this.getConfig().getBoolean("permissions") == true && (player.hasPermission("rpgitem.repairs") || player.hasPermission("rpgitem.admin") || player.isOp()))
			{
				//Checks if items are enabled
				if(this.getConfig().getBoolean("items") == true)
				{
					//checks if the durability bar is default
					if(this.getConfig().getBoolean("default_durability_bar") == false)
					{
						repaired = 0;
						for(ItemStack item : player.getInventory())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
					//activates if the durability bar isn't enabled
					else
					{
						repaired = 1;
						for(ItemStack item : player.getInventory())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
				}
				//checks if armor is enabled
				if(this.getConfig().getBoolean("armor") == true)
				{
					if(this.getConfig().getBoolean("default_durability_bar") == false)
					{
						repaired = 0;
						for(ItemStack item : player.getInventory().getArmorContents())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}			
					else
					{
						repaired = 1;
						for(ItemStack item : player.getInventory().getArmorContents())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
				}
			}
			else
			{
				if(this.getConfig().getBoolean("items") == true)
				{
			
					if(this.getConfig().getBoolean("default_durability_bar") == false)
					{
						repaired = 0;
						for(ItemStack item : player.getInventory())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
			
					else
					{
						repaired = 1;
						for(ItemStack item : player.getInventory())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
				}
				if(this.getConfig().getBoolean("armor") == true)
				{
					if(this.getConfig().getBoolean("default_durability_bar") == false)
					{
						repaired = 0;
						for(ItemStack item : player.getInventory().getArmorContents())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
			
					else
					{
						repaired = 1;
						for(ItemStack item : player.getInventory().getArmorContents())
						{
							if(item != null && item.getType() != Material.AIR)
								item.setDurability(repaired);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onDisable()
	{		
		this.getLogger().info("RPGItem disabled!");
	}

}
