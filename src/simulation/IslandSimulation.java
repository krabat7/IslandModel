package simulation;
import field.IslandField;
import field.Location;
import lifeform.animal.herbivore.*;
import lifeform.animal.predator.*;
import lifeform.plant.Plant;
import simulation.helperObject.Pair;
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
    private int countHerbivores = 30; // Изменять перед созданием объекта IslandSimulation
    private int countPlants = 25; // Изменять перед созданием объекта IslandSimulation
    private int countPredators = 10; // Изменять перед созданием объекта IslandSimulation
    private static volatile IslandSimulation instance;
    private volatile ScheduledExecutorService executorService;

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

        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);
    }

    private void runIslandModel(){
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask();

        executorService.scheduleAtFixedRate(animalLifecycleTask, 0, 20, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 10, 45, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 5, 25, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Pair<Herbivore, Integer>> herbivoreInfo = new ArrayList<>();
        herbivoreInfo.add(new Pair<>(new Buffalo(), new Buffalo().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Caterpillar(), new Caterpillar().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Deer(), new Deer().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Duck(), new Duck().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Goat(), new Goat().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Horse(), new Horse().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Mouse(), new Mouse().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Rabbit(), new Rabbit().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new Sheep(), new Sheep().getMaxPopulation()));
        herbivoreInfo.add(new Pair<>(new WildBoar(), new WildBoar().getMaxPopulation()));

        int totalMaxPopulation = herbivoreInfo.stream()
                .mapToInt(Pair::getRight)
                .sum();

        double proportion = (double) countHerbivores / totalMaxPopulation;

        List<Herbivore> herbivores = new ArrayList<>();

        for (Pair<Herbivore, Integer> info : herbivoreInfo) {
            int speciesCount = (int) Math.round(proportion * info.getRight());
            for (int i = 0; i < speciesCount; i++) {
                herbivores.add(info.getLeft());
            }
        }

        return herbivores;
    }

    private List<Predator> createPredators(int countPredators) {
        List<Pair<Predator, Integer>> predatorInfo = new ArrayList<>();
        predatorInfo.add(new Pair<>(new Bear(), new Bear().getMaxPopulation()));
        predatorInfo.add(new Pair<>(new Eagle(), new Eagle().getMaxPopulation()));
        predatorInfo.add(new Pair<>(new Fox(), new Fox().getMaxPopulation()));
        predatorInfo.add(new Pair<>(new Snake(), new Snake().getMaxPopulation()));
        predatorInfo.add(new Pair<>(new Wolf(), new Wolf().getMaxPopulation()));

        int totalMaxPopulation = predatorInfo.stream()
                .mapToInt(Pair::getRight)
                .sum();

        double proportion = (double) countPredators / totalMaxPopulation;

        List<Predator> predators = new ArrayList<>();

        for (Pair<Predator, Integer> info : predatorInfo) {
            int speciesCount = (int) Math.round(proportion * info.getRight());
            for (int i = 0; i < speciesCount; i++) {
                predators.add(info.getLeft());
            }
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

    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
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

    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

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
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public void setCountHerbivores(int countHerbivores) {
        this.countHerbivores = countHerbivores;
    }

    public void setCountPlants(int countPlants) {
        this.countPlants = countPlants;
    }

    public void setCountPredators(int countPredators) {
        this.countPredators = countPredators;
    }
}

