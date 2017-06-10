package UnivHash;

import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/20/17.
 * @author Marc Magdi
 */
public class HashTableN implements HashTable {

    /**.
     * The hash table containing the data.
     */
    private HashTable data[];

    /**.
     * Counter for how many times we needed to rebuild the hash table.
     */
    private int collisions = 0;

    /**.
     * Object responsible for providing an universal hashing.
     */
    private UniversalHashing universalHashing;

    /**.
     * The whole capacity of the hash table
     */
    private int capacity = 0;

    /**.
     * Constructor given the needed array as the data has to be static.
     * @param numbers the array to be hashed.
     */
    HashTableN(int numbers[]) {
        int values[] = removeDuplicates(numbers);
        this.universalHashing = new UniversalHashingImp(values.length);
        this.data = new HashTableNSquare[values.length];
        this.universalHashing.setRandomParameters(values.length);

        buildPerfectTable(values);
    }

    private int[] removeDuplicates(int numbers[]) {
        Set<Integer> set = new HashSet<>();

        for (int num : numbers) {
            set.add(num);
        }

        int returnVal[] = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            returnVal[i++] = num;
        }

        return returnVal;
    }

    /**.
     * Entry point for start building the hash table.
     * @param numbers the array to build the hash table with.
     */
    private void buildPerfectTable(int numbers[]) {
        List<Integer>[] integerList = new List[numbers.length];
        initializeCollisionList(integerList, numbers.length);
        capacity += numbers.length;
        for (int num : numbers) {
            int hashVal = universalHashing.getHashValue(num);
            if (!integerList[hashVal].contains(num))
                integerList[hashVal].add(num);
        }

        for (int i = 0; i < integerList.length; i++) {
            List<Integer> list = integerList[i];
            if (list != null && list.size() > 0) {
                HashTable hashtable = new HashTableNSquare(getArray(list));
                collisions += hashtable.getCollisions();
                capacity += hashtable.getCapacity();
                data[i] = hashtable;
            }
        }
    }

    /**.
     * Extract an array out of a list.
     * @param list the list of items to switch to array.
     * @return an array of items of the given list.
     */
    private int[] getArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        int i = 0;
        for (int num : list) {
            arr[i++] = num;
        }

        return arr;
    }

    /**.
     * Initialize the given array with new list items.
     * @param integerList the array to add the items to.
     * @param size the size of the array.
     */
    private void initializeCollisionList(List<Integer> integerList[], int size) {
        for (int i = 0; i < size; i++) {
            integerList[i] = new ArrayList<>();
        }
    }

    @Override
    public boolean exist(int value) {
        int hashValue = universalHashing.getHashValue(value);
        if (data[hashValue] == null) return false;
        return data[hashValue].exist(value);
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
//        int nums [] = {0, 1, 2, 4, 8, 909, 77, 22, 4, 66 ,111};
//        int nums [] = {228, 836};
//        int nums [] = {959, 299, 5};
//        int nums [] = {816963, 662556, 508149};
//        HashTable hashTableV1 = new HashTableNSquare(nums);
//        System.out.println(hashTableV1.exist(1));
//        System.out.println(hashTableV1.getCollisions());

        int nums2[] = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            nums2[i] = random.nextInt();
        }

        HashTable hashTableV11 = new HashTableN(nums2);
        System.out.println("Collision: " + hashTableV11.getCollisions());
        System.out.println("Collision: " + hashTableV11.getCapacity());

//        String str = System.getProperty("user.dir") + File.separator + "src" + File.separator
//                + "assignment" + File.separator + "four" + File.separator + "keys10000001000000.txt";
//        File file = new File(str);
//
//        Scanner scanner = new Scanner(file);
//        String line = scanner.next();
//        String numberString[] = line.split(",");
//        int numbers[] = new int[numberString.length];
//
//        for (int i = 0; i < numberString.length; i++) {
//            numbers[i] =  Integer.parseInt(numberString[i]);
//        }
//
//        HashTable hashTableV1 = new HashTableN(numbers);
//        System.out.println("Collision: " + hashTableV1.getCollisions());
//        System.out.println("Collision: " + hashTableV1.getCapacity());
//        System.out.println(hashTableV1.exist(100));
    }
}
