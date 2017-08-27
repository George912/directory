package ru.bellintegrator.app.dao.impl.orm.hibernate;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import ru.bellintegrator.app.dao.GenericDAO;
import ru.bellintegrator.app.dao.impl.AbstractConnectable;
import ru.bellintegrator.app.dao.impl.UserDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.User;

import java.util.List;

public class HibernateUserDAO extends AbstractConnectable implements GenericDAO<User>, UserDAO {

    private final static Object monitor = new Object();

    @Override
    public int create(User user) throws DAOException {
        synchronized (monitor) {
            Transaction transaction = null;
            int userId;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                userId = (int) session.save(user);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                throw new DAOException("Exception while creating user: " + e);
            }

            return userId;
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        synchronized (monitor) {
            Transaction transaction = null;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                User persistUser = session.get(User.class, user.getId());

                session.delete(persistUser);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                throw new DAOException("Exception while removing user: " + e);
            }
        }
    }

    @Override
    public void update(User user) throws DAOException {
        synchronized (monitor) {
            Transaction transaction = null;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                User persistUser = session.get(User.class, user.getId());
                persistUser.setMiddleName(user.getMiddleName());
                persistUser.setPassword(user.getPassword());
                persistUser.setLastName(user.getLastName());
                persistUser.setFirstName(user.getFirstName());
                persistUser.setLogin(user.getLogin());

                session.update(persistUser);

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                throw new DAOException("Exception while updating user: " + e);
            }
        }
    }

    @Override
    public List<User> getAll(int ownerId) throws DAOException {
        synchronized (monitor) {
            List<User> users;
            Transaction transaction = null;

            try (Session session = getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                users = session.createQuery("FROM User").list();

                transaction.commit();

            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }

                throw new DAOException("Exception while getting user list: " + e);
            }

            return users;
        }
    }

    @Override
    public User getById(int id, int ownerId) throws DAOException {
        synchronized (monitor) {
            User user;

            try (Session session = getSessionFactory().openSession()) {
                IdentifierLoadAccess<User> userIdentifierLoadAccess = session.byId(User.class);
                user = userIdentifierLoadAccess.load(id);

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving user by id: " + e);
            }

            return user;
        }
    }

    @Override
    public List<User> getByName(String name, int ownerId) throws DAOException {
        synchronized (monitor) {
            List<User> users;

            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(User.class);
                users = criteria.add(Restrictions.eq(("firstName"), name)).list();

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving user list by name: " + e);
            }

            return users;
        }
    }

    @Override
    public User getUserByCredential(String login, String password) throws DAOException {
        synchronized (monitor) {
            User user = null;

            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq(("login"), login))
                        .add(Restrictions.eq(("password"), password));
                List result = criteria.list();

                if (!result.isEmpty()) {
                    user = (User) result.get(0);
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving user by credential: " + e);
            }

            return user;
        }
    }

    @Override
    public int getUserId(String login, String password) throws DAOException {
        synchronized (monitor) {
            int userId = -1;

            try (Session session = getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq(("login"), login))
                        .add(Restrictions.eq(("password"), password));
                List result = criteria.list();

                if (!result.isEmpty()) {
                    userId = ((User) criteria.list().get(0)).getId();
                }

            } catch (HibernateException e) {
                throw new DAOException("Exception while retrieving user id: " + e);
            }

            return userId;
        }
    }

}
