package ru.pravvich.generic.store;

public class UserStore implements Store<User> {
    private User[] users = new User[100];
    private int index = 0;

    @Override
    public void add(User user) {
        users[index++] = user;
    }
}
