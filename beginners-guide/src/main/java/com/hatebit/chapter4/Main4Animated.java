package com.hatebit.chapter4;

import com.hatebit.utils.FastMathUtils;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.Animation;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import org.lwjgl.Sys;

public class Main4Animated extends SimpleApplication implements AnimEventListener {

    private static final String ANI_IDLE = "Idle";
    private static final String ANI_WALK = "Walk";
    private static final String ANI_RUN = "Run";

    private static final String MAPPING_WALK = "walk forward";
    private static final String MAPPING_RUN = "run forward";

    public static void main(String[] args) {
        new Main4Animated().start();
    }

    private AnimChannel channel;
    private Spatial player;

    @Override
    public void simpleInitApp() {
        // needed to change paths in Jaime.j3m file
        player = assetManager.loadModel("models/jaime/Jaime.j3o");
        player.rotate(0, FastMathUtils.toRadians(180), 0);
        rootNode.attachChild(player);

        final AnimControl control = player.getControl(AnimControl.class);

        final Animation run = control.getAnim(ANI_WALK).clone();
        run.setLength(run.getLength() / 2);
        run.setName("Run");
        control.addAnim(run);

        control.getAnimationNames().forEach(System.out::println);
        control.addListener(this);
        channel = control.createChannel();
        channel.setAnim(ANI_IDLE);

        final DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        inputManager.addMapping(MAPPING_WALK, new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping(MAPPING_RUN, new KeyTrigger(KeyInput.KEY_LSHIFT));
        inputManager.addListener(actionListener, MAPPING_WALK);
        inputManager.addListener(actionListener, MAPPING_RUN);
        inputManager.addListener(analogListener, MAPPING_WALK);
    }

    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(MAPPING_WALK) && isPressed) {
                if (!channel.getAnimationName().equals(ANI_WALK)) {
                    channel.setAnim(ANI_WALK);
                }
            }
            if (name.equals(MAPPING_RUN) && isPressed) {
                if (!channel.getAnimationName().equals(ANI_RUN)) {
                    channel.setAnim(ANI_RUN);
                }
            }
            if (!isPressed) {
                channel.setAnim(ANI_IDLE);
            }
        }
    };

    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float intensity, float tpf) {
            if (name.equals(MAPPING_WALK)) {
                player.move(0, 0, tpf);
            }
        }
    };

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (animName.equals(ANI_WALK)) {
            System.out.println(control.getSpatial().getName() + " completed one walk loop.");
        } else if (animName.equals(ANI_IDLE)) {
            System.out.println(control.getSpatial().getName() + " completed one idle loop.");
        } else if (animName.equals(ANI_RUN)) {
            System.out.println(control.getSpatial().getName() + " completed one run loop.");
        }

    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        if (animName.equals(ANI_WALK)) {
            System.out.println(control.getSpatial().getName() + " started walking.");
        } else if (animName.equals(ANI_IDLE)) {
            System.out.println(control.getSpatial().getName() + " started being idle.");
        } else if (animName.equals(ANI_RUN)) {
            System.out.println(control.getSpatial().getName() + " started running.");
        }
    }

}
