package simulation.thread.animalLifecycleTask.task;

import field.IslandField;
import lifeform.animal.Animal;

import java.util.List;

/**
 * Задача для перемещения животных на острове
 */
public class AnimalMoveTask implements Runnable {
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
