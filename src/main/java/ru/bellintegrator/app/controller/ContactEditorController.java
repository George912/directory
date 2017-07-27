package ru.bellintegrator.app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.exception.PersonalDataNotSetException;
import ru.bellintegrator.app.exception.PhoneNumberFormatException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.util.Annunciator;
import ru.bellintegrator.app.validation.ContactEditorValidator;

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
    private ContactEditorValidator validator = ContactEditorValidator.getInstance();
    private GroupService groupService;
    private ContactService contactService;

    public void setContact(Contact contact) {
        this.contact = contact;

        lastNameTextField.setText(contact.getLastName());
        nameTextField.setText(contact.getFirstName());
        middleNameTextField.setText(contact.getMiddleName());
        firstPhoneNumberTypeComboBox.getSelectionModel().select(contact.getFirstPhoneNumberType().getName());
        firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
        secondPhoneNumberTypeComboBox.getSelectionModel().select(contact.getSecondPhoneNumberType().getName());
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

    public ContactEditorController(ContactService contactService, GroupService groupService) {

        this.contactService = contactService;
        this.groupService = groupService;

    }

    @FXML
    private void initialize() {

        firstPhoneNumberTypeComboBox.setItems(preparePhoneNumberTypeComboBoxData(PhoneNumberType.values()));
        secondPhoneNumberTypeComboBox.setItems(preparePhoneNumberTypeComboBoxData(PhoneNumberType.values()));

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        try {
            groupObservableList.addAll(groupService.getAllGroups());
            groupCheckListView.setItems(groupObservableList);

        } catch (DAOException e) {
            Annunciator.showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    @FXML
    private void saveButtonClick() {

        try {
            validator.validate(lastNameTextField.getText(), nameTextField.getText(), middleNameTextField.getText(), firstPhoneNumberTextField.getText(), secondPhoneNumberTextField.getText());

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
            stringObservableList.add(phoneNumberTypes[i].getName());
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

    private void createContact() {

        contact.setNotes(notesTextArea.getText());
        contact.setEmail(emailTextField.getText());
        contact.setFirstName(nameTextField.getText());
        contact.setFirstPhoneNumber(firstPhoneNumberTextField.getText());
        contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeDescription(firstPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
        contact.setLastName(lastNameTextField.getText());
        contact.setMiddleName(middleNameTextField.getText());
        contact.setSecondPhoneNumber(secondPhoneNumberTextField.getText());
        contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeDescription(secondPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));

        addContactToGroup(contact, groupCheckListView.getCheckModel().getCheckedItems());

        log.debug("createContact method: " + contact);

        try {
            contactService.addContact(contact);
        } catch (DAOException e) {
            Annunciator.showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    private void updateContact() {

        contact.setNotes(notesTextArea.getText());
        contact.setEmail(emailTextField.getText());
        contact.setFirstName(nameTextField.getText());
        contact.setFirstPhoneNumber(firstPhoneNumberTextField.getText());
        contact.setFirstPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeDescription(firstPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
        contact.setLastName(lastNameTextField.getText());
        contact.setMiddleName(middleNameTextField.getText());
        contact.setSecondPhoneNumber(secondPhoneNumberTextField.getText());
        contact.setSecondPhoneNumberType(PhoneNumberType.getPhoneNumberTypeByTypeDescription(secondPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));

        addContactToGroup(contact, groupCheckListView.getCheckModel().getCheckedItems());

        log.debug("updateContact method: " + contact);

        try {
            contactService.updateContact(contact);
        } catch (DAOException e) {
            Annunciator.showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

}
