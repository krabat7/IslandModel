package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Buffalo extends Herbivore {
    /**
     * Конструктор класса Buffalo.
     * Устанавливает значения характеристик для буйвола.
     */
    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    /**
     * Метод размножения буйвола.
     * Если партнер является буйволом, то создается новый буйвол и добавляется на поле.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}
