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
    private int M = 11; // Default capacity
    private int size;

    public MyHashTable(){
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M){
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        if (key instanceof MyTestingClass) {
            return ((MyTestingClass) key).hashCode(M);
        } else {
            return key.hashCode() % M;
        }
    }


    public void put(K key, V value) {
        int index = Math.abs(hash(key)) % M;
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> current = chainArray[index];
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
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    chainArray[index] = current.next;
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

    public void checker() {
        if ((double) size / M >= LOAD_FACTOR) {
            resizeAndRehash();
        }
    }

    private void resizeAndRehash() {
        int newCapacity = M * 2;
        HashNode<K, V>[] newChainArray = new HashNode[newCapacity];

        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                HashNode<K, V> next = current.next;
                int newIndex = Math.abs(current.key.hashCode()) % newCapacity;
                current.next = newChainArray[newIndex];
                newChainArray[newIndex] = current;
                current = next;
            }
        }
        M = newCapacity;
        chainArray = newChainArray;
    }


    public int getBucketSize(int index) {
        if (index < 0 || index >= chainArray.length) {
            throw new IllegalArgumentException("invalid bucket index");
        }
        int count = 0;
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void printBucket(int index) {
        if (index < 0 || index >= chainArray.length) {
            throw new IllegalArgumentException("Invalid bucket index");
        }
        System.out.println("Elements in Bucket " + index + ":");
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            System.out.println(current.key + " -> " + current.value);
            current = current.next;
        }
    }


    public int getSize() {
        return size;
    }
}