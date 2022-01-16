package com.hatebit.utils;

import com.jme3.anim.AnimComposer;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class ListAnims extends SimpleApplication {

    public static void main(String[] args) {
        final ListAnims app = new ListAnims();
        app.setShowSettings(false);
        app.start();
    }

    public void simpleInitApp() {
        final Spatial model = assetManager.loadModel("models/jaime/Jaime.j3o");
        model.setLocalScale(0.01f);
        rootNode.attachChild(model);

        final AnimControl control = model.getControl(AnimControl.class);
        if (control == null) {
            System.out.println("No AnimControl");
        } else {
            control.getAnimationNames().forEach(System.out::println);
        }

        final AnimComposer composer = model.getControl(AnimComposer.class);
        if (composer == null) {
            System.out.println("No AnimComposer");
        } else {
            composer.getAnimClipsNames().forEach(System.out::println);
        }

        final DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }
}
