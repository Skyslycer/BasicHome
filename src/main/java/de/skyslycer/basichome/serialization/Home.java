package de.skyslycer.basichome.serialization;

import org.bukkit.Location;

public class Home {

    private final Location location;
    private final boolean publicHome;

    public Home(Location location, boolean publicHome) {
        this.location = location;
        this.publicHome = publicHome;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isPublicHome() {
        return publicHome;
    }

}
