package ru.pravvich.user;

import java.sql.Timestamp;

/**
 * User's data.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private Timestamp createAccount;

    public User() {}

    public User(final String name,
                final String login,
                final String email) {

        this.name = name;
        this.login = login;
        this.email = email;
    }

    public User(final int id,
                final String name,
                final String login,
                final String email) {

        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public User(final int id,
                final String name,
                final String login,
                final String email,
                final Timestamp createAccount) {

        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createAccount = createAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Timestamp createAccount) {
        this.createAccount = createAccount;
    }
}
