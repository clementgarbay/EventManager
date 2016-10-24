package fr.eventmanager.entity.helper;

import fr.eventmanager.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author clementgarbay
 */
public class UserHelper {

    public static User build(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String company = request.getParameter("company");

        return new User(name, email, password, company);
    }
}
