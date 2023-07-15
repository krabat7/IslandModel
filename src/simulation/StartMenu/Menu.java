package simulation.StartMenu;

import field.IslandField;
import simulation.IslandSimulation;

/**
 * Класс для работы с меню начала симуляции
 */
public class Menu {
    private final Parameters parameters;

    /**
     * Конструктор класса
     */
    public Menu() {
        parameters = new Parameters();
    }

    /**
     * Метод для запуска симуляции
     */
    public void startSimulation() {
        System.out.println("----------------------------------");
        System.out.println("Хотите ли вы внести свои параметры перед началом симуляции?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int answer = parameters.takeInt(1, 2);
        if (answer == 1) {
            parameters.changeParameters();
        } else {
            IslandField.getInstance().initializeLocations();
            IslandSimulation.getInstance().createIslandModel();
        }
        System.out.println("----------------------------------");
        System.out.println("Загрузка симуляции острова...");
        System.out.println("----------------------------------");
        System.out.println();
    }
}
