package ru.bellintegrator.app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.service.ContactService;
import ru.bellintegrator.app.dao.service.GroupService;
import ru.bellintegrator.app.exception.PersonalDataNotSetException;
import ru.bellintegrator.app.exception.PhoneNumberFormatException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class ContactEditorController {

    private static final Logger log = LoggerFactory.getLogger(ContactEditorController.class);

    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField firstPhoneNumberTextField;
    @FXML
    private TextField secondPhoneNumberTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private TextField groupNameTextField;
    @FXML
    private TextArea groupNotesTextArea;
    @FXML
    private ComboBox<String> firstPhoneNumberTypeComboBox;
    @FXML
    private ComboBox<String> secondPhoneNumberTypeComboBox;
    @FXML
    private CheckListView<Group> groupCheckListView;
    @FXML
    private Label errorLabel;

    Contact contact;
    private Stage dialogStage;
    private EditorAction editorAction;

    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.FILE);
    GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
    GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
    ContactService contactService = new ContactService(contactGenericDAO);
    GroupService groupService = new GroupService(groupGenericDAO);

    public void setContact(Contact contact) {
        this.contact = contact;

        lastNameTextField.setText(contact.getLastName());
        nameTextField.setText(contact.getFirstName());
        middleNameTextField.setText(contact.getMiddleName());
        firstPhoneNumberTypeComboBox.getSelectionModel().select(PhoneNumberType.getStringFromPhoneNumberType(contact.getFirstPhoneNumberType()));
        firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
        secondPhoneNumberTypeComboBox.getSelectionModel().select(PhoneNumberType.getStringFromPhoneNumberType(contact.getSecondPhoneNumberType()));
        secondPhoneNumberTextField.setText(contact.getSecondPhoneNumber());
        emailTextField.setText(contact.getEmail());
        notesTextArea.setText(contact.getNotes());

        for (Group group : contact.getGroupList()) {
            groupCheckListView.getCheckModel().check(group);
        }


    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEditorAction(EditorAction editorAction) {
        this.editorAction = editorAction;
    }

    public ContactEditorController() {
    }

    @FXML
    private void initialize() {

        firstPhoneNumberTypeComboBox.setItems(preparePhoneNumberTypeComboBoxData(PhoneNumberType.values()));
        secondPhoneNumberTypeComboBox.setItems(preparePhoneNumberTypeComboBoxData(PhoneNumberType.values()));

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        groupObservableList.addAll(groupService.getAllGroups());

        groupCheckListView.setItems(groupObservableList);

    }

    @FXML
    private void saveButtonClick() {

        try {
            validate();

            switch (editorAction) {
                case CREATE:
                    createContact();
                    break;

                case UPDATE:
                    updateContact();
                    break;
            }

            dialogStage.close();

        } catch (PersonalDataNotSetException | PhoneNumberFormatException e) {
            log.debug(e.getMessage());
            errorLabel.setText(e.getMessage());
        }

    }

    @FXML
    private void cancelButtonClick() {

        dialogStage.close();

    }

    private ObservableList<String> preparePhoneNumberTypeComboBoxData(PhoneNumberType[] phoneNumberTypes) {

        ObservableList<String> stringObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < phoneNumberTypes.length; i++) {
            stringObservableList.add(PhoneNumberType.getStringFromPhoneNumberType(phoneNumberTypes[i]));
        }

        return stringObservableList;

    }

    private void addContactToGroup(Contact contact, ObservableList<Group> groupObservableList) {

        if (!contact.getGroupList().isEmpty())
            contact.getGroupList().clear();

        for (Group group : groupObservableList) {
            if (!contact.getGroupList().contains(group))
                contact.getGroupList().add(group);
        }

    }

    private void validate() throws PersonalDataNotSetException, PhoneNumberFormatException {

        StringBuilder personalDataErrorMessageStringBuilder = new StringBuilder("Не полные персональные данные: установите");
        boolean incorrectPersonalData = false;

        if (lastNameTextField.getText().isEmpty()){
            personalDataErrorMessageStringBuilder.append(" фамилию");
            incorrectPersonalData = true;

        }else if (nameTextField.getText().isEmpty()){
            personalDataErrorMessageStringBuilder.append(" имя");
            incorrectPersonalData = true;

        }else if (middleNameTextField.getText().isEmpty()){
            personalDataErrorMessageStringBuilder.append(" отчество");
            incorrectPersonalData = true;
        }

        if(incorrectPersonalData)
            throw new PersonalDataNotSetException(personalDataErrorMessageStringBuilder.toString());

        if ((!firstPhoneNumberTextField.getText().trim().isEmpty() && !firstPhoneNumberTextField.getText().trim().matches("\\d*"))
                || (!secondPhoneNumberTextField.getText().trim().isEmpty() && !secondPhoneNumberTextField.getText().matches("\\d*")))
            throw new PhoneNumberFormatException("Телефон должен содержать только цифры.");
    }

    private void createContact(){

        contact.setNotes(notesTextArea.getText());
        contact.setEmail(emailTextField.getText());
        contact.setFirstName(nameTextField.getText());
        contact.setFirstPhoneNumber(firstPhoneNumberTextField.getText());
        contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeFromString(firstPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
        contact.setLastName(lastNameTextField.getText());
        contact.setMiddleName(middleNameTextField.getText());
        contact.setSecondPhoneNumber(secondPhoneNumberTextField.getText());
        contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeFromString(secondPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));

        addContactToGroup(contact, groupCheckListView.getCheckModel().getCheckedItems());

        contactService.addContact(contact);

    }

    private void updateContact(){

        int contactId = contact.getId();

        contact.setNotes(notesTextArea.getText());
        contact.setEmail(emailTextField.getText());
        contact.setFirstName(nameTextField.getText());
        contact.setFirstPhoneNumber(firstPhoneNumberTextField.getText());
        contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeFromString(firstPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
        contact.setLastName(lastNameTextField.getText());
        contact.setMiddleName(middleNameTextField.getText());
        contact.setSecondPhoneNumber(secondPhoneNumberTextField.getText());
        contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeFromString(secondPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));

        addContactToGroup(contact, groupCheckListView.getCheckModel().getCheckedItems());

        contactService.updateContact(contact);

    }

}
