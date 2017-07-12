package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Group;
import model.PhoneNumberType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neste_000 on 11.06.2017.
 */
public class DataManager {

    //<editor-fold desc="Поля">

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);
    private ObservableList<Group> groupObservableList;
    private ObservableList<Contact> contactObservableList;
    private static DataManager instance;
    private Map<String, ObservableList<Contact>> contactGroupAssociationMap;

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

    public Map<String, ObservableList<Contact>> getContactGroupAssociationMap() {
        return contactGroupAssociationMap;
    }

    public void setContactGroupAssociationMap(Map<String, ObservableList<Contact>> contactGroupAssociationMap) {
        this.contactGroupAssociationMap = contactGroupAssociationMap;
    }

    //</editor-fold>

    //<editor-fold desc="Методы">


    //</editor-fold>

    private DataManager() {

        groupObservableList = FXCollections.observableArrayList();
        contactObservableList = FXCollections.observableArrayList();
        contactObservableList.add(new Contact(0, "1", "1", "1", "232266", PhoneNumberType.MOBILE, "234234", PhoneNumberType.HOME, "email", "notes"));
        contactObservableList.add(new Contact(1, "2 ", "2 ", "2 "));
        contactGroupAssociationMap = new HashMap<String, ObservableList<Contact>>();
        groupObservableList.add(new Group(0, "g1", "notes"));

        initializeData();

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
