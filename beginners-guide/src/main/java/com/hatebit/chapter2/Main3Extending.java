package com.hatebit.chapter2;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

import java.awt.*;

public class Main3Extending extends SimpleApplication {

    public static void main(String[] args) {
        final Main3Extending app = new Main3Extending();


        final AppSettings settings = new AppSettings(true);
        settings.setResolution(640, 480);
        settings.setDepthBits(24);
        settings.setFullscreen(false); // device.isFullScreenSupported()
        settings.setTitle("monkey business");

        app.setSettings(settings);
        app.setPauseOnLostFocus(true);
        app.setShowSettings(false);

        app.start();
    }

    @Override
    public void simpleInitApp() {
        setDisplayFps(true);
        setDisplayStatView(false);

        final Box b = new Box(1, 1, 1);
        final Geometry geom = new Geometry("Box", b);
        final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
    }

    private static AppSettings createAppSettings() {
        final AppSettings settings = new AppSettings(true);
        final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final DisplayMode[] modes = device.getDisplayModes();
        final DisplayMode mode = modes[0];
        settings.setResolution(mode.getWidth(), mode.getHeight());
        settings.setFrequency(mode.getRefreshRate());
        settings.setDepthBits(mode.getBitDepth());
        settings.setFullscreen(device.isFullScreenSupported());
        settings.setTitle("monkey business");
        return settings;
    }
}
