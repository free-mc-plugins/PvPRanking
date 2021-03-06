package com.ericlam.mc.ranking.defaultdatahandle;

import com.ericlam.mc.ranking.api.DataHandler;
import com.ericlam.mc.ranking.api.PlayerData;
import org.bukkit.OfflinePlayer;

import javax.annotation.Nonnull;
import java.util.TreeSet;
import java.util.UUID;

public class DefaultDataHandler extends DataHandler {

    @Override
    public PlayerData getPlayerData(UUID playerUniqueId) {
        return DefaultDataManager.getInstance().findData(playerUniqueId);
    }

    @Override
    public TreeSet<PlayerData> getAllPlayerData() {
        return new TreeSet<PlayerData>(DefaultDataManager.getInstance().getAllData());
    }

    @Override
    public void savePlayerData(UUID playerUniqueId) {
        DefaultDataManager.getInstance().saveData(playerUniqueId);
    }

    @Override
    public String[] showPlayerData(@Nonnull OfflinePlayer player) {
        DefaultData data = DefaultDataManager.getInstance().findData(player.getUniqueId());
        String line1 = "§ePlayer: §f" + player.getName();
        String line2 = "§eKills: §f" + data.getKills();
        String line3 = "§eDeaths: §f" + data.getDeaths();
        String line4 = "§ePlayed: §f" + data.getPlays();
        String line5 = "§eScore: §f" + data.getFinalScores();
        return new String[]{line1, line2, line3, line4, line5};
    }

    @Override
    public boolean removePlayerData(@Nonnull OfflinePlayer player) {
        return DefaultDataManager.getInstance().removeData(player.getUniqueId());
    }

    @Override
    public void saveAllPlayerData() {
        DefaultDataManager.getInstance().saveData();
    }
}
