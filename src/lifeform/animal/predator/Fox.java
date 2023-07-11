package lifeform.animal.predator;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.animal.herbivore.Duck;

import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {
    public Fox() {
        super(8, 2, 2, 30, "Fox");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
