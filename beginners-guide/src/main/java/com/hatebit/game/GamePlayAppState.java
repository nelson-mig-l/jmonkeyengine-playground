package com.hatebit.game;

import com.hatebit.game.enitty.Creep;
import com.hatebit.game.enitty.EntityFactory;
import com.hatebit.game.enitty.Tower;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;

public class GamePlayAppState extends AbstractAppState {

    private static final int CREEP_COUNT = 6;

    private int level, score, health, budget;
    private boolean lastGameWon;

    private Node rootNode;

    @Override
    public void initialize(final AppStateManager stateManager, final Application app) {
        super.initialize(stateManager, app);
        this.rootNode = ((SimpleApplication) app).getRootNode();

        final AssetManager assetManager = app.getAssetManager();
        final EntityFactory factory = new EntityFactory(assetManager);

        rootNode.attachChild(factory.floor());
        rootNode.attachChild(factory.player(0, 0));
        final Tower tower1 = factory.tower(-4, 3);
        final Tower tower2 = factory.tower(+4, 3);
        tower1.addControl(new TowerControl(this));
        tower2.addControl(new TowerControl(this));
        rootNode.attachChild(tower1);
        rootNode.attachChild(tower2);
        for (int c = 0; c < CREEP_COUNT; c++) {
            final float x = FastMath.nextRandomInt(-5, 5);
            final float z = FastMath.nextRandomInt(5, 15);
            final Creep creep = factory.creep(x, z);
            creep.addControl(new CreepControl(this));
            rootNode.attachChild(creep);
        }

    }

    @Override
    public void cleanup() {
        rootNode.detachAllChildren();
    }

    public void addBudget(final int value) {
        budget += value;
    }

    public void removeHealth(final int value) {
        health -= value;
    }

}
