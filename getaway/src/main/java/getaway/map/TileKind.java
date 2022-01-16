package getaway.map;

import java.util.Arrays;

public enum TileKind {
    CEMENT('B'),
    FENCE('F'),
    ROAD('R'),
    WATER('W'),
    GRASS('G'),
    HOUSE('H');

    private final char ch;

    TileKind(char ch) {
        this.ch = ch;
    }

    public static TileKind fromChar(final char ch) {
        return Arrays.stream(values())
                .filter(t -> t.ch == ch)
                .findAny()
                .orElseThrow();
    }


}
