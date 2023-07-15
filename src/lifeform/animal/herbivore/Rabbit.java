package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Rabbit extends Herbivore {
    /**
     * Конструктор класса Rabbit.
     * Устанавливает значения характеристик для кролика.
     */
    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }

    /**
     * Размножается с партнером.
     * Если партнером является кролик, создается новый кролик на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getColumn());
        }
    }
}
