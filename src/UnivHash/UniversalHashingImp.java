package UnivHash;

import java.util.Random;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/20/17.
 * @author Marc Magdi
 */
public class UniversalHashingImp implements UniversalHashing {
    /**.
     * The parameters for hashing.
     */
    private int a, b, p = -1, m = 0;

    /**.
     * Random number generator for a and b.
     */
    private Random random;

    /**.
     * Prime random generator.
     */
    private PrimeGenerator primeGenerator;

    /**.
     * @param tableSize the table size as the lower bound for the prime number.
     */
    public UniversalHashingImp(int tableSize) {
        random = new Random();
        primeGenerator = PrimeGeneratorImp.getInstance(tableSize);
        p = primeGenerator.getRandomPrime(tableSize);
    }

    @Override
    public void setRandomParameters(int m) {
        a = random.nextInt();
        b = random.nextInt();
        this.m = m;
//        System.out.println(a +" " + b + " " + m + " " + p);
    }

    @Override
    public void updateRandomParameters() {
        setRandomParameters(m);
    }

    @Override
    public int getHashValue(int number) {
        int hash = ((a * number + b) % p) % m;
        return (hash + m) % m; // to get away from negative numbers
    }
}
