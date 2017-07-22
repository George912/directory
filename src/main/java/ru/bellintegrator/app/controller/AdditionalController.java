package ru.bellintegrator.app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.bellintegrator.app.ContactListChangeObserver;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.service.ContactService;
import ru.bellintegrator.app.dao.service.GroupService;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

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

    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.FILE);
    GenericDAO<Contact> contactGenericDAO = daoFactory.getContactDAO();
    GenericDAO<Group> groupGenericDAO = daoFactory.getGroupDAO();
    ContactService contactService = new ContactService(contactGenericDAO);
    GroupService groupService = new GroupService(groupGenericDAO);

    @FXML
    private void initialize() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        contactObservableList.addAll(contactService.getAllContacts());
        contactTableView.setItems(contactObservableList);

        lastNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        nameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        middleNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMiddleName()));
        firstPhoneNumberTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstPhoneNumber()));
        firstPhoneNumberTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(PhoneNumberType.getStringFromPhoneNumberType(cellData.getValue().getFirstPhoneNumberType())));
        secondPhoneNumberTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondPhoneNumber()));
        secondPhoneNumberTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(PhoneNumberType.getStringFromPhoneNumberType(cellData.getValue().getSecondPhoneNumberType())));
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
        contactTableView.refresh();

    }
}
