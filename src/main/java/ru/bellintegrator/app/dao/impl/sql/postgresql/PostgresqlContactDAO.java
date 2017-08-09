package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.util.List;

public class PostgresqlContactDAO extends AbstractDAOWithIdGenerator<Contact> {

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

    @Override
    public Contact getById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Contact> getByName(String name) throws DAOException {
        return null;
    }

}
