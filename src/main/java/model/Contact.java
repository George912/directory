package model;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Contact {

    //<editor-fold desc="поля">

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

    //</editor-fold>

    //<editor-fold desc="конструкторы">

    public Contact() {
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

    //</editor-fold>

    //<editor-fold desc="методы получения и установки">

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

    //</editor-fold>
}
