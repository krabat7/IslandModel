package lifeform.animal.herbivore;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 1, 0.01, 500, "Mouse");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Mouse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Mouse(), location.getRow(), location.getColumn());
        }
    }
}