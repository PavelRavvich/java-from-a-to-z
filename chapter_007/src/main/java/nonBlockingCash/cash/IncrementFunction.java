package nonBlockingCash.cash;

interface IncrementFunction<K extends Number> {
    boolean incrementVersion(final K idKey);
}
