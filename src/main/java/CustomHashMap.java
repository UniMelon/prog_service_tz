import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

public class CustomHashMap<K,V> extends SomeHash {

    private int capacity = 16;
    private List<Entry>[] entries;
    private Entry entryNull;
    private int size = 0;

    public CustomHashMap() {
        entries = new LinkedList[capacity];
    }

    public CustomHashMap(int new_capacity) throws IllegalArgumentException {
        if (new_capacity<1) throw new IllegalArgumentException("Illegal Argument");

        capacity = new_capacity;
        entries = new LinkedList[new_capacity];
    }

    public V put(K key, V val) throws IllegalArgumentException {
        synchronized(entries){
            int hash;

            if (key != null) {
                if (key.hashCode() < 0) throw new IllegalArgumentException("Illegal Argument");
                else hash = hashFunc(key.hashCode());

            } else {
                entryNull = new Entry();
                entryNull.setValue(val);
                return val;
            }
            if (entries[hash] != null){
                for (Entry e : entries[hash]){
                    if (key.equals(e.getKey())){
                        e.setValue(val);
                        return val;
                    }
                }
            } else entries[hash] = new LinkedList<>();

            entries[hash].add(new Entry(key,val));
            ++size;
        }
        return val;
    }

    public V get(final K key) {
        synchronized(entries){
            int hash = hashFunc(key.hashCode());

            if (entries[hash] != null) {
                for (Entry e: entries[hash])
                    if (key.equals(e.getKey())) return e.getValue();
            }
        }
        return null;
    }

    public int getCapacity(){
        return capacity;
    }

    public int size(){
        return size;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Entry {
        private K key;
        private V value;
    }
}
