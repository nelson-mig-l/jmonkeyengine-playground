package getaway.map;

import com.jme3.material.Material;
import com.jme3.scene.shape.Box;

public class GenericTile extends Tile {

    public GenericTile(final int x, final int y, final Material material) {
        super(x, y, material, new Box(0.5f, 0.1f, 0.5f));
        setLocalTranslation(x, -0.1f, y);
    }

}
