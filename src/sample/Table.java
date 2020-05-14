package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Table {

    // Creates student table in the database if it doesn't already exist
    public void createStudentTable() {
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Students(" +
                    "studentID int," +
                    " firstName varchar(255)," +
                    " lastName varchar(255)," +
                    " email varchar(255)," +
                    " sex varchar(1), " +
                    "PRIMARY KEY (studentID))");
            stmt.executeUpdate();
            System.out.println("Student table created!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates courses table in the database if it doesn't already exist
    public void createCoursesTable() {
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Courses(" +
                    "courseID varchar(255)," +
                    " courseTitle varchar(255)," +
                    " department varchar(255)," +
                    " PRIMARY KEY (courseID))");
            stmt.executeUpdate();
            System.out.println("Courses table created!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates classes table in the database if it doesn't already exist
    public void createClassesTable() {
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Classes(" +
                    "courseID varchar(255)," +
                    " courseSection varchar(25)," +
                    " studentID int," +
                    " year int," +
                    " Semester varchar(255)," +
                    " GPA varchar(1)," +
                            "CONSTRAINT chk_GPA CHECK (GPA IN ('A','B','C','D','F','W'))," +
                    " PRIMARY KEY (studentID, courseID, courseSection))");
            stmt.executeUpdate();
            System.out.println("Classes table created!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
