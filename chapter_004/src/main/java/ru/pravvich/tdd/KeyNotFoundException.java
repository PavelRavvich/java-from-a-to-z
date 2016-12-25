package ru.pravvich.tdd;

/**
 * Determines exception for case then key not found.
 */
class KeyNotFoundException extends Exception {
    KeyNotFoundException(String massage) {
        super(massage);
    }
}
