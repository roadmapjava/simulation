package action;

import gamemap.WorldMap;

public class InitialEntitiesPlacementAction extends Action {
    @Override
    public void perform(WorldMap worldMap) {
        worldMap.setupDefaultEntityPositions();
    }
}
