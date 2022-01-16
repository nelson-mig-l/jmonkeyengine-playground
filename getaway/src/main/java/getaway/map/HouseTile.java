package getaway.map;

import com.jme3.material.Material;
import com.jme3.scene.shape.Box;

public class HouseTile extends Tile {

    public HouseTile(int x, int y, Material material) {
        super(x, y, material, new Box(0.5f, 0.5f, 0.5f));
        setLocalTranslation(x, 0.5f, y);
    }

}
