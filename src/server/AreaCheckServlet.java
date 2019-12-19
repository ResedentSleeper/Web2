package server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {

    private ServletConfig config;

    private JavaBean bean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        bean = (JavaBean) request.getSession().getAttribute("dotbean");

        boolean load;

        try {
            load = request.getParameter("load").equals("1");
        } catch (NullPointerException e) {
            load = false;
        }

        if (!load) {
            try {
                double x = Double.parseDouble(request.getParameter("x_h").trim());
                double y = Double.parseDouble(request.getParameter("y_h").trim());
                double r = Double.parseDouble(request.getParameter("r_h").trim());

                Dot p = new Dot(x, y, r);

                bean.addDot(p);
            } catch (Exception e) {
                e.printStackTrace();
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }

        response.setContentType("text/html; charset=UTF-8");

        request.getServletContext().getRequestDispatcher("/result.jsp").include(request, response);

    }

}