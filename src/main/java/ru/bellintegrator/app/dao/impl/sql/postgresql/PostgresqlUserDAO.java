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
import java.util.ArrayList;
import java.util.List;

public class PostgresqlUserDAO extends AbstractDAOWithIdGenerator<User> implements UserDAO, Connectable {

    ConfigLoader configLoader = ConfigLoader.getInstance();

    @Override
    public int create(User user) throws DAOException {
        String query = "{call add_user(?, ?, ?, ?, ?)}";
//        user.setId(generateId());

        try (CallableStatement statement = getConnection().prepareCall(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getMiddleName());
            statement.setString(5, user.getLastName());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception while creating user:" + e);
        }

        return user.getId();
    }

    @Override
    public void delete(User user) throws DAOException {
        String query = "{call delete_user(?)}";

        try (CallableStatement statement = getConnection().prepareCall(query)) {
            statement.setInt(1, user.getId());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception while deleting user:" + e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        String query = "{call update_user(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = getConnection().prepareCall(query)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getMiddleName());
            statement.setString(6, user.getLastName());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception while updating user:" + e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        String query = "{call get_all_users()}";
        List<User> users = new ArrayList<>();

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting all users:" + e);
        }

        return users;
    }

    @Override
    public User getById(int id) throws DAOException {
        String query = "{call get_user_by_id(?)}";
        User user = null;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setInt(1, user.getId());

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting user by id:" + e);
        }

        return user;
    }

    @Override
    public List<User> getByName(String name) throws DAOException {
        String query = "{call get_user_by_name(?)}";
        List<User> users = new ArrayList<>();

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, name);

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting users by name:" + e);
        }

        return users;
    }

    @Override
    public User getUserByCredential(String login, String password) throws DAOException {
        String query = "{call get_user_by_credential(?, ?)}";
        User user = null;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, login);
            statement.setString(2, password);

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting user by credential:" + e);
        }

        return user;
    }

    @Override
    public int getUserId(String login, String password) throws DAOException {
        String query = "{call get_user_by_credential(?, ?)}";
        User user = null;

        try (CallableStatement statement = getConnection().prepareCall(query);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, login);
            statement.setString(2, password);

            //todo: use row mapper
            while (resultSet.next()) {
            }

        } catch (SQLException e) {
            throw new DAOException("Exception while getting user id:" + e);
        }

        return user.getId();
    }

}
