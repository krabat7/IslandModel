package lifeform.animal.predator;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Fox extends Predator {
    /**
     * Конструктор класса Fox.
     * Устанавливает значения характеристик для лисы.
     */
    public Fox() {
        super(8, 2, 2, 30, "Fox");
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
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Размножается с партнером.
     * Если партнером является лиса, создается новая лиса на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
