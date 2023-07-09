package simulation.helperObject;

public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getLeft() {
        return first;
    }

    public U getRight() {
        return second;
    }
}