package de.skyslycer.basichome.serialization;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public class SerializableLocation {

    private String world;
    private double x;
    private double y;
    private double z;

    public SerializableLocation(String world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Location toBukkitLocation() {
        var world = Bukkit.getServer().getWorld(getWorld());

        if (world == null) {
            return null;
        }

        return new Location(world, getX(), getY(), getZ());
    }

    public static SerializableLocation fromLocation(Location location) {
        return new SerializableLocation(location.getWorld().getName(), location.getX(), location.getY(), location.getZ());
    }

    public static SerializableLocation fromPlayer(Player player) {
        return fromLocation(player.getLocation());
    }

}
