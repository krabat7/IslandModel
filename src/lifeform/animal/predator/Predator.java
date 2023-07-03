package lifeform.animal.predator;

import lifeform.animal.Animal;

public abstract class Predator extends Animal {

    public Predator(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
}
