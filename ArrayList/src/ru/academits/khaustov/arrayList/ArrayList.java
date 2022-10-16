package ru.academits.khaustov.arrayList;

import java.util.*;
import java.util.function.UnaryOperator;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            currentIndex++;

            checkChanges();

            if (currentIndex >= items.length) {
                throw new NoSuchElementException("Коллекция кончилась.");
            }

            return items[currentIndex];
        }

        final void checkChanges() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменён.");
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public boolean contains(Object o) {
        for (T t : this) {
            if (o.equals(t)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            Object[] newArray = Arrays.copyOf(items, size);

            //noinspection unchecked
            return (T1[]) newArray;
        }

        Object[] newArray = Arrays.copyOf(items, array.length);

        //noinspection unchecked
        return (T1[]) newArray;
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException("Добавляемый элемент не должен быть пустым.");
        }

        if (size < items.length) {
            items[size] = t;
            size++;
            modCount++;

            return true;
        }

        increaseCapacity();
        items[size] = t;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("Добавляемый элемент не должен быть пустым.");
        }

        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); index++) {
            if (o.equals(i.next())) {
                if (index < size - 1) {
                    System.arraycopy(items, index + 1, items, index, size - index - 1);
                }

                items[size - 1] = null;
                size--;
                modCount++;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isNotContains = true;

        for (int i = 0; i < c.size(); i++) {
            for (T t : this) {
                if (t.equals(c.toArray()[i])) {
                    isNotContains = false;

                    break;
                }
            }

            if (isNotContains) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] collectionArray = c.toArray();
        int collectionArrayLength = collectionArray.length;

        if (collectionArrayLength == 0) {
            return false;
        }

        ensureCapacity(size + collectionArrayLength);

        for (int i = size, j = 0; i < items.length; i++, j++) {
            //noinspection unchecked
            items[i] = (T) collectionArray[j];
        }

        size = size + collectionArrayLength;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] collectionArray = c.toArray();
        int collectionArrayLength = collectionArray.length;
        int newSize = size + collectionArrayLength;

        if (collectionArrayLength == 0 || index >= newSize) {
            return false;
        }

        ensureCapacity(newSize);

        for (int i = size - 1; i >= index; i--) {
            items[i + collectionArrayLength] = items[i];
            items[i] = null;
        }

        for (int i = index, j = 0; i < index + collectionArrayLength; i++, j++) {
            //noinspection unchecked
            items[i] = (T) collectionArray[j];
        }

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] collectionArray = c.toArray();
        int collectionArrayLength = collectionArray.length;

        if (collectionArrayLength == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (Object o : collectionArray) {
            for (int j = 0; j < size; j++) {
                //noinspection unchecked
                if (items[j].equals((T) o)) {
                    if (j < size - 1) {
                        System.arraycopy(items, j + 1, items, j, size - j - 1);
                    }

                    items[size - 1] = null;
                    size--;
                    modCount++;

                    isRemoved = true;
                }
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] collectionArray = c.toArray();
        int collectionArrayLength = collectionArray.length;

        if (collectionArrayLength == 0) {
            items = null;
            modCount++;

            return true;
        }

        boolean isChanged = false;

        for (Object o : collectionArray) {
            for (int j = 0; j < size; j++) {
                //noinspection unchecked
                if (!(items[j].equals((T) o))) {
                    if (j < size - 1) {
                        System.arraycopy(items, j + 1, items, j, size - j - 1);
                    }

                    items[size - 1] = null;
                    size--;
                    modCount++;
                    isChanged = true;


                }
            }
        }

        return isChanged;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void clear() {
        items = null;
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < items.length && index >= 0) {
            return items[index];
        }

        throw new IndexOutOfBoundsException("Индекс выходит за границы списка.");
    }

    @Override
    public T set(int index, T element) {
        int itemsLength = items.length;

        if (index >= itemsLength || index < 0) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка.");
        }

        T prevElement = items[index];
        items[index] = element;

        return prevElement;
    }

    @Override
    public void add(int index, T element) {
        ensureCapacity(size + 1);

        if (index < size - 1) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        T prevElement = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;
        modCount++;

        return prevElement;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); index++) {
            if (i.next().equals(o)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int elementIndex = -1;

        for (Iterator<T> i = iterator(); i.hasNext(); index++) {
            if (i.next().equals(o)) {
                elementIndex = index;
            }
        }

        return elementIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}