package servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        boolean player = true;
        HttpSession history = req.getSession(true);
        Gson gson = new Gson();

        char field[][] = new char[3][3];
        for (int i = 0; i < field.length; ++i)
            for (int j = 0; j < field[i].length; ++j)
                field[i][j] = '_';

        if (history.getAttribute("field") != null) {
            field = gson.fromJson((String) history.getAttribute("field"), char[][].class);
        }

        if (history.getAttribute("player") != null) {
            player = gson.fromJson(history.getAttribute("player").toString(), boolean.class);
            player = !player;
        }


        int iCoord = num / 3;
        int jCoord = num % 3;

        if (field[iCoord][jCoord] == '_') {
            if (player)
                field[iCoord][jCoord] = 'X';
            else
                field[iCoord][jCoord] = 'O';
        }

        String stringField = gson.toJson(field);
        history.setAttribute("field", stringField);
        String stringPlayer = gson.toJson(player);
        history.setAttribute("player", player);

        boolean win = false;

        for (int i = 0; i < field.length; ++i) {
            if ((field[i][0] == field[i][1]) && (field[i][1] == field[i][2]) && (field[i][0] != '_'))
                win = true;
            else if ((field[0][i] == field[1][i]) && (field[1][i] == field[2][i]) && (field[0][i] != '_'))
                win = true;
        }

        if (!win) {
            if ((field[0][0] == field[1][1]) && (field[1][1] == field[2][2]) && (field[0][0] != '_'))
                win = true;
            if ((field[0][2] == field[1][1]) && (field[0][2] == field[2][0]) && (field[0][2] != '_'))
                win = true;
        }

        PrintWriter printWriter = resp.getWriter();

        if (win)
            printWriter.write(stringPlayer);
        else
            printWriter.write(stringField);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession history = req.getSession(true);
        Gson gson = new Gson();

        char field[][] = new char[3][3];
        for (int i = 0; i < field.length; ++i)
            for (int j = 0; j < field[i].length; ++j)
                field[i][j] = '_';

        if (history.getAttribute("field") != null) {
            field = gson.fromJson((String) history.getAttribute("field"), char[][].class);
        }

        PrintWriter printWriter = resp.getWriter();
        String stringField = gson.toJson(field);
        printWriter.write(stringField);
    }
}
