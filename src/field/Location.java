package field;

import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.plant.Plant;

import java.util.*;

public class Location {
    private int row;
    private int column;
    private List<Animal> animals;
    private List<Plant> plants;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animal.setRow(row);
        animal.setColumn(column);

        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setColumn(column);
        plants.add(new Plant());
    }
    public void removePlant() {
        plants.remove(0);
    }

    public List<Plant> getPlants() {
        return plants;
    }
    public List<Animal> getAnimals() {
        return animals;
    }
    public List<LifeForm> getLifeForms() {
        List<LifeForm> lifeForms = new ArrayList<>();
        lifeForms.add((LifeForm) getAnimals());
        lifeForms.add((LifeForm) getPlants());
        return lifeForms;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
