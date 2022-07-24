package ru.academits.khaustov.range_main;

import ru.academits.khaustov.range.Range;

import java.util.Arrays;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(12.3, 16.7);
        Range range2 = new Range(14.3, 15.9);

        System.out.println("Начало первого диапазона: " + range1.getFrom());
        System.out.println("Конец первого диапазона: " + range1.getTo());
        System.out.println("Начало второго диапазона: " + range2.getFrom());
        System.out.println("Конец второго диапазона: " + range2.getTo());

        System.out.println("Длина первого диапазона равна: " + range1.getLength());
        System.out.println("Длина второго диапазона равна: " + range2.getLength());

        double number = 14.5;

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " принадлежит первому диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит первому диапазону");
        }

        if (range2.isInside(number)) {
            System.out.println("Число " + number + " принадлежит второму диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит второму диапазону");
        }

        double[] intervalsIntersection = range1.getIntervalsIntersection(range2);

        if (intervalsIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: " + Arrays.toString(intervalsIntersection));
        }

        Range[] intervalsUnion = range1.getIntervalsUnion(range1, range2);

        if (intervalsUnion.length == 1) {
            double from = intervalsUnion[0].getFrom();
            double to = intervalsUnion[0].getTo();

            System.out.printf("Объединение двух диапазонов: [%f, %f]%n", from, to);
        } else {
            double from1 = intervalsUnion[0].getFrom();
            double to1 = intervalsUnion[0].getTo();
            double from2 = intervalsUnion[1].getFrom();
            double to2 = intervalsUnion[1].getFrom();

            System.out.printf("Объединение двух диапазонов: [%f, %f], [%f, %f]%n", from1, to1, from2, to2);
        }

        Range[] intervalsDifference = range1.getIntervalsDifference(range1, range2);

        if (intervalsDifference == null) {
            System.out.println("Разность диапазонов равна нулю");
        } else if (intervalsDifference.length == 1) {
            double from = intervalsDifference[0].getFrom();
            double to = intervalsDifference[0].getTo();

            System.out.printf("Разность диапазонов равна: [%f, %f]%n", from, to);
        } else {
            double from1 = intervalsDifference[0].getFrom();
            double to1 = intervalsDifference[0].getTo();
            double from2 = intervalsDifference[1].getFrom();
            double to2 = intervalsDifference[1].getTo();

            System.out.printf("Разность диапазонов равна: [%f, %f], [%f, %f]%n", from1, to1, from2, to2);
        }

        System.out.println();

        range1.setFrom(11.5);
        range1.setTo(12.6);
        range2.setFrom(12);
        range2.setTo(13.7);

        System.out.println("Новое начало первого диапазона равно: " + range1.getFrom());
        System.out.println("Новый конец первого диапазона равен: " + range1.getTo());
        System.out.println("Новое начало второго диапазона равно: " + range2.getFrom());
        System.out.println("Новый конец второго диапазона равен: " + range2.getTo());

        System.out.println("Новая длина первого диапазона равна: " + range1.getLength());
        System.out.println("Новая длина второго диапазона равна: " + range2.getLength());

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " принадлежит первому диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит первому диапазону");
        }

        if (range2.isInside(number)) {
            System.out.println("Число " + number + " принадлежит второму диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит второму диапазону");
        }

        intervalsIntersection = range1.getIntervalsIntersection(range2);

        if (intervalsIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: " + Arrays.toString(intervalsIntersection));
        }

        intervalsUnion = range1.getIntervalsUnion(range1, range2);

        if (intervalsUnion.length == 1) {
            double from = intervalsUnion[0].getFrom();
            double to = intervalsUnion[0].getTo();

            System.out.printf("Объединение двух диапазонов: [%f, %f]%n", from, to);
        } else {
            double from1 = intervalsUnion[0].getFrom();
            double to1 = intervalsUnion[0].getTo();
            double from2 = intervalsUnion[1].getFrom();
            double to2 = intervalsUnion[1].getFrom();

            System.out.printf("Объединение двух диапазонов: [%f, %f], [%f, %f]%n", from1, to1, from2, to2);
        }

        intervalsDifference = range1.getIntervalsDifference(range1, range2);

        if (intervalsDifference == null) {
            System.out.println("Разность диапазонов равна нулю");
        } else if (intervalsDifference.length == 1) {
            double from = intervalsDifference[0].getFrom();
            double to = intervalsDifference[0].getTo();

            System.out.printf("Разность диапазонов равна: [%f, %f]%n", from, to);
        } else {
            double from1 = intervalsDifference[0].getFrom();
            double to1 = intervalsDifference[0].getTo();
            double from2 = intervalsDifference[1].getFrom();
            double to2 = intervalsDifference[1].getTo();

            System.out.printf("Разность диапазонов равна: [%f, %f], [%f, %f]%n", from1, to1, from2, to2);
        }
    }
}
