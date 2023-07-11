package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Horse extends Herbivore {
    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}
