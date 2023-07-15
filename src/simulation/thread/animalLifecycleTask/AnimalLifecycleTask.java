package simulation.thread.animalLifecycleTask;

import simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import simulation.thread.animalLifecycleTask.task.AnimalHpDecreaseTask;
import simulation.thread.animalLifecycleTask.task.AnimalMoveTask;
import simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Задача для жизненного цикла животных на острове
 */
public class AnimalLifecycleTask implements Runnable {
    private final AnimalEatTask animalEatTask;
    private final AnimalMultiplyTask animalMultiplyTask;
    private final AnimalHpDecreaseTask animalHpDecreaseTask;
    private final CountDownLatch latch;

    public AnimalLifecycleTask() {
        latch = new CountDownLatch(3);
        animalEatTask = new AnimalEatTask(latch);
        animalMultiplyTask = new AnimalMultiplyTask(latch);
        animalHpDecreaseTask = new AnimalHpDecreaseTask(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(animalEatTask); // животное ест
        System.out.println("1");
        executorService.submit(animalMultiplyTask); // животное размножается
        System.out.println("2");
        executorService.submit(animalHpDecreaseTask); // уменьшение здоровья
        System.out.println("3");
        try {
            latch.await(); // ожидаем, пока CountDownLatch не достигнет нуля
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new AnimalMoveTask()); // животные двигаются на другие локации
        System.out.println("4");
    }

    public AnimalMultiplyTask getObjectMultiplyTask() {
        return animalMultiplyTask;
    }

    public AnimalEatTask getAnimalEatTask() {
        return animalEatTask;
    }

    public AnimalHpDecreaseTask getAnimalHpDecreaseTask() {
        return animalHpDecreaseTask;
    }
}
