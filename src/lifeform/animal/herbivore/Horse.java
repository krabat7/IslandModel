package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Horse extends Herbivore {
    /**
     * Конструктор класса Horse.
     * Устанавливает значения характеристик для лошади.
     */
    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }

    /**
     * Размножается с партнером.
     * Если партнером является лошадь, создается новая лошадь на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}
