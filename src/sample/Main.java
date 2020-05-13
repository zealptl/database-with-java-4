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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
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

        // Add to Database form
        Text add = new Text("Add Elements:");
        add.setFont(new Font("Arial", 24));
        inputMenu.getChildren().add(add);

        Label addTable = new Label("Table: ");
        TextField tableInput = new TextField();
        tableInput.setPromptText("Enter table name");

        Label dataFields = new Label("Data fields: ");
        TextField dataFieldsInput = new TextField();
        dataFieldsInput.setPromptText("Enter comma separated values");

        Button insertBtn = new Button("Insert");

        inputMenu.getChildren().addAll(addTable, tableInput, dataFields, dataFieldsInput, insertBtn);

        insertBtn.setOnAction(e -> {
            String tableToInsert = tableInput.getText();
            String input = dataFieldsInput.getText();
            List<String> inputs = Arrays.asList(input.trim().split("\\s*,\\s*"));
            List<List<String>> data = new ArrayList<>();
            data.add(inputs);


            FillData fillData = new FillData();


            switch (tableToInsert) {
                case "Students":
                    try {
                        System.out.println("ENTERED STUDENTS SECTION");
                        fillData.insertStudentData(data);
                        System.out.println("Student Entry Successful");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Courses":
                    try {
                        fillData.insertCoursesData(data);
                        System.out.println("Course Entry Successful");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Classes":
                    try {
                        fillData.insertStudentData(data);
                        System.out.println("Class Entry Successful");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
            tableInput.clear();
            dataFieldsInput.clear();
        });

        // Delete from database form
        Text delete = new Text("Delete Elements:");
        delete.setFont(new Font("Arial", 24));
        inputMenu.getChildren().add(delete);

        Label deleteTable = new Label("Table: ");
        TextField deleteTableInput = new TextField();
        deleteTableInput.setPromptText("Enter table name: ");

        Label deleteDataFields = new Label("Data fields");
        TextField deleteDataFieldsInput = new TextField();
        deleteDataFieldsInput.setPromptText("Enter comma separated values");

        Button deleteBtn = new Button("Delete");

        deleteBtn.setOnAction(e -> {
            String tableToDeleteFrom = deleteTableInput.getText();
            FillData fillData = new FillData();

            switch (tableToDeleteFrom) {
                case "Students": {
                    String input = deleteDataFieldsInput.getText();
                    try {
                        fillData.deleteStudentData(input);
                        System.out.println("Student Deletion Successful");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                case "Courses": {
                    String input = deleteDataFieldsInput.getText();
                    try {
                        fillData.deleteCoursesData(input);
                        System.out.println("Course Deletion Successful");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                case "Classes": {
                    String input = deleteDataFieldsInput.getText();
                    List<String> inputs = Arrays.asList(input.trim().split("\\s*,\\s*"));
                    try {
                        fillData.deleteClassesData(inputs);
                        System.out.println("Class Deletion Successful");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
            deleteTableInput.clear();
            deleteDataFieldsInput.clear();
        });

        inputMenu.getChildren().addAll(deleteTable, deleteTableInput, deleteDataFields, deleteDataFieldsInput, deleteBtn);

        // Search from database form
        Text search = new Text("Search Elements:");
        search.setFont(new Font("Arial", 24));
        inputMenu.getChildren().add(search);

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

        //Show Table to Console Form
        Text display = new Text("Display Table:");
        display.setFont(new Font("Arial", 24));
        inputMenu.getChildren().add(display);

        Label tableToDisplay = new Label("Table: ");
        TextField tableToDisplayInput = new TextField();
        tableToDisplayInput.setPromptText("Enter table name");

        Button displayBtn = new Button("Display");

        inputMenu.getChildren().addAll(tableToDisplay, tableToDisplayInput, displayBtn);

        displayBtn.setOnAction(e -> {
            String table = tableToDisplayInput.getText();
            RetrieveData rd = new RetrieveData();
            try {
                rd.retrieveEntireTable(table);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        // Adding group to center of layout to draw the pie chart
        group.getChildren().add(canvas);
        border.setCenter(group);

        // Creating tables and filling the tables
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
