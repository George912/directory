package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.dao.impl.sql.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

import java.util.List;

public class PostgresqlUserDAO extends AbstractDAOWithIdGenerator<User> implements UserDAO{

    @Override
    public int create(User user) throws DAOException {
        return 0;
    }

    @Override
    public void delete(User user) throws DAOException {

    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public List<User> getAll() throws DAOException {
        return null;
    }

    @Override
    public User getById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<User> getByName(String name) throws DAOException {
        return null;
    }

    @Override
    public boolean isExist(String login, String password) {
        return false;
    }

    @Override
    public int getUserId(String login, String password) {
        return 0;
    }

}
