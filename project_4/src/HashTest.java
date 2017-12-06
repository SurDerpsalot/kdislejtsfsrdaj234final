import java.util.HashMap;

/**
 * 
 * @author maden
 * @version 1
 */
public class HashTest extends student.TestCase {

    /**
     * tests the construction
     */
    public void testHash() {
       Hash hm = new Hash(10);
        assertNotNull(hm);
        
        assertTrue(hm.isEmpty());
        assertEquals(false, hm.containsKey("key"));
        assertEquals(hm.size(), 0);
        assertEquals(hm.getCapacity(), 10);
    }

    /**
     * 
     */
    public void testHashCode() {
        Hash hm = new Hash(3);
        assertEquals(-1, (int)hm.put("key1", 1));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 3);
        assertEquals(1, (int)hm.put("key1", 2));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 3);
        assertEquals(-1, (int)hm.put("keyx", 1));
        assertEquals(hm.size(), 2);
        assertEquals(hm.getCapacity(), 6);
        assertEquals(-1, (int)hm.put("key3", 1));
        assertEquals(hm.size(), 3);
        assertEquals(hm.getCapacity(), 6);
        assertEquals(-1, (int)hm.put("keyy", 4));
        assertEquals(hm.size(), 4);
        assertEquals(hm.getCapacity(), 12);
    }


    /**
     * 
     */
    public void testEqualsObject() {
        Hash hm = new Hash(3);
        assertEquals(-1, (int)hm.put("key1", 1));
        assertEquals(hm.size(), 1);
        assertEquals(hm.getCapacity(), 3);
        assertEquals(-1, (int)hm.put("key2", 2));
        assertEquals((int)hm.get("key1"), 1);
        assertEquals((int)hm.get("key2"), 2);
    }

}
