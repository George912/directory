package ru.bellintegrator.app.dao.factory.impl.file;

import ru.bellintegrator.app.dao.GroupDAO;
import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.impl.file.FileContactDAO;
import ru.bellintegrator.app.dao.ContactDAO;
import ru.bellintegrator.app.dao.impl.file.FileGroupDAO;

/**
 * Created by neste_000 on 19.07.2017.
 */
public class FileDAOFactory extends DAOFactory {

    public static final String FILE = "state";

    @Override
    public ContactDAO getContactDAO() {
        return new FileContactDAO();
    }

    @Override
    public GroupDAO getGroupDAO() {
        return new FileGroupDAO();
    }

}
