public abstract class Animal {
    int weight; // Вес одного животного, кг
    int step; // Скорость перемещения, не более чем, клеток за ход
    int maxFood; // Максимальное количество килограммов пищи нужно животному для полного насыщения

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    abstract void eat();
    abstract void multiply();
    abstract void move();
    abstract void die();
}
