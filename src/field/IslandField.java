package field;

import lifeform.animal.Animal;
import java.util.ArrayList;
import java.util.List;

public class IslandField {
    private Location[][] locations;

    public IslandField(int numRows, int numColumns) {
        locations = new Location[numRows][numColumns];
        initializeLocations();
    }

    private void initializeLocations() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int row, int column) {
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

    public void setPlant(int row, int column, boolean hasPlant) {
        Location location = getLocation(row, column);
        location.setHasPlant(hasPlant);
    }

    public List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }
}