package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyRectangle extends MyShape {

    //Variables
    private double width;
    private double height;

    //Constructors
    public MyRectangle() {
        super(0,0);
        this.width = 0;
        this.height = 0;
    }
    public MyRectangle(double x, double y, double width, double height) {
        super(x - width / 2, y - height / 2);
        this.width = width;
        this.height = height;
    }

    //Getters
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    //Setters
    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }

    //MyRectangle specific methods
    public double getPerimeter() {
        return 2 * (this.width + this.height);
    }
    public double getArea() {
        return width * height;
    }

    //Overridden methods from MyShape
    public String toString() {
        return this.getClass().getName() + "\nWidth: " + this.width + "\nHeight: " + this.height +
                "\nPerimeter: " + this.getPerimeter() + "\nArea: " + this.getArea();
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color.getColor());
        gc.fillRect(this.x, this.y, this.width, this.height);
    }

    //Overridden methods from MyShapePosition
    public MyRectangle getBoundingBox() {
        return new MyRectangle(this.x + this.width / 2, this.y + this.height / 2, this.width, this.height);
    }
    public boolean doOverlap(MyShape shape2) {
        return doMyRectangleOverlap(this.getBoundingBox(), shape2.getBoundingBox());
    }
}
