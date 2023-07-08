package simulation.thread;

import field.IslandField;
import simulation.IslandSimulation;
import simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

public class StatisticsTask implements Runnable{
    @Override
    public void run() {
        AnimalMultiplyTask animalMultiplyTask = new AnimalMultiplyTask();
        AnimalEatTask animalEatTask = new AnimalEatTask();

        int babies = animalMultiplyTask.getBabies().get();
        int countAnimalsEnd = babies + animalEatTask.getCountAnimals().get();
        int animalsAreEaten = animalEatTask.getAnimalsAreEaten().get();
        int countPlants = IslandField.getInstance().getAllPlants().size();

        System.out.print("Время с начала старта: ");
        System.out.println(IslandSimulation.getInstance().getTimeNow());

        System.out.print("Животные на острове: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Животных съедено: ");
        System.out.println(animalsAreEaten);

        System.out.print("Детенышей появилось на свет: ");
        System.out.println(babies);

        System.out.print("Растений на острове: ");
        System.out.println(countPlants);
    }
}