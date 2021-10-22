package com.hatebit.game.enitty;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Floor extends Geometry {

    Floor(final Material material) {
        super("Floor", new Box(33, 0.1f, 33));
        setLocalTranslation(0, -0.1f, 0);
        setMaterial(material);
    }

}
