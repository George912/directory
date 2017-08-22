package ru.bellintegrator.app.dao.impl.sql.postgresql;

import ru.bellintegrator.app.dao.impl.sql.AbstractConnectable;
import ru.bellintegrator.app.dao.impl.sql.ContactLinkGroupDao;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class PostgresqlContactLinkGroupDAO extends AbstractConnectable implements ContactLinkGroupDao {

    private final static Object monitor = new Object();

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
