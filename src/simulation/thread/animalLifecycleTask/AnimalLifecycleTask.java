package simulation.thread.animalLifecycleTask;

import simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import simulation.thread.animalLifecycleTask.task.AnimalHpDecreaseTask;
import simulation.thread.animalLifecycleTask.task.AnimalMoveTask;
import simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalLifecycleTask implements Runnable{
    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(2); // Создаем CountDownLatch с числом 2

        executorService.submit(new AnimalEatTask(latch)); // животное ест
        System.out.println("1");
        executorService.submit(new AnimalMultiplyTask(latch)); // животное размножается
        System.out.println("2");
        try {
            latch.await(); // Ожидаем, пока CountDownLatch не достигнет нуля
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.submit(new AnimalHpDecreaseTask()); // уменьшение здоровья
        System.out.println("3");
        executorService.submit(new AnimalMoveTask()); // животные двигаются на другие локации
        System.out.println("4");
    }
}
