package com.hatebit.game.enitty;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Tower extends Node {

    public static final float HEIGHT = 4.0f;

    Tower(final int x, final int z, final Material material) {
        final Box mesh = new Box(0.5f, HEIGHT, 0.5f);
        final Geometry geometry = new Geometry(generateName(x, z), mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(x, HEIGHT, z);
        attachChild(geometry);
    }

    private String generateName(final int x, final int z) {
        return "Tower@(" + x + "," + z + ")";
    }
}
