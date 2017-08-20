package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.viewmodel.EditorAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class GroupEditorServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println(req.getParameter("group_id"));
        log("group_id");
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/groupeditor.jsp");
        EditorAction action = EditorAction.UNKNOWN;
        //todo get data from db, check param edit/or create
        Group group = new Group(0, "g1", "g1", 0);

        switch (action) {
            case CREATE:
                System.out.println("CREATE");
                group = new Group(0, "g1", "g1", 0);
                break;

            case UPDATE:
                System.out.println("UPDATE");
                //todo getById from DB
                break;

            case UNKNOWN:
                break;

            default:
                break;

        }

        req.setAttribute("group", group);
        dispatcher.include(req, res);
    }

}
