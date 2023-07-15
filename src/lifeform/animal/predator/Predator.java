package lifeform.animal.predator;

import lifeform.animal.Animal;

public abstract class Predator extends Animal {

    /**
     * Конструктор класса Predator.
     *
     * @param weight        Вес хищника
     * @param step          Шаг перемещения
     * @param maxHp         Максимальное количество здоровья
     * @param maxPopulation Максимальное количество особей
     * @param name          Название хищника
     */
    public Predator(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
}
