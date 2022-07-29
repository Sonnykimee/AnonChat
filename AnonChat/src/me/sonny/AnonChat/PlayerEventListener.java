package me.sonny.AnonChat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {
	
	private AnonChat PLUGIN;
	
	public PlayerEventListener(AnonChat plugin) {
		PLUGIN = plugin;
	}
	
	// Player chat listener
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		// Cancel player chat event and broadcast the message with an anonymous name
		Player player = event.getPlayer();
		
		if (PLUGIN.anonPlayers.contains(player)) {
			// Player has enabled anonymous chat
			event.setCancelled(true);
			
			String result = "";
			
			// Get configuration values
			FileConfiguration config = PLUGIN.getConfig();
			
			String nameFormat = config.getString("name-display-format");
			int nameLen = config.getInt("name-length");
			String randText = config.getString("random-seed-text");
			String chars = Utils.removeDuplicates(config.getString("random-name-charset")); // Remove duplicates.
			
			String anonName = Utils.createRandomChars(player.getName(), chars, nameLen, randText);
			
			// Make chat message
			result += ChatColor.translateAlternateColorCodes('&', nameFormat);
			result = result.replaceAll("%NAME%", anonName);
			result = result.replaceAll("%MSG%", event.getMessage());
			
			PLUGIN.getServer().broadcastMessage(result);
		}
	}
	
	// Player quit listener
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		// Remove player from the list
		PLUGIN.anonPlayers.remove(event.getPlayer());
	}
}
