package com.extendedclip.papi.expansion.dynmap;

import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.dynmap.DynmapAPI;

import java.util.HashMap;
import java.util.Map;

public class DynmapExpansion extends PlaceholderExpansion implements Cacheable, Configurable {

    private DynmapAPI dynmap;

    private final String VERSION = getClass().getPackage().getImplementationVersion();

    @Override
    public boolean register() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(getPlugin());
        if (plugin == null) return false;
        dynmap = (DynmapAPI) plugin;
        return super.register();
    }

    @Override
    public String getIdentifier() {
        return "dynmap";
    }

    @Override
    public String getPlugin() {
        return "dynmap";
    }

    @Override
    public String getAuthor() {
        return "clip";
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public String onPlaceholderRequest(Player player, String param) {

        boolean visible = dynmap.getPlayerVisbility(player);
        Location loc = player.getLocation();
        switch (param.toLowerCase()) {
            case "x":
                return visible ? String.valueOf(loc.getBlockX()) : getString("hidden.x", "");
            case "y":
                return visible ? String.valueOf(loc.getBlockY()) : getString("hidden.y", "");
            case "z":
                return visible ? String.valueOf(loc.getBlockZ()) : getString("hidden.z", "");
            case "world":
                return visible ? String.valueOf(loc.getWorld().getName()) : getString("hidden.world", "");
        }
        return null;
    }

    @Override
    public void clear() {
        dynmap = null;
    }

    @Override
    public Map<String, Object> getDefaults() {
        Map<String, Object> def = new HashMap<>();
        def.put("hidden.x", "hidden");
        def.put("hidden.y", "hidden");
        def.put("hidden.z", "hidden");
        def.put("hidden.world", "unknown");
        return def;
    }
}
