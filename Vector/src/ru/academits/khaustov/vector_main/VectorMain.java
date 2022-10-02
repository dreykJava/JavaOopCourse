package ru.academits.khaustov.vector_main;

import ru.academits.khaustov.vector.Vector;

import java.util.Arrays;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {1.6, 2.6, 3.87, 7.12};
        Vector vector1 = new Vector(array1);
        System.out.println("Значения первого вектора: " + vector1);

        double[] array2 = {1.1, 7.12, 5.69, 9.34};
        Vector vector2 = new Vector(array2);
        System.out.println("Значения второго вектора: " + vector2);

        System.out.println("Значения второго вектора: " + new Vector(vector2));

        double[] vector1Values = vector1.getVectorValues();
        System.out.println("Значения первого вектора: " + Arrays.toString(vector1Values));

        Vector vector3 = new Vector(vector2.getSize());

        vector3.setVectorValues(vector2.getVectorValues());
        System.out.println("Значения третьего вектора: " + vector3);

        System.out.println("Сумма векторов равна: " + vector1.getVectorsSum(vector2));
        System.out.println("Разность векторов равна: " + vector1.getVectorDif(vector2));

        double number = 12.3;
        System.out.println("Умножение вектора на число равно: " + vector1.getVectorAndScalarMulti(number));
        System.out.println("Умножение вектора на число равно: " + vector2.getVectorAndScalarMulti(number));

        System.out.println("Разворот вектора равен: " + vector1.getVectorInversion());

        System.out.println("Длина вектора равна: " + vector1.getVectorLength());

        int valueIndex = 1;
        System.out.println("Значение вектора с индексом " + valueIndex + " равно: " + vector1.getVectorValue(valueIndex));

        vector1.setVectorValue(valueIndex, 12.4);
        System.out.println("Значение вектора с индексом " + valueIndex + " равно: " + vector1.getVectorValue(valueIndex));

        vector2.setVectorValue(valueIndex, 10);
        System.out.println("Значение вектора с индексом " + valueIndex + " равно: " + vector2.getVectorValue(valueIndex));

        Vector vectorsSum = Vector.getVectorsSum(vector1, vector2);
        System.out.println("Сумма векторов равна: " + vectorsSum);

        System.out.println(vector1);
        System.out.println(vector2);

        Vector vectorsDif = Vector.getVectorsDif(vector1, vector2);
        System.out.println("Разность векторов равна: " + vectorsDif);

        double vectorsMulti = Vector.getVectorsMulti(vector1, vector2);
        System.out.println("Сумма векторов равна: " + vectorsMulti);
    }
}