package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.plant.Plant;
import simulation.IslandSimulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AnimalEatTask implements Runnable {
    private final CountDownLatch latch;

    public AnimalEatTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("EAT_START");

        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        List<LifeForm> lifeFormsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();

                System.out.println("Животное: " + currentAnimal + " координаты -> " + currentAnimal.getRow() + ":" + currentAnimal.getColumn());
                if (currentAnimal.getMaxHp() > 0) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<LifeForm> locationLifeForms = location.getLifeForms();
                    System.out.println("SIZE: " + locationLifeForms.size());

                    if (!locationLifeForms.isEmpty()) {
                        for (LifeForm lifeForm : locationLifeForms) {
                            System.out.println("Возможно еда: " + lifeForm + " координаты -> " + lifeForm.getRow() + ":" + lifeForm.getColumn());
                            if (currentAnimal.getChanceToEat(lifeForm.getName()) > 0 && !(lifeFormsEaten.contains(lifeForm))) {
                                System.out.println("Еда: " + lifeForm);
                                boolean isEaten = currentAnimal.eat(lifeForm);

                                if (isEaten) {
                                    if (lifeForm instanceof Animal) {
                                        Animal animalEaten = (Animal) lifeForm;
                                        if (location.getAnimals().contains(animalEaten)) {
                                            IslandField.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                            System.out.println("wtf");
                                        }
                                        lifeFormsEaten.add(animalEaten);
                                        System.out.println("wtf3");
                                    } else{
                                        Plant plant = (Plant) lifeForm;
                                        if (location.getPlants().size() > 0) {
                                            IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                        }
                                    }
                                    System.out.println("Съеден");
                                }
                                System.out.println("Попытка засчитана");
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
                System.out.println("Перебрал всю еду");
            }
        } else {
            System.out.println("ВСЕ ЖИВОТНЫЕ УМЕРЛИ!");
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
        System.out.println("END_EAT");
    }
}
