package field;

import lifeform.LifeForm;
import lifeform.animal.Animal;
import lifeform.plant.Plant;

import java.util.*;

public class Location {
    private final int row;
    private final int column;
    private final List<Animal> animals;
    private final List<Plant> plants;

    /**
     * Конструктор класса Location.
     * Устанавливает значения строки и столбца для данного местоположения.
     *
     * @param row    Номер строки
     * @param column Номер столбца
     */
    public Location(int row, int column) {
        this.row = row;
        this.column = column;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    /**
     * Метод для добавления животного в данное местоположение.
     * Устанавливает координаты животного и добавляет его в список животных.
     *
     * @param animal Животное для добавления
     */
    public void addAnimal(Animal animal) {
        animal.setRow(row);
        animal.setColumn(column);

        animals.add(animal);
    }

    /**
     * Метод для удаления животного из данного местоположения.
     *
     * @param animal Животное для удаления
     */
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Метод для добавления растения в данное местоположение.
     * Устанавливает координаты растения и добавляет его в список растений.
     *
     * @param plant Растение для добавления
     */
    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setColumn(column);
        plants.add(plant);
    }

    /**
     * Метод для удаления растения из данного местоположения.
     *
     * @param plant Растение для удаления
     */
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    /**
     * Метод для получения списка растений в данном местоположении.
     *
     * @return Список растений
     */
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Метод для получения списка животных в данном местоположении.
     *
     * @return Список животных
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Метод для получения списка всех живых форм в данном местоположении.
     * Включает как животных, так и растения.
     *
     * @return Список живых форм
     */
    public List<LifeForm> getLifeForms() {
        List<LifeForm> lifeForms = new ArrayList<>();
        lifeForms.addAll(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
}
