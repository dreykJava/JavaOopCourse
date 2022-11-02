package ru.academits.khaustov.shapes_main;

import ru.academits.khaustov.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        AreaComparator<Shape> comparator = new AreaComparator<>();

        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        PerimeterComparator<Shape> comparator = new PerimeterComparator<>();

        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Square(12),
                new Square(14),
                new Triangle(1, 2, 3, 4, 5, 6),
                new Triangle(4, 2, 5, 4, 6, 3),
                new Rectangle(12, 13),
                new Rectangle(9, 8),
                new Rectangle(100, 100),
                new Circle(6),
                new Circle(8.4)};

        Shape maxAreaShape = getMaxAreaShape(shapes);
        Shape secondPerimeterShape = getSecondMaxPerimeterShape(shapes);

        System.out.println("Фигура с максимальной площадью: " + maxAreaShape);
        System.out.println("Фигура с вторым по величине периметром: " + secondPerimeterShape);
    }
}
