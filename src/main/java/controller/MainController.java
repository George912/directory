package controller;

import data.DataManager;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.Contact;
import model.Group;
import model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //<editor-fold desc="поля">

    @FXML
    private TableView contactTableView;
    @FXML
    private TableView groupTableView;
    @FXML
    private ComboBox groupComboBox;
    @FXML
    private ImageView addContactImageView;
    @FXML
    private ImageView editContactImageView;
    @FXML
    private ImageView deleteContactImageView;
    @FXML
    private ImageView addGroupImageView;
    @FXML
    private ImageView editGroupImageView;
    @FXML
    private ImageView deleteGroupImageView;
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
    private DataManager dataManager;

    //</editor-fold>

    public MainController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void createContact() {

        int id = dataManager.getContactObservableList().get(dataManager.getContactObservableList().size() - 1).getId() + 1;
        //получить инфу из UI sgsdfg
        dataManager.getContactObservableList().add(new Contact(id, "", "", "", "", PhoneNumberType.MOBILE, "", PhoneNumberType.MOBILE, "", ""));

    }

    @FXML
    private void editContact() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        //получить объект из UI
        Contact contact = new Contact();

        int editableContactId = contact.getId();

        for (int i = 0; i < contactObservableList.size(); i++) {
            Contact editableContact = contactObservableList.get(i);

            //получить инфу из UI sgsdfg
            if (editableContact.getId() == editableContactId) {
                editableContact.setNotes("");
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

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        //получить объект из UI
        Contact contact = new Contact();

        int deletableContactId = contact.getId();

        for (int i = 0; i < contactObservableList.size(); i++) {
            Contact deletableContact = contactObservableList.get(i);

            if (deletableContact.getId() == deletableContactId)
                contactObservableList.remove(i);
        }

    }

    @FXML
    private void createGroup() {

        int id = ((Group) dataManager.getGroupObservableSet().toArray()[dataManager.getGroupObservableSet().toArray().length - 1]).getId() + 1;
        //получить инфу из UI sgsdfg
        dataManager.getGroupObservableSet().add(new Group(id, "", ""));

    }

    @FXML
    private void editGroup() {

        ObservableSet<Group> groupObservableSet = dataManager.getGroupObservableSet();

        //получить объект из UI
        Group group = new Group();

        int editableGroupId = group.getId();

        Iterator<Group> groupIterator = groupObservableSet.iterator();

        while (groupIterator.hasNext()) {
            Group editableGroup = groupIterator.next();

            //получить инфу из UI sgsdfg
            if (editableGroup.getId() == editableGroupId) {
                editableGroup.setName("");
                editableGroup.setNotes("");
            }
        }

    }

    @FXML
    private void deleteGroup() {

        ObservableSet<Group> groupObservableSet = dataManager.getGroupObservableSet();

        //получить объект из UI
        Group group = new Group();

        int editableGroupId = group.getId();

        Iterator<Group> groupIterator = groupObservableSet.iterator();

        while (groupIterator.hasNext()) {
            Group deletableGroup = groupIterator.next();

            //получить инфу из UI sgsdfg
            if (deletableGroup.getId() == editableGroupId)
                groupObservableSet.remove(deletableGroup);
        }

    }

}
