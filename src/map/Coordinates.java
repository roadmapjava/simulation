package map;

import java.util.Objects;

public class Coordinates {
    private final int X;
    private final int Y;

    public Coordinates(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return X == that.X && Y == that.Y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }

    @Override
    public String toString() {
        return "map.Coordinates{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
