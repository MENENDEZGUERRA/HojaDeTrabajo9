import java.util.HashMap;

public class StandardHashMap<K, V> implements CustomMap<K, V> {

    private HashMap<K, V> map;

    public StandardHashMap() {
        map = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
