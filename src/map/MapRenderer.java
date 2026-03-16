package map;

import entity.Entity;

public class MapRenderer {
    private static final String PREDATOR_SPRITE = "\uD83D\uDC3A";
    private static final String HERBIVORE_SPRITE = "\uD83D\uDC07";
    private static final String GRASS_SPRITE = "\uD83C\uDF3F";
    private static final String ROCK_SPRITE = "\u26F0";
    private static final String TREE_SPRITE = "\uD83C\uDF33";
    private static final String EMPTINESS_SPRITE = "\uD83D\uDFE9";

    private WorldMap worldMap;

    public MapRenderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void render() {
        for (int i = 0; i < worldMap.getMAP_SIZE(); i++) {
            for (int j = 0; j < worldMap.getMAP_SIZE(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isEmpty(coordinates)) {
                    System.out.print(EMPTINESS_SPRITE);
                } else {
                    Entity entity = worldMap.getEntity(coordinates);
                    String entityName = entity.getClass().getSimpleName();
                    switch (entityName) {
                        case "Predator":
                            System.out.print(PREDATOR_SPRITE);
                            break;
                        case "Herbivore":
                            System.out.print(HERBIVORE_SPRITE);
                            break;
                        case "Grass":
                            System.out.print(GRASS_SPRITE);
                            break;
                        case "Rock":
                            System.out.print(ROCK_SPRITE);
                            break;
                        case "Tree":
                            System.out.print(TREE_SPRITE);
                            break;
                    }
                }
            }
            System.out.println();
        }
    }

}
