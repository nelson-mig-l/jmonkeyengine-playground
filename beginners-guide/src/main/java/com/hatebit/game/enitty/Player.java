package com.hatebit.game.enitty;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Player extends Node {

    public static final float HEIGHT = 1f;

    Player(final int x, final int z, final Material material) {
        final Geometry geometry = new Geometry("Player", new Box(10, HEIGHT, 2));
        geometry.setMaterial(material);
        geometry.setLocalTranslation(x, HEIGHT, z);
        attachChild(geometry);
    }
}
