package getaway;


import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

public class Player extends Node {

    public Player(final AssetManager assetManager) {
        attachChild(assetManager.loadModel("textures/car/car.obj"));
        setLocalTranslation(10, 0, 10);
        setLocalScale(0.25f);
    }

}
