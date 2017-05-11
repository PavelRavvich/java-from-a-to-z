package ru.pravvich.jdbs;

/**
 * Emulation PropertyLoader for test database.
 */
public class StubPropertyLoader extends DBPropertyLoader {
    @Override
    public String getUrlDB() {
        return "jdbc:postgresql://localhost:5432/test_auto_services";
    }
}
