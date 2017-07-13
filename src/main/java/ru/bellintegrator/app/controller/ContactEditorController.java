package ru.bellintegrator.app.controller;

import ru.bellintegrator.app.data.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import org.controlsfx.control.CheckListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.util.Util;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class ContactEditorController {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(ContactEditorController.class);

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
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

    Contact contact;
    private Stage dialogStage;
    private DataManager dataManager;
    private EditorAction editorAction;

    //</editor-fold>

    //<editor-fold desc="методы получения и установки">

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;

        lastNameTextField.setText(contact.getLastName());
        nameTextField.setText(contact.getFirstName());
        middleNameTextField.setText(contact.getMiddleName());
        firstPhoneNumberTypeComboBox.getSelectionModel().select(Util.getStringFromPhoneNumberType(contact.getFirstPhoneNumberType()));
        firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
        secondPhoneNumberTypeComboBox.getSelectionModel().select(Util.getStringFromPhoneNumberType(contact.getSecondPhoneNumberType()));
        secondPhoneNumberTextField.setText(contact.getSecondPhoneNumber());
        emailTextField.setText(contact.getEmail());
        notesTextArea.setText(contact.getNotes());

        for (Group group : contact.getGroupList()){
            System.out.println(group);
            groupCheckListView.getSelectionModel().select(group);
        }


    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public EditorAction getEditorAction() {
        return editorAction;
    }

    public void setEditorAction(EditorAction editorAction) {
        this.editorAction = editorAction;
    }

    //</editor-fold>

    public ContactEditorController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void initialize() {
        firstPhoneNumberTypeComboBox.setItems(preparePhoneNumberTypeComboBoxData(PhoneNumberType.values()));
        secondPhoneNumberTypeComboBox.setItems(preparePhoneNumberTypeComboBoxData(PhoneNumberType.values()));

        groupCheckListView.setItems(dataManager.getGroupObservableList());
        groupCheckListView.getSelectionModel().selectionModeProperty().setValue(SelectionMode.MULTIPLE);

    }

    @FXML
    private void saveButtonClick() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        int contactId = contact.getId();

        switch (editorAction) {
            case CREATE:

                contact.setNotes(notesTextArea.getText());
                contact.setEmail(emailTextField.getText());
                contact.setFirstName(nameTextField.getText());
                contact.setFirstPhoneNumber(firstPhoneNumberTextField.getText());
                contact.setFirstPhoneNumberType(Util.getPhoneNumberTypeFromString(firstPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
                contact.setLastName(lastNameTextField.getText());
                contact.setMiddleName(middleNameTextField.getText());
                contact.setSecondPhoneNumber(secondPhoneNumberTextField.getText());
                contact.setSecondPhoneNumberType(Util.getPhoneNumberTypeFromString(secondPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));

                contact.getGroupList().addAll(groupCheckListView.getSelectionModel().getSelectedItems());

                contactObservableList.add(contact);

                break;

            case UPDATE:
                System.out.println(groupCheckListView.getSelectionModel().getSelectedItems().size());


                for (Group group: groupCheckListView.getSelectionModel().getSelectedItems())
                    System.out.println(group);

                for (int i = 0; i < contactObservableList.size(); i++) {
                    Contact editableContact = contactObservableList.get(i);

                    if (editableContact.getId() == contactId) {
                        editableContact.setNotes(notesTextArea.getText());
                        editableContact.setEmail(emailTextField.getText());
                        editableContact.setFirstName(nameTextField.getText());
                        editableContact.setFirstPhoneNumber(firstPhoneNumberTextField.getText());
                        editableContact.setFirstPhoneNumberType(Util.getPhoneNumberTypeFromString(firstPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
                        editableContact.setLastName(lastNameTextField.getText());
                        editableContact.setMiddleName(middleNameTextField.getText());
                        editableContact.setSecondPhoneNumber(secondPhoneNumberTextField.getText());
                        editableContact.setSecondPhoneNumberType(Util.getPhoneNumberTypeFromString(secondPhoneNumberTypeComboBox.getSelectionModel().getSelectedItem()));
                        editableContact.getGroupList().addAll(groupCheckListView.getSelectionModel().getSelectedItems());
                    }
                }

                break;
        }

        dialogStage.close();

    }

    @FXML
    private void cancelButtonClick() {

        dialogStage.close();

    }

    private ObservableList<String> preparePhoneNumberTypeComboBoxData(PhoneNumberType[] phoneNumberTypes) {

        ObservableList<String> stringObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < phoneNumberTypes.length; i++) {
            stringObservableList.add(Util.getStringFromPhoneNumberType(phoneNumberTypes[i]));
        }

        return stringObservableList;

    }

}
