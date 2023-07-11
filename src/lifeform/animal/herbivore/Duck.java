package lifeform.animal.herbivore;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" ->  1;
            default ->  0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Duck){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
