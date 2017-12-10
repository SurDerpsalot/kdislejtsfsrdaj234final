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
        KVPair kv = new KVPair(1, 23);
        assertNotNull(kv);
        assertEquals(kv.getKeyHandle(), 1);
        assertEquals(kv.getValueList().size(), 1);
        assertEquals(kv.getValueList().get(0).intValue(), 23);
    }

    /**
     * Test method for {@link KVPair#getArtistHandle()}.
     */
    public void testGetArtistHandle() {
        KVPair kv = new KVPair(1, 23);
        assertEquals(kv.getKeyHandle(), 1);
    }

    /**
     * Test method for {@link KVPair#getSongHandles()}.
     */
    public void testGetSongHandles() {
        KVPair kv = new KVPair(12, 27);
        assertEquals(kv.addValue(24), 2);
        assertEquals(kv.addValue(25), 3);
        assertEquals(kv.getKeyHandle(), 12);
        assertEquals(kv.getValueList().size(), 3);
        assertEquals(kv.getValueList().get(0).intValue(), 24);
        assertEquals(kv.getValueList().get(1).intValue(), 25);
        assertEquals(kv.getValueList().get(2).intValue(), 27);
    }

    /**
     * Test method for {@link KVPair#deleteSecondaryHandle(java.lang.Object)}.
     */
    public void testDeleteSecondaryHandle() {
        KVPair kv = new KVPair(1, 26);
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
        assertEquals(kv.getValueList().get(0).intValue(), 2);
        assertEquals(kv.getValueList().get(9).intValue(), 32);
        assertEquals(kv.getValueList().get(12).intValue(), 43);
        System.out.println(kv.getValueList().get(0).intValue());
        System.out.println(kv.getValueList().get(1).intValue());
        System.out.println(kv.getValueList().get(2).intValue());
        System.out.println(kv.getValueList().get(3).intValue());
        System.out.println(kv.getValueList().get(4).intValue());
        System.out.println(kv.getValueList().get(5).intValue());
        System.out.println(kv.getValueList().get(6).intValue());
        System.out.println(kv.getValueList().get(7).intValue());
        System.out.println(kv.getValueList().get(8).intValue());
        System.out.println(kv.getValueList().get(9).intValue());
        System.out.println(kv.getValueList().get(10).intValue());
        System.out.println(kv.getValueList().get(11).intValue());
        System.out.println(kv.getValueList().get(12).intValue());
        System.out.println(kv.getValueList().get(13).intValue());
        System.out.println(kv.getValueList().get(14).intValue());
        assertEquals(kv.getValueList().size(), 15);
        assertTrue(kv.deleteSecondaryHandle(39));;
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