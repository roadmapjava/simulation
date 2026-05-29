package gamemap;

public enum Size {
    SMALL(10),
    MIDDLE(20),
    LARGE(30);
    private final int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
