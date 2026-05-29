package entity;

import gamemap.*;

import java.util.List;

public abstract class Creature extends Entity {
    private Coordinates coordinates;
    private final int speed;
    private int hp;
    private final PathSearcher pathSearcher = new PathSearcher();


    public Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void makeMove(WorldMap worldMap) {
        List<Coordinates> moves = pathSearcher.search(worldMap, this);
        if (!moves.isEmpty()) {
            move(moves, worldMap);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    protected abstract void move(List<Coordinates> moves, WorldMap worldMap);

    protected void applyMove(Coordinates coordinates, WorldMap worldMap) {
        worldMap.removeEntity(this.getCoordinates());
        worldMap.addEntity(coordinates, this);
        this.setCoordinates(coordinates);
    }

}
