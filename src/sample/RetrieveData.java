package sample;

import javafx.scene.shape.Line;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class RetrieveData {
    public LinkedHashMap<String, Integer> getData(String course, String year, String semester) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT courseID, GPA FROM Classes WHERE courseID = ? && year = ? && semester = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, course);
        stmt.setString(2, year);
        stmt.setString(3, semester);

        ResultSet rs = stmt.executeQuery();

        LinkedHashMap<String, Integer> gpaData = new LinkedHashMap<>();
        gpaData.put("A",0);
        gpaData.put("B",0);
        gpaData.put("C",0);
        gpaData.put("D",0);
        gpaData.put("F",0);
        gpaData.put("W",0);

        while (rs.next()) {
            String gpa = rs.getString("GPA");
            gpaData.put(gpa, gpaData.get(gpa) + 1);
        }

        System.out.println(gpaData);

        return gpaData;

    }


}
