package trans.america;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import java.util.HashSet;
import java.util.Set;

public class MapRender {

    private final Map map;
    private final TileFactory tileFactory;

    private int lastXx = Integer.MIN_VALUE;
    private int lastYy = Integer.MIN_VALUE;

    private Set<String> loaded = new HashSet<>();

    public MapRender(Map map, TileFactory tileFactory) {
        this.map = map;
        this.tileFactory = tileFactory;
    }

    public void render(Node rootNode, PhysicsSpace physicsSpace, final int xx, final int yy) {
        if (lastXx == xx && lastYy == yy) {
            return;
        }
        lastXx = xx;
        lastYy = yy;

        final Set<String> toLoad = getInRange(xx, yy, 1);
        final Set<String> toKeep = getInRange(xx, yy, 3);
        final Set<String> toFree = new HashSet<>(loaded);
        toFree.removeAll(toKeep);
        //System.out.println(toLoad);
        //System.out.println(toFree);

        for (final String name : toLoad) {
            final String[] tokens = name.split("_");
            final int x = Integer.parseInt(tokens[0]);
            final int y = Integer.parseInt(tokens[1]);
            final Map.Tile tile = map.get(x, y);
            if (!loaded.contains(name)) {
                loaded.add(name);
                switch (tile) {
                    case NONE:
                        rootNode.attachChild(tileFactory.createOutOfBounds(name, x, y));
                        break;
                    case SMOOTH:
                        final Geometry smooth = tileFactory.createGround(name, x, y, ColorRGBA.Yellow);
                        physicsSpace.add(smooth);
                        rootNode.attachChild(smooth);
                        break;
                    case RUGGED:
                        final Geometry rugged = tileFactory.createGround(name, x, y, ColorRGBA.Brown);
                        physicsSpace.add(rugged);
                        rootNode.attachChild(rugged);
                        break;
                    default:
                        rootNode.attachChild(tileFactory.createGround(name, x, y, ColorRGBA.Cyan));
                }
            }
        }
        for (final String name : toFree) {
            rootNode.detachChildNamed(name);
            // TODO: also free physics control
        }
        loaded.removeAll(toFree);
    }

    private Set<String> getInRange(int xx, int yy, int range) {
        final HashSet<String> result = new HashSet<>();
        for (int x = xx - range; x <= xx + range; x++) {
            for (int y = yy - range; y <= yy + range; y++) {
                result.add(nameFor(x, y));
            }
        }
        return result;
    }

    private static String nameFor(int x, int y) {
        return x + "_" + y;
    }

}
