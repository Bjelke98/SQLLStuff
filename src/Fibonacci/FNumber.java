package Fibonacci;

public class FNumber {

    private static int spotTracker = 0;

    private final int spot;
    private final int number;

    public FNumber(int number) {
        this.spot = ++spotTracker;
        this.number = number;
    }

    public int getSpot() {
        return spot;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return spot+": "+number;
    }
}
