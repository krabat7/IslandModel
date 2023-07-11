package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import simulation.IslandSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalMultiplyTask implements Runnable{
    private int babies;
    private CountDownLatch latch;
    public AnimalMultiplyTask(CountDownLatch latch){
        this.latch = latch;
    }
    public AnimalMultiplyTask(){

    }
    @Override
    public void run() {
        System.out.println("MULT_START");
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {   //проверка на умерли ли все животные
            for (Animal currentAnimal : animals) {
                if (!(animalsMultiplied.contains(currentAnimal))) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!(animalsMultiplied.contains(partner))) {
                                currentAnimal.multiply(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        } else {
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
        System.out.println("Babies: " + babies);
        System.out.println("MULT_END");
    }

    public int getBabies() {
        return babies;
    }
}
