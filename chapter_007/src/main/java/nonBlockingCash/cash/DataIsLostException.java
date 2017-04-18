package nonBlockingCash.cash;

import java.util.ConcurrentModificationException;

public class DataIsLostException extends ConcurrentModificationException {
    public DataIsLostException() {
        super("Data is lost");
    }
}
