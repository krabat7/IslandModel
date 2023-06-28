package lifeform.animal;

import field.Location;

public abstract class Animal {
    double weight; // Вес одного животного, кг
    int step; // Скорость перемещения, не более чем, клеток за ход
    double maxFood; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    int maxPopulation; // Максимальное количество вида животного на 1 клетке
    public Animal(double weight, int step, double maxFood, int maxPopulation){
        this.weight = weight;
        this.step = step;
        this.maxFood = maxFood;
        this.maxPopulation = maxPopulation;
    }

    public double getWeight() {
        return weight;
    }
    public int getStep() {
        return step;
    }
    public double getMaxFood() {
        return maxFood;
    }
    public int getMaxPopulation() {
        return maxPopulation;
    }

    public abstract void eat(Object food);
    public abstract void multiply(Animal partner);
    public abstract void move(Location[][] locations);
}
