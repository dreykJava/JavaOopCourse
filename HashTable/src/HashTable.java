import java.util.*;

public class HashTable<T> implements Collection<T> {
    private T[][] hashTable;
    private int capacity;
    private int modCount;

    public HashTable(T[][] hashTable) {
        int hashTableLength = hashTable.length;

        this.hashTable = Arrays.copyOf(hashTable, hashTableLength);
        capacity = hashTableLength;
    }

    public HashTable(int capacity, T[][] hashTable) {
        if (capacity >= hashTable.length)
            this.hashTable = Arrays.copyOf(hashTable, capacity);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        Object[][] newArray = new Object[capacity][];
        //noinspection unchecked
        hashTable = (T[][]) newArray;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    @Override
    public boolean contains(Object o) {
        int hashCode = Math.abs(o.hashCode() % hashTable.length);
        int hashTableListLength = hashTable[hashCode].length;

        for (int i = 0; i < hashTableListLength; i++) {
            if (o.equals(hashTable[hashCode][i])) {
                return true;
            }
        }

        return false;
    }

    public class MyListIterator implements Iterator<T> {
        private int currentTopIndex = 0;
        private int currentDownIndex = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return currentTopIndex < capacity && currentDownIndex + 1 < hashTable[currentTopIndex].length;
        }

        public T next() {
            if (currentDownIndex + 1 < hashTable[currentTopIndex].length) {
                currentDownIndex++;
            } else if (currentTopIndex < capacity) {
                currentTopIndex++;
                currentDownIndex = 0;
            } else {
                throw new NoSuchElementException("Коллекция кончилась.");
            }

            checkChanges();

            return hashTable[currentTopIndex][currentDownIndex];
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
    public Object[] toArray() {
        int downListsLengthSum = 0;

        for (T[] ts : hashTable) {
            downListsLengthSum += ts.length;
        }

        Object[] newArray = new Object[downListsLengthSum];

        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); index++) {
            newArray[index] = i.next();
        }

        return newArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        int downListsLengthSum = 0;

        for (T[] ts : hashTable) {
            downListsLengthSum += ts.length;
        }

        if (a.length < capacity) {
            a = Arrays.copyOf(a, downListsLengthSum);
        }

        int index = 0;

        for (Iterator<T> i = iterator(); i.hasNext(); index++) {
            //noinspection unchecked
            a[index] = (T1) i.next();
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        int hashTableIndex = Math.abs(t.hashCode() % capacity);

        for (int i = 0; i < hashTable[hashTableIndex].length; i++) {
            if (hashTable[hashTableIndex][i] != null) {
                continue;
            }

            hashTable[hashTableIndex][i] = t;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        int objectIndex = Math.abs(o.hashCode() % capacity);
        int hashTableListLength = hashTable[objectIndex].length;

        for (int i = 0; i < capacity; i++) {
            if (!(o.equals(hashTable[objectIndex][i]))) {
                continue;
            }

            System.arraycopy(hashTable[objectIndex], i + 1, hashTable[objectIndex], i, hashTableListLength - i - 1);
            hashTable[objectIndex][hashTableListLength - 1] = null;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isNotContains = true;

        for (int i = 0; i < c.size(); i++) {
            for (T t : this) {
                if (c.toArray()[i].equals(t)) {
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
        Object[] collection = c.toArray();
        int collectionLength = collection.length;

        if (collectionLength == 0) {
            return false;
        }

        for (Object o : collection) {
            int elementListHashTableIndex = Math.abs(o.hashCode() % capacity);

            for (int j = 0; j < hashTable[elementListHashTableIndex].length; j++) {
                if (hashTable[elementListHashTableIndex][j] != null) {
                    continue;
                }

                //noinspection unchecked
                hashTable[elementListHashTableIndex][j] = (T) o;
                modCount++;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] collection = c.toArray();
        int collectionLength = collection.length;
        int currentModCount = modCount;

        if (collectionLength == 0) {
            return false;
        }

        for (Object o : collection) {
            int objectListHashTableIndex = Math.abs(o.hashCode() % capacity);

            for (int j = 0; j < hashTable[objectListHashTableIndex].length; j++) {
                if (!(hashTable[objectListHashTableIndex][j].equals(o))) {
                    continue;
                }

                System.arraycopy(hashTable[objectListHashTableIndex], j + 1, hashTable[objectListHashTableIndex], j, objectListHashTableIndex - j - 1);
                hashTable[objectListHashTableIndex][hashTable[objectListHashTableIndex].length - 1] = null;
                modCount++;
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] collection = c.toArray();
        int collectionLength = collection.length;
        int currentModCount = modCount;

        if (collectionLength == 0) {
            return false;
        }

        for (Object o : collection) {
            int objectListHashTableIndex = Math.abs(o.hashCode() % capacity);

            for (int j = 0; j < hashTable[objectListHashTableIndex].length; j++) {
                if (hashTable[objectListHashTableIndex][j].equals(o)) {
                    continue;
                }

                System.arraycopy(hashTable[objectListHashTableIndex], j + 1, hashTable[objectListHashTableIndex], j, objectListHashTableIndex - j - 1);
                hashTable[objectListHashTableIndex][hashTable[objectListHashTableIndex].length - 1] = null;
                modCount++;
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public void clear() {
        Object[][] clearHashTable = new Object[capacity][];
        //noinspection unchecked
        hashTable = (T[][]) clearHashTable;
    }
}
