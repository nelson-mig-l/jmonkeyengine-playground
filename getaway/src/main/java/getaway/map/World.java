package getaway.map;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class World extends Node {

    private static final int WIDTH = 25;
    private static final int HEIGHT = 20;

    private final TileFactory tileFactory;

    public World(final AssetManager assetManager) {
        tileFactory = new TileFactory(assetManager);
        final List<String> lines = getMapDefinition();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                final char ch = lines.get(y).charAt(x);
                final TileKind kind = TileKind.fromChar(ch);
                final Tile tile = tileFactory.create(x, y, kind);
                attachChild(tile);
            }
        }
    }

    private List<String> getMapDefinition() {
        try {
            final URI resource = this.getClass().getClassLoader().getResource("map.txt").toURI();
            return Files.readAllLines(Path.of(resource));
        } catch (IOException | URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
    }

}
