package foursquare;

import java.util.Arrays;

public class Figure {
    private final Point[] points;

    public Figure(final Point... points) {
        if (points.length != 4) {
            throw new IllegalArgumentException();
        }
        this.points = points;
    }

    public boolean isFoursquare() {
        final double[] ways = new double[6];
        int waysCount = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = (i + 1); j < 4; j++) {
                ways[waysCount++] =
                        this.getLength(
                                this.points[j], this.points[i]
                        );
            }
        }

        Arrays.sort(ways);

        return allSidesEquals(ways) && diagonalsIsEquals(ways);
    }

    private boolean allSidesEquals(final double[] ways) {
        return ways[0] == ways[1] && ways[1] == ways[2] &&
                ways[2] == ways[3];
    }

    private boolean diagonalsIsEquals(final double[] ways) {
        return ways[4] == ways[5];
    }

    private double getLength(final Point fst, final Point scd) {
        final double length =
                Math.sqrt(
                        Math.pow(scd.getX() - fst.getX(), 2) +
                                Math.pow(scd.getY() - fst.getY(), 2)
                );

        if (length >= 0) {
            return length;
        }

        return length * (-1D);
    }
}
