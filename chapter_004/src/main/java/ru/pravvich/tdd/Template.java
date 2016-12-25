package ru.pravvich.tdd;

/**
 * Determines method for replace keys on values.
 * If key not found throws KeyNotFoundException.
 */
interface Template {
    String generate(String text, Object data) throws KeyNotFoundException;
}
