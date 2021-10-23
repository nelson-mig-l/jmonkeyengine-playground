package com.hatebit.utils;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;

public class CubeUtils {

    private static final Mesh CUBE_MESH = new Box(1, 1, 1);

    private final AssetManager assetManager;

    public CubeUtils(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Geometry createCube(final String name, final Vector3f position, final ColorRGBA color) {
        final Geometry geometry = new Geometry(name, CUBE_MESH);
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(position);
        return geometry;
    }

}
