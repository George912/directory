package ru.bellintegrator.app.state.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.data.DataManager;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.state.ISaveToStore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neste_000 on 13.07.2017.
 */
public class Saver implements ISaveToStore {

    //<editor-fold desc="поля">

    private static final Logger log = LoggerFactory.getLogger(Saver.class);
    private DataManager dataManager;

    //</editor-fold>

    @Override
    public void save() {

        dataManager = DataManager.getInstance();

        try (FileOutputStream fileOutputStream = new FileOutputStream("state");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            log.debug("serialization: " + dataManager.getContactObservableList());
            log.debug("serialization: " + dataManager.getGroupObservableList());

            List<Contact> contacts = new ArrayList<>();
            List<Group> groups = new ArrayList<>();

            contacts.addAll(dataManager.getContactObservableList());
            groups.addAll(dataManager.getGroupObservableList());

            objectOutputStream.writeObject(contacts);
            objectOutputStream.writeObject(groups);

            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            log.debug(e.getMessage());

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

    }

}
