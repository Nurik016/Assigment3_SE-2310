import java.util.Random;

public class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
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

        for (int i = 0; i <= 10000; i++) {
            MyTestingClass key = generateRandomKey();
            String value = generateRandomValue();
            table.put(key, value);
        }
        printBucketSizes(table);
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