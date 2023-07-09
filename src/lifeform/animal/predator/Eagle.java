package lifeform.animal.predator;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }

    @Override
    public boolean eat(Object food) {
        double chanceToEat;
        LifeForm lifeForm = null;
        boolean animalEatFood;

        if (food instanceof LifeForm) {
            lifeForm = (LifeForm) food;
        } else{
            try {
                throw new ObjectNotLifeFormException("Объект не является животными/растением.");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        String foodName = lifeForm.getName();

        switch (foodName) {
            case "Fox" -> chanceToEat = 0.1;
            case "Duck" -> chanceToEat = 0.8;
            case "Rabbit", "Mouse" -> chanceToEat = 0.9;
            default -> chanceToEat = 0;
        }

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;

        if (animalEatFood){
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения

            Location location = IslandField.getInstance().getLocation(lifeForm.getRow(), lifeForm.getColumn()); // Животное/растение удаляется из списка обиталей локации после съедения
            if (lifeForm instanceof Animal){
                Animal animal = (Animal) lifeForm;
                if (location.getAnimals().contains(animal)) {
                    location.removeAnimal(animal);
                }
            }else{
                if (location.getPlants().size() > 0) {
                    location.removePlant();
                }
            }
        }
        return animalEatFood;
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            location.addAnimal(new Eagle());
        }
    }
}
