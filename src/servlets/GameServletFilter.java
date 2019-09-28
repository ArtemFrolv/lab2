package servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String numString = servletRequest.getParameter("num");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getContextPath();
        boolean check = false;

        if (numString == null)
            path += "/wrong.html";
        else {
            if (!isInt(numString))
                path += "/wrong.html";
            else {
                int num = Integer.parseInt(numString);
                if ((num > 8) || (num < 0))
                    path += "/wrong.html";
                else
                    check = true;
            }
        }
        if (check)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            resp.sendRedirect(path);
    }

    private static boolean isInt(String str) {
        return str.matches("[-+]?\\d+");
    }
}
