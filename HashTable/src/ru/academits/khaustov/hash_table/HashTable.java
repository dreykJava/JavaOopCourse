package ru.academits.khaustov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int modCount;
    private int size;

    public HashTable(Collection<E> collection) {
        if (collection == null) {
            throw new NullPointerException("Вместо коллекции передали null.");
        }

        if (collection.size() == 0) {
            //noinspection unchecked
            lists = (ArrayList<E>[]) new ArrayList[10];

            return;
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[collection.size()];
        addAll(collection);
        size = collection.size();
    }

    public HashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размер таблицы должен быть больше 0.");
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[length];
    }

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[10];
    }

    private int getListIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int listIndex = getListIndex(o);

        if (lists[listIndex] != null) {
            return lists[listIndex].contains(o);
        }

        return false;
    }

    public class ListIterator implements Iterator<E> {
        private int currentListIndex = 0;
        private int currentElementIndex = -1;
        private int elementsPassedCount = 0;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return elementsPassedCount < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменён.");
            }

            currentElementIndex++;
            elementsPassedCount++;

            while (lists[currentListIndex] == null) {
                currentListIndex++;
            }

            if (currentElementIndex < lists[currentListIndex].size()) {
                return lists[currentListIndex].get(currentElementIndex);
            }

            currentListIndex++;

            while (lists[currentListIndex] == null) {
                currentListIndex++;
            }

            return lists[currentListIndex].get(0);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[size];

        int index = 0;

        for (E element : this) {
            resultArray[index] = element;
            index++;
        }

        return resultArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        int index = 0;

        for (E element : this) {
            //noinspection unchecked
            a[index] = (T) element;
            index++;
        }

        if (index < a.length) {
            a[index] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        int listIndex = getListIndex(element);

        if (lists[listIndex] == null) {
            lists[listIndex] = new ArrayList<>();
        }

        lists[listIndex].add(element);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int listIndex = getListIndex(o);

        if (lists[listIndex] != null && lists[listIndex].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Вместо коллекции передан null.");
        }

        if (collection.size() == 0) {
            return true;
        }

        boolean isRemoved = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            int initialListSize = list.size();

            if (list.removeAll(collection)) {
                isRemoved = true;

                size -= initialListSize - list.size();
                modCount++;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Вместо коллекции передан null.");
        }

        if (size == 0) {
            return false;
        }

        if (collection.size() == 0) {
            clear();

            return true;
        }

        boolean isChanged = false;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                continue;
            }

            int initialListSize = list.size();

            if (list.retainAll(collection)) {
                isChanged = true;

                size -= initialListSize - list.size();
                modCount++;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
        modCount++;
    }
}