package simulation.thread;

import simulation.IslandSimulation;

public class PlantGrowthTask implements Runnable{
    @Override
    public void run() {
        IslandSimulation.getInstance().placePlants(10);
        System.out.println("PLANT");
    }
}
