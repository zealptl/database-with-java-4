package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FillData {
    public List<List<String>> readData(String path) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public void insertStudentData(List<List<String>> data) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "INSERT INTO Students (studentID, firstName, lastName, email, sex)" + "VALUES(?, ?, ?, ?, ?)";
        for(List<String> d : data) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(d.get(0)));
            stmt.setString(2, d.get(1));
            stmt.setString(3, d.get(2));
            stmt.setString(4, d.get(3));
            stmt.setString(5, d.get(4));
            stmt.execute();

        }
        System.out.println("All entries successful!");
        conn.close();
    }

    public void insertCoursesData(List<List<String>> data) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "INSERT INTO Courses (courseId, courseTitle, department)" + "VALUES(?, ?, ?)";
        for (List<String> d : data) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, d.get(0));
            stmt.setString(2, d.get(1));
            stmt.setString(3, d.get(2));
            stmt.execute();
        }
        System.out.println("All entries successful!");
        conn.close();
    }

    public void insertClassesData(List<List<String>> data) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "INSERT INTO Classes (courseID, courseSection, studentID, year, Semester, GPA)" + "VALUES(?, ?, ?, ?, ?, ?)";
        for (List<String> d : data) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, d.get(0));
            stmt.setString(2, d.get(1));
            stmt.setInt(3, Integer.parseInt(d.get(2)));
            stmt.setInt(4, Integer.parseInt(d.get(3)));
            stmt.setString(5, d.get(4));
            stmt.setString(6, d.get(5));
            stmt.execute();
        }
        System.out.println("All entries successful!");
        conn.close();
    }

    public void deleteStudentData(String data) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "DELETE FROM Students WHERE studentID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, data);
        stmt.execute();
        conn.close();
    }

    public void deleteCoursesData(String data) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "DELETE FROM Courses WHERE courseID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data);
        stmt.execute();
        conn.close();
    }

    public void deleteClassesData(List<String> data) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "DELETE FROM Classes WHERE courseID = ? &&  courseSection = ? && studentID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,data.get(0));
        stmt.setString(2,data.get(1));
        stmt.setString(3,data.get(2));
        stmt.execute();
        conn.close();
    }
}
