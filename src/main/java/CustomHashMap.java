import java.util.NoSuchElementException;

public class CustomHashMap {
    private static final int CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.7;
    private int initialCapacity;
    public Entry[] entries;
    private int size = 0;

    public CustomHashMap() {
        initialCapacity = CAPACITY;
        entries = new Entry[initialCapacity];
    }

    private int countBucketPosition(Integer key) {
        if(key != null) return Math.abs(key.hashCode() % entries.length);
        else return 0;
    }

    public void put(int key, long value) {
        resize();

        Entry newEntry = new Entry(key, value);
        int position = countBucketPosition(newEntry.getKey());
        Entry temp = entries[position];

        if (temp == null) {
            entries[position] = newEntry;
            size++;
            return;
        }
        while (position < entries.length) {
            if (position == entries.length - 1)
                position = 0;

            temp = entries[position];

            if (temp == null) {
                entries[position] = new Entry(key, value);
                size++;
                return;
            }
            if (newEntry.getKey().equals(temp.getKey())) {
                temp.setValue(value);
                return;
            }
            position++;
        }
    }

    public long get(int key) {
        int position = countBucketPosition(key);
        Entry temp = entries[position];

        while (position < entries.length && temp != null) {
            if (position == entries.length - 1)
                position = 0;

            if (temp.getKey() == key)
                return temp.getValue();

            temp = entries[position];
            position++;
        }
        throw new NoSuchElementException("There isn't element with such key");
    }

    public int getSize() {
        return size;
    }

    public void resize() {
        if (size == initialCapacity * LOAD_FACTOR) {
            size = 0;
            Entry[] tempEntries = entries;
            initialCapacity *= 2;

            if (initialCapacity > (Integer.MAX_VALUE - 1))
                throw new RuntimeException("Can't resize map, it has maximum capacity.");

            entries = new Entry[entries.length * 2];

            for (int i = 0; i < tempEntries.length; i++) {
                if (tempEntries[i] != null)
                    put(tempEntries[i].getKey(), tempEntries[i].getValue());
            }
        }
    }
}
