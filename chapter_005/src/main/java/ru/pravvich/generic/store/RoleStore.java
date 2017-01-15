package ru.pravvich.generic.store;

public class RoleStore implements Store<Role> {
    private Role[] roles = new Role[100];
    private int index = 0;

    @Override
    public void add(Role role) {
        roles[index++] = role;
    }
}
