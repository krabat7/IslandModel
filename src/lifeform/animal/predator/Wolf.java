package lifeform.animal.predator;


import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.animal.herbivore.Duck;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 3, 8, 30, "Wolf");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Horse", "Buffalo" -> 0.1;
            case "Deer", "WildBoar" -> 0.15;
            case "Duck"-> 0.4;
            case "Goat","Rabbit" -> 0.6;
            case "Sheep" -> 0.7;
            case "Mouse" -> 0.8;
            default -> 0;
        };
    }
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wolf){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Wolf(), location.getRow(), location.getColumn());
        }
    }
}
