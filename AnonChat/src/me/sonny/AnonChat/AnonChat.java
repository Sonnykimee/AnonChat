package me.sonny.AnonChat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class AnonChat extends JavaPlugin implements Listener {
	
	// List of players who are anonymous on chat
	List<Player> anonPlayers;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage("Masqeurade! Paper faces on parade, Masquerade! Hide your face, so the world will never find you!");
		
		anonPlayers = new ArrayList<>();
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
	
	// Player chat listener
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		// Cancel player chat event and broadcast the message with an anonymous name
		Player player = event.getPlayer();
		
		if (anonPlayers.contains(player)) {
			// Player has enabled anonymous chat
			event.setCancelled(true);
			
			String anonName = "Anon_" + createRandomSerial(player.getName(), 8);
			
			this.getServer().broadcastMessage(ChatColor.WHITE + "<" + ChatColor.GRAY + anonName + ChatColor.WHITE + "> " + event.getMessage());
		}
	}
	
	// Creates a random string based on the given seed and date.
	public String createRandomSerial(String seed, int serialLength) {
		String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
		
		// Random string changes in daily basis
		LocalDate date = LocalDate.now();
		seed += date.toString();
		seed += "DownWithM$"; // ha ha
		
		Random rand = new Random();
		rand.setSeed(seed.hashCode());
		
		String result = "";
		
		for (int i=0; i<serialLength; i++) {
			result += chars.charAt(rand.nextInt(chars.length()));
		}
		
		return result;
	}
}