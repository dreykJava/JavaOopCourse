package ru.academits.khaustov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorValues;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        vectorValues = new double[n];

        for (int i = 0; i <= n - 1; i++) {
            vectorValues[i] = 0;
        }
    }

    public Vector(Vector vector) {
         this.vectorValues = vector.vectorValues;
    }

    public Vector(double[] vectorValues) {
        this.vectorValues = vectorValues;
    }

    public Vector(int n, double[] vectorValues) {
        Vector vectorCopy = new Vector(n);

        for (int i = 0; i <= vectorValues.length - 1; i++) {
            vectorCopy.vectorValues[i] = vectorValues[i];
        }

        this.vectorValues = vectorCopy.vectorValues;
    }

    public double[] getVectorValues() {
        return vectorValues;
    }

    public void setVectorValues(double[] vectorValues) {
        this.vectorValues = vectorValues;
    }

    public int getSize() {
        return vectorValues.length;
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder("{");

        for (int i = 0; i <= vectorValues.length - 2; i++) {
            a.append(vectorValues[i]);
            a.append(", ");
        }

        a.append(vectorValues[vectorValues.length - 1]);
        a.append("}");

        return a.toString();
    }

    public Vector getVectorsSum(Vector vector) {
        int maxVectorSize = Math.max(this.getSize(), vector.getSize());

        Vector vector1 = new Vector(maxVectorSize, this.vectorValues);
        Vector vector2 = new Vector(maxVectorSize, vector.vectorValues);
        Vector vectorsSum = new Vector(maxVectorSize);

        for (int i = 0; i <= maxVectorSize - 1; i++) {
            vectorsSum.vectorValues[i] = vector1.vectorValues[i] + vector2.vectorValues[i];
        }

        return vectorsSum;
    }

    public Vector getVectorDif(Vector vector) {
        int maxVectorSize = Math.max(this.getSize(), vector.getSize());

        Vector vector1 = new Vector(maxVectorSize, this.vectorValues);
        Vector vector2 = new Vector(maxVectorSize, vector.vectorValues);
        Vector vectorsDif = new Vector(maxVectorSize);

        for (int i = 0; i <= maxVectorSize - 1; i++) {
            vectorsDif.vectorValues[i] = vector1.vectorValues[i] - vector2.vectorValues[i];
        }

        return vectorsDif;
    }

    public Vector getVectorAndScalarMulti(double scalar) {
        Vector multi = new Vector(getSize());

        for (int i = 0; i <= getSize() - 1; i++) {
            multi.vectorValues[i] = vectorValues[i] * scalar;
        }

        return multi;
    }

    public Vector getVectorInversion() {
        Vector inversion = new Vector(getSize());

        for (int i = 0; i <= getSize() - 1; i++) {
            inversion.vectorValues[i] = vectorValues[i] * -1;
        }

        return inversion;
    }

    public double getVectorLength() {
        double vectorLength = 0;

        for (int i = 0; i <= getSize() - 1; i++) {
            vectorLength = vectorLength + Math.pow(vectorValues[i], 2);
        }

        return Math.sqrt(vectorLength);
    }

    public double getVectorValue(int valueIndex) {
        return vectorValues[valueIndex];
    }

    public void setVectorValue(int valueIndex, double value) {
        vectorValues[valueIndex] = value;
    }

    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || vector.getClass() != getClass()) {
            return false;
        }

        Vector comparisonVector = (Vector) vector;

        if (this.getSize() != comparisonVector.getSize()) {
            return false;
        }

        double epsilon = 1.0e-10;

        for (int i = 0; i <= this.getSize() - 1;) {
            if (Math.abs(this.vectorValues[i] - comparisonVector.vectorValues[i]) > epsilon) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(vectorValues);

        return hash;
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        int maxVectorSize = Math.max(vector1.getSize(), vector2.getSize());
        vector1 = new Vector(maxVectorSize, vector1.vectorValues);
        vector2 = new Vector(maxVectorSize, vector2.vectorValues);
        Vector resultVector = new Vector(maxVectorSize);

        for (int i = 0; i <= maxVectorSize - 1; i++) {
            resultVector.vectorValues[i] = vector1.vectorValues[i] + vector2.vectorValues[i];
        }

        return resultVector;
    }

    public static Vector getVectorsDif(Vector vector1, Vector vector2) {
        int maxVectorSize = Math.max(vector1.getSize(), vector2.getSize());
        vector1 = new Vector(maxVectorSize, vector1.vectorValues);
        vector2 = new Vector(maxVectorSize, vector2.vectorValues);
        Vector resultVector = new Vector(maxVectorSize);

        for (int i = 0; i <= maxVectorSize - 1; i++) {
            resultVector.vectorValues[i] = vector1.vectorValues[i] - vector2.vectorValues[i];
        }

        return resultVector;
    }

    public static double getVectorsMulti(Vector vector1, Vector vector2) {
        int maxVectorSize = Math.max(vector1.getSize(), vector2.getSize());
        vector1 = new Vector(maxVectorSize, vector1.vectorValues);
        vector2 = new Vector(maxVectorSize, vector2.vectorValues);
        double resultVector = 0;

        for (int i = 0; i <= maxVectorSize - 1; i++) {
            resultVector = resultVector + vector1.vectorValues[i] * vector2.vectorValues[i];
        }

        return resultVector;
    }
}