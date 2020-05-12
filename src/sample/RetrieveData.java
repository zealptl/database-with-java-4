package sample;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

public class RetrieveData {
    public LinkedHashMap<String, Integer> getData(String course, String year, String semester) throws Exception {

        PreparedStatement stmt = null;

        if(course.equals("all") && !year.equals("all") && !semester.equals("all")) {
            stmt = retrieveAllCourse(year, semester);
        } else if (!course.equals("all") && year.equals("all") && !semester.equals("all")) {
            stmt = retrieveAllYear(course, semester);
        } else if (!course.equals("all") && !year.equals("all") && semester.equals("all")) {
            stmt = retrieveAllSemester(course, year);
        } else if (course.equals("all") && year.equals("all") && !semester.equals("all")) {
            stmt = retrieveAllCouurseYear(semester);
        } else if (course.equals("all") && !year.equals("all") && semester.equals("all")) {
            stmt = retrieveAllCourseSemester(year);
        } else if (!course.equals("all") && year.equals("all") && semester.equals("all")) {
            stmt = retrieveAllYearSemester(course);
        } else if (course.equals("all") && year.equals("all") && semester.equals("all")) {
            stmt = retrieveAllGPA();
        } else {
            String query = "SELECT GPA FROM Classes WHERE courseID = ? && year = ? && semster = ?";
            stmt = retrieve(course, year, semester);
        }

        ResultSet rs = stmt.executeQuery();
        System.out.println(rs);

        if (!rs.next()) {
            System.out.println("Data Not Found :(");
            return null;
        } else {
            LinkedHashMap<String, Integer> gpaData = new LinkedHashMap<>();
            gpaData.put("A", 0);
            gpaData.put("B", 0);
            gpaData.put("C", 0);
            gpaData.put("D", 0);
            gpaData.put("F", 0);
            gpaData.put("W", 0);

            while (rs.next()) {
                String gpa = rs.getString("GPA");
                gpaData.put(gpa, gpaData.get(gpa) + 1);
            }

            System.out.println(gpaData);

            return gpaData;
        }
    }

    private PreparedStatement retrieveAllCourse(String year, String semester) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes where year = ? && semester = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, year);
        stmt.setString(2, semester);

        return stmt;
    }

    private PreparedStatement retrieveAllYear(String courseID, String semester) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes WHERE courseID = ? && semester = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,courseID);
        stmt.setString(2,semester);

        return stmt;
    }

    private PreparedStatement retrieveAllSemester(String courseID, String year) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes WHERE courseID = ? && year = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,courseID);
        stmt.setString(2,year);

        return stmt;
    }



    private PreparedStatement retrieveAllCouurseYear(String semester) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes WHERE semester = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,semester);

        return stmt;
    }

    private PreparedStatement retrieveAllCourseSemester(String year) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes WHERE year = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,year);

        return stmt;
    }

    private PreparedStatement retrieveAllYearSemester(String courseID) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes WHERE courseID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,courseID);

        return stmt;
    }

    private PreparedStatement retrieveAllGPA() throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes";
        PreparedStatement stmt = conn.prepareStatement(query);

        return stmt;
    }

    private PreparedStatement retrieve(String courseID, String year, String semester) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String query = "SELECT GPA FROM Classes WHERE courseID = ? && year = ? && semester = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,courseID);
        stmt.setString(2,year);
        stmt.setString(3,semester);

        return stmt;
    }

}
