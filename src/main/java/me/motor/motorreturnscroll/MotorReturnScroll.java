package me.motor.motorreturnscroll;


import me.motor.motorreturnscroll.command.MainCommand;
import me.motor.motorreturnscroll.event.RightClick;
import me.motor.motorreturnscroll.manage.ManageScroll;
import org.bukkit.plugin.java.JavaPlugin;

public final class MotorReturnScroll extends JavaPlugin {
    private ManageScroll manageScroll;
    @Override
    public void onEnable() {
        getLogger().info("[귀환서] 플러그인이 로드되었습니다.");
        manageScroll = new ManageScroll(this);
        this.manageScroll.loadScrollData();
        getCommand("귀환서").setExecutor(new MainCommand(this));
        getServer().getPluginManager().registerEvents(new RightClick(this), this);
        // Plugin startup logic

    }

    public ManageScroll getManageScroll() {
        return manageScroll;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
