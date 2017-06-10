package UnivHash;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/20/17.
 * This interface is for universal hashing functions.
 * @author Marc Magdi
 */
public interface UniversalHashing {

    /**.
     * Set the random parameters a, b and set the value for the prime number.
     * @param m the maximum size of the hash value.
     */
    void setRandomParameters(int m);

    /**.
     * update the random parameters a, b and set the value for the prime number.
     */
    void updateRandomParameters();


    /**.
     * @param number the number to hash and get its value.
     * @return the hashed value of the given number.
     */
    int getHashValue(int number);
}
