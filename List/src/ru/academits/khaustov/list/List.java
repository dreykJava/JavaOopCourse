package ru.academits.khaustov.list;

public class List<T> {
    private ListItem<T> head;
    private int size;

    public List() {
        head = null;
        size = 0;
    }

    public List(T first) {
        head = new ListItem<>(first);
        size = 1;
    }

    public List(List<T> list) {
        if (list.head == null) {
            throw new NullPointerException("Передан пустой список.");
        }

        head = new ListItem<>(list.head.getData());
        ListItem<T> currentItem = head;

        for (ListItem<T> copyItem = list.head.getNext(); copyItem != null; copyItem = copyItem.getNext(), currentItem = currentItem.getNext()) {
            currentItem.setNext(new ListItem<>(copyItem.getData()));
        }

        size = list.getSize();
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неправильный индекс. Допустимые значения: [" + 0 + "; " + (size - 1) + "].");
        }
    }

    private ListItem<T> iterateToIndex(int index) {
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
            throw new NullPointerException("В списке отсутствует первый элемент.");
        }

        return head.getData();
    }

    public T get(int index) {
        checkIndex(index, size);

        return iterateToIndex(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index, size);

        ListItem<T> item = iterateToIndex(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T remove(int index) {
        checkIndex(index, size);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> item = iterateToIndex(index - 1);
        T deletedData = item.getNext().getData();
        item.setNext(item.getNext().getNext());

        size--;

        return deletedData;
    }

    public void addFirst(T data) {
        if (head == null) {
            head = new ListItem<>(data);

            size++;

            return;
        }

        ListItem<T> previousHead = head;
        head = new ListItem<>(data, previousHead);

        size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Неправильный индекс. Дпоустимые значения: [" + 0 + "; " + size + "].");
        }

        if (index == 0) {
            addFirst(data);

            return;
        }

        int i = 1;

        for (ListItem<T> item = head; item != null; item = item.getNext(), i++) {
            if (i == index) {
                ListItem<T> addingItem = new ListItem<>(data, item.getNext());
                item.setNext(addingItem);

                break;
            }
        }

        size++;
    }

    public boolean remove(T data) {
        if (head == null) {
            throw new NullPointerException("Список пуст.");
        }

        if (data == null) {
            if (head.getData() == null) {
                removeFirst();

                return true;
            }

            for (ListItem<T> item = head; item.getNext() != null; item = item.getNext()) {
                if (item.getNext().getData() == null) {
                    item.setNext(item.getNext().getNext());

                    size--;

                    return true;
                }
            }

            return false;
        }

        if (data.equals(head.getData())) {
            removeFirst();

            return true;
        }

        for (ListItem<T> item = head; item.getNext() != null; item = item.getNext()) {
            if (data.equals(item.getNext().getData())) {
                item.setNext(item.getNext().getNext());

                size--;

                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException("Первый элемент отсутствует.");
        }

        T deletedData = head.getData();
        head = head.getNext();

        size--;

        return deletedData;
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
        List<T> listCopy = new List<>(head.getData());
        int index = 1;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext(), index++) {
            listCopy.add(index, item.getData());
        }

        listCopy.size = size;

        return listCopy;
    }

    public String stringBuilder() {
        if (head == null) {
            return "[]";
        }

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