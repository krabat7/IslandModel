package lifeform.animal.predator;

import field.Location;
import lifeform.animal.Animal;
import lifeform.plant.Plant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator {
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    @Override
    public void eat(Object food) {
        double chanceToEat;
        String name = null;

        if (food instanceof Animal) {
            name = ((Animal) food).getName();
        }
        if (food instanceof Plant) {
            name = ((Plant) food).getName();
        }

        switch (name) {
            case "Duck" -> chanceToEat = 0.1;
            case "Buffalo" -> chanceToEat = 0.2;
            case "Horse" -> chanceToEat = 0.4;
            case "WildBoar" -> chanceToEat = 0.5;
            case "Goat", "Sheep" -> chanceToEat = 0.7;
            case "Deer", "Rabbit", "Snake" -> chanceToEat = 0.8;
            case "Mouse" -> chanceToEat = 0.9;
            default -> chanceToEat = 0;
        }

        boolean animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
}

    @Override
    public void multiply(Animal partner) {

    }

    @Override
    public void move(Location[][] locations) {
        Random rnd = ThreadLocalRandom.current();
        int row = rnd.nextInt();
        int column = rnd.nextInt();
    }
}
