
/**
 * 
 * @author bfin96
 * @version 1
 */
public class HashTest extends student.TestCase {

    /**
     * tests the construction
     */
    public void testHash() {
        Hash hm = new Hash(10);
        assertNotNull(hm);
        assertEquals(-1, hm.get("key"));
        assertEquals(hm.size(), 0);
        assertEquals(hm.getCapacity(), 10);
    }

    /**
     * 
     */
    public void testHashCode() {
        Hash hm = new Hash(3);
        assertEquals(true, hm.put("key1", 1));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 3);
        assertEquals(false, hm.put("key1", 2));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 3);
        assertEquals(true, hm.put("keyx", 1));
        assertEquals(hm.size(), 2);
        assertEquals(hm.getCapacity(), 6);
        assertEquals(true, hm.put("key3", 1));
        assertEquals(hm.size(), 3);
        assertEquals(hm.getCapacity(), 6);
        assertEquals(true, hm.put("keyy", 4));
        assertEquals(hm.size(), 4);
        assertEquals(hm.getCapacity(), 12);
    }


    /**
     * 
     */
    public void testEqualsObject() {
        Hash hm = new Hash(3);
        assertEquals(true, hm.put("key1", 1));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 3);
        assertEquals(true, hm.put("key2", 2));
        assertEquals(hm.get("key1"), 1);
        assertEquals(hm.get("key2"), 2);
    }
    
    /**
     * 
     */
    public void testRemoveObject() {
        Hash hm = new Hash(2);
        assertEquals(false, hm.remove("Twiggle"));
        assertEquals(true, hm.put("keyA", 1));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 2);
        assertEquals(true, hm.put("keyB", 1));
        assertEquals(hm.size(), 2);
        assertEquals(hm.getCapacity(), 4);
        assertEquals(true, hm.put("keyC", 4));
        assertEquals(hm.size(), 3);
        assertEquals(hm.getCapacity(), 8);
        assertEquals(true, hm.put("keyD", 1));
        assertEquals(hm.size(), 4);
        assertEquals(hm.getCapacity(), 8);
        assertEquals(true, hm.put("keyE", 1));
        assertEquals(hm.size(), 5);
        assertEquals(hm.getCapacity(), 16);
        assertEquals(true, hm.put("keyF", 4));
        assertEquals(hm.size(), 6);
        assertEquals(hm.getCapacity(), 16);
        assertEquals(4, hm.get("keyF"));
        assertEquals(true, hm.remove("keyF"));
        assertEquals(hm.size(), 6);
        assertEquals(hm.getCapacity(), 16);
        assertEquals(-1, hm.get("keyF"));
    }
    
    
    

}
