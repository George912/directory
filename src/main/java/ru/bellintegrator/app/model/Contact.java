package ru.bellintegrator.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by neste_000 on 11.07.2017.
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String firstPhoneNumber;
    private PhoneNumberType firstPhoneNumberType;
    private String secondPhoneNumber;
    private PhoneNumberType secondPhoneNumberType;
    private String email;
    private String notes;
    private List<Group> groupList;
    private User owner;

    public Contact() {
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.firstPhoneNumber = "";
        this.firstPhoneNumberType = PhoneNumberType.MOBILE;
        this.secondPhoneNumber = "";
        this.secondPhoneNumberType = PhoneNumberType.MOBILE;
        this.email = "";
        this.notes = "";
    }

    public Contact(int id, String firstName, String lastName, String middleName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.firstPhoneNumber = "";
        this.firstPhoneNumberType = PhoneNumberType.MOBILE;
        this.secondPhoneNumber = "";
        this.secondPhoneNumberType = PhoneNumberType.MOBILE;
        this.email = "";
        this.notes = "";
    }

    public Contact(int id, String firstName, String lastName, String middleName, String firstPhoneNumber, PhoneNumberType firstPhoneNumberType) {
        this(id, firstName, lastName, middleName);
        this.firstPhoneNumber = firstPhoneNumber;
        this.firstPhoneNumberType = firstPhoneNumberType;
    }

    public Contact(int id, String firstName, String lastName, String middleName, String firstPhoneNumber, PhoneNumberType firstPhoneNumberType, String secondPhoneNumber, PhoneNumberType secondPhoneNumberType, String email, String notes) {
        this(id, firstName, lastName, middleName, firstPhoneNumber, firstPhoneNumberType);
        this.secondPhoneNumber = secondPhoneNumber;
        this.secondPhoneNumberType = secondPhoneNumberType;
        this.email = email;
        this.notes = notes;
    }

    public Contact(int userId) {
        this.owner = new User(userId);
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

    @Column(name = "firstphonenumber", nullable = false, length = 11)
    public String getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(String firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    @Column(name = "firstphonenumbertype", nullable = false, length = 10)
    public String getFirstPhoneNumberType() {
        return firstPhoneNumberType.name();
    }

    public void setFirstPhoneNumberType(String firstPhoneNumberType) {
        this.firstPhoneNumberType = PhoneNumberType.getTypeByName(firstPhoneNumberType);
    }

    @Column(name = "secondphonenumber", nullable = false, length = 11)
    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    @Column(name = "secondphonenumbertype", nullable = false, length = 10)
    public String getSecondPhoneNumberType() {
        return secondPhoneNumberType.name();
    }

    public void setSecondPhoneNumberType(String secondPhoneNumberType) {
        this.secondPhoneNumberType = PhoneNumberType.getTypeByName(secondPhoneNumberType);
    }

    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "notes", nullable = false, length = 300)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "firstname", nullable = false, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middlename", nullable = false, length = 30)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @ManyToMany()
    @JoinTable(name = "contacts_groups",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    public List<Group> getGroupList() {
        return groupList;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false, referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", firstPhoneNumber='" + firstPhoneNumber + '\'' +
                ", firstPhoneNumberType=" + firstPhoneNumberType +
                ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
                ", secondPhoneNumberType=" + secondPhoneNumberType +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                ", groupList=" + groupList +
                ", owner=" + owner +
                '}';
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        return !(middleName != null ? !middleName.equals(contact.middleName) : contact.middleName != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }

}
