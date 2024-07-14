package me.motor.motorreturnscroll.command;

import me.motor.motorreturnscroll.MotorReturnScroll;
import me.motor.motorreturnscroll.manage.ManageScroll;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Switch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class MainCommand implements CommandExecutor {

    private final MotorReturnScroll plugin;

    public MainCommand(MotorReturnScroll plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§f§l[ §b귀환서 §f§l] §f플레이어만 사용 가능한 명령어입니다.");
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage("§f§l[ §b귀환서 §f§l] §f명령어 모음");
            sender.sendMessage("§a- 귀환서 획득");
            sender.sendMessage("§a- 귀환서 설정 - 손에 든 아이템을 귀환서로 설정합니다");
            sender.sendMessage("§a- 귀환서 위치등록 - 현재 위치를 귀환 위치로 설정합니다.");
            return false;
        }
        Player player = (Player) sender;
        commandCase(player, args);
        return false;

    }

    public void commandCase(Player player, String[] args) {
        switch (args[0]) {
            case "위치등록":
                plugin.getManageScroll().setScrollLocation(player.getLocation());
                player.sendMessage("§f§l[ §b귀환서 §f§l] §f귀환 위치를 새롭게 설정했습니다. >>");
                return;
            case "설정":
                if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                    player.sendMessage("§f§l[ §b귀환서 §f§l] §f손에 아이템이 없습니다");
                    return;
                }
                plugin.getManageScroll().setScroll(player.getInventory().getItemInMainHand());
                player.sendMessage("§f§l[ §b귀환서 §f§l] §f귀환서를 새롭게 설정했습니다.");
                return;
            case "획득":
                if (plugin.getManageScroll().getScroll() == null) {
                    ItemStack item = new ItemStack(Material.PAPER);
                    item.getItemMeta().setDisplayName("§f§l[ §b귀환서 §f§l]");
                    item.getItemMeta().setLore(Collections.singletonList("§7손에 들고 사용 시 귀환 장소로 이동합니다"));
                    plugin.getManageScroll().setScroll(item);
                }
                player.getInventory().addItem(plugin.getManageScroll().getScroll());
                player.sendMessage("§f§l[ §b귀환서 §f§l] §f귀환서를 획득했습니다");
                return;
            default:
                player.sendMessage("§f§l[ §b귀환서 §f§l] §f해당 명령어는 존재하지 않습니다.");
                return;

        }
    }
}
