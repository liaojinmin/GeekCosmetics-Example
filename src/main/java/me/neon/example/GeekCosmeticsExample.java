package me.neon.example;

import org.bukkit.plugin.java.JavaPlugin;

public final class GeekCosmeticsExample extends JavaPlugin {

    public static GeekCosmeticsExample plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logicnew'p
        new ExampleBuilder().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
