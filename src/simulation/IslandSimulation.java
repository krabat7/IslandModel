package simulation;
import field.IslandField;
import field.Location;
import lifeform.animal.herbivore.*;
import lifeform.animal.predator.*;
import lifeform.plant.Plant;
import simulation.thread.animalLifecycleTask.AnimalLifecycleTask;
import simulation.thread.PlantGrowthTask;
import simulation.thread.StatisticsTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class IslandSimulation {
    private IslandField islandField;
    private final long startTime;
    private static volatile IslandSimulation instance;

    private IslandSimulation() {
        startTime = System.currentTimeMillis();
        createIslandModel();
        runIslandModel();
    }
    public static IslandSimulation getInstance() {
        if (instance == null) {
            synchronized (IslandSimulation.class) {
                if (instance == null) {
                    instance = new IslandSimulation();
                }
            }
        }
        return instance;
    }
    private void createIslandModel(){
        islandField = IslandField.getInstance();

        placeHerbivores();
        placePredators();
        placePlants(300);
    }

    private void runIslandModel(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask();

        executorService.scheduleAtFixedRate(animalLifecycleTask, 0, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 0, 50, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 0, 1, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores() {
        List<Herbivore> herbivores = new ArrayList<>();
        for (int i = 0; i < new Buffalo().getMaxPopulation(); i++) {
            herbivores.add(new Buffalo());
        }
        for (int i = 0; i < new Caterpillar().getMaxPopulation(); i++) {
            herbivores.add(new Caterpillar());
        }
        for (int i = 0; i < new Deer().getMaxPopulation(); i++) {
            herbivores.add(new Deer());
        }
        for (int i = 0; i < new Duck().getMaxPopulation(); i++) {
            herbivores.add(new Duck());
        }
        for (int i = 0; i < new Goat().getMaxPopulation(); i++) {
            herbivores.add(new Goat());
        }
        for (int i = 0; i < new Horse().getMaxPopulation(); i++) {
            herbivores.add(new Horse());
        }
        for (int i = 0; i < new Mouse().getMaxPopulation(); i++) {
            herbivores.add(new Mouse());
        }
        for (int i = 0; i < new Rabbit().getMaxPopulation(); i++) {
            herbivores.add(new Rabbit());
        }
        for (int i = 0; i < new Sheep().getMaxPopulation(); i++) {
            herbivores.add(new Sheep());
        }
        for (int i = 0; i < new WildBoar().getMaxPopulation(); i++) {
            herbivores.add(new WildBoar());
        }
        return herbivores;
    }

    private List<Predator> createPredators() {
        List<Predator> predators = new ArrayList<>();
        for (int i = 0; i < new Bear().getMaxPopulation(); i++) {
            predators.add(new Bear());
        }
        for (int i = 0; i < new Eagle().getMaxPopulation(); i++) {
            predators.add(new Eagle());
        }
        for (int i = 0; i < new Fox().getMaxPopulation(); i++) {
            predators.add(new Fox());
        }
        for (int i = 0; i < new Snake().getMaxPopulation(); i++) {
            predators.add(new Snake());
        }
        for (int i = 0; i < new Wolf().getMaxPopulation(); i++) {
            predators.add(new Wolf());
        }
        return predators;
    }

    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    public void placeHerbivores() {
        List<Herbivore> herbivores = createHerbivores();
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(islandField.getNumRows());
                int column = random.nextInt(islandField.getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()) {
                    islandField.addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    public void placePredators() {
        List<Predator> predators = createPredators();

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(islandField.getNumRows());
                int column = random.nextInt(islandField.getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(predator.getName())).toList().size() <= predator.getMaxPopulation()) {
                    islandField.addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }
    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(islandField.getNumRows());
                int column = random.nextInt(islandField.getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    islandField.addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }
    public long getTimeNow() {
        return System.currentTimeMillis() - startTime;
    }
}

