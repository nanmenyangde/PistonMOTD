# PistonMOTD

[![Discord embed](https://discordapp.com/api/guilds/739784741124833301/embed.png)](https://discord.gg/CDrcxzH)
[![Build Status](https://ci.codemc.io/buildStatus/icon?job=AlexProgrammerDE%2FPistonMOTD)](https://ci.codemc.io/job/AlexProgrammerDE/job/PistonMOTD/)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/AlexProgrammerDE/PistonMOTD)](https://github.com/AlexProgrammerDE/PistonBot/releases)

**Best MOTD plugin with multi-platform support!**

## Features

* Customize player-counter tooltip
* Customize the online player and max player counter
* Random MOTD
* A custom client out of date message. You can force that to always happen too.
* Random favicon
* Placeholders (extendable via api)
* Hide your player-count (only on bukkit)
* Very easy to understand config
* Modular (You can enable/disable every feature)

Compatible with external MOTD plugins. (Can't promise it will work with every MOTD plugin.)

## API

We got an easy to integrate API for adding additional placeholders into the plugin.

Repository:

```xml
  <repositories>
      <repository>
          <id>codemc-repo</id>
          <url>https://repo.codemc.org/repository/maven-public/</url>
      </repository>
  </repositories>
```

Dependency:

```xml
  <dependencies>
    <dependency>
      <groupId>net.pistonmaster</groupId>
      <artifactId>pistonmotd-api</artifactId>
      <version>4.3.2</version>
    </dependency>
  </dependencies>
```

## Links

* Website: https://www.pistonmaster.net/PistonMOTD/
* SpigotMC: https://www.spigotmc.org/resources/80567/
* Ore: https://ore.spongepowered.org/AlexProgrammerDE/PistonMOTD/
* MCPlugins: https://mcplugins.io/resources/78/
* Github: https://github.com/AlexProgrammerDE/PistonMOTD/
* Discord: https://discord.gg/CDrcxzH
* Jitpack: https://jitpack.io/#AlexProgrammerDE/PistonMOTD/