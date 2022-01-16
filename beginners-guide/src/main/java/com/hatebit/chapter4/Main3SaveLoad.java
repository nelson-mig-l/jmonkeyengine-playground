package com.hatebit.chapter4;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetNotFoundException;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main3SaveLoad extends SimpleApplication {

    public static void main(String[] args) {
        new Main3SaveLoad().start();
    }

    @Override
    public void simpleInitApp() {
        load();
        final Spatial model = assetManager.loadModel("models/mymodel/MyModel.j3o");
        model.move(
                FastMath.nextRandomFloat() * 10 - 5,
                FastMath.nextRandomFloat() * 10 - 5,
                FastMath.nextRandomFloat() * 10 - 5);
        rootNode.attachChild(model);

        final DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }

    @Override
    public void stop() {
        save();
        super.stop();
    }

    private void load() {
        assetManager.registerLocator("./", FileLocator.class);
        try {
            final Spatial spatial = assetManager.loadModel("savedgame.j3o");
            rootNode.attachChild(spatial);
        } catch (AssetNotFoundException ignore) {}
    }

    private void save() {
        final File file = new File("savedgame.j3o");
        final BinaryExporter exporter = BinaryExporter.getInstance();
        try {
            exporter.save(rootNode, file);
        } catch (IOException ex) {
            Logger.getLogger(Main3SaveLoad.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
        }
    }
}
