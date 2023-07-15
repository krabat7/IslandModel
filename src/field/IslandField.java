package field;

import lifeform.animal.Animal;
import lifeform.plant.Plant;
import java.util.ArrayList;
import java.util.List;

public class IslandField {
    private Location[][] locations; // Двумерный массив состоящий из локаций(ячеек)
    private final int numRows = 10; //default
    private final int numColumns = 4; //default
    private static volatile IslandField instance;

    /**
     * Приватный конструктор класса IslandField.
     * Используется для создания единственного экземпляра класса (синглтон).
     */
    private IslandField() {
    }

    /**
     * Метод для получения экземпляра класса IslandField.
     *
     * @return Экземпляр класса IslandField
     */
    public static IslandField getInstance() {
        if (instance == null) {
            synchronized (IslandField.class) {
                if (instance == null) {
                    instance = new IslandField();
                }
            }
        }
        return instance;
    }

    /**
     * Метод для инициализации локаций (ячеек) на острове.
     * Устанавливает размеры двумерного массива и создает локации.
     *
     * @param numRows    Количество строк
     * @param numColumns Количество столбцов
     */
    public void initializeLocations(int numRows, int numColumns) {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    /**
     * Перегруженный метод для инициализации локаций (ячеек) на острове.
     * Использует значения по умолчанию для размеров.
     */
    public void initializeLocations() {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    /**
     * Метод для получения локации (ячейки) по заданным координатам.
     *
     * @param row    Номер строки
     * @param column Номер столбца
     * @return Локация с заданными координатами
     */
    public synchronized Location getLocation(int row, int column) {
        return locations[row][column];
    }

    /**
     * Метод для добавления животного в указанную локацию.
     *
     * @param animal Животное для добавления
     * @param row    Номер строки локации
     * @param column Номер столбца локации
     */
    public void addAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.addAnimal(animal);
    }

    /**
     * Метод для удаления животного из указанной локации.
     *
     * @param animal Животное для удаления
     * @param row    Номер строки локации
     * @param column Номер столбца локации
     */
    public void removeAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.removeAnimal(animal);
    }

    /**
     * Метод для добавления растения в указанную локацию.
     *
     * @param plant  Растение для добавления
     * @param row    Номер строки локации
     * @param column Номер столбца локации
     */
    public void addPlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.addPlant(plant);
    }

    /**
     * Метод для удаления растения из указанной локации.
     *
     * @param plant  Растение для удаления
     * @param row    Номер строки локации
     * @param column Номер столбца локации
     */
    public void removePlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.removePlant(plant);
    }

    /**
     * Метод для получения списка всех животных на острове.
     *
     * @return Список всех животных
     */
    public synchronized List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }

    /**
     * Метод для получения списка всех растений на острове.
     *
     * @return Список всех растений
     */
    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlants;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getNumColumns() {
        return numColumns;
    }
}
