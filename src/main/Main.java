package main;

import simulation.IslandSimulation;

public class Main {
    public static void main(String[] args) {
        printStartWords();
        IslandSimulation.getInstance();
    }
    private static void printStartWords(){
        System.out.println("--------------------------");
        System.out.println("Загрузка симуляции острова...");
        System.out.println("--------------------------");
        System.out.println();
    }
}
