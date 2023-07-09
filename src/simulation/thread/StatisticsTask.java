package simulation.thread;

import field.IslandField;
import simulation.IslandSimulation;
import simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import simulation.thread.animalLifecycleTask.task.AnimalHpDecreaseTask;
import simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

public class StatisticsTask implements Runnable{
    private boolean isTimeOver;
    private int babies;
    private int animalsDiedOfHunger;
    private int countAnimalsEnd;
    private int animalsAreEaten;
    private int countPlants;
    @Override
    public void run() {
        long timeNow = IslandSimulation.getInstance().getTimeNow();
        isTimeOver = checkTime(timeNow);

        AnimalMultiplyTask animalMultiplyTask = new AnimalMultiplyTask();
        AnimalEatTask animalEatTask = new AnimalEatTask();
        AnimalHpDecreaseTask animalHpDecreaseTask = new AnimalHpDecreaseTask();

        babies = animalMultiplyTask.getBabies().get();
        animalsDiedOfHunger = animalHpDecreaseTask.getAnimalsDiedOfHunger().get();
        countAnimalsEnd = babies + animalEatTask.getCountAnimals().get() - animalsDiedOfHunger;
        animalsAreEaten = animalEatTask.getAnimalsAreEaten().get();
        countPlants = IslandField.getInstance().getAllPlants().size();

        printStats();

        if (isTimeOver) {
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }
    private boolean checkTime(long timeNow){
        return timeNow / 60 >= 5;
    }
    private void printStats(){
        System.out.println();

        if (isTimeOver) {
            System.out.println("ПОБЕДА!!! ВЫ ПРОДЕРЖАЛИСЬ 5 МИНУТ!");
            System.out.println("----------------------------------");
        } else {
            System.out.printf("Время с начала старта: %s сек.", IslandSimulation.getInstance().getTimeNow());
            System.out.println();
        }

        System.out.println("СТАТИСТИКА ПО ОСТРОВУ");
        System.out.println();

        System.out.print("Животных на острове: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Животных съедено: ");
        System.out.println(animalsAreEaten);

        System.out.print("Животных умерло от голода: ");
        System.out.println(animalsDiedOfHunger);

        System.out.print("Детенышей появилось на свет: ");
        System.out.println(babies);

        System.out.print("Растений на острове: ");
        System.out.println(countPlants);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }
}