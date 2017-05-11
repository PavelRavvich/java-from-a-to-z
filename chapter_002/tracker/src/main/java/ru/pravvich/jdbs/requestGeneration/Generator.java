package ru.pravvich.jdbs.requestGeneration;

@FunctionalInterface
public interface Generator {
    String generate(final String typeRequest, final String ... conditions);
}
