package lifeform.plant;

public class Plant {
    double foodAmount = 1; // Вес растения в кг
    public double getFoodAmount() {
        return foodAmount;
    }

    public Plant(double foodAmount) {
        this.foodAmount = foodAmount;
    }
    public void decreaseFoodAmount(double amount) {
        foodAmount -= amount;
    }
}
