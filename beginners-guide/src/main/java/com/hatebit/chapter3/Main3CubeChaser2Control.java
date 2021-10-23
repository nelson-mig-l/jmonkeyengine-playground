package com.hatebit.chapter3;

import com.hatebit.utils.CubeUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Main3CubeChaser2Control extends SimpleApplication {

    public static void main(String[] args) {
        final Main3CubeChaser2Control app = new Main3CubeChaser2Control();
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

    }

    private void makeCubes(CubeUtils cubeUtils, int number) {
        for (int i = 0; i < number; i++) {
            final Vector3f position = new Vector3f(
                    FastMath.nextRandomInt(-20, 20),
                    FastMath.nextRandomInt(-20, 20),
                    FastMath.nextRandomInt(-20, 20));
            final Geometry cube = cubeUtils.createCube("Cube" + i, position, ColorRGBA.randomColor());
            if (FastMath.nextRandomInt(1, 4) == 4) {
                final CubeChaserControl control = new CubeChaserControl(cam, rootNode);
                cube.addControl(control);
            }
            rootNode.attachChild(cube);
        }
    }

}
