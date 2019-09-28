package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/area")

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date timeStart = new Date();
        double X = Double.parseDouble(req.getParameter("X").replace(',', '.'));
        double Y = Double.parseDouble(req.getParameter("Y").replace(',', '.'));
        double R = Double.parseDouble(req.getParameter("R").replace(',', '.'));

        String check = "Мимо";

        Gson gson = new Gson();
        ArrayList<PointCoordinates> coordinates = new ArrayList<>();
        HttpSession history = req.getSession(true);

        if (((X * X + Y * Y) <= R * R / 4) && (Y >= 0) && (X <= 0))
            check = "Есть пробитие!!!";
        if ((-R / 2 <= Y) && (Y <= 0) && (-R <= X) && (X <= 0))
            check = "Есть пробитие!!!";
        if ((Y >= (2*X - R)) && (Y<=0) && (X >= 0))
            check = "Есть пробитие!!!";

        Type type = new TypeToken<ArrayList<PointCoordinates>>() {}.getType();

        if (history.getAttribute("coord") != null) {
            coordinates = gson.fromJson((String) history.getAttribute("coord"), type);
        }
        Date timeExecute = new Date();
        double execute = timeExecute.getTime() - timeStart.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        PointCoordinates pointCoordinates = new PointCoordinates(X, Y, R, simpleDateFormat.format(timeStart), execute, check);
        coordinates.add(pointCoordinates);
        String coord = gson.toJson(coordinates);
        history.setAttribute("coord", coord);
        String path = req.getContextPath() + "/table.jsp";
        resp.sendRedirect(path);
    }
}