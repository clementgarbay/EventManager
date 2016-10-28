package fr.eventmanager.tags.application;

import fr.eventmanager.core.router.Path;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class GetUrl extends SimpleTagSupport {
    private Path path;
    private String paramsStr;

    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        try {
            String fullPath = path.getFullPath();

            try { // TOREVIEW
                JSONObject json = new JSONObject(paramsStr);
                Map<String, String> params = json.keySet().stream().collect(Collectors.toMap(key -> key, key -> (json.get(key) instanceof Integer) ? Integer.toString(json.getInt(key)) : json.getString(key)));
                if (params.size() > 0) fullPath = path.getFullPath(params);
            } catch (NullPointerException | JSONException ignored) {}

            getJspContext().getOut().print(pageContext.getServletContext().getContextPath() + fullPath);
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

    public String getParams() {
        return paramsStr;
    }

    public void setParams(String paramsStr) {
        this.paramsStr = paramsStr;
    }
}
