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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.MainApp;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;

import java.io.IOException;
import java.util.*;

public class MainController{

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

    private ContactService contactService;
    private GroupService groupService;

    public MainController(ContactService contactService, GroupService groupService) {

        this.contactService = contactService;
        this.groupService = groupService;

    }

    @FXML
    private void initialize() {

        initContactTableView();

        initGroupTableView();

        initCheckListView();

        initPhoneNumberTypeComboBoxes();

        initGroupCheckListView();

    }

    @FXML
    private void createContact() {

        showContactEditor(new Contact(), EditorAction.CREATE);

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        contactObservableList.addAll(contactService.getAllContacts());

        contactTableView.setItems(contactObservableList);

    }

    @FXML
    private void editContact() {

        Contact contact = contactTableView.getSelectionModel().getSelectedItem();

        if (contact != null) {
            showContactEditor(contact, EditorAction.UPDATE);
            ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
            contactObservableList.addAll(contactService.getAllContacts());
            contactTableView.getItems().clear();
            contactTableView.setItems(contactObservableList);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Редактирование контакта");
            alert.setHeaderText("Не выбран контакт для редактирования.");
            alert.setContentText("Выберите контакт в таблице и нажмите кнопку редактирования.");
            alert.showAndWait();
        }

    }

    @FXML
    private void deleteContact() {

        Contact contact = contactTableView.getSelectionModel().getSelectedItem();

        if (contact != null) {
            contactService.deleteContact(contact);
            ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
            contactObservableList.addAll(contactService.getAllContacts());
            contactTableView.setItems(contactObservableList);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Удаление контакта");
            alert.setHeaderText("Не выбран контакт для удаления.");
            alert.setContentText("Выберите контакт в таблице и нажмите кнопку удаления.");
            alert.showAndWait();
        }

    }

    @FXML
    private void createGroup() {

        showGroupEditor(new Group(), EditorAction.CREATE);
        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        groupObservableList.addAll(groupService.getAllGroups());
        groupTableView.setItems(groupObservableList);
        checkListView.setItems(groupObservableList);
        groupCheckListView.setItems(groupObservableList);

    }

    @FXML
    private void editGroup() {

        Group group = groupTableView.getSelectionModel().getSelectedItem();

        if (group != null) {
            showGroupEditor(group, EditorAction.UPDATE);
            ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
            groupObservableList.addAll(groupService.getAllGroups());
            groupTableView.getItems().clear();
            groupTableView.setItems(groupObservableList);
            checkListView.setItems(groupObservableList);
            groupCheckListView.setItems(groupObservableList);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Редактирование группы");
            alert.setHeaderText("Не выбрана группа для редактирования.");
            alert.setContentText("Выберите группу в таблице и нажмите кнопку редактирования.");
            alert.showAndWait();
        }


    }

    @FXML
    private void deleteGroup() {

        Group group = groupTableView.getSelectionModel().getSelectedItem();

        if (group != null) {
            groupService.deleteGroup(group);
            ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
            groupObservableList.addAll(groupService.getAllGroups());
            groupTableView.setItems(groupObservableList);
            checkListView.setItems(groupObservableList);
            groupCheckListView.setItems(groupObservableList);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Удаление группы");
            alert.setHeaderText("Не выбрана группа для удаления.");
            alert.setContentText("Выберите группу в таблице и нажмите кнопку удаления.");
            alert.showAndWait();
        }

    }

    private void showContactEditor(Contact contact, EditorAction editorAction) {

        String stageTitle = "";
        String fxmlPath = "/fxml/contact_editor.fxml";
        FXMLLoader loader = null;
        GridPane page = null;
        Stage dialogStage = null;
        Scene scene = null;
        ContactEditorController contactEditorController = new ContactEditorController(contactService, groupService);

        try {
            loader = new FXMLLoader();
            loader.setController(contactEditorController);
            loader.setLocation(MainApp.class.getResource(fxmlPath));
            page = loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            scene = new Scene(page, 400, 330);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            contactEditorController.setDialogStage(dialogStage);
            contactEditorController.setContact(contact);
            contactEditorController.setEditorAction(editorAction);

            dialogStage.showAndWait();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

    }

    private void showGroupEditor(Group group, EditorAction editorAction) {

        String stageTitle = "";
        String fxmlPath = "/fxml/group_editor.fxml";
        FXMLLoader loader = null;
        GridPane page = null;
        Stage dialogStage = null;
        Scene scene = null;
        GroupEditorController groupEditorController = new GroupEditorController(groupService);

        try {

            loader = new FXMLLoader();
            loader.setController(groupEditorController);
            loader.setLocation(MainApp.class.getResource(fxmlPath));

            dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            page = loader.load();

            groupEditorController.setDialogStage(dialogStage);
            groupEditorController.setGroup(group);
            groupEditorController.setEditorAction(editorAction);

            scene = new Scene(page, 380, 200);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            dialogStage.showAndWait();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

    }

    private void findContactByGroup(List<Group> groupList, List<Contact> contactList) {

        Set<Contact> contactSet = new HashSet<>();

        if (groupList.isEmpty()) {
            contactSet.addAll(contactList);
        } else {
            for (Group group : groupList) {
                for (Contact contact : contactList) {
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

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        groupObservableList.addAll(groupService.getAllGroups());

        groupCheckListView.setItems(groupObservableList);
        groupCheckListView.getCheckModel().clearChecks();

    }

    private void initContactTableView() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        contactObservableList.addAll(contactService.getAllContacts());
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
                    firstPhoneNumberTypeComboBox.getSelectionModel().select(contact.getFirstPhoneNumberType().getName());
                    firstPhoneNumberTextField.setText(contact.getFirstPhoneNumber());
                    secondPhoneNumberTypeComboBox.getSelectionModel().select(contact.getSecondPhoneNumberType().getName());
                    secondPhoneNumberTextField.setText(contact.getSecondPhoneNumber());
                    emailTextField.setText(contact.getEmail());
                    notesTextArea.setText(contact.getNotes());

                    for (Group group : contact.getGroupList()) {
                        groupCheckListView.getCheckModel().check(group);
                    }

                    groupCheckListView.refresh();
                }

            }
        });

    }

    private void initGroupTableView() {

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        groupObservableList.addAll(groupService.getAllGroups());
        groupTableView.setItems(groupObservableList);
        groupTableViewGroupNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        groupTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                Group group = (Group) newValue;

                if (group != null) {
                    groupNameTextField.setText(group.getName());
                    groupNotesTextArea.setText(group.getNotes());
                }
            }
        });

    }

    private void initCheckListView() {

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        groupObservableList.addAll(groupService.getAllGroups());

        checkListView.setItems(groupObservableList);

        checkListView.getCheckModel().getCheckedItems().addListener((ListChangeListener<Group>) c -> {
            contactTableView.getItems().clear();
            findContactByGroup(checkListView.getCheckModel().getCheckedItems(), contactService.getAllContacts());
        });

    }

    private void initPhoneNumberTypeComboBoxes() {

        String[] phoneNumberTypes = new String[PhoneNumberType.values().length];

        for (int i = 0; i < phoneNumberTypes.length; i++) {
            phoneNumberTypes[i] = PhoneNumberType.values()[i].getName();
        }

        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        stringObservableList.addAll(Arrays.asList(phoneNumberTypes));

        firstPhoneNumberTypeComboBox.setItems(stringObservableList);
        secondPhoneNumberTypeComboBox.setItems(stringObservableList);

    }

    private void initGroupCheckListView() {

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        groupObservableList.addAll(groupService.getAllGroups());

        groupCheckListView.setItems(groupObservableList);

    }

}
