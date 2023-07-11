package field;

import lifeform.animal.Animal;
import lifeform.plant.Plant;
import java.util.ArrayList;
import java.util.List;

public class IslandField {
    private final Location[][] locations; // Двумерный массив состоящий из локаций(ячеек)
    private int numRows = 10; //default
    private int numColumns = 4; //default
    private static volatile IslandField instance;

    private IslandField() {
        locations = new Location[numRows][numColumns];
        initializeLocations();
    }
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

    private void initializeLocations() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    public synchronized Location getLocation(int row, int column) {
        return locations[row][column];
    }

    public void addAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.addAnimal(animal);
    }

    public void removeAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.removeAnimal(animal);
    }

    public void addPlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.addPlant(plant);
    }

    public void removePlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.removePlant(plant);
    }

    public synchronized List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }
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

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

}