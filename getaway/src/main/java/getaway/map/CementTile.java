package getaway.map;

import com.jme3.material.Material;
import com.jme3.scene.shape.Box;

public class CementTile extends Tile {

    public CementTile(final int x, final int y, final Material material) {
        super(x, y, material, new Box(0.5f, 0.5f, 0.5f));
        setLocalTranslation(x, -0.5f, y);
    }
}
