package ru.academits.khaustov.list_main;

import ru.academits.khaustov.list.List;
import ru.academits.khaustov.list_item.ListItem;

public class ListMain {
    public static void main(String[] args) {
        ListItem<Integer> el4 = new ListItem<>(4);
        ListItem<Integer> el3 = new ListItem<>(10, el4);
        ListItem<Integer> el2 = new ListItem<>(8, el3);
        ListItem<Integer> el1 = new ListItem<>(5, el2);
        ListItem<Integer> head = new ListItem<>(3, el1);
        List<Integer> list1 = new List<>(head, 5);

        System.out.println("Размер списка равен: " + list1.getSize());
        System.out.println("Первый элемент списка равен: " + list1.getHeadData());
        System.out.println("Второй элемент списка равен: " + list1.getHead().getNext().getData());
        System.out.println("Третий элемент списка равен: " + list1.getItemData(2));

        list1.setItemData(1, 12);
        System.out.println("Второй элемент списка равен: " + list1.getItemData(1));

        System.out.println(list1);
        System.out.println("Четвёртый элемент списка равен: " + list1.getItemData(3));
        System.out.println("Из списка был удалён элемент: " + list1.removeItem(3));
        System.out.println(list1);
    }
}
