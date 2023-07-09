package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;
import simulation.IslandSimulation;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalMultiplyTask implements Runnable{
    final private AtomicInteger babies;
    private CountDownLatch latch;
    public AnimalMultiplyTask(CountDownLatch latch){
        babies = new AtomicInteger();
        this.latch = latch;
    }
    public AnimalMultiplyTask(){
        babies = new AtomicInteger();
    }
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        System.out.println("MULT");
        if (animals.size() > 0) {   //проверка на умерли ли все животные
            for (int i = 0; i < animals.size(); i++) {
                Animal currentAnimal = animals.get(i);
                Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());

                List<Animal> locationAnimals = location.getAnimals();
                if (locationAnimals.size() > 0) {
                    locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();
                    if (locationAnimals.size() > 0) {
                        Animal partner = locationAnimals.get(0);
                        currentAnimal.multiply(partner);

                        animals.remove(currentAnimal);
                        animals.remove(partner);

                        babies.incrementAndGet();
                    }
                }
            }
        } else {
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
        System.out.println("MULT2");
    }

    public AtomicInteger getBabies() {
        return babies;
    }
}
