package lifeform.animal.herbivore;

import lifeform.animal.Animal;

public abstract class Herbivore extends Animal {
    /**
     * Конструктор класса Herbivore.
     * Устанавливает значения характеристик для травоядного животного.
     *
     * @param weight        Вес животного
     * @param step          Шаг передвижения животного
     * @param maxHp         Максимальное количество очков здоровья животного
     * @param maxPopulation Максимальное количество животных данного вида на острове
     * @param name          Название вида животного
     */
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }

    /**
     * Получает вероятность поедания определенного вида пищи.
     * Для травоядных животных вероятность поедания растений равна 1, для всех остальных видов пищи вероятность равна 0.
     *
     * @param foodName Название вида пищи
     * @return Вероятность поедания пищи
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Plant" -> 1;
            default -> 0;
        };
    }
}
