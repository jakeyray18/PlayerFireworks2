package com.jakeyray18;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jakeyray18 on 29/09/2014.
 */

public class PlayerFireworks2 extends JavaPlugin
{

    public void onEnable()
    {
        getCommand("firework").setExecutor(new FireworksCommandExecutor(this));

        final FileConfiguration config = this.getConfig();

        config.addDefault("PlayerFireworks.Message", "&5Whoosh, Firework away.");
        config.addDefault("PlayerFireworks.CoolDown-Message", "&4You cannot launch another firework for %time% seconds!");
        config.addDefault("PlayerFireworks.Default-CoolDown-Time", 3);
        config.addDefault("PlayerFireworks.NoAccessMessage", "&4You are not allowed to launch fireworks.");

        config.options().copyDefaults(true);

        saveConfig();
    }
}
