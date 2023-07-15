package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.plant.Plant;
import simulation.IslandSimulation;
import simulation.thread.StatisticsTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Задача для питания животных на острове
 */
public class AnimalEatTask implements Runnable {
    private final CountDownLatch latch;
    private int animalsEaten;

    /**
     * Конструктор класса
     * @param latch Счетчик CountDownLatch для синхронизации потоков
     */
    public AnimalEatTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<LifeForm> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getName().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getMaxHp() > 0) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<LifeForm> locationLifeForms = location.getLifeForms();

                    if (!locationLifeForms.isEmpty()) {
                        for (LifeForm lifeForm : locationLifeForms) {
                            if (currentAnimal.getChanceToEat(lifeForm.getName()) > 0 && !(lifeFormsEaten.contains(lifeForm))) {
                                boolean isEaten = currentAnimal.eat(lifeForm);

                                if (isEaten) {
                                    if (lifeForm instanceof Animal animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            IslandField.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                        }
                                        lifeFormsEaten.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) lifeForm;
                                        if (location.getPlants().size() > 0) {
                                            IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {
            System.out.printf("ВЫ ПРОИГРАЛИ! ВСЕ ЖИВОТНЫЕ УМЕРЛИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay());
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("ПОБЕДИЛИ ГУСЕНИЦЫ! В ЖИВЫХ ОСТАЛИСЬ ТОЛЬКО ОНИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay()); // так как они бесссмертные и не требуют пищи
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
    }
    public int getAnimalsEaten() {
        return animalsEaten;
    }
}
