package ru.pravvich.jdbs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyLoader implements PropertyLoader {
    private String urlDB;
    private String username;
    private String password;
    private String rootDB;
    private final Properties properties;

    public DBPropertyLoader() {
        this.properties = new Properties();
        this.load();
    }

    public String getRootDB() {
        return rootDB;
    }

    public String getUrlDB() {
        return urlDB;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void load() {

        try (final InputStream r = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("database")

        ) {

            this.properties.load(r);

            this.urlDB = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
            this.rootDB = properties.getProperty("rootDB");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public String getCreateDBScript() {
        return this.properties.getProperty("createDB");
    }

    public String getScriptForCreateTasks() {
        return this.properties.getProperty("createTasks");
    }

    public String getScriptForCreateComments() {
        return this.properties.getProperty("createComments");
    }

}
