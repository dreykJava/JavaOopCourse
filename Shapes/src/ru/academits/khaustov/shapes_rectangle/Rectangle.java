package ru.academits.khaustov.shapes_rectangle;

import ru.academits.khaustov.shapes_Interface.Shape;

public class Rectangle implements Shape {
    private double side1;
    private double side2;

    public void setSidesLength(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public double getWidth() {
        return side1;
    }

    public double getHeight() {
        return side2;
    }

    public double getArea() {
        return side1 * side2;
    }

    public double getPerimeter() {
        return (side1 + side2) * 2;
    }
}
