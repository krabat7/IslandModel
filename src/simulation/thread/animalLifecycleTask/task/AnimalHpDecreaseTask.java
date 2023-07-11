package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalHpDecreaseTask implements Runnable {
    final private double percentOfHpToDecrease = 15;
    private CountDownLatch latch;

    public AnimalHpDecreaseTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("HP_START");
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHp() > 0).toList();
        for (Animal animal : animals) {
            double hpToDecrease = animal.getMaxHp() * percentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                System.out.println("HP MAX: " + animal.getMaxHp() + "/ Осталось HP: " + (animal.getHp() - hpToDecrease));
                animal.setHp(animal.getHp() - hpToDecrease);
                System.out.println("Отнимаем здоровье");
            } else {
                System.out.println("Животное должно умереть от голода");
                Location location = IslandField.getInstance().getLocation(animal.getRow(), animal.getColumn());
                IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                System.out.println("Животное умерло от голода");
            }
        }
        latch.countDown();
        System.out.println("HP_END");
    }
}
