package ru.bellintegrator.app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.directory.MainApp;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainController {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML
    private TableView<Contact> contactTableView;
    @FXML
    private TableView<Group> groupTableView;
    @FXML
    private TableColumn<Contact, String> contactTableViewLastNameColumn;
    @FXML
    private TableColumn<Contact, String> contactTableViewNameColumn;
    @FXML
    private TableColumn<Contact, String> contactTableViewMiddleNameColumn;
    @FXML
    private TableColumn<Group, String> groupTableViewGroupNameTableColumn;
    @FXML
    private CheckListView<Group> checkListView;
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
    @FXML
    private CheckListView<Group> groupCheckListView;

    private DataManager dataManager;


    //</editor-fold>

    public MainController() {

        dataManager = DataManager.getInstance();

    }

    @FXML
    private void initialize() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        contactObservableList.addAll(dataManager.getContactObservableList());
        contactTableView.setItems(contactObservableList);
        contactTableViewLastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        contactTableViewNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        contactTableViewMiddleNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMiddleName()));

        contactTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                Contact contact = (Contact) newValue;

                clearContactInfoUIComponents();

                if (contact != null) {
                    lastNameTextField.setText(contact.getLastName());
                    nameTextField.setText(contact.getFirstName());
                    middleNameTextField.setText(contact.getMiddleName());
                    firstPhoneNumberTypeComboBox.getSelectionModel().select(PhoneNumberType.getStringFromPhoneNumberType(contact.getFirstPhoneNumberType()));
                    firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
                    secondPhoneNumberTypeComboBox.getSelectionModel().select(PhoneNumberType.getStringFromPhoneNumberType(contact.getSecondPhoneNumberType()));
                    secondPhoneNumberTextField.setText(contact.getSecondPhoneNumber());
                    emailTextField.setText(contact.getEmail());
                    notesTextArea.setText(contact.getNotes());

                    groupCheckListView.setItems(dataManager.getGroupObservableList());

                    for (Group group : contact.getGroupList())
                        groupCheckListView.getCheckModel().check(group);
                }

            }
        });

        groupTableView.setItems(dataManager.getGroupObservableList());
        groupTableViewGroupNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        groupTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                Group group = (Group) newValue;

                groupNameTextField.setText(group.getName());
                groupNotesTextArea.setText(group.getNotes());
            }
        });

        checkListView.setItems(dataManager.getGroupObservableList());

        checkListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Group>() {
            @Override
            public void onChanged(Change<? extends Group> c) {
                contactTableView.getItems().clear();
                findContactByGroup(checkListView.getCheckModel().getCheckedItems(), dataManager.getContactObservableList());
            }
        });

    }

    @FXML
    private void createContact() {

        int id;

        if (dataManager.getContactObservableList().isEmpty())
            id = 0;

        else
            id = dataManager.getContactObservableList().get(dataManager.getContactObservableList().size() - 1).getId() + 1;

        showContactEditor(new Contact(id, "", "", ""), EditorAction.CREATE);

    }

    @FXML
    private void editContact() {

        //получить объект из UI
        Contact contact = (Contact) contactTableView.getSelectionModel().getSelectedItem();

        if (contact != null)
            showContactEditor(contact, EditorAction.UPDATE);

    }

    @FXML
    private void deleteContact() {

        ObservableList<Contact> contactObservableList = dataManager.getContactObservableList();

        Contact contact = (Contact) contactTableView.getSelectionModel().getSelectedItem();

        if (contact != null) {
            int deletableContactId = contact.getId();

            for (int i = 0; i < contactObservableList.size(); i++) {
                Contact deletableContact = contactObservableList.get(i);

                if (deletableContact.getId() == deletableContactId)
                    contactObservableList.remove(i);
            }

            contactTableView.setItems(contactObservableList);
        }

    }

    @FXML
    private void createGroup() {

        int id;

        if (dataManager.getGroupObservableList().isEmpty())
            id = 0;

        else
            id = ((Group) dataManager.getGroupObservableList().get(dataManager.getGroupObservableList().size() - 1)).getId() + 1;

        showGroupEditor(new Group(id, ""), EditorAction.CREATE);

    }

    @FXML
    private void editGroup() {

        Group group = (Group) groupTableView.getSelectionModel().getSelectedItem();

        if (group != null)
            showGroupEditor(group, EditorAction.UPDATE);

    }

    @FXML
    private void deleteGroup() {

        ObservableList<Group> groupObservableList = dataManager.getGroupObservableList();

        Group group = (Group) groupTableView.getSelectionModel().getSelectedItem();

        if (group != null) {
            int editableGroupId = group.getId();

            Iterator<Group> groupIterator = groupObservableList.iterator();

            while (groupIterator.hasNext()) {
                Group deletableGroup = groupIterator.next();

                if (deletableGroup.getId() == editableGroupId)
                    groupObservableList.remove(deletableGroup);
            }
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

        } catch (IOException e) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void findContactByGroup(ObservableList<Group> groupObservableList, ObservableList<Contact> contactObservableList) {

        Set<Contact> contactSet = new HashSet<>();

        if (groupObservableList.isEmpty()) {
            contactSet.addAll(dataManager.getContactObservableList());
        } else {
            for (Group group : groupObservableList) {
                for (Contact contact : contactObservableList) {
                    if (contact.getGroupList().contains(group))
                        contactSet.add(contact);
                }
            }
        }

        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        contacts.addAll(contactSet);
        contactTableView.setItems(contacts);

    }

    private void clearContactInfoUIComponents() {

        lastNameTextField.clear();
        nameTextField.clear();
        middleNameTextField.clear();
        firstPhoneNumberTypeComboBox.getSelectionModel().clearSelection();
        firstPhoneNumberTextField.clear();
        secondPhoneNumberTypeComboBox.getSelectionModel().clearSelection();
        secondPhoneNumberTextField.clear();
        emailTextField.clear();
        notesTextArea.clear();

        groupCheckListView.setItems(dataManager.getGroupObservableList());
        groupCheckListView.getCheckModel().clearChecks();

    }

}
