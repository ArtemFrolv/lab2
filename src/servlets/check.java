package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

@WebServlet("/check")
public class check extends HttpServlet {
    private static HashSet<String> kek = new HashSet<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession history = req.getSession(true);
        kek.add(history.getId());
        PrintWriter printWriter = resp.getWriter();
        for (String aKek : kek) {
            printWriter.write(aKek + "\n");
        }
    }
}
