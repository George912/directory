package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Group;

import java.util.List;

public class PostgresqlGroupDAO extends AbstractDAOWithIdGenerator<Group> {

    @Override
    public int create(Group group) throws DAOException {
        return 0;
    }

    @Override
    public void delete(Group group) throws DAOException {

    }

    @Override
    public void update(Group group) throws DAOException {

    }

    @Override
    public List<Group> getAll() throws DAOException {
        return null;
    }

    @Override
    public Group getById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Group> getByName(String name) throws DAOException {
        return null;
    }

}
