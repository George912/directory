package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.sql.AbstractConnectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;

import java.util.List;

//todo: синхронизация
public class HibernateContactDAO extends AbstractConnectable implements GenericDAO<Contact> {

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
        List<Contact> contacts;
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            contacts = session.createQuery("FROM Contact ").list();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while retrieving contact list: " + e);
        }

        return contacts;
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
