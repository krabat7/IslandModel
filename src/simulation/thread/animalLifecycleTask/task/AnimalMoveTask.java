package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import lifeform.animal.Animal;

import java.util.List;

public class AnimalMoveTask implements Runnable{
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals();
        System.out.println("MOVE");
        for (Animal animal : animals){
            animal.move();
        }
        System.out.println("MOVE2");
    }
}
