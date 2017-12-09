
/**
 *
 * @author Madelyn Newcomb m1newc Bradley Finagin bfin96
 * @version 2017-09-18
 */
public class BSTTest extends student.TestCase {

    /**
     * test the BST tree's default implementation
     */
    public void testBST() {
        BST<Integer, Integer>  newTree = new BST<Integer, Integer>();
        assertNull(newTree.getRoot());
        assertNull(newTree.getTemp());
    }

    /**
     * test the BST construction with an existing tempNode
     */
    public void testBSTTreeNode() {
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        newTree.setRootNode(1, 2);
        BST<Integer, Integer> newTree2 = 
                new BST<Integer, Integer>(newTree.getRoot());
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
        assertEquals(newTree.getRoot().getKey() == 2, true);
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

//    /**
//     * set the tempArray
//     */
//    public void testSetGetTempArray() {
//        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
//        newTree.setRootNode();
//        newTree.setRootNode(3, 4);
//        newTree.setTempNode();
//        newTree.setTempNode(1, 6);
//        newTree.insert(newTree.getRoot(), newTree.getTemp());
//        newTree.setTempNode(9, 10);
//        newTree.insert(newTree.getRoot(), newTree.getTemp());
//        newTree.searchTree(9);
//        
//
//    }

    /**
     * tests removing nodes by keys and dumping the tree;
     * 
     */
    public void testRemoveKey() {
        
        
        BST<Integer, Integer> newTree = new BST<Integer, Integer>();
        newTree.setRootNode(200, 4);
        newTree.setTempNode(500, 6);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.isEmpty(), false);
        newTree.setTempNode(300, 5);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode(700, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree(500);
        newTree.searchTree(9);
        newTree.treeDump(false);
        newTree.remove(500, 6, true);
        newTree.treeDump(false);
        newTree.remove(700, 8, true);
        newTree.treeDump(false);
        newTree.remove(300, 5, false);
        newTree.treeDump(false);
        newTree.remove(300, 5, true);
        newTree.treeDump(true);
        newTree.remove(200, 4, true);
        assertEquals(newTree.isEmpty(), true);
        newTree.treeDump(false);
        newTree.remove(100, 2, true);
        newTree.treeDump(false);
    }

}
