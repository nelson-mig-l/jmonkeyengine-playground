package getaway.map;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

public abstract class Tile extends Geometry {

    public Tile(final int x, final int y, final Material material, final Mesh mesh) {
        super(generateName(x, y), mesh);
        setMaterial(material);
    }

    private static String generateName(int x, int y) {
        return "Tile(" + x + "," + y + ")";
    }

}
