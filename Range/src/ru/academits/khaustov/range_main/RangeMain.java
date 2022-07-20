package ru.academits.khaustov.range_main;

import ru.academits.khaustov.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range = new Range(12.3, 16.7);

        System.out.println("Начало диапазона: " + range.getFrom());
        System.out.println("Конец диапазона: " + range.getTo());

        System.out.println("Длина диапазона равна: " + range.getRangeLength());

        double number = 14;

        if (range.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапазону.");
        } else {
            System.out.println("Число " + number + " не принадлежит диапазону.");
        }

        System.out.println();

        range.setFrom(11.5);
        range.setTo(12.6);

        System.out.println("Новое начало диапазона равно: " + range.getFrom());
        System.out.println("Новый конец диапазона равен: " + range.getTo());

        System.out.println("Новая длина диапазона равна: " + range.getRangeLength());

        if (range.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапазону");
        } else {
            System.out.println("Число " + number + " не принадлежит диапазону");
        }
    }
}
