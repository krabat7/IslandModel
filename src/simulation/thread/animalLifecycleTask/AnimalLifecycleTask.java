package simulation.thread.animalLifecycleTask;

import field.IslandField;
import simulation.thread.StatisticsTask;
import simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Move + !multiply + !eat / ГДЕ ТО ЗДЕСЬ СОЗДАТЬ ЕЩЕ 1 ПУЛ для того чтобы hp отнимались
public class AnimalLifecycleTask implements Runnable{
    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new AnimalEatTask()); // животное ест
        executorService.submit(new AnimalMultiplyTask()); // животное размножается
        executorService.submit(new StatisticsTask()); // статистика по острову

        // join
        // move
    }
}
