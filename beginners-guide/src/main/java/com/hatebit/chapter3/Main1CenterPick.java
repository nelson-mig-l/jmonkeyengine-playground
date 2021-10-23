package com.hatebit.chapter3;

import com.hatebit.utils.CubeUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Main1CenterPick extends SimpleApplication {

    private final static Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);

    private final static String MAPPING_ROTATE = "Rotate";

    public static void main(String[] args) {
        final Main1CenterPick app = new Main1CenterPick();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        inputManager.addMapping(MAPPING_ROTATE, TRIGGER_ROTATE);

        inputManager.addListener(analogListener, MAPPING_ROTATE);

        final CubeUtils cubeUtils = new CubeUtils(assetManager);

        rootNode.attachChild(cubeUtils.createCube("Red Cube", new Vector3f(0, 1.5f, 0), ColorRGBA.Red));
        rootNode.attachChild(cubeUtils.createCube("Blue Cube", new Vector3f(0, -1.5f, 0), ColorRGBA.Blue));

        attachCenterMark(cubeUtils);
    }


    private AnalogListener analogListener = (name, intensity, tpf) -> {
        System.out.println("You triggered: " + name);
        if (name.equals(MAPPING_ROTATE)) {
            final CollisionResults results = new CollisionResults();
            final Ray ray = new Ray(cam.getLocation(), cam.getDirection());
            rootNode.collideWith(ray, results);
            results.iterator().forEachRemaining(collision -> {
                float dist = collision.getDistance();
                final Vector3f pt = collision.getContactPoint();
                final String target = collision.getGeometry().getName();
                System.out.println("Sorted Selection: " + target + " at " + pt + ", " + dist + " WU away.");
            });
            if (results.size() > 0) {
                final Geometry target = results.getClosestCollision().getGeometry();
                if (target.getName().equals("Red Cube")) {
                    target.rotate(0, -intensity, 0); // rotate left
                } else if (target.getName().equals("Blue Cube")) {
                    target.rotate(0, intensity, 0); // rotate right
                }
            } else {
                System.out.println("Selection: Nothing" );
            }
        }
    };

    private void attachCenterMark(final CubeUtils cubeUtils) {
        final Geometry geometry = cubeUtils.createCube("center mark", Vector3f.ZERO, ColorRGBA.White);
        geometry.scale(4);
        geometry.setLocalTranslation(settings.getWidth() / 2f, settings.getHeight() / 2f, 0);
        guiNode.attachChild(geometry); // attach to 2D user interface
    }
}
