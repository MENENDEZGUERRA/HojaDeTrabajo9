import java.util.Comparator;

public class TwoThreeTreeMap<K, V> implements CustomMap<K, V> {

    private class Node {
        K key;
        V value;
        Node left, middle, right;
        boolean isThreeNode;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = middle = right = null;
            isThreeNode = false;
        }
    }

    private Node root;
    private int size;
    private Comparator<K> comparator;

    public TwoThreeTreeMap(Comparator<K> comparator) {
        root = null;
        size = 0;
        this.comparator = comparator;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            NodeWrapper nw = put(root, key, value);
            if (nw != null) {
                root = nw.node;
            }
        }
        size++;
    }

    private class NodeWrapper {
        Node node;

        NodeWrapper(Node node) {
            this.node = node;
        }
    }

    private NodeWrapper put(Node node, K key, V value) {
        if (node == null) {
            return new NodeWrapper(new Node(key, value));
        }

        if (node.isThreeNode) {
            // Handle 3-node case
        } else {
            // Handle 2-node case
            int cmp = comparator.compare(key, node.key);
            if (cmp < 0) {
                NodeWrapper nw = put(node.left, key, value);
                if (nw != null) {
                    node.left = nw.node;
                }
            } else if (cmp > 0) {
                NodeWrapper nw = put(node.right, key, value);
                if (nw != null) {
                    node.right = nw.node;
                }
            } else {
                node.value = value;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = comparator.compare(key, node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
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
