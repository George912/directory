package ru.bellintegrator.app.viewmodel;

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
import ru.bellintegrator.app.MainApp;
import ru.bellintegrator.app.Mode;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;
import ru.bellintegrator.app.util.ConfigLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainViewModel extends AbstractViewModel {

    private static final Logger log = LoggerFactory.getLogger(MainViewModel.class);
    private User user;
    private ContactService contactService;
    private GroupService groupService;
    private Mode mode;
    private ConfigLoader configLoader;

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
    private TextField contactSearchField;

    public MainViewModel(ContactService contactService, GroupService groupService) {
        this.contactService = contactService;
        this.groupService = groupService;
        configLoader = ConfigLoader.getInstance();
    }

    public MainViewModel(ContactService contactService, GroupService groupService, Mode mode) {
        this(contactService, groupService);
        this.mode = mode;
    }

    public MainViewModel(ContactService contactService, GroupService groupService, Mode mode, User user) {
        this(contactService, groupService, mode);
        this.user = user;
    }

    @FXML
    private void initialize() {
        initContactTableView();
        initGroupTableView();
        initCheckListView();
        initPhoneNumberTypeComboBoxes();
        initGroupCheckListView();
        changeAppMode(mode);
    }

    @FXML
    private void createContact() {

        showContactEditor(new Contact(), EditorAction.CREATE);

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

        try {
            contactObservableList.addAll(contactService.getAllContacts(user.getId()));
            contactTableView.setItems(contactObservableList);

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    @FXML
    private void editContact() {

        Contact contact = contactTableView.getSelectionModel().getSelectedItem();

        if (contact != null) {
            showContactEditor(contact, EditorAction.UPDATE);
            ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
            try {
                contactObservableList.addAll(contactService.getAllContacts(user.getId()));
                contactTableView.getItems().clear();
                contactTableView.setItems(contactObservableList);

            } catch (DAOException e) {
                showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
            }

        } else {
            showAlert("Редактирование контакта", "Не выбран контакт для редактирования.", "Выберите контакт в таблице и нажмите кнопку редактирования.");
        }

    }

    @FXML
    private void deleteContact() {

        Contact contact = contactTableView.getSelectionModel().getSelectedItem();

        if (contact != null) {
            log.debug("deleteContact method. Contact = " + contact);
            try {
                contactService.deleteContact(contact);

                ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
                contactObservableList.addAll(contactService.getAllContacts(user.getId()));
                contactTableView.setItems(contactObservableList);

            } catch (DAOException e) {
                showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
            }

        } else {
            showAlert("Удаление контакта", "Не выбран контакт для удаления.", "Выберите контакт в таблице и нажмите кнопку удаления.");
        }

    }

    @FXML
    private void createGroup() {

        showGroupEditor(new Group(), EditorAction.CREATE);
        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

        try {
            groupObservableList.addAll(groupService.getAllGroups(user.getId()));

            groupTableView.setItems(groupObservableList);
            checkListView.setItems(groupObservableList);
            groupCheckListView.setItems(groupObservableList);

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    @FXML
    private void editGroup() {

        Group group = groupTableView.getSelectionModel().getSelectedItem();

        if (group != null) {
            showGroupEditor(group, EditorAction.UPDATE);
            ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
            try {
                groupObservableList.addAll(groupService.getAllGroups(user.getId()));
                groupTableView.getItems().clear();
                groupTableView.setItems(groupObservableList);
                checkListView.setItems(groupObservableList);
                groupCheckListView.setItems(groupObservableList);

            } catch (DAOException e) {
                showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
            }

        } else {
            showAlert("Редактирование группы", "Не выбрана группа для редактирования.", "Выберите группу в таблице и нажмите кнопку редактирования.");
        }

    }

    @FXML
    private void deleteGroup() {

        Group group = groupTableView.getSelectionModel().getSelectedItem();

        if (group != null) {
            log.debug("deleteGroup method. Group = " + group);
            try {
                groupService.deleteGroup(group, user.getId());
                ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

                groupObservableList.addAll(groupService.getAllGroups(user.getId()));
                groupTableView.setItems(groupObservableList);
                checkListView.setItems(groupObservableList);
                groupCheckListView.setItems(groupObservableList);

            } catch (DAOException e) {
                showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
            }

        } else {
            showAlert("Удаление группы", "Не выбрана группа для удаления.", "Выберите группу в таблице и нажмите кнопку удаления.");
        }

    }

    @FXML
    private void searchContact() {
        ObservableList<Contact> items = FXCollections.observableArrayList();

        try {
            items.addAll(contactService.getContactsByName(contactSearchField.getText(), user.getId()));
            contactTableView.setItems(items);

        } catch (DAOException e) {
            log.debug("Exception while contact searching: " + e);
        }
    }

    private void showContactEditor(Contact contact, EditorAction editorAction) {
        ContactEditorViewModel contactEditorController = new ContactEditorViewModel(contactService, groupService, user);

        log.debug("showContactEditor method. Action = " + editorAction + ", contact = " + contact);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(contactEditorController);
            loader.setLocation(MainApp.class.getResource(configLoader.getFxmlContactEditorWindowPath()));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page, 400, 350);
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
        GroupEditorViewModel groupEditorController = new GroupEditorViewModel(groupService);

        log.debug("showGroupEditor method. Action = " + editorAction + ", group = " + group);

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setController(groupEditorController);
            loader.setLocation(MainApp.class.getResource(configLoader.getFxmlGroupEditorWindowPath()));

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);

            GridPane page = loader.load();

            groupEditorController.setDialogStage(dialogStage);
            groupEditorController.setGroup(group);
            groupEditorController.setEditorAction(editorAction);

            Scene scene = new Scene(page, 380, 200);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            dialogStage.showAndWait();

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

    }

    private void findContactByGroup(List<Group> groupList, List<Contact> contactList) {

        Set<Contact> contactSet = new HashSet<>();

        log.debug("findContactByGroup method. Group list = " + groupList);

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

        try {
            groupObservableList.addAll(groupService.getAllGroups(user.getId()));
            groupCheckListView.setItems(groupObservableList);
            groupCheckListView.getCheckModel().clearChecks();

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    private void initContactTableView() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

        try {
            contactObservableList.addAll(contactService.getAllContacts(user.getId()));
            log.debug("initContactTableView method. Items = " + contactObservableList);

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

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }


    }

    private void initGroupTableView() {

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

        try {
            groupObservableList.addAll(groupService.getAllGroups(user.getId()));

            log.debug("initGroupTableView method. Items = " + groupObservableList);

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

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    private void initCheckListView() {

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

        try {
            groupObservableList.addAll(groupService.getAllGroups(user.getId()));

            log.debug("initCheckListView method. Items = " + groupObservableList);

            checkListView.setItems(groupObservableList);

            checkListView.getCheckModel().getCheckedItems().addListener((ListChangeListener<Group>) c -> {
                contactTableView.getItems().clear();
                try {
                    findContactByGroup(checkListView.getCheckModel().getCheckedItems(), contactService.getAllContacts(user.getId()));

                } catch (DAOException e) {
                    showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
                }
            });

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }


    }

    private void initPhoneNumberTypeComboBoxes() {

        String[] phoneNumberTypes = new String[PhoneNumberType.values().length];

        for (int i = 0; i < phoneNumberTypes.length; i++) {
            phoneNumberTypes[i] = PhoneNumberType.values()[i].getName();
        }

        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        stringObservableList.addAll(Arrays.asList(phoneNumberTypes));

        log.debug("initPhoneNumberTypeComboBoxes method. Items = " + stringObservableList);

        firstPhoneNumberTypeComboBox.setItems(stringObservableList);
        secondPhoneNumberTypeComboBox.setItems(stringObservableList);

    }

    private void initGroupCheckListView() {

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
        try {
            groupObservableList.addAll(groupService.getAllGroups(user.getId()));
            log.debug("initGroupCheckListView method. Items = " + groupObservableList);

            groupCheckListView.setItems(groupObservableList);

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    private void changeAppMode(Mode mode) {
        switch (mode) {
            case READ_ONLY:
                turnOnReadOnlyMode(true);
                break;

            case READ_WRITE:
                turnOnReadOnlyMode(false);
                break;
        }
    }

    private void turnOnReadOnlyMode(boolean b) {
        addContactImageView.setDisable(b);
        editContactImageView.setDisable(b);
        deleteContactImageView.setDisable(b);

        addGroupImageView.setDisable(b);
        editGroupImageView.setDisable(b);
        deleteGroupImageView.setDisable(b);
    }

}
