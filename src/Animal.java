public abstract class Animal {
    double weight; // Вес одного животного, кг
    int step; // Скорость перемещения, не более чем, клеток за ход
    double maxFood; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    public Animal(double weight, int step, double maxFood){
        this.weight = weight;
        this.step = step;
        this.maxFood = maxFood;
    }

    public double getWeight() {
        return weight;
    }
    public int getStep() {
        return step;
    }
    public double getMaxFood() {
        return maxFood;
    }

    abstract void eat();
    abstract void multiply();
    abstract void move();
    abstract void die();
}
