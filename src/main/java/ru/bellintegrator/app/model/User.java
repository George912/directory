package ru.bellintegrator.app.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;

    public User() {
        this.id = id;
        this.login = "";
        this.password = "";
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
    }

    public User(int id) {
        this.id = id;
        this.login = "";
        this.password = "";
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
    }

    public User(int id, String login, String password) {
        this(id);
        this.login = login;
        this.password = password;
    }

    public User(int id, String login, String password, String firstName, String middleName, String lastName) {
        this(id, login, password);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="login", nullable=false, length=20, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name="password", nullable=false, length=20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="firstname", nullable=false, length=30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="lastname", nullable=false, length=50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="middlename", nullable=false, length=30)
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
