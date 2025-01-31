package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

public class JspView implements View{
    private static final String REDIRECT_PREFIX = "redirect:";
    private String viewName;

    public JspView(String viewName){
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // DispatcherServelt의 기존 move 그대로 복붙
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }

        Set<String> keys = model.keySet();
        for(String key : keys)
            request.setAttribute(key, model.get(key));

        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
