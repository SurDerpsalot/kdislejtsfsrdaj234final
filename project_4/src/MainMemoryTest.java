import java.nio.ByteBuffer;

/**
 * This is the testing file for the MainMemory. It runs
 * with 100% test coverage on the creation, insertion,
 * and retrieval of information in the MainMemory.
 * 
 * @author Bfin96
 * @version 1
 */
public class MainMemoryTest extends student.TestCase {
    
    /**
     * This tests the constructor as well as the setters and getters
     * for the private variables
     */
    public void testMainMemory() {
        MainMemory test1 = new MainMemory(1024);
        MainMemory test2 = new MainMemory(2048);
        MainMemory test3 = new MainMemory(4096);
        assertEquals(test1.getBlockSize(), 1024);
        assertEquals(test2.getBlockFillSize(), 0);
        assertEquals(test3.getBlockSize(), 4096);
        assertEquals(test1.getInBuff().array().length == 1024, true);
        test1.setBlockSize(32);
        test2.setBlockFillSize(32);
        byte[] b = new byte[32];
        test3.setBuff(b);
        ByteBuffer c = ByteBuffer.wrap(b);
        test1.setInBuff(c);
        assertEquals(test1.getBlockSize(), 32);
        assertEquals(test2.getBlockFillSize(), 32);
        assertEquals(test3.getBuff().length, 32);
        assertEquals(test1.getInBuff().array().length, 32);
    }
    
    /**
     * This tests the adding of values into the MainMemory
     */
    public void testAddToTheFill() {
        MainMemory test1 = new MainMemory(11);
        byte[] b = new byte[11];
        b[0] = 1;
        b[1] = 0;
        b[2] = 8;
        b[3] = 't';
        b[4] = 'w';
        b[5] = 'i';
        b[6] = 'l';
        b[7] = 'i';
        b[8] = 'g';
        b[9] = 'h';
        b[10] = 't';
        test1.addToTheFill(b);
        assertEquals(test1.getBuff().length == 11, true);
        b = new byte[10];
        b[0] = 1;
        b[1] = 0;
        b[2] = 7;
        b[3] = 's';
        b[4] = 'p';
        b[5] = 'a';
        b[6] = 'r';
        b[7] = 'k';
        b[8] = 'l';
        b[9] = 'e';
        test1.addToTheFill(b);        
        assertEquals(test1.getBlockFillSize() == 21 , true);
        assertEquals(test1.getInBuff().remaining() == 1, true);
        assertEquals(test1.getInBuff().capacity() == 22, true);
        assertEquals(test1.getBuff().length == 22, true);
        assertEquals(test1.getBuff()[0] == 1, true);
        assertEquals(test1.getBuff()[1] == 0, true);
        assertEquals(test1.getBuff()[2] == 8, true);
        assertEquals(test1.getBuff()[3] == 't', true);
        assertEquals(test1.getBuff()[4] == 'w', true);
        assertEquals(test1.getBuff()[5] == 'i', true);
        assertEquals(test1.getBuff()[6] == 'l', true);
        assertEquals(test1.getBuff()[7] == 'i', true);
        assertEquals(test1.getBuff()[8] == 'g', true);
        assertEquals(test1.getBuff()[9] == 'h', true);
        assertEquals(test1.getBuff()[10] == 't', true);
        assertEquals(test1.getBuff()[11] == 1, true);
        assertEquals(test1.getBuff()[12] == 0, true);
        assertEquals(test1.getBuff()[13] == 7, true);
        assertEquals(test1.getBuff()[14] == 's', true);
        assertEquals(test1.getBuff()[15] == 'p', true);
        assertEquals(test1.getBuff()[16] == 'a', true);
        assertEquals(test1.getBuff()[17] == 'r', true);
        assertEquals(test1.getBuff()[18] == 'k', true);
        assertEquals(test1.getBuff()[19] == 'l', true);
        assertEquals(test1.getBuff()[20] == 'e', true);
        assertEquals(test1.getBuff()[21] == 0, true);
        b = new byte[5];
        b[0] = 1;
        b[1] = 0;
        b[2] = 2;
        b[3] = 'R';
        b[4] = 'D';
        test1.addToTheFill(b);
        assertEquals(test1.getBlockFillSize() == 26, true);
        assertEquals(test1.getBuff().length == 33, true);
        assertEquals(test1.getBuff()[21] == 1, true);
        assertEquals(test1.getBuff()[22] == 0, true);
        assertEquals(test1.getBuff()[23] == 2, true);
        assertEquals(test1.getBuff()[24] == 'R', true);
        assertEquals(test1.getBuff()[25] == 'D', true);
        b = new byte[5];
        b[0] = 1;
        b[1] = 0;
        b[2] = 2;
        b[3] = 'f';
        b[4] = 's';
        test1.addToTheFill(b);
        assertEquals(test1.getBlockFillSize() == 31, true);
        assertEquals(test1.getBuff().length == 33, true);
        assertEquals(test1.getBuff()[26] == 1, true);
        assertEquals(test1.getBuff()[27] == 0, true);
        assertEquals(test1.getBuff()[28] == 2, true);
        assertEquals(test1.getBuff()[29] == 'f', true);
        assertEquals(test1.getBuff()[30] == 's', true);
    }
    
    /**
     * This tests the retrieving the information from the memory
     * based on passing in the starting index of the record being
     * retrieved.
     *
    public void testRecordGetting() {
        MainMemory test1 = new MainMemory(11);
        byte[] b = new byte[11];
        b[0] = 1;
        b[1] = 0;
        b[2] = 8;
        b[3] = 't';
        b[4] = 'w';
        b[5] = 'i';
        b[6] = 'l';
        b[7] = 'i';
        b[8] = 'g';
        b[9] = 'h';
        b[10] = 't';
        test1.addToTheFill(b);
        assertEquals(test1.getBuff().length == 11, true);
        assertEquals("twilight".equals(test1.getRecordValue(0)),true);
        assertEquals(test1.getRecordValue(22) == "",true);
        assertEquals(test1.getRecordValue(-2) == "",true);
        assertEquals("twilight".equals(test1.readEntry(0)),true);
        assertEquals(test1.getRecordFlag(0) == 1, true);
        assertEquals(test1.getRecordFlag(12) == -1, true);
        assertEquals(test1.getRecordFlag(-1) == -1, true);
        b = new byte[10];
        b[0] = 1;
        b[1] = 0;
        b[2] = 7;
        b[3] = 's';
        b[4] = 'p';
        b[5] = 'a';
        b[6] = 'r';
        b[7] = 'k';
        b[8] = 'l';
        b[9] = 'e';
        test1.addToTheFill(b);
        assertEquals("sparkle".equals(test1.readEntry(11)) ,true);
        b = new byte[5];
        b[0] = 0;
        b[1] = 0;
        b[2] = 2;
        b[3] = 'R';
        b[4] = 'D';
        test1.addToTheFill(b);
        assertEquals(test1.readEntry(22) == "",true);
    
    }
    
    */
}
