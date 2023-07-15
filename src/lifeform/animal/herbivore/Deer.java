package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Deer extends Herbivore {
    /**
     * Конструктор класса Deer.
     * Устанавливает значения характеристик для оленя.
     */
    public Deer() {
        super(300, 4, 50, 20, "Deer");
    }

    /**
     * Метод размножения оленя.
     * Если партнер является оленем, то создается новый олень и добавляется на поле.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Deer){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Deer(), location.getRow(), location.getColumn());
        }
    }
}
