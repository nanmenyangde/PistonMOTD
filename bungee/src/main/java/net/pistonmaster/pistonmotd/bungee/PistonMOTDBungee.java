package net.pistonmaster.pistonmotd.bungee;

import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.pistonmaster.pistonmotd.api.PlaceholderUtil;
import net.pistonmaster.pistonmotd.shared.PistonMOTDPlugin;
import net.pistonmaster.pistonmotd.shared.PlayerWrapper;
import net.pistonmaster.pistonmotd.shared.StatusFavicon;
import net.pistonmaster.pistonmotd.shared.utils.LuckPermsWrapper;
import org.bstats.bungeecord.Metrics;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PistonMOTDBungee extends Plugin implements PistonMOTDPlugin {
    protected LuckPermsWrapper luckpermsWrapper = null;

    @Override
    public void onEnable() {
        BungeeAudiences.create(this);

        logName();

        loadConfig();

        registerCommonPlaceholder();
        for (String server : getProxy().getServers().keySet()) {
            PlaceholderUtil.registerParser(new ServerPlaceholder(server));
        }

        startup("Looking for hooks");
        if (getProxy().getPluginManager().getPlugin("LuckPerms") != null) {
            try {
                startup("Hooking into LuckPerms");
                luckpermsWrapper = new LuckPermsWrapper();
            } catch (Exception ignored) {
            }
        }

        startup("Registering listeners");
        getProxy().getPluginManager().registerListener(this, new PingEvent(this));

        startup("Registering command");
        getProxy().getPluginManager().registerCommand(this, new BungeeCommand(this));

        checkUpdate();

        startup("Loading metrics");
        new Metrics(this, 8968);

        startup("Done! :D");
    }

    @Override
    public void onDisable() {
        startup("Unloading the listeners");
        getProxy().getPluginManager().unregisterListeners(this);

        startup("Unloading the commands");
        getProxy().getPluginManager().unregisterCommands(this);

        startup("Finished unloading!");
    }

    @Override
    public StatusFavicon createFavicon(Path path) throws Exception {
        return new StatusFavicon(Favicon.create(ImageIO.read(Files.newInputStream(path))));
    }

    @Override
    public Path getPluginConfigFile() {
        return getDataFolder().toPath().resolve("config.yml");
    }

    @Override
    public Path getFaviconFolder() {
        return getDataFolder().toPath().resolve("favicons");
    }

    @Override
    public InputStream getDefaultConfig() {
        return getResourceAsStream("config.yml");
    }

    @Override
    public List<PlayerWrapper> getPlayers() {
        return getProxy().getPlayers().stream().map(this::wrap).collect(Collectors.toList());
    }

    @Override
    public int getMaxPlayers() {
        return getProxy().getConfig().getPlayerLimit();
    }

    @Override
    public int getPlayerCount() {
        return getProxy().getOnlineCount();
    }

    private PlayerWrapper wrap(ProxiedPlayer player) {
        return new PlayerWrapper() {
            @Override
            public String getDisplayName() {
                return player.getDisplayName();
            }

            @Override
            public String getName() {
                return player.getName();
            }

            @Override
            public UUID getUniqueId() {
                return player.getUniqueId();
            }
        };
    }

    @Override
    public String getVersion() {
        return getDescription().getVersion();
    }

    @Override
    public void info(String message) {
        getLogger().info(message);
    }

    @Override
    public void warn(String message) {
        getLogger().warning(message);
    }

    @Override
    public void error(String message) {
        getLogger().severe(message);
    }
}
