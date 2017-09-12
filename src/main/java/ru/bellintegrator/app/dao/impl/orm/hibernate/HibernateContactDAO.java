package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.AbstractConnectable;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.User;

import java.util.List;

@Repository("contactDao")
public class HibernateContactDAO extends AbstractConnectable implements GenericDAO<Contact> {

    private final static Object monitor = new Object();
    private static final Logger log = Logger.getLogger(HibernateContactDAO.class);

    @Override
    public int create(Contact contact) throws DAOException {
        log.debug("Call create method: contact = " + contact);
        synchronized (monitor) {
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

                log.error("Exception while creating contact: ", e);
                throw new DAOException("Exception while creating contact: ", e);
            }

            return contactId;
        }
    }

    @Override
    public void delete(Contact contact) throws DAOException {
        log.debug("Call delete method: contact = " + contact);
        synchronized (monitor) {
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

                log.error("Exception while removing contact: ", e);
                throw new DAOException("Exception while removing contact: ", e);
            }
        }
    }

    @Override
    public void update(Contact contact) throws DAOException {
        log.debug("Call update method: contact = " + contact);
        synchronized (monitor) {
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

                log.error("Exception while updating contact: ", e);
                throw new DAOException("Exception while updating contact: ", e);
            }
        }
    }

    @Override
    public List<Contact> getAll(int ownerId) throws DAOException {
        log.debug("Call getAll method: ownerId = " + ownerId);
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Contact.class);
                criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
                return criteria.list();

            } catch (HibernateException e) {
                log.error("Exception while retrieving contact list: ", e);
                throw new DAOException("Exception while retrieving contact list: ", e);
            }
        }
    }

    @Override
    public Contact getById(int id, int ownerId) throws DAOException {
        log.debug("Call getById method: id = " + id + ", ownerId = " + ownerId);
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Contact.class);
                criteria.add(Restrictions.eq(("id"), id));
                criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
                List result = criteria.list();

                if (!result.isEmpty()) {
                    return (Contact) result.get(0);
                }

            } catch (HibernateException e) {
                log.error("Exception while retrieving contact by id: ", e);
                throw new DAOException("Exception while retrieving contact by id: ", e);
            }

            return null;
        }
    }

    @Override
    public List<Contact> getByName(String name, int ownerId) throws DAOException {
        log.debug("Call getByName method: name = " + name + ", ownerId = " + ownerId);
        synchronized (monitor) {
            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Contact.class);
                criteria.add(Restrictions.eq(("firstName"), name));
                criteria.add(Restrictions.eq(("owner"), new User(ownerId)));
                return criteria.list();

            } catch (HibernateException e) {
                log.error("Exception while retrieving contact list by name: ", e);
                throw new DAOException("Exception while retrieving contact list by name: ", e);
            }
        }
    }

}
