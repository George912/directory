package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.dao.factory.DAOFactory;
import ru.bellintegrator.app.dao.factory.DAOFactoryType;
import ru.bellintegrator.app.dao.impl.sql.AnalyticalInfoDAO;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.service.AnalyticalInfoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //todo get info data from db
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/admin.jsp");
        DAOFactoryType daoFactoryType = DAOFactoryType.SQL_POSTGRESQL;
        DAOFactory daoFactory = DAOFactory.getDAOFactory(daoFactoryType);

        try {
            AnalyticalInfoDAO dao = daoFactory.getAnalyticalInfoDAO();
            AnalyticalInfoService service = new AnalyticalInfoService(dao);
            AnalyticalInfo info = service.collectAnalyticalInfo();
            req.setAttribute("info", info);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        dispatcher.include(req, res);
    }

}
