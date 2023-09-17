package org.storm.mc.compass;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassListener implements Listener {
    private final String specialCompassDisplayName;

    public CompassListener(String specialCompassDisplayName) {
        this.specialCompassDisplayName = specialCompassDisplayName;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getType() == Material.COMPASS && event.getAction().name().contains("RIGHT_CLICK")) {
            ItemMeta itemMeta = itemInHand.getItemMeta();
            if (itemMeta != null && specialCompassDisplayName.equals(itemMeta.getDisplayName())) {
                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();
                player.sendMessage(" ");
                player.sendMessage("☀ Твои координаты: X=" + x + ", Y=" + y + ", Z=" + z);
                player.sendMessage("");

                int newAmount = itemInHand.getAmount() - 1;
                if (newAmount <= 0) {
                    player.getInventory().setItemInMainHand(null);
                } else {
                    itemInHand.setAmount(newAmount);
                }

                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
            }
        }
    }
}
