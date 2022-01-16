package com.hatebit.game;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;


public class Main extends SimpleApplication {

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

        stateManager.attach(new GamePlayAppState());
    }

}
