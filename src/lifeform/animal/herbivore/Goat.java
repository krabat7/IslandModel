package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Goat extends Herbivore {
    /**
     * Конструктор класса Goat.
     * Устанавливает значения характеристик для козы.
     */
    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }

    /**
     * Метод размножения козы.
     * Если партнер является козой, то создается новая коза и добавляется на поле.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}
