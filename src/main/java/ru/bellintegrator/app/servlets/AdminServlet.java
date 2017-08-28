package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.service.AnalyticalInfoService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminServlet extends AbstractServlet {

    private AnalyticalInfoService service;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdminServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/admin.jsp");
        service = new AnalyticalInfoService(infoDAO);
        log.debug("Initialize AdminServlet");
        log.info("AdminServlet instance created");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        try {
            AnalyticalInfo info = service.collectAnalyticalInfo();
            log.debug("Request set attribute info = " + info);
            req.setAttribute("info", info);
            log.debug("Go to /views/admin.jsp");
            dispatcher.include(req, res);

        } catch (DAOException e) {
            log.error("Exception in method service: " + e);
        }
    }

}
