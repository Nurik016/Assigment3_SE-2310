import java.util.Random;

public class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    public int hashCode(int capacity) {
        int hash = value;
        hash = (hash ^ (hash >>> 20)) ^ (hash >>> 12);
        hash = (hash ^ (hash >>> 7)) ^ (hash >>> 4);
        return Math.abs(hash) % capacity;
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

        for (int i = 0; i <10000; i++) {
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
    }
}