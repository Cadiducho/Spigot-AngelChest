package de.jeffclan.AngelChest;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {
	
	AngelChestPlugin plugin;
	
	public BlockListener(AngelChestPlugin plugin) {
		this.plugin=plugin;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(!event.getBlock().getType().equals(Material.CHEST)) return;
		if(!plugin.isAngelChest(event.getBlock())) return;
		AngelChest angelChest = plugin.getAngelChest(event.getBlock());
		if(!angelChest.owner.equals(event.getPlayer()) && !event.getPlayer().hasPermission("angelchest.protect.ignore")) {
			event.getPlayer().sendMessage(plugin.messages.MSG_NOT_ALLOWED_TO_BREAK_OTHER_ANGELCHESTS);
			event.setCancelled(true);
			return;
		}
		Utils.destroyAngelChest(event.getBlock(), angelChest, plugin);
	}
	
}