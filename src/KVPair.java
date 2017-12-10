import java.util.ArrayList;

/**
 * 
 * @author Madelyn Newcomb m1newc
 * @version 11/11/2017
 * @param <int> the key's type
 * @param <S> the value's type
 */
public class KVPair {
    private int firstHandle;
    private BSTValues secondaryHandles;
    /**
     * constructor
     * @param fH the Key name's key
     * @param sH the Value name's key
     */
    public KVPair(int fH, int sH) {
        firstHandle = fH;
        secondaryHandles = new BSTValues(sH);
    }
    /**
     * get the Key name's key
     * @return int: Key
     */
    protected int getKeyHandle() {
        return firstHandle;
    }
    /**
     * get the Value name's key
     * @return S: Value
     */
    protected ArrayList<Integer> getValueList() {
        return secondaryHandles.treeDump();
    }
    /**
     * add a value to the secondary handle array given the KVPair
     * @param newValue is the value to add
     * @return the size of the secondary array
     */
    protected int addValue(int newValue) {
        if (secondaryHandles == null 
                || secondaryHandles.getRoot() == null) {
            secondaryHandles = new BSTValues();
        }
        if (!isDuplicateValue(newValue)) {
            secondaryHandles.setTempNode(newValue);
            secondaryHandles.setRootNode(secondaryHandles.insert(
                    secondaryHandles.getRoot(), secondaryHandles.getTemp()));
        }
        return secondaryHandles.treeDump().size();
    }
    
    /**
     * deletes a secondary handle from the linked list
     * @param secondKey is the value to delete
     * @return true if the value was deleted
     */
    protected boolean deleteSecondaryHandle(int secondKey) {
        secondaryHandles.remove(secondKey);
        return secondaryHandles.removeSuccess;
    }
    /**
     * checks if a key already exists in a given KVPair
     * @param secondKey the value to match
     * @return true if it already exists
     */
    protected boolean isDuplicateValue(int secondKey) {
        int size = secondaryHandles.treeDump().size(); 
        if (size == 0) {
            return false;
        }
        return secondaryHandles.searchTree(secondKey);
    }
    /**
     * 
     * @author maden
     *
     * @param <S> is the type
     */
    public class BSTValues { // extends Comparable<? super int>

        private TNode root; //the root of the BST
        private TNode temp; //a TNode instance accessible from the outside
        private TNode[] tempArray; //
        private boolean removeSuccess; //
       // private ArrayList<S> removedArray;
        /**
         * default constructor
         */
        public BSTValues() {
            root = null;
            temp = null;
        };

        /**
         * BST constructor given an element
         * @param rt  is the root node you want to give the BST
         */
        public BSTValues(int rt) {
            root = new TNode(rt);
        };
                
        /**
         * this function inserts a new TNode into the BST.
         * The sort key is the name field, and if the same key is given to 
         * a new instance of TNode, the new node's value will be added to 
         * the end of the KVPair's value list, assuming it is not a duplicate.
         * @param rt  the root node being called
         * @param newNode the parameters for the new node
         * @return a pointer to the root of the BST that includes the new node
         */
        public TNode insert(TNode rt, TNode newNode) {
            if (rt == null) {
                return newNode;
            } 
            else if (rt.get() == newNode.get()) { //duplicate value
                return rt;
            }
            else if (rt.get()> newNode.get()) {
                // the root's name is after the new one alphabetically
                rt.setLeft(insert(rt.getLeft(), newNode));
            } 
            else { // the root's name preceded that of the new node
                rt.setRight(insert(rt.getRight(), newNode));
            }
            return rt;
        };


        /**
         * set the root node with the parameters of 
         * an existing TNode. 
         * @param t   is the existing TNode to copy
         */
        public void setRootNode(TNode t) {
            if (t != null) {
                root = new TNode(t);
            }
            else
            {
                root = null;
            }
                   
        };

        /**
         * set the temp node with the given parameters
         * @param elem the value
         */
        public void setTempNode(int elem) {
            temp = new TNode(elem);
        };

        /**
         * get the pointer to the BST's tempArray.
         * 
         * @return tempArray
         */
        public TNode[] getTempArray() {
            return tempArray;
        };

        ///////////////////////////////////////////////
        // Get values
        /**
         * get the value of the BST tree's root.
         * 
         * @return TNode root.
         */
        public TNode getRoot() {
            return root;
        }

        /**
         * get the value of the BST tree's temp.
         * 
         * @return TNode temp.
         */
        public TNode getTemp() {
            return temp;
        }

