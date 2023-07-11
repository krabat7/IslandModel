package simulation.thread;

import field.IslandField;
import simulation.IslandSimulation;
import simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import simulation.thread.animalLifecycleTask.task.AnimalHpDecreaseTask;
import simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

public class StatisticsTask implements Runnable{
    private boolean isTimeOver;
    private int babies;
    private int animalsDied;
    private int countAnimalsEnd;
    private int animalsWere;
    private int countPlants;
    @Override
    public void run() {
        long timeNow = IslandSimulation.getInstance().getTimeNow();
        isTimeOver = checkTime(timeNow);

        babies = new AnimalMultiplyTask().getBabies();
        countAnimalsEnd = IslandField.getInstance().getAllAnimals().size();
        animalsDied += Math.max(-(countAnimalsEnd - animalsWere), 0);
        countPlants = IslandField.getInstance().getAllPlants().size();
        animalsWere = countAnimalsEnd;
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

        System.out.print("Животных умерло: ");
        System.out.println(animalsDied);

        System.out.print("Детенышей появилось на свет: ");
        System.out.println(babies);

        System.out.print("Растений на острове: ");
        System.out.println(countPlants);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }
}