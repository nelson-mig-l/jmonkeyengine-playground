package getaway.map;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

import java.util.HashMap;
import java.util.Map;

public class TileFactory {

    private final Map<TileKind, Material> materials = new HashMap<>();
    private final AssetManager assetManager;

    public TileFactory(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Tile create(final int x, final int y, final TileKind kind) {
        final Material material = getMaterial(kind);
        switch (kind) {
            case HOUSE:
                return new HouseTile(x, y, material);
            case CEMENT:
                return new CementTile(x, y, material);
            case WATER:
                return new WaterTile(x, y, material);
            default:
                return new GenericTile(x, y, material);
        }
    }

    private Material getMaterial(final TileKind kind) {
        return materials.computeIfAbsent(kind, t -> {
            final Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            switch (kind) {
                case CEMENT:
                    m.setColor("Color", ColorRGBA.Gray);
                    break;
                case FENCE:
                    m.setColor("Color", ColorRGBA.Brown);
                    break;
                case ROAD:
                    m.setColor("Color", ColorRGBA.Black);
                    break;
                case WATER:
                    m.setColor("Color", ColorRGBA.Blue);
                    break;
                case GRASS:
                    m.setColor("Color", ColorRGBA.Green);
                    break;
                case HOUSE:
                    m.setColor("Color", ColorRGBA.Red);
                    break;
            }
            return m;
        });
    }

    }
