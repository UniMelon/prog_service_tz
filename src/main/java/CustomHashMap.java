import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomHashMap extends SomeHash{
    private static final double LOAD_FACTOR = 0.7;
    private Entry[] entries = new Entry[16];
    private int size = 0;

    private int findSlot(Object key) {
        int i = hashIndex(key);

        while ((entries[i] != null) && !Objects.equals(entries[i].getKey(), key))
            i = (i + 1) % getCapacity();

        return i;
    }

    public void put(Object key, Object value) {
        if (tableIsTwoThirdsFull()) resizeTableToTwice();
        int index = findSlot(key);

        if (index == getCapacity() - 1 || key == null) index = 0;

        if (entries[index] != null) {
            entries[index].setValue(value);
            return;
        }

        entries[index] = new Entry(key, value);
        ++size;
    }

    public Object get(Object key) {
        int index = findSlot(key);
        Entry temp = entries[index];

        while(index < getCapacity() && temp != null) {
            if (index == getCapacity() - 1) index = 0;
            if (temp.getKey() == key) return temp.getValue();
        }
        throw new NoSuchElementException("There isn't element with such key");
    }

    private boolean tableIsTwoThirdsFull() {
        return ((double) size / (double) getCapacity()) >= LOAD_FACTOR;
    }

    private void resizeTableToTwice() {
        size = 0;
        Entry[] tempEntries = entries;
        entries = new Entry[getCapacity() * 2];

        for (int i = 0; i < tempEntries.length; i++) {
            if (tempEntries[i] != null)
                put(tempEntries[i].getKey(), tempEntries[i].getValue());
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
