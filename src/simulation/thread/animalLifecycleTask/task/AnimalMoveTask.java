package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import lifeform.animal.Animal;

import java.util.List;

public class AnimalMoveTask implements Runnable{
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        System.out.println("MOVE");
        for (Animal animal : animals){
            System.out.println(animal + " РРРРРРРРРР " + animal.getRow() + " РРРРРРРР " + animal.getColumn());
            animal.move();
            System.out.println(animal + " HAHAHAHAH " + animal.getRow() + " HAHAHAH " + animal.getColumn());
        }
        System.out.println("MOVE_END");
    }
}
