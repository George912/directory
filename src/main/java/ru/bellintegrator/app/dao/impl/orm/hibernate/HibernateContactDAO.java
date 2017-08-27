package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.AbstractConnectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.User;

import java.util.List;

//todo: синхронизация
public class HibernateContactDAO extends AbstractConnectable implements GenericDAO<Contact> {

    @Override
    public int create(Contact contact) throws DAOException {
        Transaction transaction = null;
        int contactId;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            contactId = (int) session.save(contact);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while creating contact: " + e);
        }

        return contactId;
    }

    @Override
    public void delete(Contact contact) throws DAOException {
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Contact persistContact = session.get(Contact.class, contact.getId());

            session.delete(persistContact);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while removing contact: " + e);
        }
    }

    @Override
    public void update(Contact contact) throws DAOException {
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Contact persistContact = session.get(Contact.class, contact.getId());
            persistContact.setFirstName(contact.getFirstName());
            persistContact.setSecondPhoneNumber(contact.getSecondPhoneNumber());
            persistContact.setNotes(contact.getNotes());
            persistContact.setMiddleName(contact.getMiddleName());
            persistContact.setLastName(contact.getLastName());
            persistContact.setFirstPhoneNumber(contact.getFirstPhoneNumber());
            persistContact.setEmail(contact.getEmail());
            persistContact.setGroupList(contact.getGroupList());
            persistContact.setFirstPhoneNumberType(contact.getFirstPhoneNumberType());
            persistContact.setSecondPhoneNumberType(contact.getSecondPhoneNumberType());

            session.update(persistContact);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("Exception while updating contact: " + e);
        }
    }

    @Override
    public List<Contact> getAll(int ownerId) throws DAOException {
        List<Contact> contacts;

        try (Session session = getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Contact.class);
            criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
            contacts = criteria.list();

        } catch (HibernateException e) {
            throw new DAOException("Exception while retrieving contact list: " + e);
        }

        return contacts;
    }

    @Override
    public Contact getById(int id, int ownerId) throws DAOException {
        Contact contact = null;

        try (Session session = getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Contact.class);
            criteria.add(Restrictions.eq(("id"), id));
            criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
            List result = criteria.list();

            if (!result.isEmpty()) {
                contact = (Contact) result.get(0);
            }

        } catch (HibernateException e) {
            throw new DAOException("Exception while retrieving contact by id: " + e);
        }

        return contact;
    }

    @Override
    public List<Contact> getByName(String name, int ownerId) throws DAOException {
        List<Contact> contacts;

        try (Session session = getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Contact.class);
            criteria.add(Restrictions.eq(("firstName"), name));
            criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
            contacts = criteria.list();

        } catch (HibernateException e) {
            throw new DAOException("Exception while retrieving contact list by name: " + e);
        }

        return contacts;
    }

}
