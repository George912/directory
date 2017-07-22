package ru.bellintegrator.app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.bellintegrator.app.ContactListChangeObserver;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.service.ContactService;
import ru.bellintegrator.app.service.GroupService;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class AdditionalController implements ContactListChangeObserver {

    @FXML
    private TableView<Contact> contactTableView;
    @FXML
    private TableColumn<Contact, String> lastNameTableColumn;
    @FXML
    private TableColumn<Contact, String> nameTableColumn;
    @FXML
    private TableColumn<Contact, String> middleNameTableColumn;
    @FXML
    private TableColumn<Contact, String> firstPhoneNumberTableColumn;
    @FXML
    private TableColumn<Contact, String> firstPhoneNumberTypeTableColumn;
    @FXML
    private TableColumn<Contact, String> secondPhoneNumberTableColumn;
    @FXML
    private TableColumn<Contact, String> secondPhoneNumberTypeTableColumn;
    @FXML
    private TableColumn<Contact, String> emailTableColumn;
    @FXML
    private TableColumn<Contact, String> notesTableColumn;
    @FXML
    private TableColumn<Contact, String> groupsTableColumn;

    private ContactService contactService;
    private GroupService groupService;

    public AdditionalController(ContactService contactService, GroupService groupService) {

        this.contactService = contactService;
        this.groupService = groupService;

    }

    @FXML
    private void initialize() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        contactObservableList.addAll(contactService.getAllContacts());
        contactTableView.setItems(contactObservableList);

        lastNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        nameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        middleNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMiddleName()));
        firstPhoneNumberTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstPhoneNumber()));
        firstPhoneNumberTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstPhoneNumberType().getName()));
        secondPhoneNumberTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondPhoneNumber()));
        secondPhoneNumberTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondPhoneNumberType().getName()));
        emailTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        notesTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNotes()));
        groupsTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getContactGroups()));

    }

    //todo
    private String getContactGroups() {
        return "группа";
    }

    @Override
    public void listChanged() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        contactObservableList.addAll(contactService.getAllContacts());
        contactTableView.setItems(contactObservableList);

    }
}
