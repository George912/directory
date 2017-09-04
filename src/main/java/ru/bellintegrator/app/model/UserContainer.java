package ru.bellintegrator.app.model;

import java.util.List;

public class UserContainer {

    private List<User> users;

    public UserContainer(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

}