        //////////////////////////////////////////////////////
        // Validate node features

        /**
         * removes a single node that contains the name parameter from the BST.
         * 
         * @param elem is the value name
         * @return boolean true if the value was found and removed
         */
        public boolean remove(int elem) {
            removeSuccess = false;
            root = removeByKey(root, elem);
            return removeSuccess;            
        }

        /**
         * Delete the maximum valued element in a subtree. From OpenDSA.
         * 
         * @param rt
         *            is the root of the level
         * @return the root after deletion
         */
        private TNode deletemax(TNode rt) {
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
        private TNode getmax(TNode rt) {
            if (rt.getRight() == null) {
                return rt;
            }
            return getmax(rt.getRight());
        }

        /**
         * remove the first TNode in the current root's branches that has the
         * name specified.
         * 
         * @param rt  is the current TNode branch
         * @param elem is the Key that matches on the TNode to be removed
         * @return the child after removal of the names TNode.
         */
        private TNode removeByKey(TNode rt, int elem) {
            if (rt == null) { // the TNode could not be removed in this section
                return null;
            } 
            if (rt.get() > elem) { 
                rt.setLeft(removeByKey(rt.getLeft(), elem));
            } 
            else if (rt.get() < elem) {
                rt.setRight(removeByKey(rt.getRight(), elem));
            } 
            else { // Found it
                removeSuccess = true;

             //   System.out.println("--------------------------"+elem+ "----------------------------");
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
         * searches the BST for a node with the given name
         * @param elem is the key name of the node to find
         * @return the values that correspond to the given key
         */
        public boolean searchTree(int elem) {
            boolean found = (search(root, elem));
            return found;
        }
        
        /**
         * searches for all the TNode instances with the name provided
         * 
         * @param rt
         *            is the name of the base node
         * @param elem
         *            is the name of the node we are looking for
         * @return true if they find the element
         */
        private boolean search(TNode rt, int elem) {
            if (rt == null) {
                return false;
            }
            if (rt.get() == elem) { // found it
                return true;
            }
            if (rt.get() <
                    elem ) { // too small
                return search(rt.getRight(), elem);
            }
            // too big
            return search(rt.getLeft(), elem);
        }


        /**
         * Runs an in-Order traversal of the BST starting at the 
         * root and returns an arrayList of the handle values.
         * @return the handles in the BST tree
         */
        public ArrayList<Integer> treeDump() { 
            ArrayList<Integer> handleList = new ArrayList<Integer>();
            handleList = inorderDump(root, handleList);
          //  System.out.printf("BST size is %d\n", handleList.size());
            return handleList;
        }

        /**
         * performs an in-order traversal starting at the given rt, and prints
         * them.
         * @param list is the array of keys to return
         * @param rt  is the base of the traversal.
         * @return is the in-order list of handles in this tree
         */
        private ArrayList<Integer> inorderDump(TNode rt, ArrayList<Integer> list) {
            if (rt != null) {
                list = inorderDump(rt.getLeft(), list);
                list.add(rt.get());
                list = inorderDump(rt.getRight(), list);
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
        private class TNode {
            private TNode left;
            private TNode right;
            private int value;
            /**
             * TNode copy constructor
             * 
             * @param t
             *            is another TNode
             */
            public TNode(TNode t) {
                if (t != null) {
                    left = t.getLeft();
                    right = t.getRight();
                    value = t.get();
                }
            }

            /**
             * constructor
             * @param elem is an existing value
             */
            public TNode(int elem) {
                left = null;
                right = null;
                value = elem;
            };

            /**
             * copy construct the left child
             * 
             * @param t   is the node to copy into the left child
             */
            public void setLeft(TNode t) {
                left = t;
            };

            /**
             * copy construct the right child
             * 
             * @param t
             *            is the node to copy into the right child
             */
            public void setRight(TNode t) {
                right = t;
            };

            /**
             * sets a TNode's values to those of another TNode.
             * 
             * @param t   is the TNode to copy
             */
            public void setValue(TNode t) {
                if (t == null) {
                    return;
                }
                value = t.get();
            }
           

            /**
             * get the base of the left child
             * 
             * @return the (TNode) left child
             */
            public TNode getLeft() {
                return left;
            }

            /**
             * get the base of the right child
             * 
             * @return the (TNode) right child
             */
            public TNode getRight() {
                return right;
            }

            /**
             * get the values at the TNode
             * 
             * @return the value at this position
             */
            public int get() {
                return value;
            }
        }
    }
}
