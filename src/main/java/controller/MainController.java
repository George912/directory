package controller;

import data.DataManager;
import directory.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Contact;
import model.Group;
import model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

import java.io.IOException;
import java.util.Iterator;

public class MainController {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML
    private TableView contactTableView;
    @FXML
    private TableView groupTableView;
    @FXML
    private TableColumn<Contact, String> contactTableViewLastNameColumn;
    @FXML
    private TableColumn<Contact, String> contactTableViewNameColumn;
    @FXML
    private TableColumn<Contact, String> contactTableViewMiddleNameColumn;
    @FXML
    private TableColumn<Group, String> groupTableViewGroupNameTableColumn;
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
    @FXML
    private ComboBox<String> firstPhoneNumberTypeComboBox;
    @FXML
    private ComboBox<String> secondPhoneNumberTypeComboBox;

    private DataManager dataManager;


    //</editor-fold>

    public MainController() {
        dataManager = DataManager.getInstance();
    }

    @FXML
    private void initialize() {

        contactTableView.setItems(dataManager.getContactObservableList());
        contactTableViewLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        contactTableViewNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        contactTableViewMiddleNameColumn.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());

        contactTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                Contact contact = (Contact) newValue;

                lastNameTextField.setText(contact.getLastName());
                nameTextField.setText(contact.getFirstName());
                middleNameTextField.setText(contact.getMiddleName());
                firstPhoneNumberTypeComboBox.getSelectionModel().select(Util.getStringFromPhoneNumberType(contact.getFirstPhoneNumberType()));
                firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
                secondPhoneNumberTypeComboBox.getSelectionModel().select(Util.getStringFromPhoneNumberType(contact.getSecondPhoneNumberType()));
                secondPhoneNumberTextField.setText(contact.getSecondPhoneNumber());
                emailTextField.setText(contact.getEmail());
                notesTextArea.setText(contact.getNotes());

            }
        });

        groupTableView.setItems(dataManager.getGroupObservableList());
        groupTableViewGroupNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        groupTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                Group group = (Group) newValue;

                groupNameTextField.setText(group.getName());
                groupNotesTextArea.setText(group.getNotes());
            }
        });

    }

    @FXML
    private void createContact() {

        int id = dataManager.getContactObservableList().get(dataManager.getContactObservableList().size() - 1).getId() + 1;

        showContactEditor(new Contact(id, "", "", ""), EditorAction.CREATE);

    }

    @FXML
    private void editContact() {

        //получить объект из UI
        Contact contact = (Contact) contactTableView.getSelectionModel().getSelectedItem();

        showContactEditor(contact, EditorAction.UPDATE);

    }

    @FXML
    private void deleteContact() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        Contact contact = (Contact) contactTableView.getSelectionModel().getSelectedItem();

        int deletableContactId = contact.getId();

        for (int i = 0; i < contactObservableList.size(); i++) {
            Contact deletableContact = contactObservableList.get(i);

            if (deletableContact.getId() == deletableContactId)
                contactObservableList.remove(i);
        }

    }

    @FXML
    private void createGroup() {

        int id = ((Group) dataManager.getGroupObservableList().get(dataManager.getGroupObservableList().size() - 1)).getId() + 1;

        showGroupEditor(new Group(id, ""), EditorAction.CREATE);

    }

    @FXML
    private void editGroup() {

        Group group = (Group) groupTableView.getSelectionModel().getSelectedItem();

        showGroupEditor(group, EditorAction.UPDATE);

    }

    @FXML
    private void deleteGroup() {

        ObservableList<Group> groupObservableList = dataManager.getGroupObservableList();

        Group group = (Group) groupTableView.getSelectionModel().getSelectedItem();

        int editableGroupId = group.getId();

        Iterator<Group> groupIterator = groupObservableList.iterator();

        while (groupIterator.hasNext()) {
            Group deletableGroup = groupIterator.next();

            if (deletableGroup.getId() == editableGroupId)
                groupObservableList.remove(deletableGroup);
        }

    }

    private void showContactEditor(Contact contact, EditorAction editorAction) {

        String stageTitle = "";
        String fxmlPath = "/fxml/contact_editor.fxml";
        FXMLLoader loader = null;
        GridPane page = null;
        Stage dialogStage = null;
        Scene scene = null;
        ContactEditorController contactEditorController = null;

        try {

            loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            page = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            scene = new Scene(page);
            dialogStage.setScene(scene);

            contactEditorController = loader.getController();
            contactEditorController.setDialogStage(dialogStage);
            contactEditorController.setContact(contact);
            contactEditorController.setEditorAction(editorAction);

            dialogStage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void showGroupEditor(Group group, EditorAction editorAction) {

        String stageTitle = "";
        String fxmlPath = "/fxml/group_editor.fxml";
        FXMLLoader loader = null;
        GridPane page = null;
        Stage dialogStage = null;
        Scene scene = null;
        GroupEditorController groupEditorController = null;

        try {

            loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            page = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            scene = new Scene(page);
            dialogStage.setScene(scene);

            groupEditorController = loader.getController();
            groupEditorController.setDialogStage(dialogStage);
            groupEditorController.setGroup(group);
            groupEditorController.setEditorAction(editorAction);

            dialogStage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
