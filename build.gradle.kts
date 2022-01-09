plugins {
    java
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "de.skyslycer"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18-R0.1-SNAPSHOT")
}

bukkit {
    main = "de.skyslycer.basichome.BasicHome"
    name = "BasicHome"
    author = "Skyslycer"

    commands {
        register("home") {
            aliases = listOf("h")
            description = "Teleport to a home."
        }
        register("sethome") {
            aliases = listOf("sh", "seth", "shome", "createhome", "ch", "chome", "createh")
            description = "Create a home."
        }
        register("deletehome") {
            aliases = listOf("dh", "deleteh", "dhome", "removehome", "rh", "rhome", "removeh", "delhome", "remhome")
            description = "Delete a home."
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}