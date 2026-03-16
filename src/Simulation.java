import action.*;
import map.*;

import java.util.*;

public class Simulation {
    private final WorldMap worldMap = WorldMapCreator.create();
    private int movesCyclesCounter = 0;
    private final MapRenderer mapRenderer = new MapRenderer(worldMap);
    public static volatile boolean isOngoing = true;
    public static volatile boolean isPaused = false;
    private List<Action> initActions = new ArrayList<>(List.of(
            new InitialEntitiesPlacementAction()
    ));
    private List<Action> turnActions = new ArrayList<>(List.of(
            new MoveAction(), new AddingEntitiesAction()
    ));

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
        Simulation simulation = new Simulation();
        new InputThread().start();
        simulation.startSimulation();
    }
}

class InputThread extends Thread {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        while (true) {
            String input = scanner.nextLine();
            if (input.length() != 1 || input.charAt(0) < 49 || input.charAt(0) > 51 || input.equals(" ")) {
                System.out.println("Вы ввели неверную цифру. Попробуйте снова...");
                continue;
            }
            int flag = Integer.parseInt(input);
            switch (flag) {
                case 1:
                    Simulation.isPaused = true;
                    break;
                case 2:
                    Simulation.isPaused = false;
                    break;
                case 3:
                    Simulation.isOngoing = false;
                    return;
            }
        }
    }
}
