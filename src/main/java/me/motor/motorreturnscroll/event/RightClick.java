package me.motor.motorreturnscroll.event;

import me.motor.motorreturnscroll.MotorReturnScroll;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.PLUGIN;

public class RightClick implements Listener {

    private final MotorReturnScroll plugin;

    public RightClick(MotorReturnScroll plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick (PlayerInteractEvent event) {

        Action action = event.getAction();
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand().clone();
        mainHand.setAmount(1);
        if (event.getHand() != EquipmentSlot.HAND)
            return;
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (mainHand.equals(plugin.getManageScroll().getScroll())) {
                if (plugin.getManageScroll().getLocation() == null) {
                    player.sendMessage("§f§l[ §b귀환서 §f§l] §f위치가 아직 설정되지 않았습니다.");
                    return;
                }
                player.teleport(plugin.getManageScroll().getLocation(), PLUGIN);
                player.playSound(player,Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                player.sendMessage("§f§l[ §b귀환서 §f§l] §f귀환서를 사용했습니다.");
                player.getInventory().getItemInMainHand().setAmount((player.getInventory().getItemInMainHand().getAmount()-1));
                return;
            }

        }

    }
}
