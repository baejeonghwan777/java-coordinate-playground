package study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Output {
    List<Integer> output;

    public Output() {
        this.output = new ArrayList<>();
    }

    public Output(List<Integer> pair) {
        this.output = pair;
    }

    public void addValue(int value) {
        output.add(value);
    }

    public boolean isValidSize() {
        return output.size() == 4 || output.size() == 6 || output.size() == 8;
    }

    public boolean isValidLineSize() {
        return output.size() == 4;
    }

    public boolean isValidTriSize() {
        return output.size() == 6;
    }

    public boolean isValidQuadSize() {
        return output.size() == 8;
    }

    public int getValue(int index) {
        return output.get(index);
    }

    public boolean validPoint() {
        if (isValidSize()) {
            for (Integer i : output) {
                if (i <= 0 || i > 24) return false;
            }
            return checkPoint();
        }
        return false;
    }

    public boolean checkPoint() {
        if (isValidLineSize()) return true;

        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();

        for (int i = 0; i < output.size(); i += 2) {
            xSet.add(output.get(i));
            ySet.add(output.get(i + 1));
        }

        if (isValidTriSize()) return !(xSet.size() == 1 || ySet.size() == 1 || xSet.equals(ySet));
        if (isValidQuadSize()) return xSet.size() == 2 && ySet.size() == 2;
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Output output1 = (Output) object;
        return Objects.equals(output, output1.output);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(output);
    }
}
