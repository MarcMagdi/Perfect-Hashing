package UnivHash;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 223 : Data Structure II
 * Lab 4: Perfect Hashing
 * Created by Marc Magdi on 5/14/17.
 * This is an interface for a perfect hashing table.
 * @author Marc Magdi
 */
interface HashTable {
    /**.
     * Check for the given value if exist in the hash table.
     * @param value the value to check for.
     * @return true iff the given value exist in the hash table.
     */
    boolean exist(int value);

    /**.
     * @return a counter for how many times we needed to rebuild the hash table.
     */
    int getCollisions();

    /**.
     * @return the size of the slots including empty ones. (Memory Used).
     */
    int getCapacity();
}
