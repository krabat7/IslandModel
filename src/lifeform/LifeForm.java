package lifeform;

public class LifeForm {
    public double weight; // Вес животного/растения в кг
    public int maxPopulation; // Максимальное количество вида животного/растения на 1 клетке
    public String name; // Имя животного/растения
    private int row;
    private int column;
    public LifeForm(double weight, int maxPopulation, String name){
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
