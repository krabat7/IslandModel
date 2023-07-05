package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Goat extends Herbivore {
    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            location.addAnimal(new Goat());
        }
    }
}
