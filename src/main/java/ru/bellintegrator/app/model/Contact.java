package ru.bellintegrator.app.model;

import ru.bellintegrator.app.util.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Contact implements Serializable, Identifiable {

    private static final long serialVersionUID = -8767408797539567340L;
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

    public Contact() {

        this(0, "", "", "");

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

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(String firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    public PhoneNumberType getFirstPhoneNumberType() {
        return firstPhoneNumberType;
    }

    public void setFirstPhoneNumberType(PhoneNumberType firstPhoneNumberType) {
        this.firstPhoneNumberType = firstPhoneNumberType;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public PhoneNumberType getSecondPhoneNumberType() {
        return secondPhoneNumberType;
    }

    public void setSecondPhoneNumberType(PhoneNumberType secondPhoneNumberType) {
        this.secondPhoneNumberType = secondPhoneNumberType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public List<Group> getGroupList() {
        return groupList;
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
