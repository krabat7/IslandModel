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

        executorService.scheduleAtFixedRate(animalLifecycleTask, 5, 5, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 40, 80, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 7, 5, TimeUnit.SECONDS);
    }

    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();

        // Создаем по одному животному каждого вида
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

        // Генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                // Создаем экземпляр животного через рефлексию
                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }
    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();

        // Создаем по одному животному каждого вида
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());

        // Генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {
                // Создаем экземпляр животного через рефлексию
                Predator newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
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
                    System.out.println(herbivore + " coord " + row + " : " + column);
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
                    System.out.println(predator + " coord " + row + " : " + column);
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
                    System.out.println(plant + " coord " + row + " : " + column);
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

