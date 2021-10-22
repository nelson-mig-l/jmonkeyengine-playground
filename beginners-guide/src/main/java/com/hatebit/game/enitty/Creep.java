package com.hatebit.game.enitty;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Creep extends Node {

    public static final float SIZE = 0.25f;

    Creep(final float x, final float z, final Material material) {
        final Box mesh = new Box(SIZE, SIZE, SIZE);
        final Geometry geometry = new Geometry(generateName(x, z), mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(x, SIZE, z);
        attachChild(geometry);
    }

    private String generateName(final float x, final float z) {
        return "Creep@(" + Math.round(x) + "," + Math.round(z) + ")";
    }
}
