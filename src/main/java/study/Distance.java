package study;

public class Distance {
    private int value;

    public Distance(int value) {
        if(value >= 0) this.value = value;
        if(value < 0) this.value = 0;
    }

    public int getValue() {
        return value;
    }
}
