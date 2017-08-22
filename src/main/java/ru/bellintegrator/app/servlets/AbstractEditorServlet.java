package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.EditorAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractEditorServlet extends AbstractServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        String act = req.getParameter("action") == null ? (String) req.getAttribute("action") : req.getParameter("action");
        EditorAction action = EditorAction.getActionFromString(act);

        switch (action) {
            case CREATE:
                create(req, resp, userId, dispatcher);
                break;

            case CREATED:
                insert(req, resp, userId, action);
                break;

            case UPDATE:
                update(req, resp, userId, dispatcher);
                break;

            case UPDATED:
                insert(req, resp, userId, action);
                break;

            case DELETE:
                delete(req, resp, userId);
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/userdata");
                break;
        }
    }

    protected abstract void delete(HttpServletRequest req, HttpServletResponse resp, int userId);
    protected abstract void update(HttpServletRequest req, HttpServletResponse resp, int userId, RequestDispatcher dispatcher);
    protected abstract void insert(HttpServletRequest req, HttpServletResponse resp, int userId, EditorAction action);
    protected abstract void create(HttpServletRequest req, HttpServletResponse resp, int userId, RequestDispatcher dispatcher);

}
