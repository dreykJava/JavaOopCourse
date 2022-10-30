package ru.academits.khaustov.array_list_main;

import ru.academits.khaustov.array_list.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class ArrayListMain {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(12);

        System.out.println("Первый список: " + Arrays.toString(list1.toArray()));
        System.out.println("Длина первого списка: " + list1.size());

        ArrayList<Integer> list2 = new ArrayList<>(12);

        System.out.println();
        System.out.println("Второй список: " + Arrays.toString(list2.toArray()));

        list2.trimToSize();

        int element = 12;
        System.out.println();
        System.out.println("Индекс элемента " + element + " в списке 1: " + list1.indexOf(element));
        System.out.println("Индекс элемента " + element + " в списке 2: " + list2.indexOf(element));

        list1.add(1, 12);
        System.out.println();
        System.out.println("Первый список: " + Arrays.toString(list1.toArray()));
        System.out.println("Индекс последнего вхождения элемента 12 в список 1: " + list1.lastIndexOf(12));
        System.out.println("Элемент, удалённый с индекса 0: " + list1.remove(0));
        System.out.println("Первый список: " + Arrays.toString(list1.toArray()));

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(4);
        list3.add(5);
        list3.add(60);
        list3.add(71);

        Collection<Integer> collection = new ArrayList<>(3);
        collection.add(100);
        collection.add(101);
        collection.add(102);

        System.out.println();
        System.out.println("Третий список: " + Arrays.toString(list3.toArray()));
        System.out.println("Коллекция: " + Arrays.toString(collection.toArray()));

        list3.addAll(4, collection);

        System.out.println();
        System.out.println("Третий список: " + Arrays.toString(list3.toArray()));
        System.out.println("Коллекция: " + Arrays.toString(collection.toArray()));

        list3.retainAll(collection);

        System.out.println();
        System.out.println("Третий список: " + Arrays.toString(list3.toArray()));

        list3.clear();

        System.out.println();
        System.out.println("Третий список: " + Arrays.toString(list3.toArray()));
    }
}
