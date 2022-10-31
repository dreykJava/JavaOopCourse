package ru.academits.khaustov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] table;
    private int modCount;
    private int elementsCount;

    public HashTable(Collection<E> collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException("Переданная коллекция не должна быть пустой.");
        }

        //noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[collection.size()];
        table[0] = new ArrayList<>();

        for (E element : collection) {
            if (element == null) {
                table[0].add(null);
            } else {
                int listIndex = Math.abs(element.hashCode() % table.length);

                if (table[listIndex] == null) {
                    table[listIndex] = new ArrayList<>();
                }

                table[listIndex].add(element);
            }
        }

        elementsCount = collection.size();
    }

    public HashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размер таблицы должна быть больше 0.");
        }

        //noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[length];
        elementsCount = 0;
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (E element : this) {
                if (element == null) {
                    return true;
                }
            }

            return false;
        }

        int index = Math.abs(o.hashCode() % table.length);

        if (table[index] != null) {
            for (int i = 0; i < table[index].size(); i++) {
                if (o.equals(table[index].get(i))) {
                    return true;
                }
            }
        }

        return false;
    }

    public class ListIterator implements Iterator<E> {
        private int currentListIndex = 0;
        private int currentElementIndex = -1;
        private int elementsPassedCount = 0;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return elementsPassedCount < elementsCount;
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

            if (currentElementIndex < table[currentListIndex].size()) {
                return table[currentListIndex].get(currentElementIndex);
            }

            currentListIndex++;

            while (table[currentListIndex] == null) {
                currentListIndex++;
            }

            return table[currentListIndex].get(0);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[elementsCount];

        int index = 0;

        for (E element : this) {
            resultArray[index] = element;
            index++;
        }

        return resultArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < elementsCount) {
            a = Arrays.copyOf(a, elementsCount);
        }

        int index = 0;

        for (E element : this) {
            //noinspection unchecked
            a[index] = (T) element;
            index++;
        }

        for (int i = index; i < a.length; i++) {
            a[index] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        if (element == null) {
            if (table[0] == null) {
                table[0] = new ArrayList<>();
            }

            table[0].add(null);

            elementsCount++;
            modCount++;

            return true;
        }

        int hashTableIndex = Math.abs(element.hashCode() % table.length);

        if (table[hashTableIndex] == null) {
            table[hashTableIndex] = new ArrayList<>();
        }

        table[hashTableIndex].add(element);
        elementsCount++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (ArrayList<E> list : table) {
                if (list != null && list.remove(null)) {
                    elementsCount--;
                    modCount++;

                    return true;
                }
            }

            return false;
        }

        int listIndex = Math.abs(o.hashCode() % table.length);

        if (table[listIndex] != null && table[listIndex].remove(o)) {
            elementsCount--;
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
            return true;
        }

        boolean isRemoved = false;
        int initialListSize;

        for (ArrayList<E> list : table) {
            if (list == null) {
                continue;
            }

            initialListSize = list.size();

            if (list.removeAll(collection)) {
                isRemoved = true;

                elementsCount -= initialListSize - list.size();
                modCount++;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (elementsCount == 0) {
            return false;
        }

        if (collection == null) {
            clear();

            return true;
        }

        boolean isChanged = false;
        int initialListSize;

        for (ArrayList<E> list : table) {
            if (list == null) {
                continue;
            }

            initialListSize = list.size();

            if (list.retainAll(collection)) {
                isChanged = true;

                elementsCount -= initialListSize - list.size();
                modCount++;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        for (ArrayList<E> list : table) {
            if (list != null) {
                list.clear();
            }
        }

        elementsCount = 0;
        modCount++;
    }
}