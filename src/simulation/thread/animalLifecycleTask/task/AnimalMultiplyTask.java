package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalMultiplyTask implements Runnable{
    final private AtomicInteger babies;
    public AnimalMultiplyTask(){
        babies = new AtomicInteger();
    }
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
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
            //exit programmu
        }
    }

    public AtomicInteger getBabies() {
        return babies;
    }
}
