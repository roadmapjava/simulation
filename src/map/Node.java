package map;

import java.util.Objects;

public class Node implements Comparable<Node> {
    private final Node PARENT;
    private final Coordinates CURRENT;
    private final int STEPS_COMPLETED;
    private final int STEPS_REMAINING;
    private final int SUM_OF_PRIORITY;

    public Node(Node parent, Coordinates current, int stepsCompleted, int stepsRemaining) {
        PARENT = parent;
        CURRENT = current;
        STEPS_COMPLETED = stepsCompleted;
        STEPS_REMAINING = stepsRemaining;
        SUM_OF_PRIORITY = stepsCompleted + stepsRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(CURRENT, node.CURRENT);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CURRENT);
    }

    @Override
    public int compareTo(Node o) {
        return this.SUM_OF_PRIORITY - o.SUM_OF_PRIORITY;
    }

    public Node getPARENT() {
        return PARENT;
    }

    public Coordinates getCURRENT() {
        return CURRENT;
    }

    public int getSTEPS_COMPLETED() {
        return STEPS_COMPLETED;
    }

    public int getSTEPS_REMAINING() {
        return STEPS_REMAINING;
    }

    public int getSUM_OF_PRIORITY() {
        return SUM_OF_PRIORITY;
    }
}
