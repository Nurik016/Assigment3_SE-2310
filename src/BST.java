public class BST<K extends Comparable<K>, V> {
    public Node root;

    public class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BST() {
        root = null;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node current, K key, V value) {
        if (current == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            current.left = put(current.left, key, value);
        else if (cmp > 0)
            current.right = put(current.right, key, value);
        else
            current.value = value;

        return current;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node current, K key) {
        if (current == null)
            return null;

        int cmp = key.compareTo(current.key);
        if (cmp < 0)
            current.left = delete(current.left, key);
        else if (cmp > 0)
            current.right = delete(current.right, key);
        else {
            if (current.right == null)
                return current.left;
            if (current.left == null)
                return current.right;
            Node temp = current;
            current = min(temp.right);
            current.right = deleteMin(temp.right);
            current.left = temp.left;
        }
        return current;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + ":" + node.value + " ");
            inOrder(node.right);
        }
    }
}
