package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class WildBoar extends Herbivore {
    /**
     * Конструктор класса WildBoar.
     * Устанавливает значения характеристик для дикой свиньи.
     */
    public WildBoar() {
        super(400, 2, 50, 50, "WildBoar");
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
            case "Mouse" -> 0.5;
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    /**
     * Размножается с партнером.
     * Если партнером является дикая свинья, создается новая дикая свинья на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof WildBoar){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new WildBoar(), location.getRow(), location.getColumn());
        }
    }
}
