package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;
import ru.bellintegrator.app.viewmodel.EditorAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactEditorServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/contacteditor.jsp");
        EditorAction action = EditorAction.UNKNOWN;
        //todo get data from db, check param edit/or create
        Contact contact = new Contact(1, "c1", "c1", "c1", "1", PhoneNumberType.HOME, "2", PhoneNumberType.HOME, "", "");
        Group group = new Group(0, "g1", "g1", 0);
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        contact.setGroupList(groups);

        switch (action){
            case CREATE:
                System.out.println("CREATE");
                break;

            case UPDATE:
                System.out.println("UPDATE");
                break;

            case UNKNOWN:
                break;

            default:
                break;

        }

        req.setAttribute("contact", contact);
        dispatcher.include(req, res);
    }

}
