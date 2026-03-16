import map.WorldMap;

import java.util.Scanner;

public class WorldMapCreator {
    private final static Scanner scanner = new Scanner(System.in);

    public static WorldMap create() {
        System.out.println("""
                            Добро пожаловать в симуляцию!
                Пожалуйста, введите цифры от 1 до 3, обозначающие размер карты, где
                1. Маленький
                2. Средний
                3. Большой""");
        int mapSize = 0;
        while (true) {
            String input = scanner.nextLine();
            if (input.length() != 1 || input.charAt(0) < 49 || input.charAt(0) > 51 || input.equals(" ")) {
                System.out.println("Вы ввели неверную цифру. Попробуйте снова...");
                continue;
            }
            mapSize = Integer.parseInt(input);
            break;
        }
        WorldMap worldMap = new WorldMap(mapSize);
        switch (mapSize) {
            case 1:
                System.out.println("Маленький мир создан.");
                break;
            case 2:
                System.out.println("Средний мир создан.");
                break;
            case 3:
                System.out.println("Большой мир создан.");
                break;
        }
        return worldMap;
    }
}
