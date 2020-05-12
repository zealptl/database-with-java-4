package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyPieChart extends MyShape {

    //Variables
    private double xLength;
    private double yLength;
    private double arcAngle;
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
        super(0,0);
        this.xLength = xLength;
        this.yLength = yLength;
    }

    public MyPieChart(int n) {
        super(0,0);
    }

    //MyPieChart specific methods
//    private LinkedHashMap<String, Double> getPieChartData(LinkedHashMap<Character, Double> data) {
//        LinkedHashMap<String, Double> tempData = new LinkedHashMap<>();
//        Iterator<Map.Entry<Character, Double>> itr = data.entrySet().iterator();
//        int i = 0;
//        double remaining = 0;
//
//        while (itr.hasNext()) {
//            Map.Entry<Character, Double> entry = itr.next();
//            if (i < this.n) {
//                tempData.put(String.valueOf(entry.getKey()), entry.getValue());
//                i++;
//            } else {
//                remaining += entry.getValue();
//            }
//
//        }
//        tempData.put("All other letters", remaining);
//        return tempData;
//    }

    //Overridden methods from MyShape
    public String toString() {
        return null;
    }
    public void draw(GraphicsContext gc) {
    }
    public void draw(GraphicsContext gc, LinkedHashMap<String, Integer> pieChartData) {

        //Variables to create the legend
        int rectX = 850;
        int rectY = 50;
        int xCord = 925;
        int yCord = 65;
        int x_label = 630;
        int y_label = 30;
        int x_label2 = 690;
        int y_label2 = 50;


        for (Map.Entry<String, Integer> entry : pieChartData.entrySet()) {
            arcAngle = entry.getValue() * 360;
            gc.setFill(color.getRandomColor());
            gc.fillArc(x*0.8, y*0.02, xLength, yLength, startAngle, arcAngle, ArcType.ROUND);
            startAngle += arcAngle;

            //Creating legend by creating a rectangle of appropriate color and putting appropriate values
            if (yCord > xCord && rectY > xCord) {
                gc.fillRect(x_label, y_label, 50, 30);
                gc.fillText(entry.getKey() + ", " + (double) Math.round(entry.getValue() * 10000) / 10000, x_label2, y_label2);
                y_label2 += 40;
                y_label += 40;
            } else {
                gc.fillRect(rectX, rectY, 50, 30);
                gc.fillText(entry.getKey() + ", " + (double) Math.round(entry.getValue() * 10000) / 10000, xCord, yCord);
                rectY += 40;
                yCord += 40;
            }
        }
        System.out.println("DRAW SUCCESSFUL");
    }

    //Overridden methods from MyShapePosition
    public MyRectangle getBoundingBox() {
        return new MyRectangle(0,0,0,0);
    }
    public boolean doOverlap(MyShape shape2) {
        return doMyRectangleOverlap(this.getBoundingBox(), shape2.getBoundingBox());
    }
}
