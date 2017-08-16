package ru.bellintegrator.app.dao.impl.sql.postgresql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.sql.Connectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.dao.impl.sql.GroupManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostgresqlGroupDAO implements GenericDAO<Group>, Connectable, GroupManager {

    private final static Object monitor = new Object();

    @Override
    public int create(Group group) throws DAOException {
        synchronized (monitor) {
            String query = "{call add_group(?, ?, ?)}";

            try (CallableStatement statement = getConnection().prepareCall(query)) {
                statement.setString(1, group.getName());
                statement.setString(2, group.getNotes());
                statement.setInt(3, group.getOwnerId());

                statement.execute();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }

            return group.getId();
        }
    }

    @Override
    public void delete(Group group) throws DAOException {
        synchronized (monitor) {
            String query = "{call delete_group(?, ?)}";

            try (CallableStatement statement = getConnection().prepareCall(query)) {
                statement.setInt(1, group.getId());
                statement.setInt(2, group.getOwnerId());

                statement.execute();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

    @Override
    public void update(Group group) throws DAOException {
        synchronized (monitor) {
            String query = "{call update_group(?, ?, ?, ?)}";

            try (CallableStatement statement = getConnection().prepareCall(query)) {
                statement.setInt(1, group.getId());
                statement.setString(2, group.getName());
                statement.setString(3, group.getNotes());
                statement.setInt(4, group.getOwnerId());

                statement.execute();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

    @Override
    public List<Group> getAll(int ownerId) throws DAOException {
        synchronized (monitor) {
            String query = "{call get_all_groups(?)}";
            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<List<Group>> handler = new BeanListHandler<>(Group.class);
                return runner.execute(connection, query, handler, ownerId).get(0);

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

    @Override
    public Group getById(int id, int ownerId) throws DAOException {
        synchronized (monitor) {
            String query = "{call get_group_by_id(?, ?)}";
            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<Group> handler = new BeanHandler<>(Group.class);
                return runner.execute(connection, query, handler, id, ownerId).get(0);

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

    @Override
    public List<Group> getByName(String name, int ownerId) throws DAOException {
        synchronized (monitor) {
            String query = "{call get_groups_by_name(?, ?)}";
            QueryRunner runner = new QueryRunner();

            try (Connection connection = getConnection()) {
                ResultSetHandler<List<Group>> handler = new BeanListHandler<>(Group.class);
                return runner.execute(connection, query, handler, name, ownerId).get(0);

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

    @Override
    public void addGroupToContact(Group group, Contact contact) throws DAOException {
        synchronized (monitor) {
            String query = "{call add_group_to_contact(?, ?)}";

            try (CallableStatement statement = getConnection().prepareCall(query)) {
                statement.setInt(1, contact.getId());
                statement.setInt(2, group.getId());

                statement.execute();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

    @Override
    public void deleteGroupFromContact(Group group, Contact contact) throws DAOException {
        synchronized (monitor) {
            String query = "{call delete_group_from_contact(?, ?)}";

            try (CallableStatement statement = getConnection().prepareCall(query)) {
                statement.setInt(1, contact.getId());
                statement.setInt(2, group.getId());

                statement.execute();

            } catch (SQLException e) {
                throw new DAOException("Exception while creating group:" + e);
            }
        }
    }

}
