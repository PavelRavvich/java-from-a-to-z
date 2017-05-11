package ru.pravvich.jdbs;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class DBPropertyLoaderTest {
    @Test
    public void whenLoadPasswordThenGetPasswordFromDatabaseProperty() {
        PropertyLoader pl = new DBPropertyLoader();
        pl.load();
        final String result = pl.getPassword();
        Assert.assertThat(result, is("1"));
    }

    @Test
    public void whenThen() {
        PropertyLoader pl = new DBPropertyLoader();
        pl.load();
        final String result = pl.getUrlDB();
        Assert.assertThat(result, is("jdbc:postgresql://localhost:5432/auto_services"));
    }

    @Test
    public void whenThen2() {
        PropertyLoader pl = new DBPropertyLoader();
        pl.load();
        final String result = pl.getUsername();
        Assert.assertThat(result, is("postgres"));
    }
}