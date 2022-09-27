package ru.academits.khaustov.shapes_main;

import ru.academits.khaustov.shapes_Interface.Shape;
import ru.academits.khaustov.shapes_circle.Circle;
import ru.academits.khaustov.shapes_rectangle.Rectangle;
import ru.academits.khaustov.shapes_square.Square;
import ru.academits.khaustov.shapes_triangle.Triangle;

import java.util.Arrays;

public class ShapesMain {
    public static void getMaxAreaShape(Shape[] shapes) {
        int shapesLength = shapes.length;

        double[] shapesArea = new double[shapesLength];

        for (int i = 0; i <= shapesLength - 1; i++) {
            shapesArea[i] = shapes[i].getArea();
        }

        Arrays.sort(shapesArea);

        double maxShapeArea = shapesArea[shapesLength - 1];

        int maxShapeAreaIndex = 0;

        for (int i = 1; i <= shapesLength - 1; i++) {
            if (shapes[i].getArea() == maxShapeArea) {
                maxShapeAreaIndex = i;
            }
        }

        System.out.println("Фигура с максимальной площадью: " + "\n" +
                "Ширина фигуры = " + shapes[maxShapeAreaIndex].getWidth() + "\n" +
                "Высота фигуры = " + shapes[maxShapeAreaIndex].getHeight() + "\n" +
                "Площадь фигуры = " + shapes[maxShapeAreaIndex].getArea() + "\n" +
                "Периметр фигуры = " + shapes[maxShapeAreaIndex].getPerimeter() + "\n");
    }

    public static void getSecondMaxAreaShape(Shape[] shapes) {
        int shapesLength = shapes.length;

        double[] shapesArea = new double[shapesLength];

        for (int i = 0; i <= shapesLength - 1; i++) {
            shapesArea[i] = shapes[i].getArea();
        }

        Arrays.sort(shapesArea);

        double secondMaxShapeArea = shapesArea[shapesLength - 2];

        int secondMaxShapeAreaIndex = 0;

        for (int i = 1; i <= shapesLength - 1; i++) {
            if (shapes[i].getArea() == secondMaxShapeArea) {
                secondMaxShapeAreaIndex = i;
            }
        }

        System.out.println("Фигура с второй по величине площадью: " + "\n" +
                "Ширина фигуры = " + shapes[secondMaxShapeAreaIndex].getWidth() + "\n" +
                "Высота фигуры = " + shapes[secondMaxShapeAreaIndex].getHeight() + "\n" +
                "Площадь фигуры = " + shapes[secondMaxShapeAreaIndex].getArea() + "\n" +
                "Периметр фигуры = " + shapes[secondMaxShapeAreaIndex].getPerimeter() + "\n");
    }

    public static void main(String[] args) {
        Square square1 = new Square();
        square1.setSideLength(12);

        Square square2 = new Square();
        square2.setSideLength(14);

        Triangle triangle1 = new Triangle();
        triangle1.setSideLength(1, 2, 3, 4, 5, 6);

        Triangle triangle2 = new Triangle();
        triangle2.setSideLength(4, 2, 5, 4, 6, 3);

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setSidesLength(12, 13);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setSidesLength(9, 8);

        Circle circle1 = new Circle();
        circle1.setRadius(6);

        Circle circle2 = new Circle();
        circle2.setRadius(8.4);

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};

        getMaxAreaShape(shapes);
        getSecondMaxAreaShape(shapes);
    }
}
