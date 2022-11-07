package ru.academits.khaustov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class List<T> {
    private ListItem<T> head;
    private int size;

    public List() {

    }

    public List(T data) {
        head = new ListItem<>(data);
        size = 1;
    }

    public List(List<T> list) {
        if (list == null) {
            throw new NullPointerException("null - недопустимое значение для списка");
        }

        head = new ListItem<>(list.head.getData());
        ListItem<T> currentItem = head;

        for (ListItem<T> copyItem = list.head.getNext(); copyItem != null; copyItem = copyItem.getNext(), currentItem = currentItem.getNext()) {
            currentItem.setNext(new ListItem<>(copyItem.getData()));
        }

        size = list.getSize();
    }

    private static void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + " - неправильный индекс. Допустимые значения: [0; " + (size - 1) + "].");
        }
    }

    private ListItem<T> getItem(int index) {
        int itemIndex = 0;
        ListItem<T> item;

        for (item = head; item != null; item = item.getNext(), itemIndex++) {
            if (itemIndex == index) {
                break;
            }
        }

        return item;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    public T get(int index) {
        checkIndex(index, size);

        return getItem(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index, size);

        ListItem<T> item = getItem(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T remove(int index) {
        checkIndex(index, size);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);
        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        size--;

        return removedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        size++;
    }

    public void add(int index, T data) {
        checkIndex(index, size + 1);

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        size++;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            removeFirst();

            return true;
        }

        for (ListItem<T> item = head; item.getNext() != null; item = item.getNext()) {
            if (Objects.equals(data, item.getNext().getData())) {
                item.setNext(item.getNext().getNext());

                size--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }

        T removedData = head.getData();
        head = head.getNext();

        size--;

        return removedData;
    }

    public void reverse() {
        if (size < 2) {
            return;
        }

        ListItem<T> current = head;
        ListItem<T> temp;

        for (temp = current.getNext(); temp != null; temp = current.getNext()) {
            current.setNext(current.getNext().getNext());
            temp.setNext(head);
            head = temp;
        }
    }

    public List<T> copy() {
        if (head == null) {
            return null;
        }

        return new List<>(this);
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        stringBuilder.append(head.getData());

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            stringBuilder.append(", ");
            stringBuilder.append(item.getData());
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}