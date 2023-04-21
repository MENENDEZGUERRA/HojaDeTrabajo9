import java.util.LinkedList;

public class HashMap<K, V> implements CustomMap<K, V> {
    
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    
    private class Entry {
        K key;
        V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private LinkedList<Entry>[] table;
    private int size;
    
    public HashMap() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
        
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }
    
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }
    
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        int index = hash(key);
        LinkedList<Entry> list = table[index];
        
        for (Entry entry : list) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        
        list.add(new Entry(key, value));
        size++;
        
        if (size / (float) table.length >= LOAD_FACTOR) {
            resize();
        }
    }
    
    private void resize() {
        LinkedList<Entry>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        
        size = 0;
        
        for (LinkedList<Entry> list : oldTable) {
            for (Entry entry : list) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        LinkedList<Entry> list = table[index];
        
        for (Entry entry : list) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        
        return null;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
