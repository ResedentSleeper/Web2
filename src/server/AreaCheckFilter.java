package server;

import javax.servlet.*;
import java.io.IOException;

public class AreaCheckFilter implements Filter {

    public void init(FilterConfig arg0) {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {

        resp.setContentType("text/html; charset=UTF-8");

        try {

            Double.parseDouble(req.getParameter("x_h").trim());
            Double.parseDouble(req.getParameter("y_h").trim());
            Double.parseDouble(req.getParameter("r_h").trim());

            chain.doFilter(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void destroy() {}

}