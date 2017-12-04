/**
 * @author maden
 * @version 1
 */
public class KVPairTest extends student.TestCase {

    /**
     * Test method for 
     * {@link KVPair#KVPair(java.lang.Object, java.lang.Object)}.
     */
    public void testKVPair() {
        KVPair<String, Integer> kv = new KVPair<String, Integer>("key", 23);
        assertNotNull(kv);
        assertEquals(kv.getKeyHandle().compareTo("key"), 0);
        assertEquals(kv.getValueHandles().size(), 1);
        assertEquals(kv.getValueHandles().get(0).intValue(), 23);
    }

    /**
     * Test method for {@link KVPair#getArtistHandle()}.
     */
    public void testGetArtistHandle() {
        KVPair<String, Integer> kv = new KVPair<String, Integer>("key", 23);
        assertEquals(kv.getKeyHandle().compareTo("key"), 0);
        KVPair<Float, Integer> kv2 = 
                new KVPair<Float, Integer>((float)3.14159, 23);
        assertEquals(kv2.getKeyHandle(), 3.14159, 0.00001);
        
    }

    /**
     * Test method for {@link KVPair#getSongHandles()}.
     */
    public void testGetSongHandles() {
        KVPair<String, Integer> kv = new KVPair<String, Integer>("key", 26);
        assertEquals(kv.addValue(24), 2);
        assertEquals(kv.addValue(25), 3);
        assertEquals(kv.getValueHandles().get(1).intValue(), 24);
    }

    /**
     * Test method for {@link KVPair#deleteSecondaryHandle(java.lang.Object)}.
     */
    public void testDeleteSecondaryHandle() {
        KVPair<String, Integer> kv = new KVPair<String, Integer>("key", 26);
        assertEquals(kv.addValue(24), 2);
        assertEquals(kv.addValue(25), 3);
        assertEquals(kv.getValueHandles().get(1).intValue(), 24);
        assertTrue(kv.deleteSecondaryHandle(24));
        assertEquals(kv.getValueHandles().get(1).intValue(), 25);
        assertTrue(kv.deleteSecondaryHandle(26));
        assertFalse(kv.deleteSecondaryHandle(24));
        assertTrue(kv.deleteSecondaryHandle(25));
        assertEquals(kv.getValueHandles().size(), 0);
        //adding duplicate values
        assertEquals(kv.addValue(27), 1);
        assertEquals(kv.addValue(27), 2);
        assertTrue(kv.deleteSecondaryHandle(27));
        assertTrue(kv.getValueHandles().isEmpty());
    }

}
