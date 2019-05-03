package com.ericlam.mc.ranking.bukkit;

import com.ericlam.mc.rankcal.RankDataManager;
import com.ericlam.mc.ranking.DefaultData;
import com.ericlam.mc.ranking.api.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class RankingListeners implements Listener {

    private Plugin plugin;

    public RankingListeners(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        new DataLoadRunnable(uuid).runTaskAsynchronously(plugin);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        new DataSaveRunnable(uuid).runTaskAsynchronously(plugin);
    }

    @EventHandler
    public void onPlayerKills(PlayerDeathEvent e){
        Player victim = e.getEntity();
        Player killer = e.getEntity().getKiller();
        PlayerData data = RankDataManager.getInstance().getDataHandler().getPlayerData(victim.getUniqueId());
        if (!(data instanceof DefaultData)) return;
        ((DefaultData) data).addDeaths();
        if (killer == null) return;
        PlayerData kdata = RankDataManager.getInstance().getDataHandler().getPlayerData(killer.getUniqueId());
        ((DefaultData)kdata).addKills();
    }
}
