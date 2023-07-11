package lifeform.animal.herbivore;

import lifeform.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Plant" -> 1;
            default -> 0;
        };
    }
}
