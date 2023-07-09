package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalHpDecreaseTask implements Runnable{
    final private int percentOfHpToDecrease = 10;
    final private AtomicInteger animalsDiedOfHunger;
    private CountDownLatch latch;
    public AnimalHpDecreaseTask(CountDownLatch latch){
        animalsDiedOfHunger = new AtomicInteger();
        this.latch = latch;
    }
    public AnimalHpDecreaseTask(){
        animalsDiedOfHunger = new AtomicInteger();
    }
    @Override
    public void run() {
        List<Animal> animals =  IslandField.getInstance().getAllAnimals();
        System.out.println("HP");
        for(Animal animal : animals){
            if (!animal.getName().equals("Caterpillar")) {
                double hpToDecrease = animal.getMaxHp() / percentOfHpToDecrease;
                if (animal.getHp() - hpToDecrease > 0) {
                    animal.setHp(animal.getHp() - hpToDecrease);
                } else {
                    Location location = IslandField.getInstance().getLocation(animal.getRow(), animal.getColumn());
                    location.removeAnimal(animal);
                    animalsDiedOfHunger.incrementAndGet();
                }
            }
        }
        latch.countDown();
        System.out.println("HP_END");
    }

    public AtomicInteger getAnimalsDiedOfHunger() {
        return animalsDiedOfHunger;
    }
}
