package ru.bellintegrator.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 11.07.2017.
 */
@Entity
@Table(name = "contacts")
public class Contact {

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
    private List<Group> groupList = new ArrayList<>();
    private int owner;

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

    public Contact(int owner) {
        this();
        this.owner = owner;
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

    public Contact(int id, String firstName, String lastName, String middleName, String firstPhoneNumber, PhoneNumberType firstPhoneNumberType, String secondPhoneNumber, PhoneNumberType secondPhoneNumberType, String email, String notes, int owner) {
        this(id, firstName, lastName, middleName, firstPhoneNumber, firstPhoneNumberType, secondPhoneNumber, secondPhoneNumberType, email, notes);
        this.owner = owner;
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

    @Column(name="firstphonenumber", nullable=false, length=11)
    public String getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(String firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    @Column(name="firstphonenumbertype", nullable=false, length=10)
    public PhoneNumberType getFirstPhoneNumberType() {
        return firstPhoneNumberType;
    }

    public void setFirstPhoneNumberType(PhoneNumberType firstPhoneNumberType) {
        this.firstPhoneNumberType = firstPhoneNumberType;
    }

    @Column(name="secondphonenumber", nullable=false, length=11)
    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    @Column(name="secondphonenumbertype", nullable=false, length=10)
    public PhoneNumberType getSecondPhoneNumberType() {
        return secondPhoneNumberType;
    }

    public void setSecondPhoneNumberType(PhoneNumberType secondPhoneNumberType) {
        this.secondPhoneNumberType = secondPhoneNumberType;
    }

    @Column(name="email", nullable=false, length=30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="notes", nullable=false, length=300)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    @Transient
    public List<Group> getGroupList() {
        return groupList;
    }

    @Column(name="owner", nullable=false)
    public int getOwner() {
        return owner;
    }

    public void setOwner(int ownerId) {
        this.owner = ownerId;
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
