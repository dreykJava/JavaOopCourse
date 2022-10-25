package ru.academits.khaustov.range_main;

import ru.academits.khaustov.range.Range;

public class RangeMain {
    public static String printRangeArray(Range[] range) {
        int rangeLength = range.length;

        if (rangeLength == 0) {
            return "[]";
        }

        StringBuilder rangeArray = new StringBuilder("[(" + range[0].getFrom() + "; " + range[0].getTo() + ")");

        for (int i = 1; i < rangeLength; i++) {
            rangeArray.append(", (").append(range[i].getFrom()).append("; ").append(range[i].getTo()).append(")");
        }

        return rangeArray + "]";
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

        Range rangesIntersection = range1.getIntersection(range2);

        if (rangesIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: (" + rangesIntersection.getFrom() + ", " + rangesIntersection.getTo() + ")");
        }

        Range[] rangesUnion = range1.getUnion(range2);
        Range[] rangesDifference = range1.getDifference(range2);

        System.out.println("Объединение диапазонов: " + printRangeArray(rangesUnion));
        System.out.println("Разность диапазонов: " + printRangeArray(rangesDifference));

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

        rangesIntersection = range1.getIntersection(range2);

        if (rangesIntersection == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Диапазоны пересекаются в точках: (" + rangesIntersection.getFrom() + ", " + rangesIntersection.getTo() + ")");
        }

        rangesUnion = range1.getUnion(range2);
        rangesDifference = range1.getDifference(range2);

        System.out.println("Объединение диапазонов: " + printRangeArray(rangesUnion));
        System.out.println("Разность диапазонов: " + printRangeArray(rangesDifference));


    }
}