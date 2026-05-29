import action.*;
import gamemap.*;

import java.util.*;

public class Simulation {
    private final WorldMap worldMap;
    private int movesCyclesCounter = 0;
    private final MapRenderer mapRenderer;
    private static volatile boolean isOngoing = true;
    private static volatile boolean isPaused = false;
    private List<Action> initActions = List.of(
            new InitialEntitiesPlacementAction());
    private List<Action> turnActions = new ArrayList<>(List.of(
            new MoveAction(), new AddingEntitiesAction()
    ));

    public Simulation(WorldMap worldMap) {
        this.worldMap = worldMap;
        mapRenderer = new MapRenderer(worldMap);
    }

    public static void setIsPaused(boolean isPaused) {
        Simulation.isPaused = isPaused;
    }

    public static void setIsOngoing(boolean isOngoing) {
        Simulation.isOngoing = isOngoing;
    }

    private void nextTurn() {
        for (Action action : turnActions) {
            action.perform(worldMap);
        }
        movesCyclesCounter++;
        mapRenderer.render();
        System.out.println("Количество ходов: " + movesCyclesCounter);
        System.out.println("""
                Введите цифры 1, 2 или 3, где:
                1. Поставить симуляцию на паузу
                2. Возобновить симуляцию
                3. Прекратить симуляцию и завершить работу""");
    }

    public void startSimulation() {
        for (Action action : initActions) {
            action.perform(worldMap);
        }
        mapRenderer.render();
        System.out.println("----------");
        while (isOngoing) {
            if (!isPaused) {
                nextTurn();
                System.out.println("----------");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Симуляция завершена.");
    }


    public static void main(String[] args) {
        Simulation simulation = new Simulation(WorldMapFactory.create());
        InputThread inputThread = new InputThread();
        inputThread.start();
        simulation.startSimulation();
    }
}

