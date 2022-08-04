package ood.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertNull;

public class MaxMinTest {

    @Test
    public void when5isMax() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        List<Integer> value = List.of(1, 4, 2, 5, 0);
        int rsl = MaxMin.max(value, comparator);
        Assert.assertEquals(rsl, 5);
    }

    @Test
    public void when2isMin() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        List<Integer> value = List.of(8, 4, 2, 5, 55);
        int rsl = MaxMin.min(value, comparator);
        Assert.assertEquals(rsl, 2);
    }

    @Test
    public void whenListHasNoElementsAndResultIsNull() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        List<Integer> value = new ArrayList<>();
        assertNull(MaxMin.max(value, comparator));
    }
}