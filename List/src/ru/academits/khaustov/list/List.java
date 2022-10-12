package ru.academits.khaustov.list;

import ru.academits.khaustov.list_item.ListItem;

public class List<T> {
    private ListItem<T> head;
    private int count;

    public ListItem<T> getHead() {
        return head;
    }

    public List(ListItem<T> head, int count) {
        this.count = count;
        this.head = head;
    }

    public int getSize() {
        return count;
    }

    public T getHeadData() {
        return head.getData();
    }

    public T getItemData(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка и принимать положительные значения.");
        }

        int itemIndex = 0;
        T itemData = null;

        for (ListItem<T> item = head; item != null; item = item.getNext(), itemIndex++) {
            if (itemIndex == index) {
                itemData = item.getData();

                break;
            }
        }

        return itemData;
    }

    public T setItemData(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка и принимать положительные значения.");
        }

        int itemIndex = 0;
        T lastData = null;

        for (ListItem<T> item = head; item != null; item = item.getNext(), itemIndex++) {
            if (itemIndex == index) {
                lastData = item.getData();
                item.setData(data);

                break;
            }
        }

        return lastData;
    }

    public T removeItem(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка и принимать положительные значения.");
        }

        T itemData = null;

        if (index == 0) {
            itemData = head.getData();
            head = head.getNext();

            count--;

            return itemData;
        }

        int itemIndex = 1;

        for (ListItem<T> item = head; item.getNext() != null; item = item.getNext()) {
            if (itemIndex == index) {
                itemData = item.getNext().getData();
                item.setNext(item.getNext().getNext());

                break;
            }
        }

        count--;

        return itemData;
    }

    public void addBeginning(ListItem<T> item) {
        item.setNext(head);
        head = item;

        count++;
    }

    public void addIndexItem(int index, ListItem<T> item) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс должен быть меньше размера списка и принимать положительные значения.");
        }

        int i = 1;

        for (ListItem<T> newItem = head; newItem.getNext() != null; newItem = newItem.getNext(), i++) {
            if (i == index) {
                item.setNext(newItem.getNext().getNext());
                newItem.setNext(item);

                break;
            }
        }

        count++;
    }

    public boolean removeItem(T data) {
        if (data.equals(head.getData())) {
            head = head.getNext();

            count--;

            return true;
        }

        for (ListItem<T> item = head; item.getNext() != null; item = item.getNext()) {
            if (data.equals(item.getNext().getData())) {
                item.setNext(item.getNext().getNext());

                count--;

                return true;
            }
        }

        return false;
    }

    public T removeHead() {
        T itemData = head.getData();
        head = head.getNext();

        count--;

        return itemData;
    }

    public void reverseList() {
        if (count >= 2) {
            ListItem<T> itemCopy2 = head.getNext().getNext();

            head.getNext().setNext(head);
            ListItem<T> itemCopy1 = head.getNext();

            head.setNext(null);

            for (ListItem<T> item = itemCopy2; item != null; item = item.getNext(), itemCopy2 = item) {
                itemCopy2.setNext(itemCopy1);
                itemCopy1 = itemCopy2;
            }

            head = itemCopy1;
        }
    }

    public List<T> copyList() {
        ListItem<T> listCopyItem = head;

        List<T> listCopy = new List<>(listCopyItem, count);

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext(), listCopyItem = listCopyItem.getNext()) {
            listCopyItem.setNext(item);
        }

        return listCopy;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");

        string.append(head.getData());

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            string.append(", ");
            string.append(item.getData());
        }

        string.append("]");

        return string.toString();
    }
}
