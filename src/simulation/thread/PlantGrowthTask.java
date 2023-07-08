package simulation.thread;

import simulation.IslandSimulation;

// rost rastenii
public class PlantGrowthTask implements Runnable{
    @Override
    public void run() {
        IslandSimulation.getInstance().placePlants(50);
    }
}
