package ru.bellintegrator.app.dao.factory.impl.file;

import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.file.FileContactDAO;
import ru.bellintegrator.app.dao.impl.file.FileGroupDAO;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.util.Annunciator;
import ru.bellintegrator.app.util.IdGenerator;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class MemoryDAOFactory extends DAOFactory {

    public static final String CONTACT_FILE = "contact";
    public static final String GROUP_FILE = "group";

    @Override
    public GenericDAO<Contact> getContactDAO() {

        FileContactDAO fileContactDAO = new FileContactDAO();
        try {
            fileContactDAO.setIdGenerator(new IdGenerator(fileContactDAO.getAll()));
        } catch (DAOException e) {
            Annunciator.showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

        return fileContactDAO;

    }

    @Override
    public GenericDAO<Group> getGroupDAO() {

        FileGroupDAO fileGroupDAO = new FileGroupDAO();
        try {
            fileGroupDAO.setIdGenerator(new IdGenerator(fileGroupDAO.getAll()));
        } catch (DAOException e) {
            Annunciator.showAlert("Ошибка", "Во время выполнения программы возникла ошибка.", e);
        }

        return fileGroupDAO;

    }

}
