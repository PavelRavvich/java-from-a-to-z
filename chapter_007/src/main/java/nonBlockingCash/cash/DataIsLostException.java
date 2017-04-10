package nonBlockingCash.cash;

import java.util.ConcurrentModificationException;

public class DataIsLostException extends ConcurrentModificationException {
    DataIsLostException() {
        super("Data is lost");
    }
}
