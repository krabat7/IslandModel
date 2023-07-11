package lifeform.animal.predator;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.animal.herbivore.Duck;

import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Fox" -> 0.1;
            case "Duck" -> 0.8;
            case "Rabbit", "Mouse" -> 0.9;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}
