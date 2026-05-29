package gamemap;

public record Coordinates(int x, int y) {

    @Override
    public String toString() {
        return "map.Coordinates{" +
                "X=" + x +
                ", Y=" + y +
                '}';
    }
}
