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
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Main2MousePick extends SimpleApplication {

    private final static Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_RIGHT);

    private final static String MAPPING_ROTATE = "Rotate";

    public static void main(String[] args) {
        final Main2MousePick app = new Main2MousePick();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        inputManager.addMapping(MAPPING_ROTATE, TRIGGER_ROTATE);
        inputManager.addListener(analogListener, MAPPING_ROTATE);

        flyCam.setDragToRotate(true);
        inputManager.setCursorVisible(true);

        final CubeUtils cubeUtils = new CubeUtils(assetManager);

        rootNode.attachChild(cubeUtils.createCube("Red Cube", new Vector3f(0, 1.5f, 0), ColorRGBA.Red));
        rootNode.attachChild(cubeUtils.createCube("Blue Cube", new Vector3f(0, -1.5f, 0), ColorRGBA.Blue));
    }

    private AnalogListener analogListener = (name, intensity, tpf) -> {
        System.out.println("You triggered: " + name);
        if (name.equals(MAPPING_ROTATE)) {
            final Vector2f clicked2d = inputManager.getCursorPosition();
            final Vector3f clicked3d = cam.getWorldCoordinates(new Vector2f(clicked2d.getX(), clicked2d.getY()), 0);
            final Vector3f direction = cam.getWorldCoordinates(new Vector2f(clicked2d.getX(), clicked2d.getY()), 1f).subtract(clicked3d);
            final Ray ray = new Ray(clicked3d, direction);

            final CollisionResults results = new CollisionResults();
            rootNode.collideWith(ray, results);

            if (results.size() > 0) {
                final Geometry target = results.getClosestCollision().getGeometry();
                if (target.getName().equals("Red Cube")) {
                    target.rotate(0, -intensity, 0); // rotate left
                } else if (target.getName().equals("Blue Cube")) {
                    target.rotate(0, intensity, 0); // rotate right
                }
            } else {
                System.out.println("Selection: Nothing");
            }
        }
    };

}
