package servlets;

import java.util.Date;

public class PointCoordinates {
    private double X;
    private double Y;
    private double R;
    private String time;
    private double timeExecute;
    private String check;
    PointCoordinates(double X, double Y, double R, String time, double timeExecute, String check) {
        this.X = X;
        this.Y = Y;
        this.R = R;
        this.time = time;
        this.timeExecute = timeExecute;
        this.check = check;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTimeExecute() {
        return timeExecute;
    }

    public void setTimeExecute(double timeExecute) {
        this.timeExecute = timeExecute;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
