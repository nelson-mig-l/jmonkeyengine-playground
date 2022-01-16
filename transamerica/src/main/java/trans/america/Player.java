package trans.america;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Player extends Node {

    public static final float SIZE = 0.25f;

    Player(Material material) {
        final Box mesh = new Box(SIZE, SIZE, SIZE);
        final Geometry geometry = new Geometry("Player", mesh);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(0, SIZE, 0);
        attachChild(geometry);
    }
}
