package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.managers.AltarBlueprintsManager;

public abstract class AltarFeature {
    AltarBlueprintsManager altarBlueprintsManager;
    public AltarFeature() {
        this.altarBlueprintsManager = Main.getInstance().getAltarBlueprintsManager();
    }

    public abstract String getName();

}
