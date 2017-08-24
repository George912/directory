package ru.bellintegrator.app.dao.impl.orm.hibernate;

import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.util.List;

//todo: синхронизация
public class HibernateContactDAO implements GenericDAO<Contact> {

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
    public List<Contact> getAll(int ownerId) throws DAOException {
        return null;
    }

    @Override
    public Contact getById(int id, int ownerId) throws DAOException {
        return null;
    }

    @Override
    public List<Contact> getByName(String name, int ownerId) throws DAOException {
        return null;
    }

}
