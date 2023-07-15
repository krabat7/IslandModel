package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Задача для размножения животных на острове
 */
public class AnimalMultiplyTask implements Runnable {
    private int babies;
    private final CountDownLatch latch;

    public AnimalMultiplyTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        babies = 0;
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }

    public int getBabies() {
        return babies;
    }
}
