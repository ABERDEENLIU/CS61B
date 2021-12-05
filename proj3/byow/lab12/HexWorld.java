package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final long SEED = 123123;
    private static final Random RANDOM = new Random(SEED);

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.NOTHING;
            case 3: return Tileset.WATER;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    private static void addHexagon(TETile[][] world, int s, int x, int y, TETile tile) {
        for (int i = 0; i < s; i++) {
            for (int p = x; p < x + s + i + i; p += 1) {
                world[p-i][y+i] = tile;
                world[p-i][y+s*2-i-1] = tile;
            }
        }

    }

    


    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // set the point of hexagon
        addHexagon(world, 5, 10, 10, randomTile());

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
