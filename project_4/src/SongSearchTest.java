/**
 * This is filler text
 * @author bfin96 m1newc
 * @version 1.0
 */
public class SongSearchTest extends student.TestCase {

    /**
     * Filler text
     */
    public void testSongSearch() {
        String[] testargs = new String[4];
        testargs[0] = "50";
        testargs[1] = "1024";
        testargs[2] = "test.txt";
        SongSearch test = new SongSearch();
        Hash j = new Hash(3);
        assertEquals(j.size(), 0);
        test.main(testargs);
        
    }
}
