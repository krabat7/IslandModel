package simulation;

import field.IslandField;
import field.Location;
import lifeform.animal.herbivore.*;
import lifeform.animal.predator.*;
import lifeform.plant.Plant;
import simulation.thread.PlantGrowthTask;
import simulation.thread.StatisticsTask;
import simulation.thread.animalLifecycleTask.AnimalLifecycleTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Класс, моделирующий островную экосистему
 */
public class IslandSimulation {
    private final long startTime;
    private final int countHerbivores = 35;
    private final int countPlants = 40;
    private final int countPredators = 20;
    private static volatile IslandSimulation instance;
    private volatile ScheduledExecutorService executorService;

    private IslandSimulation() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Получить экземпляр класса IslandSimulation (Singleton)
     *
     * @return instance Экземпляр класса IslandSimulation
     */
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

    /**
     * Создать модель острова с заданным количеством травоядных, хищников и растений
     *
     * @param countHerbivores Количество травоядных
     * @param countPredators  Количество хищников
     * @param countPlants     Количество растений
     */
    public void createIslandModel(int countHerbivores, int countPredators, int countPlants) {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    /**
     * Создать модель острова с количеством травоядных, хищников и растений, заданным по умолчанию
     */
    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    /**
     * Запустить модель острова
     */
    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask(animalLifecycleTask.getAnimalEatTask(), animalLifecycleTask.getAnimalHpDecreaseTask(), animalLifecycleTask.getObjectMultiplyTask());

        executorService.scheduleAtFixedRate(animalLifecycleTask, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statisticsTask, 0, 8, TimeUnit.SECONDS);
    }

    /**
     * Создать список травоядных с заданным количеством
     *
     * @param countHerbivores Количество травоядных
     * @return herbivores Список травоядных
     */
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

    /**
     * Создать список хищников с заданным количеством
     *
     * @param countPredators Количество хищников
     * @return predators Список хищников
     */
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

    /**
     * Создать список растений с заданным количеством
     *
     * @param countPlants Количество растений
     * @return plants Список растений
     */
    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    /**
     * Разместить травоядных на острове
     *
     * @param countHerbivores Количество травоядных
     */
    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()) {
                    IslandField.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Разместить хищников на острове
     *
     * @param countPredators Количество хищников
     */
    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(predator.getName())).toList().size() <= predator.getMaxPopulation()) {
                    IslandField.getInstance().addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Разместить растения на острове
     *
     * @param countPlants Количество растений
     */
    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    IslandField.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Получить текущее время симуляции
     *
     * @return timeNow Текущее время симуляции
     */
    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountPredators() {
        return countPredators;
    }
}
