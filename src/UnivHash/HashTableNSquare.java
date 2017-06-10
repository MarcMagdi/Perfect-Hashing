package UnivHash;

import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/20/17.
 * @author Marc Magdi
 */
public class HashTableNSquare implements HashTable {

    /**.
     * The hash table containing the data.
     */
    private int data[];

    /**.
     * Counter for how many times we needed to rebuild the hash table.
     */
    private int collisions = 0;

    /**.
     * Object responsible for providing an universal hashing.
     */
    private UniversalHashing universalHashing;

    /**.
     * Constructor given the needed array as the data has to be static.
     * @param numbers the array to be hashed.
     */
    HashTableNSquare(int numbers[]) {
        int values[] = removeDuplicates(numbers);
        this.data = new int[values.length * values.length];
        universalHashing = new UniversalHashingImp(values.length * values.length);
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
        boolean collisionFound;
        do {
            collisionFound = false;
            initializeArray();
            universalHashing.setRandomParameters(numbers.length * numbers.length);
            for (int num : numbers) {
                int hashValue = universalHashing.getHashValue(num);
//                if (collisions > 10000) throw new InvalidFormatException();
                if (data[hashValue] != Integer.MIN_VALUE && data[hashValue] != num) { // collision found
                    collisionFound = true;
                    collisions++;
                    break;
                } else {
                    data[hashValue] = num;
                }
            }
        } while (collisionFound);
    }

    /**.
     * Set the array items with the minimum value of integers.
     */
    private void initializeArray() {
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.MIN_VALUE;
        }
    }

    @Override
    public boolean exist(int value) {
        return data[universalHashing.getHashValue(value)] == value;
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
//        int nums [] = {0, 1, 2, 4, 8, 909, 77, 22, 66 ,111};
//        int nums [] = {228, 836};
//        int nums [] = {959, 299, 5};
//        int nums [] = {816963, 662556, 508149};
//        HashTable hashTableV1 = new HashTableNSquare(nums);
//        System.out.println(hashTableV1.exist(1));
//        System.out.println(hashTableV1.getCollisions());

//        int nums2[] = new int[10000];
//        Random random = new Random();
//        for (int i = 0; i < 10000; i++) {
//            nums2[i] = random.nextInt();
//        }
//
//        HashTable hashTableV11 = new HashTableV1(nums2);
//        System.out.println(hashTableV11.getCollisions());

        String str = System.getProperty("user.dir") + File.separator + "src" + File.separator
                + "assignment" + File.separator + "four" + File.separator + "keys10000001000000.txt";
        File file = new File(str);

        Scanner scanner = new Scanner(file);
        String line = scanner.next();
        String numberString[] = line.split(",");
        int numbers[] = new int[numberString.length];

        for (int i = 0; i < numberString.length; i++) {
            numbers[i] =  Integer.parseInt(numberString[i]);
        }

        HashTable hashTableV1 = new HashTableN(numbers);
        System.out.println(hashTableV1.getCollisions());
    }
}
