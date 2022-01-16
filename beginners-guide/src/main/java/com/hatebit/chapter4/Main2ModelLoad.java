package com.hatebit.chapter4;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Main2ModelLoad extends SimpleApplication {

    public static void main(String[] args) {
        final Main2ModelLoad app = new Main2ModelLoad();
        app.setShowSettings(false);
        app.start();
    }

    public void simpleInitApp() {
        final Spatial model = assetManager.loadModel("models/mymodel/MyModel.mesh.xml");
        //final Spatial model = assetManager.loadModel("models/MyModel.j3o");
        rootNode.attachChild(model);

        final DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }
}
