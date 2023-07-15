package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;
import simulation.IslandSimulation;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Задача для уменьшения здоровья животных на острове
 */
public class AnimalHpDecreaseTask implements Runnable {
    private double percentOfHpToDecrease = 15;
    private final CountDownLatch latch;
    private int animalsDiedByHungry;

    /**
     * Конструктор класса
     * @param latch Счетчик CountDownLatch для синхронизации потоков
     */
    public AnimalHpDecreaseTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHp() > 0).toList();
        if (IslandSimulation.getInstance().getTimeNow() / 60 >= 3) {
            percentOfHpToDecrease = percentOfHpToDecrease * 2;
        }
        for (Animal animal : animals) {
            double hpToDecrease = animal.getMaxHp() * percentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);
            } else {
                Location location = IslandField.getInstance().getLocation(animal.getRow(), animal.getColumn());
                IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
