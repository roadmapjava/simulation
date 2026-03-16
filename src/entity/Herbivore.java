package entity;

import map.*;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(int speed, int hp) {
        super(speed, hp);
    }

    @Override
    protected void move(List<Coordinates> moves, WorldMap worldMap) {
        int speed = this.getSpeed();
        for (int i = 0; i < moves.size(); i++) {
            if (!worldMap.isEmpty(moves.get(i))) {
                if (worldMap.getEntity(moves.get(i)).getClass() == Grass.class) {
                    worldMap.removeEntity(moves.get(i));
                    applyMove(moves.get(i), worldMap);
                }
            } else {
                applyMove(moves.get(i), worldMap);
            }
            speed--;
            if (speed == 0) {
                return;
            }
        }


    }

}
