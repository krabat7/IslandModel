package lifeform.animal.predator;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Bear extends Predator {
    /**
     * Конструктор класса Bear.
     * Устанавливает значения характеристик для медведя.
     */
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    /**
     * Получает шанс съесть определенный вид пищи.
     *
     * @param foodName Название пищи
     * @return Шанс съесть пищу
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            case "Horse" -> 0.4;
            case "WildBoar" -> 0.5;
            case "Goat", "Sheep" -> 0.7;
            case "Deer", "Rabbit", "Snake" -> 0.8;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Размножается с партнером.
     * Если партнером является медведь, создается новый медведь на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn());
        }
    }
}
