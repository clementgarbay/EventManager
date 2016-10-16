package fr.eventmanager.tags.application;

import fr.eventmanager.utils.router.RouteId;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetUrl extends SimpleTagSupport {
    private RouteId routeId;
    private ServletRouter servletRouter = ServletRouter.getInstance();

    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().print(servletRouter.getFullPath(routeId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RouteId getRouteId() {
        return routeId;
    }

    public void setRouteId(RouteId routeId) {
        this.routeId = routeId;
    }
}
