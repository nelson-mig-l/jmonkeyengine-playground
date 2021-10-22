package com.hatebit.game;

import com.hatebit.game.enitty.EntityFactory;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

import java.util.Random;

public class Main extends SimpleApplication  {

    private static final int CREEP_COUNT = 6;

    public static void main(String[] args) {
        final Main app = new Main();

        app.setSettings(Settings.defaultSettings());
        app.setPauseOnLostFocus(true);
        app.setShowSettings(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        setDisplayFps(true);
        setDisplayStatView(false);

        viewPort.setBackgroundColor(ColorRGBA.White);

        cam.setLocation(new Vector3f(0, 8, 18f));
        cam.lookAt(new Vector3f(0, 0, 6f), Vector3f.UNIT_Y);
        flyCam.setEnabled(true);

        final Random random = new Random();
        final EntityFactory factory = new EntityFactory(assetManager);
        rootNode.attachChild(factory.floor());
        rootNode.attachChild(factory.player(0, 0));
        rootNode.attachChild(factory.tower(-4, 3));
        rootNode.attachChild(factory.tower(+4, 3));
        for (int c = 0; c < CREEP_COUNT; c++) {
            final float x = random.nextInt(10) - 5;
            final float z = random.nextInt(10) - 5 + 10;
            rootNode.attachChild(factory.creep(x, z));
        }
    }


}
