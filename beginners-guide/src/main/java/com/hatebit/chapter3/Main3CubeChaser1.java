package com.hatebit.chapter3;

import com.hatebit.utils.CubeUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Main3CubeChaser1 extends SimpleApplication {

    private static final Ray RAY = new Ray();

    public static void main(String[] args) {
        final Main3CubeChaser1 app = new Main3CubeChaser1();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10f);
        final CubeUtils cubeUtils = new CubeUtils(assetManager);
        makeCubes(cubeUtils, 40);
    }

    public void simpleUpdate(final float tpf) {
        final CollisionResults results = new CollisionResults();
        RAY.setOrigin(cam.getLocation());
        RAY.setDirection(cam.getDirection());
        rootNode.collideWith(RAY, results);
        if (results.size() > 0) {
            final Geometry target = results.getClosestCollision().getGeometry();
            final float distance = cam.getLocation().distance(target.getLocalTranslation());
            if (distance < 10) {
                target.move(cam.getDirection());
            }
        }
    }

    private void makeCubes(CubeUtils cubeUtils, int number) {
        for (int i = 0; i < number; i++) {
            final Vector3f position = new Vector3f(
                    FastMath.nextRandomInt(-20, 20),
                    FastMath.nextRandomInt(-20, 20),
                    FastMath.nextRandomInt(-20, 20));
            rootNode.attachChild(cubeUtils.createCube("Cube" + i, position, ColorRGBA.randomColor()));
        }
    }

}
