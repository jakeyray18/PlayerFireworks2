package com.jakeyray18;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Jakeyray18 on 29/09/2014.
 */

public class FireworksCommandExecutor implements CommandExecutor {

    PlayerFireworks2 plugin;
    public FireworksCommandExecutor(PlayerFireworks2 instance) {
        plugin = instance;
    }

    public HashMap<String, Long> cooldown = new HashMap<String, Long>();
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String commandLabel, String[] args) {
        if (sender instanceof Player)
        {
            UUID UUIDp = ((Player) sender).getUniqueId();
            Player user = Bukkit.getPlayer(UUIDp);
            if (user.hasPermission("playerfireworks.launch") || user.isOp())
            {
                if (commandLabel.equalsIgnoreCase("firework") || (commandLabel.equalsIgnoreCase("fw")))
                {
                    int cooldownTime = plugin.getConfig().getInt("PlayerFireworks.Default-CoolDown-Time");
                    if (cooldown.containsKey(sender.getName())) {
                        long secondsLeft = ((cooldown.get(sender.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        if (secondsLeft > 0) {
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PlayerFireworks.CoolDown-Message").replace("%time%", String.valueOf(secondsLeft))));
                            return true;
                        }
                    }

                    FireworkRandom.fireworkRandom(user);

                    if (!(user.hasPermission("playerfireworks.launch.notifyexempt")))
                    {
                        user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PlayerFireworks.Message")));
                    }
                    if (!(user.hasPermission("playerfireworks.cooldown.bypass")))
                    {
                        cooldown.put(sender.getName(), System.currentTimeMillis());
                    }
                    return true;
                }
            }
            user.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PlayerFireworks.NoAccessMessage")));
        }
        return true;
    }
}

