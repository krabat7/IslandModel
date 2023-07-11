package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}
