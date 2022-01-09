package de.skyslycer.basichome;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import de.skyslycer.basichome.commands.DeleteHomeCommand;
import de.skyslycer.basichome.commands.HomeCommand;
import de.skyslycer.basichome.commands.SetHomeCommand;
import de.skyslycer.basichome.serialization.Players;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BasicHome extends JavaPlugin {

    private static final Path dataFolder = Path.of("plugins", "BasicHome");
    private static final Path jsonFile = dataFolder.resolve("players.json");

    private Players playerData;

    @Override
    public void onEnable() {
        playerData = load();

        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("deletehome").setExecutor(new DeleteHomeCommand(this));
    }

    @Override
    public void onDisable() {
        save();
    }

    private Players load() { // Loads the data
        try {
            if (!Files.exists(jsonFile)) {
                Files.createDirectories(jsonFile.getParent());
                Files.createFile(jsonFile);
            }

            var players = new Gson().fromJson(Files.readString(jsonFile), Players.class); // Gson#fromJson loads the player data from Json and Files.readString reads it
            return (players == null ? new Players() : players);
        } catch (IOException | JsonSyntaxException exception) {
            this.getLogger().warning("Couldn't load player homes! Please check the stacktrace below:");
            exception.printStackTrace();
            return null;
        }
    }

    public void save() { // Saves the data
        try {
            if (!Files.exists(jsonFile)) {
                Files.createDirectories(jsonFile.getParent());
                Files.createFile(jsonFile);
            }

            var gson = new GsonBuilder().create();
            Files.writeString(jsonFile, gson.toJson(playerData)); // Gson#toJson converts the class into Json and Files.writeString writes it
        } catch (IOException | JsonSyntaxException exception) {
            this.getLogger().warning("Couldn't load player homes! Please check the stacktrace below:");
            exception.printStackTrace();
        }
    }

    public Players getPlayerData() {
        return playerData;
    }

}
