package lifeform.animal.predator;

import lifeform.animal.Animal;

abstract class Predator extends Animal {
    public Predator(double weight, int step, double maxFood) {
        super(weight, step, maxFood);
    }
}
