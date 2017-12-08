import java.util.ArrayList;

/**
 * This is the Hash Table. It stores values in an arraylist based off
 * of a hashing function given to us by the specification (function h).
 * The hash table will also double in size every time it goes over 50% full.
 * Methods:
 * put -- adds an element to the hashtable, will expand if that add will
 *          take it over 50% fullness. A true will return upon insertion,
 *          and a false will return if there is a duplicate found.
 *          
 * get -- if the key is located in the hashtable, it will return the value
 *          stored with it.
 *          
 * remove -- if the key is located in the hashtable, it will replace it with
 *          a gravestone (so that searches will not be inturrupted). A true
 *          return means that the key,value pair has been removed. A false
 *          means that it was not in the hashtable.
 *          
 * expand -- this function doubles the size of the hash table, then rehashes
 *          the values back into it. 
 * @author Brad bfin96
 * @version 2.0
 */
public class Hash {

    /**
     * This class is essentially a struct that
     * helps structure the data to be stored in the 
     * hash tables.
     * @author Brad
     * @version 1.5
     */
    private class Pair {
        
        /**
         * This is the key of data set
         */
        private String key;
        
        /**
         * This is the value of the data set
         * also the location of the key in
         * the memory pool
         */
        private int indexmem;
        
        /**
         * This is the boolean which determines
         * if the Pair has been deleted or not.
         */
        private boolean tombstone;
        
        /**
         * This is the constructor that will build
         * a 'dead' Pair. The value doesn't matter
         * as this Pair cannot be read from and will
         * only ever be skipped over in a search or
         * overwritten.
         *
         * @param k is the key of the Pair that was
         *          deleted
         */
        public Pair(String k) {
            key = k;
            indexmem = -1;
            tombstone = true;
        }
        
        /**
         * The standard constructor for the elements
         * that will be stored into the Hash table.
         * @param k the key of the data set being stored.
         * @param index the value of the data set being stored.
         */
        public Pair(String k, int index) {
            key = k;
            indexmem = index;
            tombstone = false;
        }
        
    }
    
    /**
     * This is the Arraylist that is our Hash table.
     */
    private ArrayList<Pair> hashTable;
    
    /**
     * This is the max capacity of the Hash table.
     */
    private int hSize;
    
    /**
     * This is the number of elements in the hash table.
     */
    private int pairsInHash;
    
    /**
     * Constructor for the hashtable
     * @param hashsize the size of the table
     */
    public Hash(int hashsize) {
        hashTable = new ArrayList<Pair>(hashsize);
        for (int i = 0; i < hashsize; i++) {
            hashTable.add(null);
        }
        hSize = hashsize;
        pairsInHash = 0;
    }
    
