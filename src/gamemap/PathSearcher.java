package gamemap;

import entity.*;

import java.util.*;

public class PathSearcher {

    public List<Coordinates> search(WorldMap worldMap, Creature creature) {
        List<Coordinates> path = new ArrayList<>();
        String className = creature.getClass().getSimpleName();
        Entity target = switch (className) {
            case "Predator" -> getTarget(worldMap, creature, Herbivore.class);
            case "Herbivore" -> getTarget(worldMap, creature, Grass.class);
            default -> null;
        };
        if (target == null) {
            return path;
        }
        Set<Node> checked = new HashSet<>();
        Queue<Node> potentialCells = new PriorityQueue<>();
        List<Coordinates> shifts = getDefaultShifts();
        Node start = new Node(null, creature.getCoordinates(), 0, 0);
        Node goal = null;
        while (goal == null && start != null) {
            for (int i = 0; i < shifts.size(); i++) {
                Coordinates coordinates = new Coordinates(start.getCurrent().x() +
                        shifts.get(i).x(), start.getCurrent().y() +
                        shifts.get(i).y());
                Node potential = new Node(start, coordinates,
                        start.getStepsCompleted() + 1,
                        getShortWay(coordinates, worldMap.getCoordinates(target)));
                if (worldMap.isCellWithinBounds(coordinates) && !checked.contains(potential) &&
                        !potentialCells.contains(potential)) {
                    if (worldMap.isEmpty(coordinates)) {
                        potentialCells.add(potential);
                    } else {
                        if (worldMap.getEntity(coordinates).getClass() == target.getClass()) {
                            goal = potential;
                        }
                    }
                }
            }
            checked.add(start);
            start = potentialCells.poll();
        }
        if (goal == null) {
            return path;
        }
        while (goal.getParent() != null) {
            path.add(goal.getCurrent());
            goal = goal.getParent();
        }
        Collections.reverse(path);

        return path;
    }

    private Entity getTarget(WorldMap worldMap, Creature myCreature, Class targetClass) {
        Entity target = null;
        int shortWay = (worldMap.getSide() * worldMap.getSide()) - 1;
        for (int i = 0; i < worldMap.getSide(); i++) {
            for (int j = 0; j < worldMap.getSide(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (!worldMap.isEmpty(coordinates)) {
                    if (worldMap.getEntity(coordinates).getClass() == targetClass) {
                        int result = getShortWay(myCreature.getCoordinates(), coordinates);
                        if (result < shortWay) {
                            shortWay = result;
                            target = worldMap.getEntity(coordinates);
                        }
                    }
                }
            }
        }

        return target;
    }

    private int getShortWay(Coordinates source, Coordinates target) {
        int myX = source.x();
        int myY = source.y();
        int targetX = target.x();
        int targetY = target.y();
        int resultX = Math.abs(myX - targetX);
        int resultY = Math.abs(myY - targetY);
        return Math.max(resultX, resultY);
    }
    public static List<Coordinates> getDefaultShifts() {
        List<Coordinates> shifts = new ArrayList<>();
        shifts.add(new Coordinates(-1, -1));
        shifts.add(new Coordinates(-1, 0));
        shifts.add(new Coordinates(-1, 1));
        shifts.add(new Coordinates(0, -1));
        shifts.add(new Coordinates(0, 1));
        shifts.add(new Coordinates(1, -1));
        shifts.add(new Coordinates(1, 0));
        shifts.add(new Coordinates(1, 1));
        return shifts;
    }
}

