package ru.bellintegrator.app.model;

import ru.bellintegrator.app.util.Identifiable;

import java.io.Serializable;

public class User implements Serializable, Identifiable {

    private static final long serialVersionUID = 6869414085774571874L;
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String login, String password) {
        this(id);
        this.login = login;
        this.password = password;
    }

    public User(int id, String login, String password, String firstName, String lastName, String middleName) {
        this(id, login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

}
