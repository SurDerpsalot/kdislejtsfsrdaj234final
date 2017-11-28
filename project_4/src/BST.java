import java.util.ArrayList;

/**
 *
 * @author Madelyn Newcomb m1newc
 * @version 2017-09-18 This is the base class for my implementation of a binary
 *          search tree. Several elements of this class was based on OpenDSA
 *          code. There is one subclass, the TreeNode.
 */
public class BST <Key extends Comparable<? super Key>, E> {

    private TreeNode root; //the root of the BST
    private TreeNode temp; //a TreeNode instance accessible from the outside
    private TreeNode[] tempArray; //an externally accessible array of TreeNodes
    private boolean removeSuccess; //global to indicate 'node removal success'
    private ArrayList<E> removedArray;
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
        root = new TreeNode(t);
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

    /**
     * point the temporary array to an existing array of TreeNodes
     * 
     * @param t
     *            an array of TreeNodes
     */
    public void setTempArray(TreeNode[] t) {
        tempArray = t;
    };

    /**
     * get the pointer to the BST's tempArray.
     * 
     * @return tempArray
     */
    public TreeNode[] getTempArray() {
        return tempArray;
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
     * @param name the string identifier of the rectangle
     * @return boolean true if the rectangle was found and removed
     */
    public boolean remove(Key k, E elem, boolean removeKey) {
        removeSuccess = false;
        if(removeKey) {
            setRootNode(removeByKey(root, k));
            if (root != null && root.getKey() == null) {
                root = null;
            }
            return removeSuccess;
        }
        else {
            removeSuccess = false;
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
            removedArray = rt.kv.getValueHandles();
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
            System.out.printf("Key not found: %s\n", k.toString());
            return null;
        } 
        else {
            return pair.getValueHandles(); 
        } 
    }
    
    /**
     * searches for all the TreeNode instances with the name provided
     * 
     * @param rt
     *            is the name of the base node
     * @param name
     *            is the name of the TreeNode we are looking for
     * @return an array of all TreeNodes with the specified name.
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
     * Runs an in-Order traversal of the BST starting at the root.
     * @return is the number of nodes in the BST
     */
    public int treeDump() { // (PrintWriter pw) {
        int count = inorderDump(root, 0);
        System.out.printf("BST size is %d\n", count);
        return count;
    }

    /**
     * performs an in-order traversal starting at the given rt, and prints
     * them.
     * 
     * @param rt  is the base of the traversal.
     * @param height is the level the node is at
     */
    private int inorderDump(TreeNode rt, int height) {
        if (rt != null) {
            int count = 0;
            count = inorderDump(rt.getLeft(), height + 1);
            if (rt.getKey() == null) {
                System.out.printf("Node has depth %d, Value (null)\n",
                        height);
                return 0;
            } 
            else {
//                System.out.printf(
 //                       "Node has depth %d, Value (%s,%.0f,%.0f,%.0f,%.0f)\n",
  //                      height, rt.getKey().toString(),
   //                     rt.getX(), rt.getY(), rt.getW(), rt.getH());
            }
            count = count + inorderDump(rt.getRight(), height + 1);
            return count + 1;
        }
        else if (rt == getRoot()) {
            System.out.println("Node has depth 0, Value (null)");            
        }
        return 0;
    }

    
    /**
     * return an array of the BST's nodes in order.
     * 
     * @param rt  the root node searched
     * @return the array of TreeNodes
     */
    private ArrayList<TreeNode> inorderList(TreeNode rt) {
        if (rt == null) {
            return null;
        }
        TreeNode thisValue = null;
        ArrayList<TreeNode> leftChildValue = inorderList(rt.getLeft());
        ArrayList<TreeNode> rightChildValue = inorderList(rt.getRight());
        int searchCount = 1; // number of nodes found
        thisValue = new TreeNode(rt);
        if (leftChildValue != null) { // the left child found some
            searchCount += leftChildValue.size();
        }
        if (rightChildValue != null) { // the right child found some
            searchCount += rightChildValue.size();
        }
        ArrayList<TreeNode> all = new ArrayList<TreeNode>();
        if (leftChildValue != null) {
            System.arraycopy(leftChildValue, 0, all.toArray(), 0, leftChildValue.size());
            if (thisValue != null) {
                all.toArray()[leftChildValue.size()] = thisValue;
            }
        } 
        else if (thisValue != null) {
            all.toArray()[0] = thisValue;
        }
        if (rightChildValue != null) {
            System.arraycopy(rightChildValue, 0, all, searchCount 
                    - rightChildValue.size(), rightChildValue.size());
        }

        return all;
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
    class TreeNode {
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
         * @param pair an existing key-value pair
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
         * construct the left child
         * @param k the key
         * @param elem the value
         */

        public void setLeft(Key k, E elem) {
            left = new TreeNode(k, elem);
        }

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
         * construct the right child
         * @param k the key
         * @param elem the value
         */

        public void setRight(Key k, E elem) {
            right = new TreeNode(k, elem);
        }

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
         * set the values
         * @param k the key
         * @param elem the first value associated with this key
         */
        public void setValues(Key k, E elem) {
            kv = new KVPair<Key, E>(k, elem);
        }

        /**
         * checks that all boxes are constrained within the designated square
         * coordinates.
         * 
         * @return true if the rectangle meets project specifications.
         */
        public boolean isValid() {
            return (kv.getKeyHandle() != null && 
                    kv.getValueHandles().size() != 0);
        };

        /**
         * this determines if there is an intersect between two KVPairs
         * @param t2
         *            is the TreeNode to be compared against
         * @return true if the nodes intersect.
         */
        public boolean isNodeInterect(TreeNode t2) {
           return (t2.getKey() == kv.getKeyHandle());
        }

        // get each component's value in TreeNode
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
        public KVPair<Key,E> getKVPair() {
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
            return kv.getValueHandles();
        }

    }
}