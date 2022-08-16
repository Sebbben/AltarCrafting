package me.Sebbben.AltarCrafting.AltarActions;

import org.bukkit.entity.Player;

public interface AltarAction {
    void finish(Player player);
    void cancel(Player player);
}
