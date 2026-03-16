package entity;

import map.*;

import java.util.List;

public class Predator extends Creature {
    private final int POWER = 50;

    public Predator(int speed, int hp) {
        super(speed, hp);
    }

    public int getPOWER() {
        return POWER;
    }

    @Override
    protected void move(List<Coordinates> moves, WorldMap worldMap) {
        int speed = this.getSpeed();
        for (int i = 0; i < moves.size(); i++) {
            if (!worldMap.isEmpty(moves.get(i))) {
                if (worldMap.getEntity(moves.get(i)).getClass() == Herbivore.class) {
                    Herbivore herbivore = (Herbivore) worldMap.getEntity(moves.get(i));
                    if (i == 0) {
                        herbivore.setHp(herbivore.getHp() - this.getPOWER());
                        herbivore.setHp(herbivore.getHp() - this.getPOWER());
                        speed -= 2;
                    } else {
                        herbivore.setHp(herbivore.getHp() - this.getPOWER());
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
