package servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerFilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String X_string = servletRequest.getParameter("X");
        String Y_string = servletRequest.getParameter("Y");
        String R_string = servletRequest.getParameter("R");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getContextPath();

        boolean check = false;

        if ((X_string == null) || (Y_string == null) || (R_string == null))
            path += "/wrong.html";
        else {
            X_string = X_string.replace(',', '.');
            Y_string = Y_string.replace(',', '.');
            R_string = R_string.replace(',', '.');
            if (!(isNumeric(X_string)) || !(isNumeric(Y_string)) || !(isNumeric(R_string)))
                path += "/wrong.html";
            else {

                double X = Double.parseDouble(X_string);
                double Y = Double.parseDouble(Y_string);
                double R = Double.parseDouble(R_string);

                if ((X == 6) && (Y == 6) && (R == 6))
                    path += "/game.html";
                else if ((-4 > X) || (4 < X) || (-3 > Y) || (5 < Y) || (1 > R) || (4 < R))
                    path += "/wrong.html";
                else {
                    check = true;
                }
            }
        }
        if (check)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            resp.sendRedirect(path);
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
