package lifeform.animal.predator;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.animal.herbivore.Duck;

import java.util.concurrent.ThreadLocalRandom;

public class Snake extends Predator {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            default -> 0;
        };
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Snake(), location.getRow(), location.getColumn());
        }
    }
}
