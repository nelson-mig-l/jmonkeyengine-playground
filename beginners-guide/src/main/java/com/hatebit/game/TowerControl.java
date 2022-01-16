package com.hatebit.game;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class TowerControl extends AbstractControl {

    private final GamePlayAppState state;

    private float timer;

    public TowerControl(final GamePlayAppState state) {
        this.state = state;
    }

    @Override
    protected void controlUpdate(final float tpf) {

    }

    @Override
    protected void controlRender(final RenderManager renderManager, final ViewPort viewPort) {

    }

    //  (index, height, and ChargesNum). I
    public int getIndex() {
        return spatial.getUserData("index");
    }

    public int getChargesNum() {
        return spatial.getUserData("chargesNum");
    }

    public int getHeight() {
        return spatial.getUserData("height");
    }
}
