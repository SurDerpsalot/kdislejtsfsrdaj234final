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
        assertEquals(d.mem.getBlockSize(), 200);
        assertEquals(d.artistHash.getCapacity(), 100);
        assertEquals(d.songHash.getCapacity(), 100);

    }

    /**
     * Test method for {@link Database#beginParsing(java.lang.String)}.
     */
    
    public void testBeginParsing() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertEquals(d.artistHash.size(), 0);
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new new band<SEP>stuff of legends");
        assertEquals(d.artistHash.size(), 2);
        assertEquals(d.songHash.size(), 1);
        assertEquals(d.artistTree.treeDump().size(), 2);
        assertEquals(d.songTree.treeDump().size(), 1);
        //TODO: test value tree values
        assertEquals(d.artistTree.searchTree(0).size(), 1);
        assertNull(d.artistTree.searchTree(11));
        assertNull(d.songTree.searchTree(0));
        assertEquals(d.songTree.searchTree(11).size(), 2);
        d.readCommand("delete new song<SEP>stuff of legends");
        assertEquals(d.artistHash.size(), 2);
        assertEquals(d.songHash.size(), 1);
        assertEquals(d.artistTree.treeDump().size(), 1);
        assertEquals(d.songTree.treeDump().size(), 1);
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
     * Test method for {@link Database#delete(java.lang.String, java.lang.String)}.
     */
    
    public void testDelete() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link Database#deleteEntry(BST, Database.Hash, Database.Hash, java.lang.String, java.lang.String)}.
     */
    
    public void testDeleteEntry() {
        fail("Not yet implemented"); // TODO
    }

}
