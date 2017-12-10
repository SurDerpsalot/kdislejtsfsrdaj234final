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
        System.out.println("--------------End of Test 1-----------------");
    }

    /**
     * Test method for {@link Database#removeArtist(java.lang.String)}.
     */
    
    public void testRemove() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertEquals(d.getArtistHash().size(), 0);
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new song<SEP>really now");
        d.readCommand("insert new new band<SEP>stuff of legends");
        System.out.println("---------------------Removal test-------------------");        
        d.readCommand("remove artist a");
        d.readCommand("remove artist new song");
        d.readCommand("print artist");
        d.readCommand("print song");
        d.readCommand("remove song b");
        d.readCommand("remove song stuff of legends");
        d.readCommand("print song");
        d.readCommand("print artist");
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new song<SEP>really now");
        d.readCommand("insert new new band<SEP>stuff of legends");
        d.readCommand("insert new new band<SEP>really now");
        d.readCommand("remove song stuff of legends");
        d.readCommand("insert new new band<SEP>stuff of legends");
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new song<SEP>stuff of legends two");
        d.readCommand("insert new song<SEP>stuff of legends three");
        d.readCommand("insert new song<SEP>stuff of legends the fourth");
        d.readCommand("insert new new band<SEP>stuff of legends two");
        d.readCommand("insert new new band<SEP>stuff of legends three");
        d.readCommand("remove artist new song");
        d.readCommand("list artist new song");
        d.readCommand("delete new song<SEP>stuff of legends two");
        d.readCommand("print song");
        d.readCommand("print artist");
        d.readCommand("insert new song<SEP>stuff of legends two");
        d.readCommand("insert new song<SEP>stuff of legends three");
        d.readCommand("insert new song<SEP>stuff of legends the fourth");
        d.readCommand("remove song stuff of legends two");
        d.readCommand("print song");
        d.readCommand("print artist");
        System.out.println("--------------------------------finished printing removals------------------------------ ");
    }

    /**
     * Test method for {@link Database#viewArtists()}.
     */
    
    public void testPrintArtistSong() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertEquals(d.getArtistHash().size(), 0);
        System.out.println("testing print artist and print song");
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new new band<SEP>stuff of legends");
        d.readCommand("insert new song<SEP>silly song");
        d.readCommand("print artist");
        d.readCommand("print song");
        System.out.println("finished printing tests");
    }

    /**
     * Test method for {@link Database#listSongsByArtist(java.lang.String)}.
     */    
    public void testList() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertEquals(d.getArtistHash().size(), 0);
        d.readCommand("insert new song<SEP>stuff of legends");
        d.readCommand("insert new new band<SEP>stuff of legends");
        d.listSongsByArtist("mickey");
        d.listSongsByArtist("new song");
        d.listArtistsBySong("missing");
        d.listArtistsBySong("stuff of legends");
    }

    /**
     * Test method for 
     * {@link Database#delete(java.lang.String, java.lang.String)}.
     */
    public void testDelete() {
        Database d = new Database("db_sample1.txt", 200, 100);
        assertEquals(d.getArtistHash().size(), 0);
        d.readCommand("insert first band<SEP>uno you know");
        d.readCommand("insert Drewid<SEP>the birds and the mees");
        d.readCommand("insert Drewid<SEP>silly song");
        d.readCommand("insert Drewid<SEP>stuff of legends");
        d.readCommand("insert first band<SEP>Library Card");
        d.readCommand("insert The Beebops<SEP>first on a top ten");
        d.readCommand("insert The Beebops<SEP>silly song");
        System.out.println("------------------------------Deletion test-----------------------------");        
        d.readCommand("delete artist a<SEP>silly song");
        d.readCommand("delete The Beebops<SEP>silly song");
        d.readCommand("delete Drewid<SEP>stuff of legends");
        d.readCommand("delete first band<SEP>Library Card");
        d.readCommand("delete first band<SEP>Library Card");
        d.readCommand("delete Drewid<SEP>silly song");
        d.readCommand("print artist");
        d.readCommand("print song");
        System.out.println("--------------------------finished printing deletions--------------------------");
    }

}
