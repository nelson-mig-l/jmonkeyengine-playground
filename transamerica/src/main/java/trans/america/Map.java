package trans.america;

import java.util.Random;

class Map {

    enum Tile {
        NONE, SMOOTH, RUGGED;
    }

    private final boolean[][] tiles; // = {
    //{true, true, true},
    //{true, false, true},
    //{true, true, true}
    //};

    Map(int radius) {
        final int size = radius * 2 + 1;
        tiles = new boolean[size][size];

        final Random random = new Random();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                tiles[x][y] = random.nextBoolean();
            }
        }
    }

    Tile get(int x, int y) {
        if ((x < 0 || x >= tiles.length) || (y < 0 || y >= tiles[0].length)) {
            return Tile.NONE;
        }
        return tiles[x][y] ? Tile.RUGGED : Tile.SMOOTH;
    }

}
