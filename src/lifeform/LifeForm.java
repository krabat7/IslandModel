package lifeform;

public class LifeForm {
    private final double weight; // Вес животного/растения в кг
    private final int maxPopulation; // Максимальное количество вида животного/растения на 1 клетке
    private final String name; // Имя животного/растения
    private int row;
    private int column;

    /**
     * Конструктор класса LifeForm
     *
     * @param weight        Вес животного/растения в кг
     * @param maxPopulation Максимальное количество вида животного/растения на 1 клетке
     * @param name          Имя животного/растения
     */
    public LifeForm(double weight, int maxPopulation, String name) {
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }
    public int getMaxPopulation() {
        return maxPopulation;
    }
    public String getName() {
        return name;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}
