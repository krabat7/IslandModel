package lifeform.animal.herbivore;

import lifeform.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int step, double maxFood, int maxPopulation) {
        super(weight, step, maxFood, maxPopulation);
    }
}
