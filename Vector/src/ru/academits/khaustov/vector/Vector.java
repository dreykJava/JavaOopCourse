package ru.academits.khaustov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        checkSize(size);

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        int size = components.length;

        checkSize(size);

        this.components = Arrays.copyOf(components, size);
    }

    public Vector(int size, double[] components) {
        checkSize(size);

        if (components.length > size) {
            throw new IllegalArgumentException("Рзамерность вектора должна быть >= количеству компонент. " +
                    "Переданная размерность: " + size + ", переданное количество компонент: " + components.length + ".");
        }

        this.components = Arrays.copyOf(components, size);
    }

    private static void checkSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0. Переданная размерность: " + size);
        }
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

    public void add(Vector vector) {
        int resultSize = vector.components.length;

        if (components.length < resultSize) {
            components = Arrays.copyOf(components, resultSize);
        }

        for (int i = 0; i < resultSize; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int resultSize = vector.components.length;

        if (components.length < resultSize) {
            components = Arrays.copyOf(components, resultSize);
        }

        for (int i = 0; i < resultSize; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void invert() {
        multiplyScalar(-1);
    }

    public double getLength() {
        double length = 0;

        for (double component : components) {
            length += component * component;
        }

        return Math.sqrt(length);
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

        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtract(vector2);

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double product = 0;
        int resultSize = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < resultSize; i++) {
            product += vector1.components[i] * vector2.components[i];
        }

        return product;
    }
}