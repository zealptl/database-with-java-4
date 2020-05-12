package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyPieChart extends MyShape {

    //Variables
    private double xLength;
    private double yLength;
    private double startAngle = 0;

    //Constructor
    public MyPieChart(double x, double y) {
        super(x, y);
    }

    public MyPieChart(double x, double y, double xLength, double yLength) {
        super(x, y);
        this.xLength = xLength;
        this.yLength = yLength;
    }

    public MyPieChart(int n, double xLength, double yLength) {
        super(0, 0);
        this.xLength = xLength;
        this.yLength = yLength;
    }

    public MyPieChart(int n) {
        super(0, 0);
    }

    //Overridden methods from MyShape
    public String toString() {
        return null;
    }

    public void draw(GraphicsContext gc) {
    }

    public void draw(GraphicsContext gc, LinkedHashMap<String, Integer> pieChartData) {
        // Calculate total number of students matching the query
        int totalStudent = 0;
        for (int val : pieChartData.values()) {
            totalStudent += val;
        }

        double degreePerPerson = (double) 360 / totalStudent;

        //Variables to create the legend
        int rectX = 850;
        int rectY = 50;
        int xCord = 925;
        int yCord = 65;
        int x_label = 630;
        int y_label = 30;
        int x_label2 = 690;
        int y_label2 = 50;
        double arcAngle;

        // Clears canvas before drawing
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (Map.Entry<String, Integer> entry : pieChartData.entrySet()) {

            // Drawing the pie chart slices
            arcAngle = entry.getValue() * degreePerPerson;
            gc.setFill(color.getRandomColor());
            gc.setFont(new Font("Arial", 15));
            gc.fillArc(x * 0.8, y * 0.02, xLength, yLength, startAngle, arcAngle, ArcType.ROUND);
            startAngle += arcAngle;

            //Creating legend by creating a rectangle of appropriate color and putting appropriate values
            if (yCord > xCord && rectY > xCord) {
                gc.fillRect(x_label, y_label, 50, 30);
                gc.fillText(entry.getKey() + ", " + (int) Math.round(entry.getValue() * 10000) / 10000, x_label2, y_label2);
                y_label2 += 40;
                y_label += 40;
            } else {
                gc.fillRect(rectX, rectY, 50, 30);
                gc.fillText(entry.getKey() + ", " + (int) Math.round(entry.getValue() * 10000) / 10000, xCord, yCord);
                rectY += 40;
                yCord += 40;
            }
        }
    }

    //Overridden methods from MyShapePosition
    public MyRectangle getBoundingBox() {
        return new MyRectangle(0, 0, 0, 0);
    }

    public boolean doOverlap(MyShape shape2) {
        return doMyRectangleOverlap(this.getBoundingBox(), shape2.getBoundingBox());
    }
}
