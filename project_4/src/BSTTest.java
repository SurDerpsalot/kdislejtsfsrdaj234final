
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
        BST newTree = new BST();
        assertNull(newTree.getRoot());
        assertNull(newTree.getTemp());
        assertNull(newTree.getTempArray());
    }

    /**
     * test the BST construction with an existing tempNode
     */
    public void testBSTTreeNode() {
        BST newTree = new BST();
        newTree.setRootNode("newnode", 1, 2, 3, 4);
        BST newTree2 = new BST(newTree.getRoot());
        assertNotNull(newTree.getRoot());
        assertNotNull(newTree2.getRoot());
        /*assertEquals(newTree.getRoot().getX(), 
                newTree2.getRoot().getX());
        assertEquals(newTree.getRoot().getY(), 
                newTree2.getRoot().getY());
        assertEquals(newTree.getRoot().getW(), 
                newTree2.getRoot().getW());
        assertEquals(newTree.getRoot().getH(), 
                newTree2.getRoot().getH());*/
    }

    /**
     * test the insert function
     */
    
    public void testInsert() {
        BST newTree = new BST();
        newTree.setRootNode("newnode", 1, 2, 3, 4);
        newTree.setTempNode("a_node", 1, 2, 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("z_node", 1, 2, 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("b_node", 1, 2, 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("y_node", 1, 2, 3, 4);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.getRoot().getName().
                compareTo("newnode"), 0);
        assertEquals(newTree.getRoot().getLeft().
                getName().compareTo("a_node"), 0);
        assertEquals(newTree.getRoot().getRight().
                getName().compareTo("z_node"), 0);
        assertEquals(newTree.getRoot().getLeft().
                getRight().getName().compareTo("b_node"), 0);
        assertEquals(newTree.getRoot().getRight().
                getLeft().getName().compareTo("y_node"), 0);
    }

    /**
     * test setting and getting the root
     */
    
    public void testSetRootNode() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        newTree.setRootNode("newnode", 1, 2, 3, 4);
        assertFalse(newTree.isEmpty());
        BST.TreeNode t = newTree.getRoot();
        assertEquals(t.getName().compareTo("newnode"), 0);
    }

    /**
     * test setting the root with one argument
     */
    
    public void testSetRootNodeTreeNode() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        newTree.setTempNode("temp", 1, 2, 3, 4);
        newTree.setRootNode(newTree.getTemp());
        assertEquals(newTree.getRoot().getName().compareTo("temp"), 0);
    }

    /**
     * test setting the root with parameters
     */
    
    public void testSetRootNodeStringDoubleDoubleDoubleDouble() {
        BST newTree = new BST();
        assertTrue(newTree.isEmpty());
        newTree.setRootNode("root", 1, 2, 3, 4);
        assertEquals(newTree.getRoot().getName().compareTo("root"), 0);
        assertEquals((int)newTree.getRoot().getX(), 1);
        assertEquals((int)newTree.getRoot().getY(), 2);
        assertEquals((int)newTree.getRoot().getW(), 3);
        assertEquals((int)newTree.getRoot().getH(), 4);
    }

    /**
     * test setting the and getting temp node
     */
    
    public void testSetGetTempNode() {
        BST newTree = new BST();
        assertNull(newTree.getTemp());
        newTree.setTempNode("tempnode", 1, 2, 3, 4);
        assertNotNull(newTree.getTemp());
        BST.TreeNode t = newTree.getTemp();
        assertEquals(t.getName().compareTo("tempnode"), 0);
    }

    /**
     * test setting the temp node with one argument
     */
    
    public void testSetTempNodeTreeNode() {
        BST newTree = new BST();
        assertNull(newTree.getTemp());
        newTree.setRootNode("temp", 1, 2, 3, 4);
        newTree.setTempNode(newTree.getRoot());
        assertEquals(newTree.getTemp().getName().compareTo("temp"), 0);
    }

    /**
     * test setting the temp node with parameters
     */
    
    public void testSetTempNodeStringDoubleDoubleDoubleDouble() {
        BST newTree = new BST();
        assertNull(newTree.getTemp());
        newTree.setTempNode("tempnodet", 1, 2, 3, 4);
        assertEquals(newTree.getTemp().getName().compareTo("tempnodet"), 0);
        assertEquals((int)newTree.getTemp().getX(), 1);
        assertEquals((int)newTree.getTemp().getY(), 2);
        assertEquals((int)newTree.getTemp().getW(), 3);
        assertEquals((int)newTree.getTemp().getH(), 4);
    }

    /**
     * set the tempArray
     */
    
    public void testSetGetTempArray() {
        BST newTree = new BST();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("child", 9, 10, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree("child");
        assertEquals(newTree.getTempArray().length, 2);
        assertEquals(newTree.getTempArray()[0].getName().compareTo("child"), 0);
        assertEquals(newTree.getTempArray()[1].getName().compareTo("child"), 0);
        assertNotSame(newTree.getTempArray()[0].getX(),
                newTree.getTempArray()[1].getX());
        BST.TreeNode[] t = newTree.getTempArray();
        assertEquals(t.length, 2);
    }

    /**
     * test removing a nodes by name or coordinates
     */
    
    public void testRemoveDoubleDoubleDoubleDouble() {
        //removal by name
        BST newTree = new BST();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("child", 9, 10, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree("child");
        newTree.remove("root");
        assertEquals(newTree.getRoot().getName().compareTo("child"), 0);
        newTree.remove("child");
        assertNotNull(newTree.getRoot());
        assertNull(newTree.getRoot().getLeft());
        assertNull(newTree.getRoot().getRight());
        //removal by coordinates
        newTree.setTempNode("child2", 1, 2, 2, 2);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.remove(1, 2, 2, 2);
        assertEquals(newTree.getRoot().getName().compareTo("child"), 0);
        assertNotNull(newTree.getRoot());
        assertNull(newTree.getRoot().getLeft());
        assertNull(newTree.getRoot().getRight());
    }

    /**
     * test searching for a node by name.
     * The temporary array holds the search results
     */
    
    public void testSearch() {
        BST newTree = new BST();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("child", 9, 10, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.searchTree("child");
        //check that the right number of children were found
        assertEquals(newTree.getTempArray().length, 2);
        assertEquals(newTree.getTempArray()[0].getName().compareTo("child"), 0);
        assertEquals(newTree.getTempArray()[1].getName().compareTo("child"), 0);
    }

    /**
     * test searching for all nodes that intersect
     */
    
    public void testRegionsearch() {
        BST newTree = new BST();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("chil", 90, 100, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempArray(
                newTree.regionsearch(newTree.getRoot(), newTree.getTemp()));
        assertEquals(newTree.getTempArray().length, 1);
        assertEquals(newTree.getTempArray()[0].getName().compareTo("chil"), 0);
        
    }

    /**
     * test tree dumping
     */
    
    public void testTreeDump() {
        BST newTree = new BST();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("chil", 90, 100, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        assertEquals(newTree.treeDump(), 3);
    }

    /**
     * test finding all interesctions /
     **/
    
    public void testIntersections() {
        BST newTree = new BST();
        newTree.setRootNode("root", 1, 2, 3, 4);
        newTree.setTempNode("child", 5, 6, 7, 8);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        newTree.setTempNode("chil", 90, 100, 11, 12);
        newTree.insert(newTree.getRoot(), newTree.getTemp());
        String out = newTree.intersections();
        assertNotNull(out);
    }

}
