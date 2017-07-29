package ru.bellintegrator.app.dao.impl.xml.sax;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.util.List;

/**
 * Created by neste_000 on 28.07.2017.
 */
public class SaxContactDAO implements GenericDAO<Contact> {

    @Override
    public int create(Contact contact) throws DAOException {
        return 0;
    }

    @Override
    public void delete(Contact contact) throws DAOException {

    }

    @Override
    public void update(Contact contact) throws DAOException {

    }

    @Override
    public List<Contact> getAll() throws DAOException {
        return null;
    }

    public void save(List<Contact> list) throws DAOException {

    }

    @Override
    public Contact getById(int id) {
        return null;
    }

    @Override
    public List<Contact> getByName(String name) {
        return null;
    }

}
