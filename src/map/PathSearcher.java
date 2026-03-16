package map;

import entity.*;

import java.util.*;

public class PathSearcher {

    public List<Coordinates> search(WorldMap worldMap, Entity entity) {
        List<Coordinates> path = new ArrayList<>();
        Entity target = null;
        if (entity.getClass().getSimpleName().equals("Predator")) {
            target = getTarget(worldMap, entity, Herbivore.class);
        } else if (entity.getClass().getSimpleName().equals("Herbivore")) {
            target = getTarget(worldMap, entity, Grass.class);
        }
        if (target == null) {
            return path;
        }
        Set<Node> checked = new HashSet<>();
        Queue<Node> potentialCells = new PriorityQueue<>();
        List<CoordinatesShift> shifts = CoordinatesShift.getDefaultShifts();
        Node start = new Node(null, entity.getCoordinates(), 0, 0);
        Node goal = null;
        while (goal == null && start != null) {
            for (int i = 0; i < shifts.size(); i++) {
                Coordinates coordinates = new Coordinates(start.getCURRENT().getX() +
                        shifts.get(i).getX_SHIFT(), start.getCURRENT().getY() +
                        shifts.get(i).getY_SHIFT());
                Node potential = new Node(start, coordinates,
                        start.getSTEPS_COMPLETED() + 1,
                        getShortWay(coordinates, target.getCoordinates()));
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
        while (goal.getPARENT() != null) {
            path.add(goal.getCURRENT());
            goal = goal.getPARENT();
        }
        Collections.reverse(path);

        return path;
    }

    private Entity getTarget(WorldMap worldMap, Entity myEntity, Class targetClass) {
        Entity target = null;
        int shortWay = (worldMap.getMAP_SIZE() * worldMap.getMAP_SIZE()) - 1;
        for (int i = 0; i < worldMap.getMAP_SIZE(); i++) {
            for (int j = 0; j < worldMap.getMAP_SIZE(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (!worldMap.isEmpty(coordinates)) {
                    if (worldMap.getEntity(coordinates).getClass() == targetClass) {
                        int result = getShortWay(myEntity.getCoordinates(), coordinates);
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
        int myX = source.getX();
        int myY = source.getY();
        int targetX = target.getX();
        int targetY = target.getY();
        int resultX = Math.abs(myX - targetX);
        int resultY = Math.abs(myY - targetY);
        return Math.max(resultX, resultY);
    }
}

