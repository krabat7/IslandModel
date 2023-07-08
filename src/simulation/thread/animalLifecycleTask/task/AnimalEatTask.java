package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalEatTask implements Runnable {
    final private AtomicInteger countAnimals;
    final private AtomicInteger animalsAreEaten;

    public AnimalEatTask(){
        countAnimals = new AtomicInteger();
        animalsAreEaten = new AtomicInteger();
    }
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        int countAnimalsStart = animals.size();
        if (countAnimalsStart > 0) {   //проверка на умерли ли все животные
            for (int i = 0; i < animals.size(); i++) {
                Animal currentAnimal = animals.get(i);
                Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());

                Random random = ThreadLocalRandom.current();
                List<LifeForm> locationLifeForms = location.getLifeForms();
                LifeForm lifeFormToEat = locationLifeForms.get(random.nextInt(locationLifeForms.size()));

                boolean isEaten = currentAnimal.eat(lifeFormToEat);
                if (isEaten) {
                    animals.remove(lifeFormToEat);
                    animalsAreEaten.incrementAndGet(); // Кол-во съеденных животных
                }

                animals.remove(currentAnimal);
            }
        } else {
            //exit programmu
        }
        countAnimals.set(countAnimalsStart - animalsAreEaten.get()); // Кол-во животных на острове
    }

    public AtomicInteger getAnimalsAreEaten() {
        return animalsAreEaten;
    }

    public AtomicInteger getCountAnimals() {
        return countAnimals;
    }
}
