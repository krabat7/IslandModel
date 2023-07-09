package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import simulation.IslandSimulation;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimalEatTask implements Runnable {
    final private AtomicInteger countAnimals;
    final private AtomicInteger animalsAreEaten;
    private CountDownLatch latch;

    public AnimalEatTask(CountDownLatch latch){
        countAnimals = new AtomicInteger();
        animalsAreEaten = new AtomicInteger();
        this.latch = latch;
    }
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
                if (currentAnimal.getMaxHp() > 0) {
                    System.out.println("START_EAT31");
                    System.out.println(currentAnimal);
                    System.out.println(currentAnimal.getRow());
                    System.out.println(currentAnimal.getColumn());
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    System.out.println("EAT32");
                    Random random = ThreadLocalRandom.current();
                    System.out.println("EAT33");
                    List<LifeForm> locationLifeForms = location.getLifeForms();
                    System.out.println("EAT34");
                    if (locationLifeForms.size() > 0) {
                        LifeForm lifeFormToEat = locationLifeForms.get(random.nextInt(locationLifeForms.size()));
                        System.out.println("EAT35");
                        System.out.println(currentAnimal + " __ " + lifeFormToEat);
                        System.out.println();
                        boolean isEaten = currentAnimal.eat(lifeFormToEat);
                        System.out.println("EAT355");
                        if (isEaten) {
                            animals.remove(lifeFormToEat);
                            animalsAreEaten.incrementAndGet(); // Кол-во съеденных животных
                        }
                        System.out.println("EAT36");
                        animals.remove(currentAnimal);
                        System.out.println("EAT37");
                    }
                }
            }
        } else {
            System.out.println("EATGGGGGG");
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        System.out.println("EAT4");
        countAnimals.set(countAnimalsStart - animalsAreEaten.get()); // Кол-во животных на острове
        latch.countDown();
        System.out.println("END_EAT");
    }

    public AtomicInteger getAnimalsAreEaten() {
        return animalsAreEaten;
    }

    public AtomicInteger getCountAnimals() {
        return countAnimals;
    }
}
