package lifeform.animal.predator;

import lifeform.animal.Animal;

public abstract class Predator extends Animal {

    public Predator(double weight, int step, double maxFood, int maxPopulation) {
        super(weight, step, maxFood, maxPopulation);
    }
}
