package field;

import lifeform.animal.Animal;

import java.util.*;

public class Location {
    private List<Animal> animals;
    private boolean hasPlant;

    public Location() {
        animals = new ArrayList<>();
        hasPlant = false;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public boolean hasPlant() {
        return hasPlant;
    }

    public void setHasPlant(boolean hasPlant) {
        this.hasPlant = hasPlant;
    }
}
