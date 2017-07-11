package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import model.Contact;
import model.Group;
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
    private ObservableSet<Group> groupObservableSet = FXCollections.observableSet();
    private ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();
    private static DataManager instance;
    private Map<String, ObservableList<Contact>> contactGroupAssociationMap = new HashMap<String, ObservableList<Contact>>();

    //</editor-fold>

    //<editor-fold desc="Методы получения">

    public ObservableSet<Group> getGroupObservableSet() {
        return groupObservableSet;
    }

    public void setGroupObservableSet(ObservableSet<Group> groupObservableSet) {
        this.groupObservableSet = groupObservableSet;
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
    }

    public static DataManager getInstance() {

        if (instance == null)
            instance = new DataManager();

        return instance;
    }

}
