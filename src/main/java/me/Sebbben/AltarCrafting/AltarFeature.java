package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.managers.AltarManager;

public abstract class AltarFeature {
    AltarManager altarManager;
    public AltarFeature() {
        this.altarManager = Main.getInstance().getAltarManager();
    }

    public abstract String getName();

}
