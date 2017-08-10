package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.dao.impl.sql.Connectable;
import ru.bellintegrator.app.dao.impl.sql.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;
import ru.bellintegrator.app.util.ConfigLoader;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresqlUserDAO extends AbstractDAOWithIdGenerator<User> implements UserDAO, Connectable {

    ConfigLoader configLoader = ConfigLoader.getInstance();

    @Override
    public int create(User user) throws DAOException {
//        user.setId(generateId());
        String query = "{call get_all_users()}";

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "");
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while preparing statement:" + e);
        }

        return user.getId();
    }

    @Override
    public void delete(User user) throws DAOException {

    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public List<User> getAll() throws DAOException {
        String query = "{call get_all_users()}";

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "");
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while preparing statement:" + e);
        }

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
