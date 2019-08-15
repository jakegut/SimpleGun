package com.borgdude.simplegun;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.borgdude.paintball.objects.Gun;

public class TestGun extends Gun {

    private final SimpleGun plugin;

    public TestGun(SimpleGun plugin) {
        this.plugin = plugin;
    }

    @Override
    public void fire(Player p) {

        Snowball ball = p.launchProjectile(Snowball.class);
        Vector vel = p.getLocation().getDirection().multiply(1.5);
        ball.setVelocity(vel);

        p.getLocation().getWorld().playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2, 1.2f);

    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public ItemStack getInGameItem() {
        ItemStack is = new ItemStack(Material.STICK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.LIGHT_PURPLE + "PaintBall TestGun");
        is.setItemMeta(im);
        return is;
    }

    @Override
    public ItemStack getLobbyItem() {
        ItemStack is = new ItemStack(Material.LIME_WOOL);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "TestGun");
        is.setItemMeta(im);
        return is;
    }

    @Override
    public String getName() {
        return "TestGun";
    }

    @Override
    public void onHit(Player player, Snowball ball, Block block, Entity entity) {
        if(entity == null) return;
        FireworkEffect effect = FireworkEffect.builder().trail(false).flicker(false).withColor(Color.AQUA)
                .with(FireworkEffect.Type.STAR).build();
        Firework fw = entity.getWorld().spawn(entity.getLocation(), Firework.class);
        FireworkMeta meta = fw.getFireworkMeta();
        meta.clearEffects();
        meta.addEffect(effect);
        meta.setPower(0);
        fw.setFireworkMeta(meta);
        new BukkitRunnable() {

            @Override
            public void run() {
                fw.detonate();
            }
            
        }.runTaskLater(plugin, 2);
    }

}
