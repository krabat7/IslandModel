package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Mouse extends Herbivore {
    /**
     * Конструктор класса Mouse.
     * Устанавливает значения характеристик для мыши.
     */
    public Mouse() {
        super(0.05, 1, 0.01, 500, "Mouse");
    }

    /**
     * Возвращает шанс съесть определенную пищу.
     *
     * @param foodName Название пищи
     * @return Шанс съесть пищу
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
     * Размножается с партнером.
     * Если партнером является мышь, создается новая мышь на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Mouse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Mouse(), location.getRow(), location.getColumn());
        }
    }
}
