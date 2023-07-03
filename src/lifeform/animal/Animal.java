package lifeform.animal;

import field.Location;
import lifeform.LifeForm;

public abstract class Animal extends LifeForm {
    final private int step; // Скорость перемещения, не более чем, клеток за ход
    final private double maxHp; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    private double hp; // Количество здоровья животного
    private int row;
    private int column;
    public Animal(double weight, int step, double maxHp, int maxPopulation, String name){
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp; // На старте максимальное количество здоровья
    }

    public abstract void eat(Object food);
    public abstract void multiply(Animal partner);
    public abstract void move(Location[][] locations);

    public int getStep() {
        return step;
    }

    public double getHp() {
        return hp;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public double getMaxHp() {
        return maxHp;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}
