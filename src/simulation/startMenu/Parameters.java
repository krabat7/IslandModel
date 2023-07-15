package simulation.startMenu;

import field.IslandField;
import simulation.IslandSimulation;

import java.util.Scanner;

/**
 * Класс для изменения параметров симуляции на старте
 */
public class Parameters {
    /**
     * Метод для изменения параметров симуляции
     */
    public void changeParameters() {
        changeIslandSize();
        int countPredators = changePredatorsSize();
        int countHerbivores = changeHerbivoresSize();
        int countPlants = changePlantsSize();

        if (countHerbivores == 0) {
            countHerbivores = IslandSimulation.getInstance().getCountHerbivores();
        }
        if (countPredators == 0) {
            countPredators = IslandSimulation.getInstance().getCountPredators();
        }
        if (countPlants == 0) {
            countPlants = IslandSimulation.getInstance().getCountPlants();
        }

        IslandSimulation.getInstance().createIslandModel(countHerbivores, countPredators, countPlants);
    }

    /**
     * Метод для изменения размера острова
     */
    private void changeIslandSize() {
        System.out.println("Хотите ли вы изменить размер острова (10x4)?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Введите желаемый размер острова!");
            System.out.println("Количество строк:");
            int rows = takeInt(1, 500);
            System.out.println("Количество столбцов:");
            int columns = takeInt(1, 500);
            IslandField.getInstance().initializeLocations(rows, columns);
        } else {
            IslandField.getInstance().initializeLocations();
        }
    }

    /**
     * Метод для изменения количества хищников
     * @return countPredators Количество хищников
     */
    private int changePredatorsSize() {
        System.out.println("Хотите ли вы изменить количество хищников (25)?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int countPredators = 0;
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Введите желаемое количество хищников от 5 до 500!");
            System.out.println("Количество хищников:");
            countPredators = takeInt(5, 500);
        }
        return countPredators;
    }

    /**
     * Метод для изменения количества травоядных
     * @return countHerbivores Количество травоядных
     */
    private int changeHerbivoresSize() {
        System.out.println("Хотите ли вы изменить количество травоядных (30)?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int countHerbivores = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Введите желаемое количество травоядных от 10 до 500!");
            System.out.println("Количество травоядных:");
            countHerbivores = takeInt(10, 500);
        }
        return countHerbivores;
    }

    /**
     * Метод для изменения количества растений
     * @return countPlants Количество растений
     */
    private int changePlantsSize() {
        System.out.println("Хотите ли вы изменить количество растений (10)?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int countPlants = 0;
        int answer = takeInt(1, 2);
        
        if (answer == 1) {
            System.out.println("Введите желаемое количество растений от 1 до 500!");
            System.out.println("Количество растений:");
            countPlants = takeInt(1, 500);
        }
        return countPlants;
    }

    /**
     * Метод для считывания целого числа с клавиатуры в заданном диапазоне
     * @param lowNum Нижняя граница диапазона
     * @param highNum Верхняя граница диапазона
     * @return number Считанное число
     */
    public int takeInt(int lowNum, int highNum) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= lowNum && number <= highNum) {
                    return number;
                } else {
                    System.out.println("Ошибка! Введенное число не находится в заданном диапазоне. Попробуйте еще раз:");
                }
            } else {
                scanner.next();
                System.out.println("Ошибка! Введено некорректное значение. Попробуйте еще раз:");
            }
        }
    }
}
