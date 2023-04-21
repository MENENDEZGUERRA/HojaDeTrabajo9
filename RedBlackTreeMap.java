import java.util.Comparator;

// La clase RedBlackTreeMap implementa la interfaz CustomMap utilizando un árbol rojo-negro.
public class RedBlackTreeMap<K, V> implements CustomMap<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Nodo interno de la clase para representar los nodos del árbol rojo-negro.
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;
    private int size;
    private final Comparator<? super K> comparator;

    // Constructor sin argumentos que crea un árbol rojo-negro vacío con orden natural.
    public RedBlackTreeMap() {
        this(null);
    }

    // Constructor con un comparador personalizado.
    public RedBlackTreeMap(Comparator<? super K> comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    // Método auxiliar para comparar claves utilizando el comparador proporcionado o el orden natural.
    private int compare(K key1, K key2) {
        if (comparator != null) {
            return comparator.compare(key1, key2);
        } else {
            return ((Comparable<? super K>) key1).compareTo(key2);
        }
    }

    // Implementación de los métodos de la interfaz CustomMap.

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null) {
            size++;
            return new Node(key, value, RED);
        }

        int cmp = compare(key, h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.value = value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        while (x != null) {
            int cmp = compare(key, x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
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

       
