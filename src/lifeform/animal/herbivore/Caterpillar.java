package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Caterpillar extends Herbivore {
    /**
     * Конструктор класса Caterpillar.
     * Устанавливает значения характеристик для гусеницы.
     */
    public Caterpillar() {
        super(0.01, 0, 0, 1000, "Caterpillar");
    }

    /**
     * Метод размножения гусеницы.
     * Если партнер является гусеницей, то создается новая гусеница и добавляется на поле.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Caterpillar(), location.getRow(), location.getColumn());
        }
    }
}
