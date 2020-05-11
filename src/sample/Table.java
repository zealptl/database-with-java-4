package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Table {
    public void createStudentTable() {
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("DROP TABLE IF EXISTS Students");
            stmt1.executeUpdate();
            PreparedStatement stmt2 = conn.prepareStatement("create table IF NOT EXISTS Students(studentID int, firstName varchar(255), lastName varchar(255), email varchar(255), sex varchar(1), " +
                    "PRIMARY KEY (studentID))");
            stmt2.executeUpdate();
            System.out.println("Student table created!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCoursesTable() {
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("DROP TABLE IF EXISTS Courses");
            stmt1.executeUpdate();
            PreparedStatement stmt2 = conn.prepareStatement("create table IF NOT EXISTS Courses(courseID varchar(255), courseTitle varchar(255), department varchar(255), PRIMARY KEY (courseID))");
            stmt2.executeUpdate();
            System.out.println("Courses table created!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createClassesTable() {
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement stmt1 = conn.prepareStatement("DROP  TABLE  IF EXISTS Classes");
            stmt1.executeUpdate();
            PreparedStatement stmt2 = conn.prepareStatement("create table IF NOT EXISTS Classes(courseID varchar(255), courseSection varchar(25), studentID int, year int, Semester varchar(255), GPA varchar(1)," +
                            "CONSTRAINT chk_GPA CHECK (GPA IN ('A','B','C','D','F','W')), PRIMARY KEY (studentID, courseID, courseSection))");
            stmt2.executeUpdate();
            System.out.println("Classes table created!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
