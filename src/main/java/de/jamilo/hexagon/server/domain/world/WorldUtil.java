package de.jamilo.hexagon.server.domain.world;

public class WorldUtil {
    public static int[][] findNeighbors(int x, int y) {
        int[][] neighs = new int[6][];
        neighs[0] = new int[] { y - 1 , x - 1 };
        neighs[1] = new int[] { y - 1 , x + 1 };
        neighs[2] = new int[] { y , x - 2 };
        neighs[3] = new int[] { y , x + 2 };
        neighs[4] = new int[] { y + 1 , x - 1 };
        neighs[5] = new int[] { y + 1 , x + 1 };
        return neighs;
    }
}