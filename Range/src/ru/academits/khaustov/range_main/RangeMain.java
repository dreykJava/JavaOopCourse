package ru.academits.khaustov.range_main;

import ru.academits.khaustov.range.Range;

public class RangeMain {
    public static void printRangeArray(Range[] unionRange, Range[] differenceRange) {
        if (unionRange.length == 1) {
            System.out.printf("Объединение двух диапазонов: [(%f, %f)]%n", unionRange[0].getFrom(), unionRange[0].getTo());
        } else {
            System.out.printf("Объединение двух диапазонов: [(%f, %f), (%f, %f)]%n", unionRange[0].getFrom(), unionRange[0].getTo(),
                    unionRange[1].getFrom(), unionRange[1].getTo());
        }

        if (differenceRange.length == 0) {
            System.out.printf("Разность диапазонов равна []%n");
        } else if (differenceRange.length == 1) {
            System.out.printf("Разность диапазонов равна: [(%f, %f)]%n", differenceRange[0].getFrom(), differenceRange[0].getTo());
        } else {
            System.out.printf("Разность диапазонов равна: [(%f, %f), (%f, %f)]%n", differenceRange[0].getFrom(), differenceRange[0].getTo(),
                    differenceRange[1].getFrom(), differenceRange[1].getTo());
        }
    }

    public static void main(String[] args) {
        Range range1 = new Range(12.3, 16.7);
        Range range2 = new Range(14.3, 15.9);

        System.out.println("Первый диапазон: ");
        System.out.println(range1);
        System.out.println("Второй диапазон: ");
        System.out.println(range2);

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

        Range rangeIntersection = range1.getIntersection(range2);

        if (rangeIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: (" + rangeIntersection.getFrom() + ", " + rangeIntersection.getTo() + ")");
        }

        Range[] rangeUnion = range1.getUnion(range2);
        Range[] rangeDifference = range1.getDifference(range2);

        printRangeArray(rangeUnion, rangeDifference);

        System.out.println();

        range1.setFrom(11.5);
        range1.setTo(12.6);
        range2.setFrom(12);
        range2.setTo(13.7);

        System.out.println("Первый диапазон: ");
        System.out.println(range1);
        System.out.println("Второй диапазон: ");
        System.out.println(range2);

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

        rangeIntersection = range1.getIntersection(range2);

        if (rangeIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: (" + rangeIntersection.getFrom() + ", " + rangeIntersection.getTo() + ")");
        }

        rangeUnion = range1.getUnion(range2);
        rangeDifference = range1.getDifference(range2);

        printRangeArray(rangeUnion, rangeDifference);
    }
}