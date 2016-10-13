package fr.eventmanager.tags.application;

import fr.eventmanager.utils.URLUtils;
import fr.eventmanager.utils.router.Route;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetUrl extends SimpleTagSupport {
    private Route route;

    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().print(URLUtils.getURL(route));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
