package me.Sebbben.AltarCrafting;

abstract class AltarFeature {
    AltarManager altarManager;
    public AltarFeature() {
        this.altarManager = Main.getInstance().getAltarManager();
        this.altarManager.registerAltarFeature(this.getName(), this);
    }

    abstract String getName();

}
