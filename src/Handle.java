/**
 * Placeholder text for placeholder class
 * @author Brad
 * @version 1.0
 * @param T is the type to extend
 */
public class Handle<T extends Comparable<? super T>> {
    private T memIndex;
    /**
     * gets the index
     * @return the memory index
     */
    public T getI() {
        return memIndex;
    }
    /**
     * sets the index in memory
     * @param index the index to set
     */
    public Handle(T index) {
        memIndex = index;
    }
}
