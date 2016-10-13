package fr.eventmanager.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.eventmanager.dao.impl.UserSampleDAOImpl;
import fr.eventmanager.service.UserService;
import fr.eventmanager.service.impl.UserServiceImpl;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class ProfilServlet extends Servlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.userService = new UserServiceImpl(new UserSampleDAOImpl());

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, Pattern.compile("/"), "displayProfilPage")
                .registerRoute(HttpMethod.POST, Pattern.compile("/"), "profil");
    }

    private void displayProfilPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("profil.jsp", request, response);
    }


}