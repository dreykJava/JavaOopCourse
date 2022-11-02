package ru.academits.khaustov.vector_main;

import ru.academits.khaustov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {1.6, 2.6, 3.87, 7.12};
        Vector vector1 = new Vector(array1);
        System.out.println("Значения первого вектора: " + vector1);

        double[] array2 = {1.1, 7.12, 5.69, 9.34};
        Vector vector2 = new Vector(array2);
        System.out.println("Значения второго вектора: " + vector2);

        Vector vector3 = new Vector(12);

        double[] array3 = {12, 6.4, 18, 34, 10};
        Vector vector4 = new Vector(12, array3);

        System.out.println("Результат умножения пустого вектора на вектор чисел: " + Vector.getScalarProduct(vector3, vector4));

        System.out.println("Значения второго вектора: " + new Vector(vector2));

        System.out.println("Значения первого вектора: " + vector1);

        vector1.add(vector2);
        System.out.println("Результат сложения векторов: " + vector1);

        vector1.subtract(vector2);
        System.out.println("Результат разности векторов: " + vector1);

        double number = 12.3;
        vector1.multiplyScalar(number);
        System.out.println("Умножение вектора на число равно: " + vector1);

        vector2.multiplyScalar(number);
        System.out.println("Умножение вектора на число равно: " + vector2);

        vector1.invert();
        System.out.println("Результат разворота вектора равен: " + vector1);

        System.out.println("Длина вектора равна: " + vector1.getLength());

        int valueIndex = 1;
        System.out.println("Значение вектора с индексом " + valueIndex + " равно: " + vector1.getComponent(valueIndex));

        vector1.setComponent(valueIndex, 12.4);
        System.out.println("Значение вектора с индексом " + valueIndex + " равно: " + vector1.getComponent(valueIndex));

        vector2.setComponent(valueIndex, 10);
        System.out.println("Значение вектора с индексом " + valueIndex + " равно: " + vector2.getComponent(valueIndex));

        Vector vectorsSum = Vector.getSum(vector1, vector2);
        System.out.println("Сумма векторов равна: " + vectorsSum);

        System.out.println(vector1);
        System.out.println(vector2);

        Vector vectorsDif = Vector.getDifference(vector1, vector2);
        System.out.println("Разность векторов равна: " + vectorsDif);

        double vectorsMultiplication = Vector.getScalarProduct(vector1, vector2);
        System.out.println("Сумма векторов равна: " + vectorsMultiplication);
    }
}