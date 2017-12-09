import java.util.ArrayList;

/**
 *
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-18 This is the base class for my implementation of a binary
 *          search tree. Several elements of this class was based on OpenDSA
 *          code. There is one subclass, the TreeNode.
 * @param <E> the value type
 * @param <Key> the key type 
 */
public class BST<Key extends Comparable<? super Key>,
    E extends Comparable<? super E>> {

    private TreeNode root; //the root of the BST
    private TreeNode temp; //a TreeNode instance accessible from the outside
    private boolean removeSuccess; //global to indicate 'node removal success'
    
    /**
     * gets the global to indicate 'node removal success'
     * @return the global to indicate 'node removal success'
     */
    public boolean getRemoveSuccess() {
        return removeSuccess;
    }
    /**
     * default constructor
     */
    public BST() {
        root = null;
        temp = null;
    };

    /**
     * BST constructor given a TreeNode
     * @param rt  is the root node you want to give the BST
     */
    public BST(TreeNode rt) {
        root = rt;
    };
    /**
     * adds a key-value pair to the BST
     * @param key the key/handle
     * @param value the value/handle
     */
    public void add(Key key, E value) {
        TreeNode t = new TreeNode(key, value);
        insert(getRoot(), t);
        
    }
    /**
     * this function inserts a new TreeNode into the BST.
     * The sort key is the name field, and if the same key is given to 
     * a new instance of TreeNode, the new node's value will be added to 
     * the end of the KVPair's value list, assuming it is not a duplicate.
     * @param rt  the root node being called
     * @param newNode the parameters for the new node
     * @return a pointer to the root of the BST that includes the new node
     */
    public TreeNode insert(TreeNode rt, TreeNode newNode) {
        if (rt == null) {
            return newNode;
        } 
        else if (rt.getKey().compareTo(newNode.getKey()) == 0) {
            //we need to merge the values
            rt.getKVPair().addValue(newNode.getValues().get(0));
        }
        else if (rt.getKey().compareTo(newNode.getKey()) > 0) {
            // the root's name is after the new one alphabetically
            rt.setLeft(insert(rt.getLeft(), newNode));
        } 
        else { // the root's name preceded that of the new node
            rt.setRight(insert(rt.getRight(), newNode));
        }
        return rt;

    };

    /**
     * sets the root node to a default constructed TreeNode.
     * This is accessible from externally.
     */
    public void setRootNode() {
        root = new TreeNode();
    };

    /**
     * set the root node with the parameters of 
     * an existing TreeNode. 
     * @param t   is the existing TreeNode to copy
     */
    public void setRootNode(TreeNode t) {
        if (t != null) {
            root = new TreeNode(t);
        }
        else
        {
            root = null;
        }
               
    };

    /**
     * set the root node with the given parameters
     * @param k the key
     * @param elem the value
     */
    public void setRootNode(Key k, E elem) {
        root = new TreeNode(k, elem);
    };

    /**
     * set the temp node to a default constructed TreeNode.
     */
    public void setTempNode() {
        temp = new TreeNode();
    };

    /**
     * set the temp node with the parameters of an existing TreeNode.
     * 
     * @param t   is the TreeNode to copy
     */
    public void setTempNode(TreeNode t) {
        temp = t;
    };

    /**
     * set the temp node with the given parameters
     * @param k the key
     * @param elem the value
     */
    public void setTempNode(Key k, E elem) {
        temp = new TreeNode(k, elem);
    };

    ///////////////////////////////////////////////
    // Get values
    /**
     * get the value of the BST tree's root.
     * 
     * @return TreeNode root.
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * get the value of the BST tree's temp.
     * 
     * @return TreeNode temp.
     */
    public TreeNode getTemp() {
        return temp;
    }

    //////////////////////////////////////////////////////
    // Validate node features
    /**
     * Identifies if the BST is empty.
     * 
     * @return true if the BST is empty.
     */
    public boolean isEmpty() {
        return (root == null);
    };
    /**
     * removes a single node that contains the name parameter from the BST.
     * 
     * @param k is the key name
     * @param elem is the value name
     * @return boolean true if the key's node was removed
     */
    public boolean delete(Key k, E elem) {
        removeSuccess = false;
        search(root, k).deleteSecondaryHandle(elem);
        // if the tree's key has no more associated handles, 
        // delete the key handle
        ArrayList<E> values = searchTree(k);
        //this.remove(k, elem, false);
        if ( values == null ) {
            setRootNode(this.removeByKey(root, k));
            return true;
        }
        return false;
    }

    /**
     * removes a single node that contains the name parameter from the BST.
     * 
     * @param k is the key name
     * @param elem is the value name
     * @param removeKey is which value to remove
     * @return boolean true if the rectangle was found and removed
     */
    public boolean remove(Key k, E elem, boolean removeKey) {
        removeSuccess = false;
        if (removeKey) {
            setRootNode(removeByKey(root, k));
            if (root != null && root.getKey() == null) {
                root = null;
            }
            return removeSuccess;
        }
        else {
            //THIS DOES NOT WORK PROPERLY
            setRootNode(removeValue(root, elem));
            if (root != null && root.getKey() == null) {
                root = null;
            }
            return removeSuccess;            
        }
    }

    /**
     * Delete the maximum valued element in a subtree. From OpenDSA.
     * 
     * @param rt
     *            is the root of the level
     * @return the root after deletion
     */
    private TreeNode deletemax(TreeNode rt) {
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deletemax(rt.getRight()));
        return rt;
    }

    /**
     * Get the maximum valued element in a subtree. From OpenDSA.
     * 
     * @param rt
     *            is the root of the current level.
     * @return the maximum value in the subtrees of rt.
     */
    private TreeNode getmax(TreeNode rt) {
        if (rt.getRight() == null) {
            return rt;
        }
        return getmax(rt.getRight());
    }

    /**
     * remove the first TreeNode in the current root's branches that has the
     * name specified.
     * 
     * @param rt  is the current TreeNode branch
     * @param k is the Key that matches on the TreeNode to be removed
     * @return the child after removal of the names TreeNode.
     */
    private TreeNode removeByKey(TreeNode rt, Key k) {
        if (rt == null) { // the TreeNode could not be removed in this section
            return null;
        } 
        else if (rt.getKey() == null) { //the treeNode is empty
            return null;
        }
        if (rt.getKey().compareTo(k) > 0) { 
            rt.setLeft(removeByKey(rt.getLeft(), k));
        } 
        else if (rt.getKey().compareTo(k) < 0) {
            rt.setRight(removeByKey(rt.getRight(), k));
        } 
        else { // Found it
            removeSuccess = true;
           // removedArray = rt.kv.getValueHandles();
            if (rt.getLeft() == null) {
                return rt.getRight();
            } 
            else if (rt.getRight() == null) {
                return rt.getLeft();
            } 
            else { // Two children
                temp = getmax(rt.getLeft());
                rt.setValue(temp);
                rt.setLeft(deletemax(rt.getLeft()));
            }
        }
        return rt;
    }

    /**
     * removes the specified value from the array of values in the KVPair
     * @param rt the root
     * @param elem the value to remove
     * @return the same node
     * TODO: check if we need to delete or nullify keys that 
     * have no values associated with them
     */
    private TreeNode removeValue(TreeNode rt, E elem) {
        if (rt == null) {
            return null;
        }
        //set the leaves of the root given if there is a coordinate match or not
        rt.kv.deleteSecondaryHandle(elem);
        rt.setLeft(removeValue(rt.getLeft(), elem));
        rt.setRight(removeValue(rt.getRight(), elem));
        return rt;
    }
    
    /**
     * searches the BST for a node with the given name
     * @param k is the key name of the node to find
     * @return the values that correspond to the given key
     */
    public ArrayList<E> searchTree(Key k) {
        KVPair<Key, E> pair = (search(getRoot(), k));
        if (pair == null) {
            //System.out.printf("Key not found: %s\n", k.toString());
            return null;
        } 
        else {
            return pair.getValueList(); 
        } 
    }
    
    /**
     * searches for all the TreeNode instances with the name provided
     * 
     * @param rt
     *            is the name of the base node
     * @param k
     *            is the name of the node we are looking for
     * @return the KVPair with the specified key.
     */
    private KVPair<Key, E> search(TreeNode rt, Key k) {
        if (rt == null || (rt.getKey() == null)) {
            return null;
        }
        if (rt.kv.getKeyHandle().compareTo(k) == 0) { // found it
            return rt.getKVPair();
        }
        if (rt.kv.getKeyHandle().compareTo(k) < 0) { // too small
            return search(rt.getRight(), k);
        }
        // too big
        return search(rt.getLeft(), k);
    }


    /**
     * Runs an in-Order traversal of the BST starting at the 
     * root and returns an arrayList of the handle values.
     * @param output is true if this is dumping an output to the std out
     * @return the handles in the BST tree
     */
    public ArrayList<Key> treeDump(boolean output) { // (PrintWriter pw) {
        ArrayList<Key> handleList = new ArrayList<Key>();
        handleList = inorderDump(root, handleList, output);
//        System.out.printf("BST size is %d\n", handleList.size());
        return handleList;
    }

    /**
     * performs an in-order traversal starting at the given rt, and prints
     * them.
     * @param list is the array of keys to return
     * @param rt  is the base of the traversal.
     * @return is the in-order list of handles in this tree
     */
    private ArrayList<Key> inorderDump(TreeNode rt, ArrayList<Key> list, boolean output) {
        if (rt != null) {
            if (rt.getKey() == null) {
                System.out.printf("Node has depth %d, Value (null)\n",
                        list.size());
                return list;
            } 
            list = inorderDump(rt.getLeft(), list, output);
            list.add(rt.getKey());
            if (output) {
                if (rt.getValues().size() >= 1);
                {
                    int i = 0;
                    while (rt.getValues().size() > i) {
                        System.out.println("(" + rt.getKey() + "," + rt.getValues().get(i) + ")");
                        i++;
                    }
                    
                }
            }
            list = inorderDump(rt.getRight(), list, output);
        }
        else if (rt == getRoot()) {
            System.out.println("Node has depth 0, Value (null)");            
        }
        return list;
    }

    
    //////////////////////////////
    //////////////////////////////
    // the binary tree node class
    //////////////////////////////
    /**
     * the binary tree node class. Each node represents a rectangle.
     * 
     * @author maden
     *
     */
    public class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private KVPair<Key, E> kv;
        /**
         * base constructor
         */
        public TreeNode() {
            left = null;
            right = null;
        };

        /**
         * TreeNode copy constructor
         * 
         * @param t
         *            is another TreeNode
         */
        public TreeNode(TreeNode t) {
            if (t != null) {
                left = t.getLeft();
                right = t.getRight();
                kv = t.getKVPair();
            }
        }

        /**
         * constructor
         * @param k is an existing key
         * @param elem is an existing value
         */
        public TreeNode(Key k, E elem) {
            left = null;
            right = null;
            kv = new KVPair<Key, E>(k, elem);
        };

        /**
         * copy construct the left child
         * 
         * @param t   is the node to copy into the left child
         */
        public void setLeft(TreeNode t) {
            left = t;
        };

        /**
         * copy construct the right child
         * 
         * @param t
         *            is the node to copy into the right child
         */
        public void setRight(TreeNode t) {
            right = t;
        };

        /**
         * sets a TreeNode's values to those of another TreeNode.
         * 
         * @param t   is the TreeNode to copy
         */
        public void setValue(TreeNode t) {
            if (t == null) {
                return;
            }
            kv = t.getKVPair();
        }
       
        /**
         * get the base of the left child
         * 
         * @return the (TreeNode) left child
         */
        public TreeNode getLeft() {
            return left;
        }

        /**
         * get the base of the right child
         * 
         * @return the (TreeNode) right child
         */
        public TreeNode getRight() {
            return right;
        }

        /**
         * get the values at the treeNode
         * 
         * @return the (String) name
         */
        public KVPair<Key, E> getKVPair() {
            return kv;
        }
        /**
         * get the key value of the KVPair
         * 
         * @return the (Key) name
         */
        public Key getKey() {
            return kv.getKeyHandle();
        }
        /**
         * get the secondary handles of the KVPair
         * 
         * @return the secondary value(s)
         */
        public ArrayList<E> getValues() {
            return kv.getValueList();
        }

    }
}