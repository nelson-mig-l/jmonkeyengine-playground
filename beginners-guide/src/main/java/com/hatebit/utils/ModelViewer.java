package com.hatebit.utils;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class ModelViewer extends SimpleApplication {

    public static void main(String[] args) {
        new ModelViewer().start();
    }

    private Spatial model;

    @Override
    public void simpleInitApp() {
        model = assetManager.loadModel("textures/car/car.obj");
        //player.rotate(0, FastMathUtils.toRadians(180), 0);
        rootNode.attachChild(model);

        final DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }

    @Override
    public void simpleUpdate(final float tpf) {
        model.rotate(0 * tpf, 0.3f * tpf, 0 * tpf);
    }

}
