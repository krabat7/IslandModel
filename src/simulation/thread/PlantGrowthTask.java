package simulation.thread;

import simulation.IslandSimulation;

/**
 * Задача для роста растений на острове
 */
public class PlantGrowthTask implements Runnable {
    @Override
    public void run() {
        System.out.println("PLANT_START");
        int countPlants = 20;
        if (IslandSimulation.getInstance().getTimeNow() >= 2) {
            IslandSimulation.getInstance().placePlants(countPlants / 2);
        } else {
            IslandSimulation.getInstance().placePlants(countPlants);
        }
        System.out.println("PLANT_END");
    }
}
