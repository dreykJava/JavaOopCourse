package ru.academits.khaustov.array_list;

import java.util.*;
import java.util.function.UnaryOperator;

public class ArrayList<E> implements List<E> {
    private E[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Размер списка должен быть >= 0. Переданный размер: " + capacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    private void increaseCapacity() {
        if (elements.length == 0) {
            //noinspection unchecked
            elements = (E[]) new Object[10];

            return;
        }

        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " недопустим. Допустимые значения: [0; " + (size - 1) + "].");
        }
    }

    private void checkAddingIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " недопустим. Допустимые значения: [0; " + size + "].");
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

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменён.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась.");
            }

            currentIndex++;

            return elements[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int resultSize = array.length;

        if (resultSize < size) {
            resultSize = size;
        }

        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, resultSize, array.getClass());
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        checkAddingIndex(index);

        if (size == elements.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        size--;
        modCount++;

        return removedElement;
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
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkAddingIndex(index);

        int collectionSize = collection.size();

        if (collectionSize == 0) {
            return false;
        }

        int newSize = size + collectionSize;
        ensureCapacity(newSize);

        int shift = size - index;

        if (shift > 0) {
            System.arraycopy(elements, index, elements, index + collectionSize, shift);
        }

        int i = index;

        for (E element : collection) {
            elements[i] = element;
            i++;
        }

        size = newSize;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int collectionSize = collection.size();

        if (collectionSize == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (Object element : collection) {
            while (remove(element)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (size == 0) {
            return false;
        }

        int collectionSize = collection.size();

        if (collectionSize == 0) {
            clear();

            return true;
        }

        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(elements[i])) {
                remove(i);

                isChanged = true;

                i--;
            }
        }

        return isChanged;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i > -1; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public java.util.ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public java.util.ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}