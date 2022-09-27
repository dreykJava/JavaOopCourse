package ru.academits.khaustov.shapes_circle;

import ru.academits.khaustov.shapes_Interface.Shape;

public class Circle implements Shape {
    private double radius;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return radius * radius * Math.PI;
    }

    public double getPerimeter() {
        return radius * Math.PI * 2;
    }
}
