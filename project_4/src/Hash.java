import java.util.HashMap;


/**
 * a class that wraps HashMap
 * @author maden
 * @version 1
 */
public class Hash {
    /**
     * 
     */
    //private static final long serialVersionUID = 1L;
    private String name;
    private int handle;
    private int capacity;
    private HashMap<String, Integer> hm;
    /**
     * constructor
     * @param sizeHash the initial hash table's size
     */
    public Hash(int sizeHash) {
        name = null;
        handle = -1;
        hm = new HashMap<String, Integer>(sizeHash, (float)0.5);
        capacity = sizeHash;
    }
    /**
     * @return the number of key-value mappings in the hash map
     */
    public int size() {
        return hm.size();
    }
    /**
     * @return the number of key-value mappings in the hash map
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * @param key is the key to search by
     * @return the value to a given key
     */
    public int get(String key) {
        return (hm.get(key) == null) ? -1 : hm.get(key);
    }
    /**
     * overrides the put function
     * @param key is the String key
     * @param value is the integer value
     * @return the previous value associated with that key
     */
    public int put(String key, Integer value) {
        Object out;
        if (!hm.containsKey(key)) {
            hm.put(key, value);
            out = -1;
        }
        else {
            out = hm.put(key, value);
        }
        if (2 * hm.size() > (capacity)) {
            capacity = capacity * 2;
        }
        return (int)out;

    }
    /**
     * overrides the hashCide and Equals functions
     * @author maden
     *
     */
    protected class stringCode {
        public String value;
        /**
         * string value
         * @param st the value
         */
        public stringCode (String st) {
            value= st;
        }
        @Override
        /**
         * the hashCode
         * @return the index in the hash table
         */
        public int hashCode() {
            return h(name, getCapacity());
        }
        @Override
        /**
         * checks if the objects are equal
         * @param o is the object to compare
         * @return true if equals
         */
        public boolean equals(Object o) {
            if (o instanceof KVPair<?, ?>) {
                KVPair<?, ?> other = (KVPair<?, ?>) o;
                return (other.getKeyHandle().toString()
                      .compareTo(String.valueOf(handle)) == 0);
            }
            return false;
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
    /**
     * checks if the hashMap is empty
     * @return true if the size is zero
     */
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return hm.isEmpty();
    }
    /**
     * checks if a key is used in the hashmap
     * @param string the key 
     * @return true if there is a match
     */
    public boolean containsKey(String string) {
        // TODO Auto-generated method stub
        return hm.containsKey(string);
    }

}