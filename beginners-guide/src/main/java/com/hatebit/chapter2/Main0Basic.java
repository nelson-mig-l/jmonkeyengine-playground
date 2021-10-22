package com.hatebit.chapter2;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Main0Basic extends SimpleApplication {

    public static void main(String[] args) {
        final Main0Basic app = new Main0Basic();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        final Box b = new Box(1, 1, 1);
        final Geometry geom = new Geometry("Box", b);
        final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
    }
}
