package de.skyslycer.basichome.serialization;

import org.bukkit.Location;

public class Home {

    private final SerializableLocation location;
    private final boolean publicHome;

    public Home(SerializableLocation location, boolean publicHome) {
        this.location = location;
        this.publicHome = publicHome;
    }

    public SerializableLocation getLocation() {
        return location;
    }

    public boolean isPublicHome() {
        return publicHome;
    }

}
