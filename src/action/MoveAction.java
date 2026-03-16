package action;

import entity.Creature;
import map.WorldMap;

import java.util.List;

public class MoveAction extends Action {
    @Override
    public void perform(WorldMap worldMap) {
        List<Creature> creatures = worldMap.getCreatures();
        for (Creature creature : creatures) {
            if (creature != worldMap.getEntity(creature.getCoordinates())) {
                continue;
            }
            creature.makeMove(worldMap);
        }
    }
}
