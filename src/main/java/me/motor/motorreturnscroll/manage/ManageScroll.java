package me.motor.motorreturnscroll.manage;

import dev.lone.itemsadder.api.CustomStack;
import me.motor.motorreturnscroll.MotorReturnScroll;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class ManageScroll {

    private final MotorReturnScroll plugin;
    private final File file;
    private final FileConfiguration config;
    private ItemStack scroll;
    private Location location;

    public ManageScroll(MotorReturnScroll plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "returnscroll.yml");
        if (!file.exists()) {
            plugin.saveResource("returnscroll.yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);


    }

    public void loadScrollData() {
        if (config.contains("scroll")) {
            scroll = config.getItemStack("scroll");
        }
        if (config.contains("location")) {
            location = config.getLocation("location");
        }
    }

    private void saveScrollData() {
        if (scroll != null) {
            config.set("scroll", scroll);
        }
        if (location != null) {
            config.set("location", location);
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScroll(ItemStack scroll) {
        ItemStack scrollcopy = scroll;
        scrollcopy.setAmount(1);
        CustomStack stack = CustomStack.byItemStack(scrollcopy);
        if (stack != null) {
            this. scroll = stack.getItemStack();
            saveScrollData();
            return;
        }
        this.scroll = scrollcopy;
        saveScrollData();
    }

    public ItemStack getScroll() {
        return scroll;
    }

    public void setScrollLocation(Location location) {
        this.location = location;
        saveScrollData();
    }

    public Location getLocation() {
        return location;
    }
}
