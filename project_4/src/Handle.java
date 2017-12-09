/**
 * Placeholder text for placeholder class
 * @author Brad
 * @version 1.0
 */
public class Handle <T extends Comparable<? super T>>{
    private T memIndex;
    public T getI() {
        return memIndex;
    }
    public Handle(T index) {
        memIndex = index;
    }
}
