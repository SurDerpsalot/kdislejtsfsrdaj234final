/**
 * @author maden
 * @version 1
 */
public class DatabaseTest extends student.TestCase {

    /**
     * Test method for {@link Database#Database(java.lang.String, int, int)}.
     */
    public void testDatabase() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertNotNull(d);
        assertEquals(d.getMem().getBlockSize(), 200);
        assertEquals(d.getArtistHash().getCapacity(), 100);
        assertEquals(d.getSongHash().getCapacity(), 100);

    }

    /**
     * Test method for {@link Database#beginParsing(java.lang.String)}.
     */
    
    public void testBeginParsing() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertEquals(d.getArtistHash().size(), 0);
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new new band<SEP>stuff of legends");
        assertEquals(d.getArtistHash().size(), 2);
        assertEquals(d.getSongHash().size(), 1);
        assertEquals(d.getArtistTree().treeDump(false).size(), 2);
        assertEquals(d.getSongTree().treeDump(false).size(), 1);
        //TODO: test value tree values
        assertEquals(d.getArtistTree().searchTree(0).size(), 1);
        assertNull(d.getArtistTree().searchTree(11));
        assertNull(d.getSongTree().searchTree(0));
        assertEquals(d.getSongTree().searchTree(11).size(), 2);
        d.readCommand("delete new song<SEP>stuff of legends");
        assertEquals(d.getArtistHash().size(), 2);
        assertEquals(d.getSongHash().size(), 1);
        assertEquals(d.getArtistTree().treeDump(false).size(), 1);
        assertEquals(d.getSongTree().treeDump(false).size(), 1);
    }

    /**
     * Test method for {@link Database#readCommand(java.lang.String)}.
     */
    
    public void testReadCommand() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#addEntryGetHandle(java.lang.String)}.
     */
    
    public void testAddEntryGetHandle() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#removeArtist(java.lang.String)}.
     */
    
    public void testRemoveArtist() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#removeSong(java.lang.String)}.
     */
    
    public void testRemoveSong() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#viewTrees()}.
     */
    
    public void testViewTrees() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#viewArtists()}.
     */
    
    public void testViewArtists() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#viewSongs()}.
     */
    
    public void testViewSongs() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#listSongsByArtist(java.lang.String)}.
     */
    
    public void testListSongsByArtist() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#listArtistsBySong(java.lang.String)}.
     */
    
    public void testListArtistsBySong() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for 
     * {@link Database#delete(java.lang.String, java.lang.String)}.
     */
    
    public void testDelete() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for 
     * {@link Database#deleteEntry(BST, Database.Hash, Database.Hash,
     *  java.lang.String, java.lang.String)}.
     */
    
    public void testDeleteEntry() {
        fail("Not yet implemented"); // TODO
    }

}
