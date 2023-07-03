package simulation;
import field.IslandField;
import lifeform.animal.herbivore.*;
import lifeform.animal.predator.*;
import lifeform.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IslandSimulation {
    private IslandField islandField;
    private List<Herbivore> herbivores;
    private List<Predator> predators;
    private List<Plant> plants;

    public IslandSimulation() {
        createIslandModel();
    }
    private void createIslandModel(){
        islandField = IslandField.getInstance();
        herbivores = createHerbivores();
        predators = createPredators();
        plants = createPlants(200);

        placeHerbivores();
        placePredators();
        placePlants();
    }

    private List<Herbivore> createHerbivores() {
        List<Herbivore> herbivores = new ArrayList<>();
        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new WildBoar());
        return herbivores;
    }

    private List<Predator> createPredators() {
        List<Predator> predators = new ArrayList<>();
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());
        return predators;
    }

    private List<Plant> createPlants(int count) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    private void placeHerbivores() {
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            int row = random.nextInt(islandField.getNumRows());
            int column = random.nextInt(islandField.getNumColumns());
            islandField.addAnimal(herbivore, row, column);
        }
    }

    private void placePredators() {
        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            int row = random.nextInt(islandField.getNumRows());
            int column = random.nextInt(islandField.getNumColumns());
            islandField.addAnimal(predator, row, column);
        }
    }
    private void placePlants() {
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < plants.size(); i++) {
            int row = random.nextInt(islandField.getNumRows());
            int column = random.nextInt(islandField.getNumColumns());
            islandField.addPlant(row, column);
        }
    }
}

