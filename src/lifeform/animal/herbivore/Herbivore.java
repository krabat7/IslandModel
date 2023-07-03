package lifeform.animal.herbivore;

import lifeform.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
}
