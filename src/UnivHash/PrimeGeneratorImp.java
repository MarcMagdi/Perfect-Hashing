package UnivHash;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/20/17.
 * @author Marc Magdi
 */
public class PrimeGeneratorImp implements PrimeGenerator {

    /**.
     * The list of primes numbers.
     */
    private List<Integer> primes;

    /**.
     * Upper bound for primes list.
     */
    private int maxPrime = 0;

    /**.
     * A random number generator for prime numbers.
     */
    private Random random;

    /**.
     * Singleton object.
     */
    private static PrimeGeneratorImp primeGenerator;

    /**.
     * Private as its a singleton class.
     * @param maxPrime the upper bound on the prime number.
     */
    private PrimeGeneratorImp(int maxPrime) {
        primes = new ArrayList<>();
        this.maxPrime = maxPrime;
        this.random = new Random();
        generatePrimes();
    }

    /**.
     * @param m lower bound for the prime number, the upper one is 2 * m.
     * @return an instance of the prime generator.
     */
    public static PrimeGenerator getInstance(int m) {
        if(primeGenerator == null || primeGenerator.maxPrime < m * 2) {
            primeGenerator = new PrimeGeneratorImp(m * 2);
        }

        return primeGenerator;
    }

    /**.
     * Generate all primes from 1 up to the max prime.
     */
    private void generatePrimes() {
        boolean isPrime[] = new boolean[maxPrime + 1];
        for (int i = 2; i <= maxPrime; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= maxPrime using Sieve of Eratosthenes
        for (int factor = 2; factor <= maxPrime; factor++) {

            // if factor is prime, then mark multiples of factor as non prime
            // suffices to consider multiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                primes.add(factor);
                for (int j = factor * 2; j <= maxPrime; j += factor) {
                    isPrime[j] = false;
                }
            }
        }
    }

    @Override
    public int getRandomPrime(int start) {
        int minPrimeIndex = getMinimumPrimeIndex(start);
        int maxPrimeIndex = getMinimumPrimeIndex(start * 2);
        int randomIndex = maxPrimeIndex - minPrimeIndex == 0 ? 0 : random.nextInt(maxPrimeIndex - minPrimeIndex);

        return primes.get(minPrimeIndex + randomIndex);
    }

    /**.
     * Run in order of log(n), using binary search to found the first element <= the given value.
     * @param value the value to search for.
     * @return the index of the first element <= the given value.
     */
    private int getMinimumPrimeIndex(int value) {
        int left = 0, right = primes.size();
        while (left < right) {
            int mid = (left + right) / 2;
            int currentValue = primes.get(mid);
            if (currentValue >= value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {

        PrimeGenerator primeGenerator = new PrimeGeneratorImp(100);

        System.out.println(primeGenerator.getRandomPrime(25));
//        int n = 10;
//
//        // initially assume all integers are prime
//        boolean[] isPrime = new boolean[n+1];
//        for (int i = 2; i <= n; i++) {
//            isPrime[i] = true;
//        }
//
//        // mark non-primes <= n using Sieve of Eratosthenes
//        for (int factor = 2; factor*factor <= n; factor++) {
//
//            // if factor is prime, then mark multiples of factor as nonprime
//            // suffices to consider mutiples factor, factor+1, ...,  n/factor
//            if (isPrime[factor]) {
//                for (int j = factor; factor*j <= n; j++) {
//                    isPrime[factor*j] = false;
//                }
//            }
//        }
//
//        // count primes
//        int primes = 0;
//        for (int i = 2; i <= n; i++) {
//            if (isPrime[i]) primes++;
//        }
//        System.out.println("The number of primes <= " + n + " is " + primes);
    }
}
