package ru.bellintegrator.app.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by neste_000 on 11.06.2017.
 */
public class DataManager {

    //<editor-fold desc="Поля">

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);
    private ObservableList<Group> groupObservableList = FXCollections.observableArrayList();
    private ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
    private static DataManager instance;

    //</editor-fold>

    //<editor-fold desc="Методы получения">

    public ObservableList<Group> getGroupObservableList() {
        return groupObservableList;
    }

    public void setGroupObservableList(ObservableList<Group> groupObservableList) {
        this.groupObservableList = groupObservableList;
    }

    public ObservableList<Contact> getContactObservableList() {
        return contactObservableList;
    }

    public void setContactObservableList(ObservableList<Contact> contactObservableList) {
        this.contactObservableList = contactObservableList;
    }

    //</editor-fold>

    //<editor-fold desc="Методы">


    //</editor-fold>

    private DataManager() {

//        contactObservableList.add(new Contact(0, "1", "1", "1", "232266", PhoneNumberType.MOBILE, "234234", PhoneNumberType.HOME, "email", "notes"));
//        contactObservableList.add(new Contact(1, "2 ", "2 ", "2 "));
//        groupObservableList.add(new Group(0, "g1", "notes"));
//        groupObservableList.add(new Group(1, "g2", "notes"));

//        initializeData();

    }

    private void initializeData() {

        initializeGroups();
        initializeContacts();

    }

    private void initializeGroups() {
        //load from source
    }

    private void initializeContacts() {
        //load from source
    }

    public static DataManager getInstance() {

        if (instance == null)
            instance = new DataManager();

        return instance;
    }

}
