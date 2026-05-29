import gamemap.Size;
import gamemap.WorldMap;

import java.util.Scanner;

public final class WorldMapFactory {
    private final static Scanner SCANNER = new Scanner(System.in);

    private WorldMapFactory() {
    }

    public static WorldMap create() {
        System.out.println("""
                            Добро пожаловать в симуляцию!
                Пожалуйста, введите цифры от 1 до 3, обозначающие размер карты, где
                1. Маленький
                2. Средний
                3. Большой""");
        int mapSize = 0;
        while (true) {
            String input = SCANNER.nextLine();
            if (input.length() != 1 || input.charAt(0) < '1' || input.charAt(0) > '3' || input.equals(" ")) {
                System.out.println("Вы ввели неверную цифру. Попробуйте снова...");
                continue;
            }
            mapSize = Integer.parseInt(input);
            break;
        }
        WorldMap worldMap = null;
        switch (mapSize) {
            case 1:
                worldMap = new WorldMap(Size.SMALL);
                System.out.println("Маленький мир создан.");
                break;
            case 2:
                worldMap = new WorldMap(Size.MIDDLE);
                System.out.println("Средний мир создан.");
                break;
            case 3:
                worldMap = new WorldMap(Size.LARGE);
                System.out.println("Большой мир создан.");
                break;
        }
        return worldMap;
    }
}
