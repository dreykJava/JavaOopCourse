package ru.academits.khaustov.hash_table_main;

import ru.academits.khaustov.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class HashTableMain {
    public static void main(String[] args) {
        Collection<Integer> collection1 = new ArrayList<>();
        collection1.add(0);
        collection1.add(1);
        collection1.add(2);
        collection1.add(4);
        collection1.add(2);
        collection1.add(null);

        HashTable<Integer> table1 = new HashTable<>(collection1);

        Integer element = null;

        if (table1.contains(element)) {
            System.out.println("Первая таблица содержит элемент " + element + ".");
        } else {
            System.out.println("Первая таблица не содержит элемент " + element + ".");
        }

        System.out.println("Размер первой таблицы: " + table1.size());

        HashTable<Integer> table2 = new HashTable<>(10);
        System.out.println("Размер второй таблицы: " + table2.size());

        element = 9;

        table2.add(null);
        table2.add(1);
        table2.add(0);
        table2.add(null);
        table2.add(element);
        table2.add(null);

        System.out.println("Рзмер второй таблицы: " + table2.size());

        if (table2.contains(element)) {
            System.out.println("Вторая таблица содержит элемент " + element);
        } else {
            System.out.println("Вторая таблица не содержит элемент " + element);
        }

        Integer[] array1 = new Integer[10];
        array1 = table2.toArray(array1);

        System.out.println("Массив из элементов второй таблицы: " + Arrays.toString(array1));

        Integer[] array2 = new Integer[0];
        array2 = table2.toArray(array2);

        System.out.println("Массив из элементов второй таблицы: " + Arrays.toString(array2));

        table2.remove(null);
        table2.remove(null);

        array2 = new Integer[3];
        array2 = table2.toArray(array2);

        System.out.println("Массив из элементов второй таблицы: " + Arrays.toString(array2));

        Collection<Integer> collection2 = new ArrayList<>();
        collection2.add(null);
        collection2.add(0);
        collection2.add(-2);

        if (!table2.containsAll(collection2)) {
            System.out.println("Вторая таблица не содержит коллекцию " + Arrays.toString(collection2.toArray()));
        } else {
            System.out.println("Вторая таблица содержит коллекцию " + Arrays.toString(collection2.toArray()));
        }

        table2.retainAll(collection2);

        Integer[] array3 = new Integer[2];
        array3 = table2.toArray(array3);

        System.out.println("Массив из элементов второй таблицы, в котором оставили только элементы коллекции " + collection2 + " : " + Arrays.toString(array3));
        System.out.println("Размер второй таблицы: " + table2.size());

        table2.clear();
        Integer[] array4 = new Integer[2];
        array4 = table2.toArray(array4);
        System.out.println("Размер второй таблицы: " + table2.size());
        System.out.println("Массив из элементов второй таблицы: " + Arrays.toString(array4));
    }
}
