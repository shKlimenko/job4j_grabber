package ood.tdd;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator);
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator.reversed());
    }

    private static <T> T find(List<T> value, Comparator<T> comparator) {
        T result;
        if (value.isEmpty()) {
            result = null;
        } else {
            result = value.get(0);
            for (int i = 1; i < value.size(); i++) {
                if (comparator.compare(result, value.get(i)) < 0) {
                    result = value.get(i);
                }
            }
        }
        return result;
    }
}