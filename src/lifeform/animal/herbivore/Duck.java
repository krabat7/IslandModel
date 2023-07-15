package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Duck extends Herbivore {
    /**
     * Конструктор класса Duck.
     * Устанавливает значения характеристик для утки.
     */
    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }

    /**
     * Метод, возвращающий вероятность поедания определенной пищи.
     *
     * @param foodName Название пищи
     * @return Вероятность поедания
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    /**
     * Метод размножения утки.
     * Если партнер является уткой, то создается новая утка и добавляется на поле.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Duck){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
