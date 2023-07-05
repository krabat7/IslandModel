package lifeform.animal.herbivore;

import field.IslandField;
import field.Location;
import lifeform.animal.Animal;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            location.addAnimal(new Rabbit());
        }
    }
}
