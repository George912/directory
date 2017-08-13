package ru.bellintegrator.app.dao.impl.sql.postgresql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ru.bellintegrator.app.dao.impl.AbstractDAOWithIdGenerator;
import ru.bellintegrator.app.dao.impl.sql.Connectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostgresqlContactDAO extends AbstractDAOWithIdGenerator<Contact> implements Connectable {

    //todo: idgenerator
    @Override
    public int create(Contact contact) throws DAOException {
        String query = "{call add_contact(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//        user.setId(generateId());

        try (CallableStatement statement = getConnection().prepareCall(query)) {
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getMiddleName());
            statement.setString(3, contact.getLastName());
            statement.setString(4, contact.getFirstPhoneNumber());
            statement.setString(5, contact.getFirstPhoneNumberType().name());
            statement.setString(6, contact.getSecondPhoneNumber());
            statement.setString(7, contact.getSecondPhoneNumberType().name());
            statement.setString(8, contact.getEmail());
            statement.setString(9, contact.getNotes());
            statement.setInt(10, contact.getOwnerId());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return contact.getId();
    }

    @Override
    public void delete(Contact contact) throws DAOException {
        String query = "{call delete_contact(?, ?)}";

        try (CallableStatement statement = getConnection().prepareCall(query)) {
            statement.setInt(1, contact.getId());
            statement.setInt(2, contact.getOwnerId());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }
    }

    @Override
    public void update(Contact contact) throws DAOException {
        String query = "{call update_contact(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = getConnection().prepareCall(query)) {
            statement.setInt(1, contact.getId());
            statement.setString(2, contact.getFirstName());
            statement.setString(3, contact.getMiddleName());
            statement.setString(4, contact.getLastName());
            statement.setString(5, contact.getFirstPhoneNumber());
            statement.setString(6, contact.getFirstPhoneNumberType().name());
            statement.setString(7, contact.getSecondPhoneNumber());
            statement.setString(8, contact.getSecondPhoneNumberType().name());
            statement.setString(9, contact.getEmail());
            statement.setString(10, contact.getNotes());
            statement.setInt(11, contact.getOwnerId());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }
    }

    @Override
    public List<Contact> getAll(int ownerId) throws DAOException {
        String query = "{call get_all_contacts(?)}";
        List<Contact> contacts;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<List<Contact>> handler = new BeanListHandler<>(Contact.class);
            List<List<Contact>> lists = runner.execute(connection, query, handler, ownerId);
            contacts = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return contacts;
    }

    @Override
    public Contact getById(int id, int ownerId) throws DAOException {
        String query = "{call get_contact_by_id(?, ?)}";
        Contact contact;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<Contact> handler = new BeanHandler<>(Contact.class);
            List<Contact> lists = runner.execute(connection, query, handler, id, ownerId);
            contact = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return contact;
    }

    @Override
    public List<Contact> getByName(String name, int ownerId) throws DAOException {
        String query = "{call get_contacts_by_name(?, ?)}";
        List<Contact> contacts;
        QueryRunner runner = new QueryRunner();

        try (Connection connection = getConnection()) {
            ResultSetHandler<List<Contact>> handler = new BeanListHandler<>(Contact.class);
            List<List<Contact>> lists = runner.execute(connection, query, handler, name, ownerId);
            contacts = lists.get(0);

        } catch (SQLException e) {
            throw new DAOException("Exception while creating group:" + e);
        }

        return contacts;
    }

}
