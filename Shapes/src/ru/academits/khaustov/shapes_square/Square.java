package ru.academits.khaustov.shapes_square;

import ru.academits.khaustov.shapes_Interface.Shape;

import java.util.Arrays;

public class Square implements Shape {
    private double sideLength;

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return sideLength * sideLength;
    }

    public double getPerimeter() {
        return sideLength * 4;
    }
}
