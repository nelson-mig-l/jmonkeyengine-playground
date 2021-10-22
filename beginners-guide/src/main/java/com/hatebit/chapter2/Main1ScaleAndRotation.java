package com.hatebit.chapter2;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Main1ScaleAndRotation extends SimpleApplication {

    public static void main(String[] args) {
        final Main1ScaleAndRotation app = new Main1ScaleAndRotation();
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
        blueCube.setLocalScale(0.5f);

        final Geometry yellowCube = createCube(1, yellowMaterial);
        yellowCube.scale(2.0f);
        yellowCube.setLocalTranslation(new Vector3f(1.0f , 0.0f , -2.0f));
        yellowCube.rotate(FastMathUtils.toRadians(45), 0, 0);

        final Quaternion roll = new Quaternion();
        roll.fromAngleAxis(FastMathUtils.toRadians(45), Vector3f.UNIT_Y);
        blueCube.setLocalRotation(roll);

        rootNode.attachChild(blueCube);
        rootNode.attachChild(yellowCube);
    }

    private Geometry createCube(final int size, final Material material) {
        final Box box = new Box(size, size, size);
        final Geometry geometry = new Geometry("Box", box);
        geometry.setMaterial(material);
        return geometry;
    }
}
