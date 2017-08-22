package ru.bellintegrator.app.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.app.exception.DAOException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.service.AnalyticalInfoService;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminServlet extends AbstractServlet {


    private AnalyticalInfoService service;
    private static final Logger log = LoggerFactory.getLogger(AdminServlet.class);

    @Override
    public void init() throws ServletException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/admin.jsp");
        service = new AnalyticalInfoService(infoDAO);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        try {
            AnalyticalInfo info = service.collectAnalyticalInfo();
            req.setAttribute("info", info);
            dispatcher.include(req, res);

        } catch (DAOException e) {
            log.debug("Exception in method service: " + e);
        }
    }

}
