package lifeform.animal.predator;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Snake extends Predator {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }

    /**
     * Возвращает шанс съесть указанную пищу для змеи.
     *
     * @param foodName Имя пищи
     * @return Шанс съесть пищу
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            default -> 0;
        };
    }

    /**
     * Метод для размножения змей.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake) {
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Snake(), location.getRow(), location.getColumn());
        }
    }
}
