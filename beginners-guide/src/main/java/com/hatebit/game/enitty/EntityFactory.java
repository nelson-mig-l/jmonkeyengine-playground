package com.hatebit.game.enitty;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

public class EntityFactory {

    private final AssetManager assetManager;

    public EntityFactory(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Floor floor() {
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Orange);
        return new Floor(material);
    }

    public Player player(final int x, final int z) {
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Yellow);
        return new Player(x, z, material);
    }

    public Tower tower(final int x, final int z) {
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Green);
        final Tower tower = new Tower(x, z, material);
        return tower;
    }

    public Creep creep(final float x, final float z) {
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Black);
        final Creep creep = new Creep(x, z, material);
        return creep;
    }

}
