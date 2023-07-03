package lifeform.animal.predator;

import error.ObjectNotFoundException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
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
        LifeForm lifeForm = null;

        if (food instanceof LifeForm) {
            lifeForm = (LifeForm) food;
        } else{
            try {
                throw new ObjectNotFoundException("Объект не является животными/растением.");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        String foodName = lifeForm.getName();

        switch (foodName) {
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

        if (animalEatFood){
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения

            Location location = IslandField.getInstance().getLocation(getRow(), getColumn()); // Животное/растение удаляется из списка обиталей локации после съедения
            if (lifeForm instanceof Animal){
                Animal animal = (Animal) lifeForm;
                location.removeAnimal(animal);
            }else{
                location.removePlant();
            }
        }
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
