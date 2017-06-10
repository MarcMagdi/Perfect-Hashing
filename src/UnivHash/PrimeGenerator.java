package UnivHash;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/20/17.
 * This interface is access to a prime generator class
 * @author Marc Magdi
 */
public interface PrimeGenerator {
    /**.
     * This function get a prime number bigger than the minimum given.
     * @param minimum the minimum value of p.
     * @return a prime number bigger than the minimum given.
     */
    int getRandomPrime(int minimum);
}
