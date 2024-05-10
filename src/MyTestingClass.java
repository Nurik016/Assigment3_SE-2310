import java.util.Random;

public class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    public int hashCode(int capacity) {
        int hash = value;
        hash = (hash ^ (hash >>> 16)) * 0x85ebca6b;
        hash = (hash ^ (hash >>> 13)) * 0xc2b2ae35;
        hash = hash ^ (hash >>> 16);
        return Math.abs(hash % capacity);
    }

    private static final Random random = new Random();

    private static MyTestingClass generateRandomKey() {
        int randomInt = random.nextInt(1000);
        return new MyTestingClass(randomInt);
    }


    private static String generateRandomValue() {
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(10) + 5;
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            sb.append(randomChar);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = generateRandomKey();
            String value = generateRandomValue();
            table.put(key, value);
        }
        printBucketSizes(table);

//        for (int i = 0; i <100; i++) {
//            table.printBucket(i);
//        }
    }

    private static void printBucketSizes(MyHashTable<MyTestingClass, String> table) {
        int capacity = table.getSize();
        System.out.println("Hash Table Size: " + capacity);

        for (int i = 0; i < capacity; i++) {
            int bucketSize = table.getBucketSize(i);
            System.out.println("Bucket " + i + ": " + bucketSize + " elements");
        }


        BST<Integer, String> bst = new BST<>();

        bst.put(5, "Five");
        bst.put(3, "Three");
        bst.put(7, "Seven");
        bst.put(2, "Two");
        bst.put(4, "Four");
        bst.put(6, "Six");
        bst.put(8, "Eight");

        System.out.println("Binary Search Tree in-order traversal:");
        bst.inOrder();
        System.out.println();

        System.out.println("Value for key 4: " + bst.get(4));
        bst.delete(3);
        System.out.println("Binary Search Tree in-order traversal after deletion:");
        bst.inOrder();
        System.out.println();
    }
}