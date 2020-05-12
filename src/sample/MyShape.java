package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyShape implements MyShapePosition {

    //Variables
    protected double x;
    protected double y;
    protected MyColor color;

    //Constructors
    public MyShape() {
        this.x = 0;
        this.y = 0;
        this.color = MyColor.BLACK;
    }
    public MyShape(double x, double y) {
        this.x = x;
        this.y = y;
        this.color = MyColor.BLACK;
    }

    //Getters
    public double getX() {  return x; }
    public double getY() { return y; }
    public MyColor getColor() { return color; }
    public Color getRandomColor() {
        return color.getRandomColor();
    }

    //Setters
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setColor(MyColor color) { this.color = color; }

    //Overridden methods from MyPoint interface
    public double[] getPoint() {
        double[] xyPoints = new double[2];
        xyPoints[0] = this.getX();
        xyPoints[1] = this.getY();
        return xyPoints;
    }
    public void setPoint(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    public void moveTo(double delX, double delY) {
        this.setX(this.x + delX);
        this.setY(this.y + delY);
    }
    public double distanceTo(double x, double y) { return Math.sqrt(Math.pow(x - this.x,2) + Math.pow(y - this.y,2)); }

    //MyShape specific methods
    public abstract String toString();
    public abstract void draw(GraphicsContext gc);

    //Methods from MyShapePosition
    public abstract MyRectangle getBoundingBox();
    public abstract boolean doOverlap(MyShape shape2);

    //Utility method for doOverlap
    public boolean doMyRectangleOverlap(MyRectangle rect1, MyRectangle rect2) {
        double x1 = rect1.getX();
        double y1 = rect1.getY();
        double x1BR = rect1.getX() + rect1.getWidth();
        double y1BR = rect1.getY() + rect1.getHeight();

        double x2 = rect2.getX();
        double y2 = rect2.getY();
        double x2BR = rect2.getX() + rect2.getWidth();
        double y2BR = rect2.getY() + rect2.getHeight();

        return x1 <= x2 && x2 <= x1BR && y1 <= y2 && x2 <= y1BR ||
                x1 <= x2 && x2 <= x1BR && y1 <= y2BR && y2BR <= y1BR ||
                x1 <= x2BR && x2BR <= x1BR && y1 <= y2 && y2 <= y1BR ||
                x1 <= x2BR && x2BR <= x1BR && y1 <= y2BR && y2BR <= y1BR;

    }

}
