package entity;

import gamemap.*;

import java.util.List;

public class Predator extends Creature {
    private final int power = 50;

    public Predator(int speed, int hp) {
        super(speed, hp);
    }

    public int getPower() {
        return power;
    }

    @Override
    protected void move(List<Coordinates> moves, WorldMap worldMap) {
        int speed = this.getSpeed();
        for (int i = 0; i < moves.size(); i++) {
            if (!worldMap.isEmpty(moves.get(i))) {
                boolean isFood = worldMap.getEntity(moves.get(i)).getClass() == Herbivore.class;
                if (isFood) {
                    Herbivore herbivore = (Herbivore) worldMap.getEntity(moves.get(i));
                    int hits = (i == 0) ? 2 : 1;
                    for (int j = 0; j < hits; j++) {
                        herbivore.setHp(herbivore.getHp() - this.getPower());
                        speed--;
                    }
                    if (herbivore.getHp() <= 0) {
                        worldMap.removeEntity(moves.get(i));
                        applyMove(moves.get(i), worldMap);
                    }
                } else {
                    return;
                }
            } else {
                applyMove(moves.get(i), worldMap);
                speed--;
            }
            if (speed == 0) {
                return;
            }
        }
    }

}
