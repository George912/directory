package ru.bellintegrator.app.dao.factory.impl.sql;

        import ru.bellintegrator.app.dao.GenericDAO;
        import ru.bellintegrator.app.dao.factory.DAOFactory;
        import ru.bellintegrator.app.exception.DAOException;
        import ru.bellintegrator.app.model.AnalyticalInfo;
        import ru.bellintegrator.app.model.User;

public abstract class AbstractSqlDAOFactory extends DAOFactory {

    public abstract GenericDAO<User> getUserDAO() throws DAOException;

    public abstract GenericDAO<AnalyticalInfo> getAnalyticalInfoDAO();

    //todo getConnection method

}
