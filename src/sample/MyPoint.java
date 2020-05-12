package sample;

public interface MyPoint {
    double[] getPoint();
    void setPoint(double x, double y);
    void moveTo(double delX, double delY);
    double distanceTo(double x, double y);
}