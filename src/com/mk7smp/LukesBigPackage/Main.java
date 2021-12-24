package com.mk7smp.LukesBigPackage;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.addRecipe(getCreeperHeadRecipe());
		Bukkit.addRecipe(getZombieHeadRecipe());
		Bukkit.addRecipe(getSkeletonHeadRecipe());
		Bukkit.addRecipe(getWitherSkeletonHeadRecipe());
		Bukkit.addRecipe(getDragonHeadRecipe());
	}

	@Override
	public void onDisable() {

	}

	// /bots displays all online players with my IP
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("bots")) {
			if (!sender.hasPermission("lbp.bots")) {
				sender.sendMessage(ChatColor.RED + "You cannot run this command.");
				return true;
			}
			if (args.length == 0) {
				List<String> onlineBots = new ArrayList<>();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getAddress().getAddress().getHostAddress().equals("47.151.250.145")) {
						onlineBots.add(players.getName());
				    }
				}
				   sender.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Luke's Bot List: " + ChatColor.RESET + "" + ChatColor.AQUA + "" + onlineBots.toString());
				   return true;
			}
		}	
		return false;
	}
	
	@EventHandler
	public void mobDeath(EntityDeathEvent event) {
		
		Entity entity = event.getEntity();
		World eworld = entity.getWorld();
		
		ItemStack membrane = new ItemStack(Material.PHANTOM_MEMBRANE);
		
		if(event.getEntity().getKiller() instanceof Player) {
			
		    Player player = event.getEntity().getKiller();
		    World world = player.getWorld();

		    ItemStack horsemeat = new ItemStack(Material.CHICKEN);
	    	ItemMeta horsemeatmeta = horsemeat.getItemMeta();
	    	horsemeatmeta.setDisplayName(ChatColor.GOLD + "Horse_Meat");
	
		    // MOB EFFECTS
		    if (entity.getType().toString() == "RABBIT") {
		        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bmk7 Mob Effects&f] &aYou've been given &c&lLeaping: 15s &afor killing a &crabbit&a!"));
		        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 2));
		    }
		    if (entity.getType().toString() == "ENDERMAN") {
		        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bmk7 Mob Effects&f] &aYou've been given &c&lRegeneration: 10s &afor killing a &cenderman&a!"));
		        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
		    }
		    if (entity.getType().toString() == "CHICKEN") {
		        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bmk7 Mob Effects&f] &aYou've been given &c&lFeather Falling: 10s &afor killing a &c&lchicken&a!"));
		        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 1));
		    }
		    
		    //HORSE MEAT
		    if (entity.getType().toString() == "HORSE") {	    	
				world.dropItemNaturally(player.getLocation(), horsemeat);
		    }
		    
		}
		
		// fix phantoms not dropping membrane when not killed by player.
		if (entity.getType().toString() == "PHANTOM") {
				//eworld.dropItemNaturally(entity.getLocation(), membrane);
				eworld.dropItem(entity.getLocation(), membrane);
		}
	    
		
		return;	
	}
	
	// CUSTOM MOB HEAD RECIPES
	
	public ShapedRecipe getCreeperHeadRecipe() {
		ItemStack item = new ItemStack(Material.CREEPER_HEAD);
		NamespacedKey key = new NamespacedKey(this, "creeper_head"); // custom name, can be anything
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		recipe.shape("GGG", "GGG", "GGG");
		recipe.setIngredient('G', Material.GUNPOWDER);
		return recipe;
	}
	public ShapedRecipe getZombieHeadRecipe() {
		ItemStack item = new ItemStack(Material.ZOMBIE_HEAD);
		NamespacedKey key = new NamespacedKey(this, "zombie_head"); // custom name, can be anything
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		recipe.shape("RRR", "RRR", "RRR");
		recipe.setIngredient('R', Material.ROTTEN_FLESH);
		return recipe;
	}
	public ShapedRecipe getSkeletonHeadRecipe() {
		ItemStack item = new ItemStack(Material.SKELETON_SKULL);
		NamespacedKey key = new NamespacedKey(this, "skeleton_skull"); // custom name, can be anything
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		recipe.shape("BBB", "BBB", "BBB");
		recipe.setIngredient('B', Material.BONE);
		return recipe;
	}
	public ShapedRecipe getWitherSkeletonHeadRecipe() {
		ItemStack item = new ItemStack(Material.WITHER_SKELETON_SKULL);
		NamespacedKey key = new NamespacedKey(this, "wither_skeleton_skull"); // custom name, can be anything
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		recipe.shape(" S ", "BBB", "CCC");
		recipe.setIngredient('B', Material.BONE);
		recipe.setIngredient('C', Material.COAL);
		recipe.setIngredient('S', Material.STONE_SWORD);
		return recipe;
	}
	public ShapedRecipe getDragonHeadRecipe() {
		ItemStack item = new ItemStack(Material.DRAGON_HEAD);
		NamespacedKey key = new NamespacedKey(this, "dragon_head"); // custom name, can be anything
		ShapedRecipe recipe = new ShapedRecipe(key, item);
		recipe.shape(" C ", "CEC", " C ");
		recipe.setIngredient('E', Material.ELYTRA);
		recipe.setIngredient('C', Material.END_CRYSTAL);
		return recipe;
	}

	
}
