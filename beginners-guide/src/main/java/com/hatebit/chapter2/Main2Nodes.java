package com.hatebit.chapter2;

import com.hatebit.utils.FastMathUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Main2Nodes extends SimpleApplication {

    public static void main(String[] args) {
        final Main2Nodes app = new Main2Nodes();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        final Material blueMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blueMaterial.setColor("Color", ColorRGBA.Blue);

        final Material yellowMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        yellowMaterial.setColor("Color", ColorRGBA.Yellow);

        final Geometry blueCube = createCube(1, blueMaterial);
        final Geometry yellowCube = createCube(1, yellowMaterial);
        yellowCube.setLocalTranslation(new Vector3f(1.0f , 0.0f , -2.0f));

        final Node pivot = new Node("pivot node");
        pivot.attachChild(blueCube);
        pivot.attachChild(yellowCube);
        pivot.rotate(0, 0, FastMathUtils.toRadians(45));
        rootNode.attachChild(pivot);
    }

    private Geometry createCube(final int size, final Material material) {
        final Box box = new Box(size, size, size);
        final Geometry geometry = new Geometry("Box", box);
        geometry.setMaterial(material);
        return geometry;
    }
}
