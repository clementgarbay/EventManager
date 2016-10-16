package fr.eventmanager.tags.application;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetResource extends SimpleTagSupport {
    private String path;
    private static final String RELATIVE_RESOURCES_PATH = "/assets";

    @Override
    public void doTag() throws JspException {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            getJspContext().getOut().print(pageContext.getServletContext().getContextPath() + RELATIVE_RESOURCES_PATH + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
