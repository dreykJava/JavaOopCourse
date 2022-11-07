package ru.academits.khaustov.list_main;

import ru.academits.khaustov.list.List;

public class ListMain {
    public static void main(String[] args) {
        List<Integer> list1 = new List<>();
        List<Integer> list2 = new List<>(0);

        System.out.println("Первый список: " + list1);
        System.out.println("Второй список: " + list2);

        List<Integer> list3 = new List<>(list2);

        System.out.println("Третий список: " + list3);

        list3.addFirst(1);
        list2.add(0, null);
        list2.add(2, 1);

        System.out.println();
        System.out.println("Второй список: " + list2);
        System.out.println("Третий список: " + list3);

        int oldData = list3.set(0, null);
        System.out.println();
        System.out.println("Старое значение элемента третьего списка с индексом 0: " + oldData);
        System.out.println("Значение элемента третьего списка с индексом 0: " + list3.getFirst());
        System.out.println("Значение элемента третьего списка с индексом 1: " + list3.get(1));
        System.out.println("Третий список: " + list3);

        System.out.println();
        System.out.println("Удалённое значение третьего списка: " + list3.remove(0));
        System.out.println("Третий список: " + list3);

        boolean isRemoved = list3.remove(null);

        System.out.println();

        if (isRemoved) {
            System.out.println("Из списка удалили элемент с значением " + null);
        } else {
            System.out.println("Из списка не было удалено значение " + null);
        }

        list2.addFirst(8);
        list2.add(2, 5);
        list2.add(3, 6);

        List<Integer> list4 = list2.copy();

        list4.reverse();

        System.out.println();
        System.out.println("Второй список: " + list2);
        System.out.println("Перевёрнутый второй список: " + list4);
    }
}
