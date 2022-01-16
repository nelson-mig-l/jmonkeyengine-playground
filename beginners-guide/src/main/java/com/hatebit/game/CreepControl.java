package com.hatebit.game;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

public class CreepControl extends AbstractControl {

    private final GamePlayAppState state;

    public CreepControl(final GamePlayAppState state) {
        this.state = state;
    }

    @Override
    protected void controlUpdate(final float tpf) {
        if (getHealth() > 0) {
            spatial.move(0, 0, -tpf);
            if (spatial.getLocalTranslation().getZ() >= 0) {
                state.removeHealth(1);
                remove();
            }
        } else {
            state.addBudget(1);
            remove();
        }
    }

    @Override
    protected void controlRender(final RenderManager renderManager, final ViewPort viewPort) {

    }

    public int getIndex() {
        return spatial.getUserData("index");
    }

    public int getHealth() {
        return spatial.getUserData("health");
    }

    private void remove() {
        spatial.removeFromParent();
        spatial.removeControl(this);
    }

}
