public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer, String> table = new MyHashTable<>();

        table.put(100, "SpongeBob");
        table.put(123, "Patrick");
        table.put(321, "Sandy");
        table.put(555, "Squidward");
        table.put(777, "SpongeBob");

        System.out.println("key 100: " + table.get(100));
        System.out.println("key 123: " + table.get(123));
        System.out.println("key 321: " + table.get(321));
        System.out.println("key 555: " + table.get(555));
        System.out.println("key 777: " + table.get(777));
        System.out.println();

        System.out.println("key 100: " + table.contains(100));
        System.out.println("key 200: " + table.contains(200));
        System.out.println();

        String removedValue = table.remove(123);
        System.out.println("removed key 123: " + removedValue);
        System.out.println("after removal: " + table.contains(123));
        System.out.println();

        Integer keyForValueSandy = table.getKey("Sandy");
        System.out.println("Key for Sandy: " + keyForValueSandy);
        System.out.println();

        //for check resizeAndRehash
        for (int i = 0; i < 20; i++) {
            table.put(i, "Value" + i);
            System.out.println("Added key " + i);
        }

        for (int i = 0; i < 20; i++) {
            System.out.println("Value for key " + i + ": " + table.get(i));
        }
    }
}
