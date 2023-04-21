import java.util.Comparator;

// La clase SplayTreeMap implementa la interfaz CustomMap utilizando un árbol splay.
public class SplayTreeMap<K, V> implements CustomMap<K, V> {

    // Nodo interno de la clase para representar los nodos del árbol splay.
    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;
    private final Comparator<? super K> comparator;

    // Constructor sin argumentos que crea un árbol splay vacío con orden natural.
    public SplayTreeMap() {
        this(null);
    }

    // Constructor con un comparador personalizado.
    public SplayTreeMap(Comparator<? super K> comparator) {
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

    // Método auxiliar para rotar a la derecha en el árbol splay.
private Node rightRotate(Node x) {
    Node y = x.left;
    x.left = y.right;
    y.right = x;
    return y;
}

// Método auxiliar para rotar a la izquierda en el árbol splay.
private Node leftRotate(Node x) {
    Node y = x.right;
    x.right = y.left;
    y.left = x;
    return y;
}

// Método auxiliar para realizar el splay en el árbol splay.
private Node splay(Node root, K key) {
    if (root == null || compare(root.key, key) == 0) {
        return root;
    }

    if (compare(key, root.key) < 0) {
        if (root.left == null) {
            return root;
        }

        if (compare(key, root.left.key) < 0) {
            root.left.left = splay(root.left.left, key);
            root = rightRotate(root);
        } else if (compare(key, root.left.key) > 0) {
            root.left.right = splay(root.left.right, key);
            if (root.left.right != null) {
                root.left = leftRotate(root.left);
            }
        }

        return (root.left == null) ? root : rightRotate(root);
    } else {
        if (root.right == null) {
            return root;
        }

        if (compare(key, root.right.key) < 0) {
            root.right.left = splay(root.right.left, key);
            if (root.right.left != null) {
                root.right = rightRotate(root.right);
            }
        } else if (compare(key, root.right.key) > 0) {
            root.right.right = splay(root.right.right, key);
            root = leftRotate(root);
        }

        return (root.right == null) ? root : leftRotate(root);
    }
}

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }
    
        root = splay(root, key);
    
        int cmp = compare(key, root.key);
        if (cmp < 0) {
            Node newNode = new Node(key, value);
            newNode.left = root.left;
            newNode.right = root;
            root.left = null;
            root = newNode;
            size++;
        } else if (cmp > 0) {
            Node newNode = new Node(key, value);
            newNode.right = root.right;
            newNode.left = root;
            root.right = null;
            root = newNode;
            size++;
        } else {
            root.value = value;
        }
    }

    @Override
    public V get(K key) {
        root = splay(root, key);
        if (root == null || compare(root.key, key) != 0) {
            return null;
        }
        return root.value;
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
