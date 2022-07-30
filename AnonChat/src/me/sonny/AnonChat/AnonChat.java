package me.sonny.AnonChat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class AnonChat extends JavaPlugin {
	
	private List<Player> anonPlayers;
	
	@Override
	public void onEnable() {
		anonPlayers = new ArrayList<>();
		
		// Set up Listener
		getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
		
		// Set up config.yml file
		saveDefaultConfig();
		
		Bukkit.getConsoleSender().sendMessage("Masquerade! Paper faces on parade, Masquerade! Hide your face, so the world will never find you!");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("It was a bright cold day in April, and the clocks were striking thirteen.");
	}
	
	// Command listener
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("anon")) {
			if (sender instanceof Player) {
				// Player performed command
				Player player = (Player) sender;
				
				if (player.hasPermission("anonchat.use"))
					// Player has permission
					if (anonPlayers.contains(player)) {
						anonPlayers.remove(player);
						player.sendMessage(ChatColor.WHITE + "Now your nickname will be displayed on chat.");
					} else {
						anonPlayers.add(player);
						player.sendMessage(ChatColor.GRAY + "You are now anonymous on chat.");
					}
				else {
					// Player does not have permission
					player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
				}
				
				return true;
			} else {
				// Console performed command
				sender.sendMessage("Only players can perform this command.");
				
				return true;
			}
		}
		
		return false;
	}
	
	// anonPlayers getter
	public List<Player> getAnonPlayers() {
		return anonPlayers;
	}
}