package map;

import entity.*;

import java.util.*;

public class WorldMap {

    private final int MAP_SIZE;
    private final Map<Coordinates, Entity> MAP;
    private static final Random RANDOM = new Random();

    public WorldMap(int MAP_SIZE) {
        if (MAP_SIZE == 1) {
            this.MAP_SIZE = 10;
        } else if (MAP_SIZE == 2) {
            this.MAP_SIZE = 20;
        } else {
            this.MAP_SIZE = 30;
        }

        MAP = new HashMap<>();
    }

    public int getMAP_SIZE() {
        return MAP_SIZE;
    }

    public boolean isEmpty(Coordinates coordinates) {
        return !MAP.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return MAP.get(coordinates);
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        MAP.put(coordinates, entity);
        entity.setCoordinates(coordinates);
    }

    public Entity removeEntity(Coordinates coordinates) {
        return MAP.remove(coordinates);
    }

    public List<Creature> getCreatures() {
        List<Creature> creatures = new ArrayList<>();
        List<Entity> entities = new ArrayList<>(MAP.values());
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                creatures.add((Creature) entity);
            }
        }
        return creatures;
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(MAP.values());
    }

    public boolean isCellWithinBounds(Coordinates coordinates) {
        int maxCell = getMAP_SIZE() - 1;
        if (coordinates.getX() < 0 || coordinates.getY() < 0 || coordinates.getX() > maxCell
                || coordinates.getY() > maxCell) {
            return false;
        }
        return true;
    }

    public void setupDefaultEntityPositions() {
        int entitiesQuantity;
        int maxRandom;
        if (getMAP_SIZE() == 10) {
            entitiesQuantity = 4;
            maxRandom = 10;
        } else if (getMAP_SIZE() == 20) {
            entitiesQuantity = 10;
            maxRandom = 20;
        } else {
            entitiesQuantity = 23;
            maxRandom = 30;
        }
        for (int i = 0; i < entitiesQuantity; i++) {
            while (true) {
                int randomX = RANDOM.nextInt(maxRandom);
                int randomY = RANDOM.nextInt(maxRandom);
                Coordinates coordinates = new Coordinates(randomX, randomY);
                if (isEmpty(coordinates)) {
                    addEntity(coordinates, new Predator(2, 100));
                    break;
                } else {
                    continue;
                }
            }
            while (true) {
                int randomX = RANDOM.nextInt(maxRandom);
                int randomY = RANDOM.nextInt(maxRandom);
                Coordinates coordinates = new Coordinates(randomX, randomY);
                if (isEmpty(coordinates)) {
                    addEntity(coordinates, new Herbivore(1, 100));
                    break;
                } else {
                    continue;
                }
            }
            while (true) {
                int randomX = RANDOM.nextInt(maxRandom);
                int randomY = RANDOM.nextInt(maxRandom);
                Coordinates coordinates = new Coordinates(randomX, randomY);
                if (isEmpty(coordinates)) {
                    addEntity(coordinates, new Grass());
                    break;
                } else {
                    continue;
                }
            }
            while (true) {
                int randomX = RANDOM.nextInt(maxRandom);
                int randomY = RANDOM.nextInt(maxRandom);
                Coordinates coordinates = new Coordinates(randomX, randomY);
                if (isEmpty(coordinates)) {
                    addEntity(coordinates, new Rock());
                    break;
                } else {
                    continue;
                }
            }
            while (true) {
                int randomX = RANDOM.nextInt(maxRandom);
                int randomY = RANDOM.nextInt(maxRandom);
                Coordinates coordinates = new Coordinates(randomX, randomY);
                if (isEmpty(coordinates)) {
                    addEntity(coordinates, new Tree());
                    break;
                } else {
                    continue;
                }
            }
        }


    }

}
