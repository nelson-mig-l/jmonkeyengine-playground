package com.hatebit.chapter4;

import com.hatebit.utils.CubeUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.ui.Picture;

public class Main6UI3D extends SimpleApplication {

    public static void main(String[] args) {
        final Main6UI3D app = new Main6UI3D();
        app.start();
    }

    private BitmapText distanceText;

    @Override
    public void simpleInitApp() {
        setDisplayStatView(false);
        setDisplayFps(false);

        final Geometry cube = new CubeUtils(assetManager)
                .createCube("cube", Vector3f.ZERO, ColorRGBA.Cyan);
        rootNode.attachChild(cube);

        //guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        guiFont = assetManager.loadFont("fonts/AcmeFont.fnt");
        distanceText = new BitmapText(guiFont);
        distanceText.setSize(guiFont.getCharSet().getRenderedSize());
        distanceText.move(
                settings.getWidth() / 2, // X
                distanceText.getLineHeight(), // Y
                0); // Z (depth layer)
        distanceText.setColor(ColorRGBA.Yellow);
        guiNode.attachChild(distanceText);


        final Geometry geom = new CubeUtils(assetManager)
                .createCube("ui", Vector3f.ZERO, ColorRGBA.Brown);
        geom.setLocalTranslation(
                settings.getWidth()/2,
                settings.getHeight()/2,
                0); // center the box
        geom.scale(10f); // scale the box
        guiNode.attachChild(geom);
    }

    public void simpleUpdate(float tpf) {
        final float distance = Vector3f.ZERO.distance(cam.getLocation());
        distanceText.setText("Distance: " + Math.rint(distance));

    }
}
