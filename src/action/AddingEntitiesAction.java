package action;

import entity.*;
import gamemap.*;

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
        int maxRandom = worldMap.getSide();
        if (herbivoreCounter <= 2) {
            for (int i = 0; i < 3; i++) {
                worldMap.spawn(() -> new Herbivore(1, 100), maxRandom);
            }
        }
        if (grassCounter <= 2) {
            for (int i = 0; i < 3; i++) {
                worldMap.spawn(() -> new Grass(), maxRandom);
            }
        }

    }
}
