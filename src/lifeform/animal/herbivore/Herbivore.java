package lifeform.animal.herbivore;

import error.ObjectNotLifeFormException;
import field.IslandField;
import field.Location;
import lifeform.LifeForm;
import lifeform.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
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
        System.out.println("HEB_EAT");
        String foodName = lifeForm.getName();

        switch (foodName) {
            case "Plant" -> chanceToEat = 1;
            default -> chanceToEat = 0;
        }
        System.out.println("HEB_EAT2");
        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        System.out.println("HEB_EAT3");
        if (animalEatFood){
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения
            System.out.println("HEB_EAT4");
            Location location = IslandField.getInstance().getLocation(lifeForm.getRow(), lifeForm.getColumn()); // Животное/растение удаляется из списка обиталей локации после съедения
            System.out.println("HEB_EAT41");
            if (lifeForm instanceof Animal){
                System.out.println("HEB_EAT421");
                Animal animal = (Animal) lifeForm;
                System.out.println("HEB_EAT422");
                if (location.getAnimals().contains(animal)) {
                    location.removeAnimal(animal);
                }
                System.out.println("HEB_EAT42");
            }else{
                System.out.println("HEB_EAT431");
                if (location.getPlants().size() > 0) {
                    System.out.println("HEB_EAT4310");
                    location.removePlant();
                }
                System.out.println("HEB_EAT43");
            }
        }
        System.out.println("HEB_EAT5");
        return animalEatFood;
    }
}
