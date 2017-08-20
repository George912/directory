package ru.bellintegrator.app.servlets;

import ru.bellintegrator.app.model.Contact;
import ru.bellintegrator.app.model.Group;
import ru.bellintegrator.app.model.PhoneNumberType;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        //todo is exist user in db, get contacts and groups

        RequestDispatcher dispatcher = context.getRequestDispatcher("/views/main.jsp");
        Group group = new Group(0, "g1", "g1", 0);
        Contact contact = new Contact(0, "c1", "c1", "c1", "1", PhoneNumberType.HOME, "2", PhoneNumberType.HOME, "", "");
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        contact.setGroupList(groups);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        Contact contact1 = new Contact(1, "c1", "c1", "c1", "1", PhoneNumberType.HOME, "2", PhoneNumberType.HOME, "", "");
        contacts.add(contact1);

        req.setAttribute("contactList", contacts);
        req.setAttribute("groupList", groups);

        dispatcher.include(req, res);

    }
}
