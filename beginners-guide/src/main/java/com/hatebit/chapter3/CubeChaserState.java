package com.hatebit.chapter3;

import com.hatebit.utils.CubeUtils;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

public class CubeChaserState extends AbstractAppState {

    private static final Ray RAY = new Ray();

    private SimpleApplication app;
    private Camera cam;
    private Node rootNode;
    private AssetManager assetManager;

    private int counter = 0;

    @Override
    public void initialize(final AppStateManager stateManager, final Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.cam = this.app.getCamera();
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();

        makeCubes(40);
    }

    @Override
    public void update(final float tpf) {
        final CollisionResults results = new CollisionResults();
        RAY.setOrigin(cam.getLocation());
        RAY.setDirection(cam.getDirection());
        rootNode.collideWith(RAY, results);
        if (results.size() > 0) {
            final Geometry target = results.getClosestCollision().getGeometry();
            if (target.getControl(ScaredCubeControl.class) != null) {
                final float distance = cam.getLocation().distance(target.getLocalTranslation());
                if (distance < 10) {
                    target.move(cam.getDirection());
                    System.out.println(target.getControl(ScaredCubeControl.class).hello()
                            + " and I am running away from "
                            + cam.getLocation());
                    counter++;
                }
            }
        }
    }

    private void makeCubes(int number) {
        final CubeUtils cubeUtils = new CubeUtils(assetManager);
        for (int i = 0; i < number; i++) {
            final Vector3f position = new Vector3f(
                    FastMath.nextRandomInt(-20, 20),
                    FastMath.nextRandomInt(-20, 20),
                    FastMath.nextRandomInt(-20, 20));
            final Geometry cube = cubeUtils.createCube("Cube" + i, position, ColorRGBA.randomColor());
            if (FastMath.nextRandomInt(1, 4) == 4) {
                cube.addControl(new ScaredCubeControl());
            }
            rootNode.attachChild(cube);
        }
    }

    public int getCounter() { return counter; }

    private static class ScaredCubeControl extends AbstractControl {

        public String hello() {
            return "Hello, my name is " + spatial.getName();
        }

        @Override
        protected void controlUpdate(final float tpf) {
            spatial.rotate(tpf, tpf, tpf);
        }

        @Override
        protected void controlRender(final RenderManager renderManager, final ViewPort viewPort) {

        }
    }

}
