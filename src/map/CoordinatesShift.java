package map;

import java.util.*;

public class CoordinatesShift {
    private final int X_SHIFT;
    private final int Y_SHIFT;

    public CoordinatesShift(int xShift, int yShift) {
        this.X_SHIFT = xShift;
        this.Y_SHIFT = yShift;
    }

    public int getX_SHIFT() {
        return X_SHIFT;
    }

    public int getY_SHIFT() {
        return Y_SHIFT;
    }

    public static List<CoordinatesShift> getDefaultShifts() {
        List<CoordinatesShift> shifts = new ArrayList<>();
        shifts.add(new CoordinatesShift(-1, -1));
        shifts.add(new CoordinatesShift(-1, 0));
        shifts.add(new CoordinatesShift(-1, 1));
        shifts.add(new CoordinatesShift(0, -1));
        shifts.add(new CoordinatesShift(0, 1));
        shifts.add(new CoordinatesShift(1, -1));
        shifts.add(new CoordinatesShift(1, 0));
        shifts.add(new CoordinatesShift(1, 1));
        return shifts;
    }
}
