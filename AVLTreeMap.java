import java.util.Comparator;

// La clase AVLTreeMap implementa la interfaz CustomMap utilizando un árbol AVL.
public class AVLTreeMap<K, V> implements CustomMap<K, V> {

    // Nodo interno de la clase para representar los nodos del árbol AVL.
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;
    private final Comparator<? super K> comparator;

    // Constructor sin argumentos que crea un árbol AVL vacío con orden natural.
    public AVLTreeMap() {
        this(null);
    }

    // Constructor con un comparador personalizado.
    public AVLTreeMap(Comparator<? super K> comparator) {
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
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = compare(key, node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1) {
            if (compare(key, node.left.key) < 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (compare(key, node.right.key) > 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    @Override
    public V get(K key) {
        Node node = get(root, key);
        return (node == null) ? null : node.value;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = compare(key, node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
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
}

