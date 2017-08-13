package ru.bellintegrator.app.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.ContactListChangeObserver;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.service.ContactService;

/**
 * Created by neste_000 on 21.07.2017.
 */
public class AdditionalViewModel extends AbstractViewModel implements ContactListChangeObserver {

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

    private ContactService contactService;

    private static final Logger log = LoggerFactory.getLogger(AdditionalViewModel.class);
    private User user;

    public AdditionalViewModel(ContactService contactService) {
        this.contactService = contactService;
    }

    public AdditionalViewModel(ContactService contactService, User user) {
        this(contactService);
        this.user = user;
    }

    @FXML
    private void initialize() {

        initializeContactTableView();

    }

    @Override
    public void listChanged() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

        try {
            contactObservableList.addAll(contactService.getAllContacts(user.getId()));

            log.debug("listChanged method. Items = " + contactObservableList);

            contactTableView.getItems().clear();
            contactTableView.setItems(contactObservableList);

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

    private void initializeContactTableView() {

        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
        try {
            contactObservableList.addAll(contactService.getAllContacts(user.getId()));
            log.debug("initializeContactTableView method. Items = " + contactObservableList);

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

        } catch (DAOException e) {
            showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

    }

}
