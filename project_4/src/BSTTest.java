
/**
 *
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-18
 */
public class BSTTest extends student.TestCase {

    /**
     * test the BST tree's default implementation
     */
    
    public void testBST() {
        BST<Integer, Integer>  newTree = new<Integer, Integer> BST();
        assertNull(newTree.getRoot());
        assertNull(newTree.getTemp());
        assertNull(newTree.getTempArray());
    }

    /**
     * test the BST construction with an existing tempNode
     */
    public void testBSTTreeNode() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        newTree.setRootNode(1, 2);
        BST<Integer, Integer> newTree2 = 
                new BST<Integer, Integer> (newTree.getRoot());
        assertNotNull(newTree.getRoot());
        assertNotNull(newTree2.getRoot());
    }

    /**
     * test the insert function
     */
    
    public void testInsert() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        newTree.setRootNode(2, 3);
        newTree.setTempNode(1, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode(3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode(1, 3);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode(2, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.getRoot().getKey().
                compareTo(1), 0);
        assertEquals(newTree.getRoot().getLeft().
                getKey().compareTo(1), 0);
        assertEquals(newTree.getRoot().getRight().
                getKey().compareTo(3), 0);
    }

    /**
     * test setting and getting the root
     */
    
    public void testSetRootNode() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        assertTrue(newTree.isEmpty());
        newTree.setRootNode(1, 4);
        assertFalse(newTree.isEmpty());
        BST<Integer, Integer>.TreeNode t = newTree.getRoot();
        assertEquals(t.getKey().compareTo(1), 0);
    }

    /**
     * test setting the root with one argument
     */
    
    public void testSetRootNodeTreeNode() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        assertTrue(newTree.isEmpty());
        newTree.setTempNode(2, 3);
        newTree.setRootNode(newTree.getTemp());
        assertEquals(newTree.getRoot().getKey().compareTo(2), 0);
    }

    /**
     * test setting the root with parameters
     */
    
    public void testSetRootNodeKeyE() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        assertTrue(newTree.isEmpty());
        newTree.setRootNode(1, 2);
        assertEquals(newTree.getRoot().getKey().compareTo(1), 0);
        assertEquals((int)newTree.getRoot().getValues().get(0), 2);
    }

    /**
     * test setting the and getting temp node
     */
    
    public void testSetGetTempNode() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        assertNull(newTree.getTemp());
        newTree.setTempNode(2, 4);
        assertNotNull(newTree.getTemp());
        BST<Integer, Integer>.TreeNode t = newTree.getTemp();
        assertEquals(t.getKey().compareTo(2), 0);
    }

    /**
     * test setting the temp node with one argument
     */
    
    public void testSetTempNodeTreeNode() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        assertNull(newTree.getTemp());
        newTree.setRootNode(1, 3);
        newTree.setTempNode(newTree.getRoot());
        assertEquals(newTree.getTemp().getKey().compareTo(1), 0);
    }

    /**
     * test setting the temp node with parameters
     */
    
    public void testSetTempNodeKeyE() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        assertNull(newTree.getTemp());
        newTree.setTempNode(3, 4);
        assertEquals(newTree.getTemp().getKey().compareTo(3), 0);
        assertEquals((int)newTree.getTemp().getValues().size(), 1);
        assertEquals((int)newTree.getTemp().getValues().get(0), 4);
    }

    /**
     * set the tempArray
     */
    
    public void testSetGetTempArray() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        newTree.setRootNode(3, 4);
        newTree.setTempNode(1, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode(9, 10);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree(9);
        //TODO: fix these tests for this new BST
        assertEquals(newTree.getTempArray().length, 2);
//        assertEquals(newTree.getTempArray()[0].getKey().compareTo("child"), 0);
//        assertEquals(newTree.getTempArray()[1].getKey().compareTo("child"), 0);
//        assertNotSame(newTree.getTempArray()[0].getX(),
//                newTree.getTempArray()[1].getX());
//        BST<Integer, Integer>.TreeNode[] t = newTree.getTempArray();
//        assertEquals(t.length, 2);
    }

    /**
     * test removing a nodes by name or coordinates
     */
    
    public void testRemoveKey() {
        //removal by name
        /*
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("child", 9, 10, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree("child");
        newTree.remove("root");
        assertEquals(newTree.getRoot().getKey().compareTo("child"), 0);
        newTree.remove("child");
        assertNotNull(newTree.getRoot());
        assertNull(newTree.getRoot().getLeft());
        assertNull(newTree.getRoot().getRight());
        //removal by coordinates
        newTree.setTempNode("child2", 1, 2, 2, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.remove(1, 2, 2, 2);
        assertEquals(newTree.getRoot().getKey().compareTo("child"), 0);
        assertNotNull(newTree.getRoot());
        assertNull(newTree.getRoot().getLeft());
        assertNull(newTree.getRoot().getRight());
        */
    }

    /**
     * test searching for a node by name.
     * The temporary array holds the search results
     */
    
    public void testSearch() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        /*
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("child", 9, 10, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree("child");
        //check that the right number of children were found
        assertEquals(newTree.getTempArray().length, 2);
        assertEquals(newTree.getTempArray()[0].getKey().compareTo("child"), 0);
        assertEquals(newTree.getTempArray()[1].getKey().compareTo("child"), 0);
        */
    }

    /**
     * test searching for all nodes that intersect
     */
    
    public void testRegionsearch() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        /*
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("chil", 90, 100, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempArray(
                newTree.regionsearch(newTree.getRoot(), newTree.getTemp()));
        assertEquals(newTree.getTempArray().length, 1);
        assertEquals(newTree.getTempArray()[0].getKey().compareTo("chil"), 0);
        */
    }

    /**
     * test tree dumping
     */
    
    public void testTreeDump() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        /*
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("chil", 90, 100, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.treeDump(), 3);
        */
    }

    /**
     * test finding all interesctions /
     **/
    
    public void testIntersections() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        /*
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("chil", 90, 100, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        String out = newTree.intersections();
        assertNotNull(out);
        */
    }

}
