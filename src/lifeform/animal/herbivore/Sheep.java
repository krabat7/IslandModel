package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Sheep extends Herbivore {
    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}
