package ru.academits.khaustov.vector;

import java.util.Arrays;

public class Vector {
    private final double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        int size = vector.getSize();

        components = new double[size];

        System.arraycopy(vector.components, 0, components, 0, size);
    }

    public Vector(double[] components) {
        int size = components.length;

        this.components = new double[size];

        System.arraycopy(components, 0, this.components, 0, size);
    }

    public Vector(int size, double[] components) {
        this.components = new double[size];

        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");

        for (int i = 0; i <= components.length - 2; i++) {
            string.append(components[i]);
            string.append(", ");
        }

        string.append(components[components.length - 1]);
        string.append("}");

        return string.toString();
    }

    public void setSum(Vector vector) {
        for (int i = 0; i < Math.max(getSize(), vector.getSize()); i++) {
            components[i] = components[i] + vector.components[i];
        }
    }

    public void setDifference(Vector vector) {
        for (int i = 0; i < Math.max(getSize(), vector.getSize()); i++) {
            components[i] = components[i] - vector.components[i];
        }
    }

    public void setScalarMultiplication(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] = components[i] * scalar;
        }
    }

    public void setInversion() {
        setScalarMultiplication(-1);
    }

    public double getMagnitude() {
        double magnitude = 0;

        for (double component : components) {
            magnitude += component * component;
        }

        return Math.sqrt(magnitude);
    }

    public double getComponent(int componentIndex) {
        return components[componentIndex];
    }

    public void setComponent(int componentIndex, double component) {
        components[componentIndex] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        if (getSize() != vector.getSize()) {
            return false;
        }

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.setSum(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.setDifference(vector2);

        return result;
    }

    public static double getMultiplication(Vector vector1, Vector vector2) {
        double product = 0;

        for (int i = 0; i < Math.max(vector1.getSize(), vector2.getSize()); i++) {
            product += vector1.components[i] * vector2.components[i];
        }

        return product;
    }
}