package ru.bellintegrator.app.servlet;

import org.apache.log4j.Logger;
import ru.bellintegrator.app.exception.ServiceException;
import ru.bellintegrator.app.model.AnalyticalInfo;
import ru.bellintegrator.app.service.impl.AnalyticalInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminServlet extends AbstractServlet {

    private AnalyticalInfoServiceImpl service;
    private static final Logger log = Logger.getLogger(AdminServlet.class);

    @Override
    public void init() throws ServletException {
        service = new AnalyticalInfoServiceImpl(infoDAO);
        log.debug("Initialize AdminServlet");
        log.info("AdminServlet instance created");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String data = req.getParameter("data");
        try {
            AnalyticalInfo info = service.collectAnalyticalInfo();
            log.debug("Request set attribute info = " + info);
            req.setAttribute("info", info);
            switch (data){
                case "uc":
                    showUserCount(req, res);
                    break;
                case "eucc":
                    showEachUserContactCount(req, res);
                    break;
                case "eugc":
                    showEachUserGroupCount(req, res);
                    break;
                case "aguc":
                    showAvgGroupUserCount(req, res);
                    break;
                case "aucc":
                    showAvgUserContactCount(req, res);
                    break;
                case "iucc":
                    showInactiveUserContactCount(req, res);
                    break;
                default:
                    dispatcher = this.getServletContext().getRequestDispatcher("/views/main.jsp");
                    log.debug("Go to /views/main.jsp");
                    dispatcher.include(req, res);
                    break;
            }
        } catch (ServiceException e) {
            log.error("Exception in method service: ", e);
        }
    }

    private void showInactiveUserContactCount(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/statistic/inactiveusercontactcount.jsp");
        log.debug("Go to /statistic/inactiveusercontactcount.jsp");
        dispatcher.include(req, res);
    }

    private void showAvgUserContactCount(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/statistic/avgusercontactcount.jsp");
        log.debug("Go to /statistic/avgusercontactcount.jsp");
        dispatcher.include(req, res);
    }

    private void showAvgGroupUserCount(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/statistic/avggroupusercount.jsp");
        log.debug("Go to /statistic/avggroupusercount.jsp");
        dispatcher.include(req, res);
    }

    private void showEachUserGroupCount(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/statistic/eachusergroupcount.jsp");
        log.debug("Go to /statistic/eachusergroupcount.jsp");
        dispatcher.include(req, res);
    }

    private void showEachUserContactCount(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/statistic/eachusercontactcount.jsp");
        log.debug("Go to /statistic/eachusercontactcount.jsp");
        dispatcher.include(req, res);
    }

    private void showUserCount(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        dispatcher = this.getServletContext().getRequestDispatcher("/views/statistic/usercount.jsp");
        log.debug("Go to /statistic/usercount.jsp");
        dispatcher.include(req, res);
    }

}
