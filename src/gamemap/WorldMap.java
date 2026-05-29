package gamemap;

import entity.*;

import java.util.*;
import java.util.function.Supplier;

public class WorldMap {

    private final int side;
    private final Map<Coordinates, Entity> entities;
    private static final Random RANDOM = new Random();

    public WorldMap(Size size) {
        side = size.getValue();
        entities = new HashMap<>();
    }

    public int getSide() {
        return side;
    }

    private void validateCoordinates(Coordinates coordinates) {
        if (!isCellWithinBounds(coordinates)) {
            throw new IllegalArgumentException("Координаты за пределами карты.");
        }
    }

    public boolean isEmpty(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        validateCoordinates(coordinates);
        Entity entity = entities.get(coordinates);
        if (entity == null) {
            throw new IllegalArgumentException("Существа по этим координатам нет");
        }
        return entity;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        validateCoordinates(coordinates);
        entities.put(coordinates, entity);
        if(entity instanceof Creature) {
            ((Creature)entity).setCoordinates(coordinates);
        }

    }

    public Entity removeEntity(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return entities.remove(coordinates);
    }

    public <T extends Entity> List<T> getEntitiesBy(Class<T> type) {
        List<T> someEntitiesByClass = new ArrayList<>();
        List<Entity> entities = new ArrayList<>(this.entities.values());
        for (Entity entity : entities) {
            if (type.isInstance(entity)) {
                someEntitiesByClass.add((T) entity);
            }
        }
        return someEntitiesByClass;
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities.values());
    }

    public boolean isCellWithinBounds(Coordinates coordinates) {
        int maxCell = getSide() - 1;
        if (coordinates.x() < 0 || coordinates.y() < 0 || coordinates.x() > maxCell
                || coordinates.y() > maxCell) {
            return false;
        }
        return true;
    }

    public void setupDefaultEntityPositions() {
        int entitiesQuantity;
        int maxRandom;
        if (getSide() == 10) {
            entitiesQuantity = 4;
            maxRandom = 10;
        } else if (getSide() == 20) {
            entitiesQuantity = 10;
            maxRandom = 20;
        } else {
            entitiesQuantity = 23;
            maxRandom = 30;
        }
        for (int i = 0; i < entitiesQuantity; i++) {
            spawn(() -> new Predator(2, 100), maxRandom);
            spawn(() -> new Herbivore(1, 100), maxRandom);
            spawn(() -> new Grass(), maxRandom);
            spawn(() -> new Rock(), maxRandom);
            spawn(() -> new Tree(), maxRandom);
        }


    }

    public void spawn(Supplier<Entity> entitySupplier, int maxRandom) {
        Coordinates coordinates;
        while (true) {
            int randomX = RANDOM.nextInt(maxRandom);
            int randomY = RANDOM.nextInt(maxRandom);
            coordinates = new Coordinates(randomX, randomY);
            if (isEmpty(coordinates)) {
                break;
            }
        }
        Entity entity = entitySupplier.get();
        addEntity(coordinates, entity);
    }

    public Coordinates getCoordinates(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Существо не найдено на карте");
    }
}
