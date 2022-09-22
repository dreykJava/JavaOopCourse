package ru.academits.khaustov.range_main;

import ru.academits.khaustov.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(12.3, 16.7);
        Range range2 = new Range(14.3, 15.9);

        System.out.println(range1.toString(range2));

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

        Range intervalsIntersection = range1.getIntersection(range2);

        if (intervalsIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: (" + intervalsIntersection.getFrom() + ", " + intervalsIntersection.getTo() + ")");
        }

        Range[] intervalsUnion = range1.getUnion(range2);

        if (intervalsUnion.length == 1) {
            System.out.printf("Объединение двух диапазонов: [(%f, %f)]%n", intervalsUnion[0].getFrom(), intervalsUnion[0].getTo());
        } else {
            System.out.printf("Объединение двух диапазонов: [(%f, %f), (%f, %f)]%n", intervalsUnion[0].getFrom(), intervalsUnion[0].getTo(),
                    intervalsUnion[1].getFrom(), intervalsUnion[1].getTo());
        }

        Range[] intervalsDifference = range1.getDifference(range2);

        if (intervalsDifference.length == 0) {
            System.out.printf("Разность диапазонов равна []%n");
        } else if (intervalsDifference.length == 1) {
            System.out.printf("Разность диапазонов равна: [(%f, %f)]%n", intervalsDifference[0].getFrom(), intervalsDifference[0].getTo());
        } else {
            System.out.printf("Разность диапазонов равна: [(%f, %f), (%f, %f)]%n", intervalsDifference[0].getFrom(), intervalsDifference[0].getTo(),
                    intervalsDifference[1].getFrom(), intervalsDifference[1].getTo());
        }

        System.out.println();

        range1.setFrom(11.5);
        range1.setTo(12.6);
        range2.setFrom(12);
        range2.setTo(13.7);

        System.out.println(range1.toString(range2));

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

        intervalsIntersection = range1.getIntersection(range2);

        if (intervalsIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: (" + intervalsIntersection.getFrom() + ", " + intervalsIntersection.getTo() + ")");
        }

        intervalsUnion = range1.getUnion(range2);

        if (intervalsUnion.length == 1) {
            System.out.printf("Объединение двух диапазонов: [(%f, %f)]%n", intervalsUnion[0].getFrom(), intervalsUnion[0].getTo());
        } else {
            System.out.printf("Объединение двух диапазонов: [(%f, %f), (%f, %f)]%n", intervalsUnion[0].getFrom(), intervalsUnion[0].getTo(),
                    intervalsUnion[1].getFrom(), intervalsUnion[1].getTo());
        }

        intervalsDifference = range1.getDifference(range2);

        if (intervalsDifference.length == 0) {
            System.out.printf("Разность диапазонов равна []%n");
        } else if (intervalsDifference.length == 1) {
            System.out.printf("Разность диапазонов равна: [(%f, %f)]%n", intervalsDifference[0].getFrom(), intervalsDifference[0].getTo());
        } else {
            System.out.printf("Разность диапазонов равна: [(%f, %f), (%f, %f)]%n", intervalsDifference[0].getFrom(), intervalsDifference[0].getTo(),
                    intervalsDifference[1].getFrom(), intervalsDifference[1].getTo());
        }
    }
}