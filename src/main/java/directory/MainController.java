package directory;

import data.DataManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Contact;
import model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //<editor-fold desc="поля">

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label messageLabel;
    private DataManager dataManager;

    //</editor-fold>

    public MainController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void createContact() {
        int id = dataManager.getContactObservableList().get(dataManager.getContactObservableList().size() - 1).getId() + 1;
        dataManager.getContactObservableList().add(new Contact(id, "", "", "", "", PhoneNumberType.MOBILE, "", PhoneNumberType.MOBILE, "", ""))
    }

    @FXML
    private void editContact() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        //получить инфу и объект из UI
        Contact contact = new Contact();

        int editableContactId = contact.getId();

        for (int i = 0; i < contactObservableList.size(); i++) {
            Contact editableContact = contactObservableList.get(i);

            //получить инфу и объект из UI sgsdfg
            if (editableContact.getId() == editableContactId) {
                editableContact.setDescription("");
                editableContact.setEmail("");
                editableContact.setFirstName("");
                editableContact.setFirstPhoneNumber("");
                editableContact.setFirstPhoneNumberType(PhoneNumberType.MOBILE);
                editableContact.setLastName("");
                editableContact.setMiddleName("");
                editableContact.setSecondPhoneNumber("");
                editableContact.setSecondPhoneNumberType(PhoneNumberType.MOBILE);
            }
        }
    }

    @FXML
    private void deleteContact() {

    }

    @FXML
    private void createGroup() {

    }

    @FXML
    private void editGroup() {

    }

    @FXML
    private void deleteGroup() {

    }

}
