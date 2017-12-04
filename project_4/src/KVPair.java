import java.util.ArrayList;

/**
 * 
 * @author Madelyn Newcomb m1newc
 * @version 11/11/2017
 * @param <F> the key's type
 * @param <S> the value's type
 */
public class KVPair<F, S> {
    private F firstHandle;
    private ArrayList<S> secondaryHandles;
    /**
     * constructor
     * @param fH the Key name's key
     * @param sH the Value name's key
     */
    public KVPair(F fH, S sH) {
        firstHandle = fH;
        secondaryHandles = new ArrayList<S>();
        secondaryHandles.add(sH);
    }
    /**
     * get the Key name's key
     * @return F: Key
     */
    protected F getKeyHandle() {
        return firstHandle;
    }
    /**
     * get the Value name's key
     * @return S: Value
     */
    protected ArrayList<S> getValueHandles() {
        return secondaryHandles;
    }
    /**
     * add a value to the secondary handle array given the KVPair
     * @param newValue is the value to add
     * @return the size of the secondary array
     */
    protected int addValue(S newValue) {
        if (secondaryHandles == null) {
            secondaryHandles = new ArrayList<S>();
        }
        if (!isDuplicateValue(newValue)) {
            secondaryHandles.add(newValue);
        }
        return secondaryHandles.size();
    }
    
    /**
     * deletes a secondary handle from the linked list
     * @param secondKey is the value to delete
     * @return true if the value was deleted
     */
    protected boolean deleteSecondaryHandle(S secondKey) {
        if (secondaryHandles == null || secondaryHandles.size() == 0) {
            return false;
            //this is an error because we shouldn't have any blank lists
        }
        boolean found = false;
        int pos = 0;
        while ( pos < secondaryHandles.size() ) {
            if (secondaryHandles.get(pos) == secondKey) {
                secondaryHandles.remove(pos);
                found = true;
            }
            else {
                pos++;
            }
        }
        return found;
    }
    /**
     * checks if a key already exists in a given KVPair
     * @param secondKey the value to match
     * @return true if it already exists
     */
    protected boolean isDuplicateValue(S secondKey) {
        if (secondaryHandles == null || secondaryHandles.size() == 0) {
            return false;
            //this is an error because we shouldn't have any blank lists
        }
        int pos = 0;
        while ( pos < secondaryHandles.size() ) {
            if (secondaryHandles.get(pos) == secondKey) {
                return true;
            }
            else {
                pos++;
            }
        }
        return false;
    }

    
}
