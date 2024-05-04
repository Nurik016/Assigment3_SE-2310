
public class MyHashTable<K, V> {
    private static final double LOAD_FACTOR = 0.75;

    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M=11;
    private int size;

    public MyHashTable(){
        this.M=11; //just DEFAULT_CAPACITY maybe i will delete this part code because we know that m already 11
        chainArray = new HashNode[M];
        size=0;
    }

    public MyHashTable(int M){
        this.M = M; // changing CAPACITY
        chainArray = new HashNode[M];
        size=0;
    }

    private int hash(K key) {
        return key.hashCode() % M;
    }
    public void put(K key, V value) {
        int i   = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[i] == null) {
            chainArray[i] = newNode;
        } else {
            HashNode<K, V> current = chainArray[i];
            while (current.next != null) {
                if (key.equals(current.key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        checker();

        //for checking busket on linkedlist //just for me
        System.out.print("Bucket " + i + ": ");
        HashNode<K, V> temp = chainArray[i];
        while (temp != null) {
            System.out.print(temp.key + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public V get(K key) {
        int i = hash(key);
        HashNode<K, V> current = chainArray[i];
        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int i = hash(key);
        HashNode<K, V> current = chainArray[i];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    chainArray[i] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> current = node;
            while (current != null) {
                if (value.equals(current.value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    public void checker(){
        if ((double) size / M >= LOAD_FACTOR){
            resizeAndRehash();
        }
    }

    private void resizeAndRehash() {
        int newCapacity = M * 2;
        HashNode<K, V>[] newChainArray = new HashNode[newCapacity];

        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> current = node;
            while (current != null) {
                int newIndex = hash(current.key) % newCapacity;

                HashNode<K, V> temp = current.next;
                current.next = newChainArray[newIndex];
                newChainArray[newIndex] = current;
                current = temp;
            }
        }

        M = newCapacity;
        chainArray = newChainArray;
    }
}
