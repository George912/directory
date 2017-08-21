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

   private DAOFactoryType daoFactoryType;
   private DAOFactory daoFactory;
   private AnalyticalInfoDAO dao;
   private AnalyticalInfoService service;

    @Override
    public void init() throws ServletException {
        daoFactoryType = DAOFactoryType.SQL_POSTGRESQL;
        daoFactory = DAOFactory.getDAOFactory(daoFactoryType);

        try {
            dao = daoFactory.getAnalyticalInfoDAO();
            service = new AnalyticalInfoService(dao);

        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/admin.jsp");

        try {
            AnalyticalInfo info = service.collectAnalyticalInfo();
            req.setAttribute("info", info);
            dispatcher.include(req, res);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
