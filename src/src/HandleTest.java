/**
 * @author maden
 * @version 1
 */
public class HandleTest extends student.TestCase {

    /**
     * Test method for {@link Handle#Handle(java.lang.Comparable)}.
     */
    public void testHandle() {
        Handle<Integer> h = new Handle<Integer>(12);
        assertEquals((int)h.getI(), 12);
    }

}
