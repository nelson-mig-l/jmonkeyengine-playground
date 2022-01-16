package com.hatebit.chapter4;

import com.hatebit.utils.CubeUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.ui.Picture;

public class Main5SimpleUI extends SimpleApplication {

    public static void main(String[] args) {
        final Main5SimpleUI app = new Main5SimpleUI();
        app.start();
    }

    private BitmapText distanceText;

    private Picture faceIcon;

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



        faceIcon = new Picture("User interface frame");
        faceIcon.setImage(assetManager, "icons/smiley.png", true);
        faceIcon.move(settings.getWidth()/2-265, 0, -2);
        faceIcon.setWidth(64);
        faceIcon.setHeight(64);
        guiNode.attachChild(faceIcon);

    }

    public void simpleUpdate(float tpf) {
        final float distance = Vector3f.ZERO.distance(cam.getLocation());
        distanceText.setText("Distance: " + Math.rint(distance));
        // this done "by the book" but having this kind of logic (loading assets)
        // on every frame has heavy hit on performance
        if (distance < 8) {
            faceIcon.setImage(assetManager, "icons/unamused.png", true);
        }
        if (distance > 9) {
            faceIcon.setImage(assetManager, "icons/smiley.png", true);
        }
    }
}
