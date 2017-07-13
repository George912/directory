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

    private DataManager() {}

    public static DataManager getInstance() {

        if (instance == null)
            instance = new DataManager();

        return instance;
    }

}
