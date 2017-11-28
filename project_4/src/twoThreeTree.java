/**
 * 
 */

/**
 * @author maden
 * Built using code from OpenDSA
 */
// 2-3 tree node implementation
public class twoThreeTree <Key extends Comparable<? super Key>, E> {
    TTNode root;
    public twoThreeTree () {
        root = null;
    }
    protected void insert(Key k, E e) {
        if(isEmpty()) {
            root = new leafNode(k, e, null, null, null, null, null);
        }
        else {
            root = root.inserthelp(root,  k,  e);
        }
    }
    /**
     * checks if the tree is empty
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * finds the value at the given key in this tree
     * @param k the key value
     * @return null if k is not a key in this list, 
     * otherwise the element's value
     */
    protected E find(Key k) {
       return root.findhelp(root, k);
    }
    protected class internalNode extends TTNode {
        public boolean isLeaf() { return false; }        
    }
    
    protected class leafNode<Key, E> extends TTNode  {
        public leafNode(Key lk, E lv, Key rk, E rv,
                TTNode p1, TTNode p2,
                TTNode p3) {
            this.setLeft(lk, lv);
            this.setRight(rk, rv);
            this.setLeftChild(p1);
            this.setCenterChild(p2);
            this.setRightChild(p3);
          }

        private void setRight(Key rk, E rv) {
            // TODO Auto-generated method stub
            this.setLeft(rk, rv);
            
        }

        private void setLeft(Key lk, E lv) {
            this.setLeft(lk, lv);
            // TODO Auto-generated method stub
            
        }

        public boolean isLeaf() { return true; }
    }
    class TTNode {
        private E lval;        // The left record
        private Key lkey;        // The node's left key
        private E rval;        // The right record
        private Key rkey;        // The node's right key
        private TTNode left;   // Pointer to left child
        private TTNode center; // Pointer to middle child
        private TTNode right;  // Pointer to right child

        public TTNode() { center = left = right = null; }
        public TTNode(Key lk, E lv, Key rk, E rv,
                      TTNode p1, TTNode p2,
                      TTNode p3) {
          lkey = lk; rkey = rk;
          lval = lv; rval = rv;
          left = p1; center = p2; right = p3;
        }

        public boolean isLeaf() { return left == null; }
        public TTNode lchild() { return left; }
        public TTNode rchild() { return right; }
        public TTNode cchild() { return center; }
        public Key lkey() { return lkey; }  // Left key
        public E lval() { return lval; }  // Left value
        public Key rkey() { return rkey; }  // Right key
        public E rval() { return rval; }  // Right value
        public void setLeft(Key k, E e) { lkey = k; lval = e; }
        public void setRight(Key k, E e) { rkey = k; rval = e; }
        public void setLeftChild(TTNode it) { left = it; }
        public void setCenterChild(TTNode it)
          { center = it; }
        public void setRightChild(TTNode it)
          { right = it; }
        
        protected E findhelp(TTNode root, Key k) {
            if (root == null) return null;          // val not found
            if (k.compareTo(root.lkey()) == 0) return root.lval();
            if ((root.rkey() != null) && (k.compareTo(root.rkey())
                 == 0))
              return root.rval();
            if (k.compareTo(root.lkey()) < 0)       // Search left
              return findhelp(root.lchild(), k);
            else if (root.rkey() == null)           // Search center
              return findhelp(root.cchild(), k);
            else if (k.compareTo(root.rkey()) < 0)  // Search center
              return findhelp(root.cchild(), k);
            else return findhelp(root.rchild(), k); // Search right
          }
        public TTNode inserthelp(TTNode rt, Key k, E e) {
            TTNode retval;
            if (rt == null) // Empty tree: create a leaf node for root
              return new TTNode(k, e, null, null, null, null, null);
            if (rt.isLeaf()) // At leaf node: insert here
              return rt.add(new TTNode(k, e, null, null, null, null, null));
            // Add to internal node
            if (k.compareTo(rt.lkey()) < 0) { // Insert left
              retval = inserthelp(rt.lchild(), k, e);
              if (retval == rt.lchild()) return rt;
              else return rt.add(retval);
            }
            else if((rt.rkey() == null) || (k.compareTo(rt.rkey()) < 0)) {
              retval = inserthelp(rt.cchild(), k, e);
              if (retval == rt.cchild()) return rt;
              else return rt.add(retval);
            }
            else { // Insert right
              retval = inserthelp(rt.rchild(), k, e);
              if (retval == rt.rchild()) return rt;
              else return rt.add(retval);
            }
          }

          // Add a new key/value pair to the node. There might be a subtree
          // associated with the record being added. This information comes
          // in the form of a 2-3 tree node with one key and a (possibly null)
          // subtree through the center pointer field.
          public TTNode add(TTNode it) {
            if (rkey == null) { // Only one key, add here
              if (lkey.compareTo(it.lkey()) < 0) {
                rkey = it.lkey(); rval = it.lval();
                center = it.lchild(); right = it.cchild();
              }
              else {
                rkey = lkey; rval = lval; right = center;
                lkey = it.lkey(); lval = it.lval();
                center = it.cchild();
              }
              return this;
            }
            else if (lkey.compareTo(it.lkey()) >= 0) { // Add left
              TTNode N1 = new TTNode(lkey, lval, null, null, it, this, null);
              it.setLeftChild(left);
              left = center; center = right; right = null;
              lkey = rkey; lval = rval; rkey = null; rval = null;
              return N1;
            }
            else if (rkey.compareTo(it.lkey()) >= 0) { // Add center
              it.setCenterChild(new TTNode(rkey, rval, null, null, it.cchild(), right, null));
              it.setLeftChild(this);
              rkey = null; rval = null; right = null;
              return it;
            }
            else { // Add right
                  TTNode N1 = new TTNode(rkey, rval, null, null, this, it, null);
                  it.setLeftChild(right);
                  right = null; rkey = null; rval = null;
                  return N1;
                }
              }
  } 
}