package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("School System");

        // Declaring necessary UI elements
        BorderPane border = new BorderPane();
        VBox inputMenu = new VBox();
        Group group = new Group();
        int w = 1250, h = 950;
        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Designing the left input section
        border.setLeft(inputMenu);
        inputMenu.setPadding(new Insets(15, 10, 15, 10));
        inputMenu.setSpacing(10);
        inputMenu.setStyle("-fx-background-color: #b2bec3");
        inputMenu.setAlignment(Pos.TOP_LEFT);

        Label courseLabel = new Label("CourseID: ");
        TextField courseField = new TextField();
        courseField.setPromptText("Enter course ID");

        Label yearLabel = new Label("Year: ");
        TextField yearField = new TextField();
        yearField.setPromptText("Enter year");

        Label semesterLabel = new Label("Semester: ");
        TextField semesterField = new TextField();
        semesterField.setPromptText("Enter Semester");

        Button addBtn = new Button("Enter");
        inputMenu.getChildren().addAll(courseLabel, courseField, yearLabel, yearField, semesterLabel, semesterField, addBtn);

        // Adding group to center of layout to draw the pie chart
        group.getChildren().add(canvas);
        border.setCenter(group);


        // Event listener to listen for button click and then draw pie chart
        addBtn.setOnAction(e -> {
            RetrieveData retrieve = new RetrieveData();
            String courseInput = courseField.getText();
            String yearInput = yearField.getText();
            String semesterInput = semesterField.getText();
            LinkedHashMap<String, Integer> data = new LinkedHashMap<>();
            try {
                data = retrieve.getData(courseInput, yearInput, semesterInput);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (data == null) {
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                gc.setFont(new Font("Arial", 36));
                gc.setFill(Color.BLACK);
                gc.fillText("Data Not Found", w * 0.3, h * 0.3);
            } else {
                MyPieChart pieChart = new MyPieChart(w * 0.3, h * 3, 300, 300);
                pieChart.draw(gc, data);
            }
            courseField.clear();
            yearField.clear();
            semesterField.clear();
        });

        Table schoolDB = new Table();
        schoolDB.createStudentTable();
        schoolDB.createCoursesTable();
        schoolDB.createClassesTable();

        FillData data = new FillData();
        List<List<String>> students = data.readData("./data/students.csv");
        List<List<String>> courses = data.readData("./data/courses.csv");
        List<List<String>> classes = data.readData("./data/classes.csv");
        data.insertStudentData(students);
        data.insertCoursesData(courses);
        data.insertClassesData(classes);


        Scene scene = new Scene(border, w, h);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
