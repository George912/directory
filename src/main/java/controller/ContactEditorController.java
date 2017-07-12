package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by neste_000 on 12.07.2017.
 */
public class ContactEditorController {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

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
    private ComboBox<PhoneNumberType> firstPhoneNumberTypeComboBox;
    @FXML
    private ComboBox<PhoneNumberType> secondPhoneNumberTypeComboBox;

    Contact contact;
    private Stage dialogStage;

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
        firstPhoneNumberTypeComboBox.getSelectionModel().select(contact.getFirstPhoneNumberType());
        firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
        secondPhoneNumberTypeComboBox.getSelectionModel().select(contact.getSecondPhoneNumberType());
        secondPhoneNumberTextField.setText(contact.getSecondPhoneNumber());
        emailTextField.setText(contact.getEmail());
        notesTextArea.setText(contact.getNotes());
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //</editor-fold>

    //edit
//    int editableContactId = contact.getId();
//
//    for (int i = 0; i < contactObservableList.size(); i++) {
//        Contact editableContact = contactObservableList.get(i);
//
//        //получить инфу из UI sgsdfg
//        if (editableContact.getId() == editableContactId) {
//            editableContact.setNotes("");
//            editableContact.setEmail("");
//            editableContact.setFirstName("");
//            editableContact.setFirstPhoneNumber("");
//            editableContact.setFirstPhoneNumberType(PhoneNumberType.MOBILE);
//            editableContact.setLastName("");
//            editableContact.setMiddleName("");
//            editableContact.setSecondPhoneNumber("");
//            editableContact.setSecondPhoneNumberType(PhoneNumberType.MOBILE);
//        }
//    }

}
