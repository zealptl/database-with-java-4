package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
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
        launch(args);
    }
}
