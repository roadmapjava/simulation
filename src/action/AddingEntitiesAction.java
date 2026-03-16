package action;

import entity.*;
import map.*;

import java.util.*;

public class AddingEntitiesAction extends Action {
    private static final Random RANDOM = new Random();

    @Override
    public void perform(WorldMap worldMap) {
        List<Entity> entities = worldMap.getEntities();
        int herbivoreCounter = 0;
        int grassCounter = 0;
        for (Entity entity : entities) {
            if (entity instanceof Herbivore) {
                herbivoreCounter++;
            }
            if (entity instanceof Grass) {
                grassCounter++;
            }
        }
        int maxRandom = worldMap.getMAP_SIZE();
        if (herbivoreCounter <= 2) {
            for (int i = 0; i < 3; i++) {
                while (true) {
                    int randomX = RANDOM.nextInt(maxRandom);
                    int randomY = RANDOM.nextInt(maxRandom);
                    Coordinates coordinates = new Coordinates(randomX, randomY);
                    if (worldMap.isEmpty(coordinates)) {
                        worldMap.addEntity(coordinates, new Herbivore(1, 100));
                        break;
                    }
                }
            }
        }
        if (grassCounter <= 2) {
            for (int i = 0; i < 3; i++) {
                while (true) {
                    int randomX = RANDOM.nextInt(maxRandom);
                    int randomY = RANDOM.nextInt(maxRandom);
                    Coordinates coordinates = new Coordinates(randomX, randomY);
                    if (worldMap.isEmpty(coordinates)) {
                        worldMap.addEntity(coordinates, new Grass());
                        break;
                    }
                }
            }
        }

    }
}
