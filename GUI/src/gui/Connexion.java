package gui;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.lang.String;

public class Connexion {

    public Connection connect() {
        // SQLite path
        String url = "jdbc:sqlite:./../students.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "SELECT * FROM students";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println("ID = " + rs.getString("ID"));
                System.out.println("LastName = " + rs.getString("LastName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> retunData() {
        String sql = "SELECT * FROM students";
        List<Student> data = new ArrayList<Student>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                // Recuperation base de données
                String ID = rs.getString("ID");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String Date = rs.getString("Date");
                String Hours = rs.getString("Hours");
                // Ajout dans la liste
                data.add(new Student(ID, lastName, firstName, Date, Hours));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }


    public List<String> searchStudent(String FirstNameIn, String LastNameIn) {
        //System.out.println("firstName :" + FirstNameIn);
        //System.out.println("LastName : " + LastNameIn);
        String sql = "SELECT * FROM students WHERE LastName = \"" + LastNameIn + "\" AND FirstName =\"" + FirstNameIn + "\";";
        //System.out.println(sql);
        //String sql =  "SELECT * FROM students WHERE LastName = \"BEN JABRIA\" AND FirstName = \"Wassim\";";
        List<String> data = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                // Recuperation base de données
                String ID = rs.getString("ID");
                String Date = rs.getString("Date");
                String Hours = rs.getString("Hours");
                // Ajout dans la liste
                data.add(ID);
                data.add(Date);
                data.add(Hours);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //
        return data;

    }
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    public String getHour(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void addDataDB (String ID1, String lastNameIn, String FirstNameIn) {

        String INSERT_SQL = "INSERT INTO students(ID, LastName, FirstName, Date, Hours) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();

            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, ID1);
            ps.setString(2, lastNameIn);
            ps.setString(3, FirstNameIn);
            ps.setString(4, getDate());
            ps.setString(5, getHour());  // You'll have to update this each and every year. BirthDate would be better.
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // to change hours and date in the DB
    public void changeDB (String ID, String Date, String Hours) {

        String INSERT_SQL = "UPDATE students set Date ? , set Hours ? WHERE ID = ?";
        //UPDATE students set date = "12:48" WHERE ID = "D73A57B3";
        PreparedStatement ps = null;
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();

            ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, ID);
            ps.setString(2, Date);
            ps.setString(3, Hours);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}