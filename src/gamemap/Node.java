package gamemap;

import java.util.Objects;

public class Node implements Comparable<Node> {
    private final Node parent;
    private final Coordinates current;
    private final int stepsCompleted;
    private final int stepsRemaining;
    private final int sumOfPriority;

    public Node(Node parent, Coordinates current, int stepsCompleted, int stepsRemaining) {
        this.parent = parent;
        this.current = current;
        this.stepsCompleted = stepsCompleted;
        this.stepsRemaining = stepsRemaining;
        sumOfPriority = stepsCompleted + stepsRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(current, node.current);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(current);
    }

    @Override
    public int compareTo(Node o) {
        return this.sumOfPriority - o.sumOfPriority;
    }

    public Node getParent() {
        return parent;
    }

    public Coordinates getCurrent() {
        return current;
    }

    public int getStepsCompleted() {
        return stepsCompleted;
    }

    public int getStepsRemaining() {
        return stepsRemaining;
    }

    public int getSumOfPriority() {
        return sumOfPriority;
    }
}
