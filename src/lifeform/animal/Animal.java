package lifeform.animal;

import field.IslandField;
import lifeform.LifeForm;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends LifeForm {
    final private int step; // Скорость перемещения, не более чем, клеток за ход
    final private double maxHp; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    private double hp; // Количество здоровья животного
    public Animal(double weight, int step, double maxHp, int maxPopulation, String name){
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp; // На старте максимальное количество здоровья
    }

    public abstract void eat(Object food);
    public abstract void multiply(Animal partner);
    public void move() {
        int randomCells = ThreadLocalRandom.current().nextInt(1, getStep() + 1);

        // Генерируем случайное направление (вверх, вниз, влево или вправо)
        int direction = ThreadLocalRandom.current().nextInt(4);

        // Вычисляем новые координаты в зависимости от направления
        int newRow = getRow();
        int newColumn = getColumn();

        switch (direction) {
            case 0 -> // Вверх
                    newRow -= randomCells;
            case 1 -> // Вниз
                    newRow += randomCells;
            case 2 -> // Влево
                    newColumn -= randomCells;
            case 3 -> // Вправо
                    newColumn += randomCells;
        }

        // Проверяем, чтобы новые координаты не выходили за границы поля
        while (newRow < 0 || IslandField.getInstance().getNumRows() >= 20 || newColumn < 0 || newColumn >= IslandField.getInstance().getNumColumns()) {
            randomCells = ThreadLocalRandom.current().nextInt(1, getStep() + 1);
            direction = ThreadLocalRandom.current().nextInt(4);

            newRow = getRow();
            newColumn = getColumn();

            switch (direction) {
                case 0 -> // Вверх
                        newRow -= randomCells;
                case 1 -> // Вниз
                        newRow += randomCells;
                case 2 -> // Влево
                        newColumn -= randomCells;
                case 3 -> // Вправо
                        newColumn += randomCells;
            }
        }

        // Обновляем новые координаты
        setRow(newRow);
        setColumn(newColumn);
    }

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
}
