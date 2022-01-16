package com.hatebit.chapter4;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;


public class Main1WireFrame extends SimpleApplication {

    public static void main(final String[] args) {
        final Main1WireFrame app = new Main1WireFrame();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        final Sphere mesh = new Sphere(16, 32, 1);
        final Geometry geometry = new Geometry("Box", mesh);
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Blue);
        geometry.setMaterial(material);
        rootNode.attachChild(geometry);
        material.getAdditionalRenderState().setWireframe(true);
    }
}
