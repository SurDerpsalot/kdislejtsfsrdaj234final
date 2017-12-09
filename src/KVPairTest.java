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
        assertEquals(kv.getValueList().size(), 1);
        assertEquals(kv.getValueList().get(0).intValue(), 23);
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
        KVPair<Integer, Integer> kv = new KVPair<Integer, Integer>(12, 27);
        assertEquals(kv.addValue(24), 2);
        assertEquals(kv.addValue(25), 3);
        assertEquals(kv.getKeyHandle().intValue(), 12);
        assertEquals(kv.getValueList().size(), 3);
        assertEquals(kv.getValueList().get(0).intValue(), 24);
        assertEquals(kv.getValueList().get(1).intValue(), 25);
        assertEquals(kv.getValueList().get(2).intValue(), 27);
    }

    /**
     * Test method for {@link KVPair#deleteSecondaryHandle(java.lang.Object)}.
     */
    public void testDeleteSecondaryHandle() {
        KVPair<String, Integer> kv = new KVPair<String, Integer>("key", 26);
        assertEquals(kv.addValue(13), 2);
        assertEquals(kv.addValue(39), 3);
        assertEquals(kv.addValue(6), 4);
        assertEquals(kv.addValue(32), 5);
        assertEquals(kv.addValue(19), 6);
        assertEquals(kv.addValue(46), 7);
        assertEquals(kv.addValue(43), 8);
        assertEquals(kv.addValue(48), 9);
        assertEquals(kv.addValue(36), 10);
        assertEquals(kv.addValue(29), 11);
        assertEquals(kv.addValue(22), 12);
        assertEquals(kv.addValue(16), 13);
        assertEquals(kv.addValue(8), 14);
        assertEquals(kv.addValue(2), 15);
        assertEquals(kv.getValueList().get(0).intValue(), 13);
        assertEquals(kv.getValueList().get(9).intValue(), 39);
        assertEquals(kv.getValueList().get(12).intValue(), 48);
        assertEquals(kv.getValueList().size(), 15);
        assertTrue(kv.deleteSecondaryHandle(39));
        assertTrue(kv.deleteSecondaryHandle(26));
        assertTrue(kv.deleteSecondaryHandle(2));
        assertTrue(kv.deleteSecondaryHandle(48));
        assertTrue(kv.deleteSecondaryHandle(46));
        assertEquals(kv.getValueList().size(), 10);
        assertFalse(kv.getValueList().isEmpty());
        //adding duplicate values
        assertEquals(kv.addValue(27), 11);
        assertEquals(kv.addValue(27), 11);
        assertTrue(kv.deleteSecondaryHandle(27));
        assertTrue(!kv.getValueList().isEmpty());
    }

}
