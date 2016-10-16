package fr.eventmanager.tags.application;

import fr.eventmanager.utils.router.Path;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetUrl extends SimpleTagSupport {
    private Path path;

    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().print(path.getFullPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path getPathId() {
        return path;
    }

    public void setPathId(Path path) {
        this.path = path;
    }
}
