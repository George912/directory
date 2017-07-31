package ru.bellintegrator.app.parser.jackson.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Contact {

    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private int id;
    @JacksonXmlProperty(localName = "firstName")
    private String firstName;
    @JacksonXmlProperty(localName = "lastName")
    private String lastName;
    @JacksonXmlProperty(localName = "middleName")
    private String middleName;
    @JacksonXmlProperty(localName = "firstPhoneNumber")
    private String firstPhoneNumber;
    @JacksonXmlProperty(localName = "firstPhoneNumberType")
    private String firstPhoneNumberType;
    @JacksonXmlProperty(localName = "secondPhoneNumber")
    private String secondPhoneNumber;
    @JacksonXmlProperty(localName = "secondPhoneNumberType")
    private String secondPhoneNumberType;
    @JacksonXmlProperty(localName = "email")
    private String email;
    @JacksonXmlProperty(localName = "notes")
    private String notes;
    @JacksonXmlProperty(localName = "groupList")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List groupIds;

    public Contact() {
    }

    public Contact(int id, String firstName, String lastName, String middleName, String firstPhoneNumber, String firstPhoneNumberType, String secondPhoneNumber, String secondPhoneNumberType, String email, String notes, List groupIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstPhoneNumber = firstPhoneNumber;
        this.firstPhoneNumberType = firstPhoneNumberType;
        this.secondPhoneNumber = secondPhoneNumber;
        this.secondPhoneNumberType = secondPhoneNumberType;
        this.email = email;
        this.notes = notes;
        this.groupIds = groupIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstPhoneNumber() {
        return firstPhoneNumber;
    }

    public void setFirstPhoneNumber(String firstPhoneNumber) {
        this.firstPhoneNumber = firstPhoneNumber;
    }

    public String getFirstPhoneNumberType() {
        return firstPhoneNumberType;
    }

    public void setFirstPhoneNumberType(String firstPhoneNumberType) {
        this.firstPhoneNumberType = firstPhoneNumberType;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public String getSecondPhoneNumberType() {
        return secondPhoneNumberType;
    }

    public void setSecondPhoneNumberType(String secondPhoneNumberType) {
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

    public List getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", firstPhoneNumber='" + firstPhoneNumber + '\'' +
                ", firstPhoneNumberType='" + firstPhoneNumberType + '\'' +
                ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
                ", secondPhoneNumberType='" + secondPhoneNumberType + '\'' +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

}