    /**
     * This function inserts a string and memindex into a hashtable
     * @param k the key string that is being brought in (artist or song)
     * @param index the location in the memory pool (i.e. their handle)
     * @return true on a successful insertion and false on a 
     * repeated value
     */
    public boolean put(String k, int index) {
        int hashIndex = h(k, hSize);
        Pair p = new Pair(k, index);
        if (hashTable.get(hashIndex) == null)
        {
            pairsInHash++;
            expand();
            hashTable.set(hashIndex, p);
            return true;
        }
        else
        {
            int hIndex = hashIndex;
            int it = 1;
            while (hashTable.get(hIndex) != null)
            {
                if (hashTable.get(hIndex).tombstone)
                {
                    pairsInHash++;
                    expand();
                    hashTable.set(hIndex, p);
                }
                else {
                    if (hashTable.get(hIndex).key.compareTo(k) == 0)
                    {
                        return false;
                    }
                    hIndex = (hashIndex + it * it) % hSize;
                    hIndex = Math.abs(hIndex);
                    it = it + 1;
                }
            }
            if (hashTable.get(hIndex) == null)
            {
                pairsInHash++;
                expand();
                hashTable.set(hIndex, p);
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    /**
     * This function doubles the size of the hash 
     * table and then re-hashes the
     * values onto the new table.
     */
    private void expand() {
        if ((float)(pairsInHash) > (float)(hSize / 2) )
        {
            int ogSize = hSize;
            hSize = hSize * 2;
            ArrayList<Pair> newHash =  new ArrayList<Pair>(hSize);
            for (int a = 0; a < hSize; a++)
            {
                newHash.add(null);
            }
            int i = 0;
            while (i < (ogSize)) {
                if (hashTable.get(i) != null) {
                    int hashIndex = h(hashTable.get(i).key, hSize);
                    if (newHash.get(hashIndex) == null)
                    {
                        
                        newHash.set(hashIndex, hashTable.get(i));
                    }
                    else
                    {
                        int hIndex = hashIndex;
                        int it = 1;
                        while (newHash.get(hIndex) != null)
                        {
                            hIndex = (hashIndex + (it * it)) % hSize;
                            hIndex = Math.abs(hIndex);
                            it++;
                        }
                        if (newHash.get(hIndex) == null)
                        {
                            newHash.set(hIndex, hashTable.get(i));
                        }
                    }
                }
                i++;
            }
            hashTable = new ArrayList<Pair>(hSize);
            hashTable = newHash;
        }
    }
    
    /**
     * This searches the HashTable for a key String. 
     * Upon finding the key, the int value stored with
     * it in the Pair is returned.
     * 
     * @param k is the artist/song being searched for
     * @return the memoryIndex of that artist/song
     */
    public int get(String k) {
        int hashIndex = h(k, hSize);
        if (pairsInHash == 0)
        {
            return -1;
        }
        if (hashTable.get(hashIndex) == null)
        {
            return -1;
        }
        else
        {
            int hIndex = hashIndex;
            int it = 1;
            while (hashTable.get(hIndex) != null)
            {
                if (hashTable.get(hIndex).key.compareTo(k) == 0)
                {
                    if (hashTable.get(hIndex).tombstone)
                    {
                        return -1;
                    }
                    return hashTable.get(hIndex).indexmem;
                }
                
                hIndex = (hashIndex + (it * it)) % hSize;
                hIndex = Math.abs(hIndex);
                it++;
            }
            return -1;
        }
    }
    
    /**
     * This method will search through the hash map for the 
     * String K. If it finds it, that Pair will be replaced
     * by a Pair with the same name and a tombstone for easier
     * navigation after deletion
     * @param k the key value being searched for and eventually
     *          deleted
     * @return True of the delete completes
     *          False if the key is not found
     */
    public boolean remove(String k) {
        int hashIndex = h(k, hSize);
        if (get(k) == -1)
        {
            return false;
        }
        if (hashTable.get(hashIndex) == null)
        {
            return false;
        }
        else
        {
            int hIndex = hashIndex;
            int it = 1;
            while (hashTable.get(hIndex) != null)
            {
                if (hashTable.get(hIndex).key.compareTo(k) == 0)
                {
                    if (hashTable.get(hIndex).tombstone)
                    {
                        return false;
                    }
                    else
                    {
                        Pair dead = new Pair(hashTable.get(hIndex).key);
                        hashTable.set(hIndex, dead);
                        return true;  
                    }
                }
                hIndex = (hashIndex + (it * it)) % hSize;
                hIndex = Math.abs(hIndex);
                it++;
            }
            return false;
        }
    }
    /**
     * Getter for the number of elements in the hash table
     * @return the number of elements in the hash table
     */
    public int size() {
        return pairsInHash;
    }
    /**
     * Getter for the max capacity of the hash table
     * @return the max capacity of the hash table
     */
    public int getCapacity() {
        return hSize;
    }
    
    /**
     * from openDSA, hm is the hash function
     * @param s the string
     * @param m the location
     * @return the handle
     */
    public int h(String s, int m)
    {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++)
        {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++)
            {
                sum  += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++ )
        {
            sum  += c[k] * mult;
            mult *= 256;
        }
        return (int)(Math.abs(sum) % m);
    }
}
