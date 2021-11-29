import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomHashMap extends SomeHash{
    private static final double LOAD_FACTOR = 1.0d;
    private Entry[] entries = new Entry[4];
    private int size = 0;

    private int findSlot(int key) {
        int i = hashFunc1(key);

        while ((entries[i] != null) && !Objects.equals(entries[i].getKey(), key))
            i = (hashFunc1(key) + (i+1) * hashFunc2(key)) % getCapacity();

        return i;
    }

    public void put(int key, long value) {
        if (tableIsFull()) resizeTableToTwice();
        int index = findSlot(key);

        if (entries[index] != null) {
            entries[index].setValue(value);
            return;
        }

        entries[index] = new Entry(key, value);
        ++size;
    }

    public Object get(int key) {
        int index = findSlot(key);
        Entry temp = entries[index];

        if (temp != null && Objects.equals(temp.getKey(), key)) return temp.getValue();

        throw new NoSuchElementException("There isn't element with such key");
    }

    private boolean tableIsFull() {
        return ((double) size / (double) getCapacity()) == LOAD_FACTOR;
    }

    private void resizeTableToTwice() {
        Entry[] tempEntries = entries;
        entries = new Entry[getCapacity() * 2];

        for (int i = 0; i < tempEntries.length; i++) {
            if (tempEntries[i] != null) entries[i] = tempEntries[i];
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    protected int getCapacity() {
        return entries.length;
    }
}
