package com.hatebit.chapter3;

import com.jme3.app.SimpleApplication;

public class Main3CubeChaser3AppState extends SimpleApplication {

    public static void main(String[] args) {
        final Main3CubeChaser3AppState app = new Main3CubeChaser3AppState();
        app.setPauseOnLostFocus(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10f);
        final CubeChaserState state = new CubeChaserState();
        stateManager.attach(state);
    }

    @Override
    public void simpleUpdate(final float tsp) {
        System.out.println("Chase counter: " + stateManager.getState(CubeChaserState.class).getCounter());
    }

}
