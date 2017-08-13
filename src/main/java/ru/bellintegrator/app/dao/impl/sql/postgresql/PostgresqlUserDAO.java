package ru.bellintegrator.app.dao.impl.sql.postgresql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.dao.impl.sql.Connectable;
import ru.bellintegrator.app.dao.impl.sql.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostgresqlUserDAO extends AbstractDAOWithIdGenerator<User> implements UserDAO, Connectable {

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
    public List<User> getAll(int ownerId) throws DAOException {
        String query = "{call get_all_users()}";
        List<User> users;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);
            List<List<User>> lists = runner.execute(connection, query, handler, ownerId);
            users = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return users;
    }

    @Override
    public User getById(int id, int ownerId) throws DAOException {
        String query = "{call get_user_by_id(?)}";
        User user;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<User> handler = new BeanHandler<>(User.class);
            List<User> lists = runner.execute(connection, query, handler, id, ownerId);
            user = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return user;
    }

    @Override
    public List<User> getByName(String name, int ownerId) throws DAOException {
        String query = "{call get_user_by_name(?)}";
        List<User> users;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<List<User>> handler = new BeanListHandler<>(User.class);
            List<List<User>> lists = runner.execute(connection, query, handler, name);
            users = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return users;
    }

    @Override
    public User getUserByCredential(String login, String password) throws DAOException {
        String query = "{call get_user_by_credential(?, ?)}";
        User user;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<User> handler = new BeanHandler<>(User.class);
            List<User> lists = runner.execute(connection, query, handler, login, password);
            user = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return user;
    }

    @Override
    public int getUserId(String login, String password) throws DAOException {
        String query = "{call get_user_by_credential(?, ?)}";
        User user;

        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<User> handler = new BeanHandler<>(User.class);
            List<User> lists = runner.execute(connection, query, handler, login, password);
            user = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return user.getId();
    }

}
