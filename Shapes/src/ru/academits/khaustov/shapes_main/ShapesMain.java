package ru.academits.khaustov.shapes_main;

import ru.academits.khaustov.shapes.Shape;
import ru.academits.khaustov.shapes.Circle;
import ru.academits.khaustov.shapes.Rectangle;
import ru.academits.khaustov.shapes.Square;
import ru.academits.khaustov.shapes.Triangle;

import java.util.Arrays;
import java.util.Comparator;

public class ShapesMain {
    public static Shape getMaxAreaShape(Shape[] shapes, Comparator<Shape> comparator) {
        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondMaxPerimeterShape(Shape[] shapes, Comparator<Shape> comparator) {
        Arrays.sort(shapes, comparator);

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Comparator<Shape> areaComparator = new Comparator<>() {
            final double epsilon = 1.0e-10;

            @Override
            public int compare(Shape o1, Shape o2) {
                if (Math.abs(o1.getArea() - o2.getArea()) <= epsilon) {
                    return 0;
                }

                if (o1.getArea() > o2.getArea()) {
                    return 1;
                }

                return -1;
            }
        };

        Comparator<Shape> perimeterComparator = new Comparator<>() {
            final double epsilon = 1.0e-10;

            @Override
            public int compare(Shape o1, Shape o2) {
                if (Math.abs(o1.getPerimeter() - o2.getPerimeter()) <= epsilon) {
                    return 0;
                }

                if (o1.getPerimeter() > o2.getPerimeter()) {
                    return 1;
                }

                return -1;
            }
        };

        Square square1 = new Square(12);
        Square square2 = new Square(14);

        Triangle triangle1 = new Triangle(1, 2, 3, 4, 5, 6);
        Triangle triangle2 = new Triangle(4, 2, 5, 4, 6, 3);

        Rectangle rectangle1 = new Rectangle(12, 13);
        Rectangle rectangle2 = new Rectangle(9, 8);
        Rectangle rectangle3 = new Rectangle(100, 100);

        Circle circle1 = new Circle(6);
        Circle circle2 = new Circle(8.4);

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, rectangle3, circle1, circle2};

        Shape maxAreaShape = getMaxAreaShape(shapes, areaComparator);
        Shape secondPerimeterShape = getSecondMaxPerimeterShape(shapes, perimeterComparator);

        System.out.println("Фигура с максимальной площадью: " + maxAreaShape);
        System.out.println("Фигура с вторым по величине периметром: " + secondPerimeterShape);
    }
}
