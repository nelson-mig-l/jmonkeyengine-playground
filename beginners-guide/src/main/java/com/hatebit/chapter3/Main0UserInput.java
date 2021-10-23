package com.hatebit.chapter3;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.*;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Main0UserInput extends SimpleApplication {

    private final static Trigger TRIGGER_COLOR = new KeyTrigger(KeyInput.KEY_SPACE);
    private final static Trigger TRIGGER_COLOR_ALT = new KeyTrigger(KeyInput.KEY_C);
    private final static Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);

    private final static String MAPPING_COLOR = "Toggle Color";
    private final static String MAPPING_ROTATE = "Rotate";

    private Geometry geom;

    public static void main(String[] args) {
        final Main0UserInput app = new Main0UserInput();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        inputManager.deleteMapping(INPUT_MAPPING_EXIT); // Key_ESCAPE
        inputManager.deleteMapping(INPUT_MAPPING_CAMERA_POS); // Key_C
        inputManager.deleteMapping(INPUT_MAPPING_MEMORY); // Key_M

        inputManager.addMapping(MAPPING_COLOR, TRIGGER_COLOR, TRIGGER_COLOR_ALT);
        inputManager.addMapping(MAPPING_ROTATE, TRIGGER_ROTATE);

        inputManager.addListener(actionListener, MAPPING_COLOR);
        inputManager.addListener(analogListener, MAPPING_ROTATE);

        final Box b = new Box(1, 1, 1);
        geom = new Geometry("Box", b);
        final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
    }

    private ActionListener actionListener = (name, isPressed, tpf) -> {
        System.out.println("You triggered: " + name);
        if (name.equals(MAPPING_COLOR) && !isPressed) {
            geom.getMaterial().setColor("Color", ColorRGBA.randomColor());
        }
    };

    private AnalogListener analogListener = (name, intensity, tpf) -> {
        System.out.println("You triggered: " + name);
        if (name.equals(MAPPING_ROTATE)) {
            geom.rotate(0, intensity, 0); // rotate around Y axis
        }
    };
}
