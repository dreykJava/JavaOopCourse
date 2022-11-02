package ru.academits.khaustov.shapes;

import java.util.Comparator;

public class AreaComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        Shape shape1 = (Shape) o1;
        Shape shape2 = (Shape) o2;

        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
