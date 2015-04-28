package com.jakeyray18;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

/**
 * Created by Jakeyray18 on 29/09/2014.
 */
public class FireworkRandom
{

    public static Firework fireworkRandom(Player user)
    {

        Firework fw = (Firework) user.getWorld().spawnEntity(user.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        Random r = new Random();
        int rt = r.nextInt(5) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
        if (rt == 3) type = FireworkEffect.Type.BURST;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;
        if (rt == 5) type = FireworkEffect.Type.STAR;
        int red = r.nextInt(256);
        int blue = r.nextInt(256);
        int green = r.nextInt(256);
        Color c1 = Color.fromRGB(red, green, blue);
        red = r.nextInt(256);
        blue = r.nextInt(256);
        green = r.nextInt(256);
        Color c2 = Color.fromRGB(red, green, blue);
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        fw.setFireworkMeta(fwm);

        return fw;

    }

}
