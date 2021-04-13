package gui;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Connexion {

    public Connection connect() {
        // SQLite connection string (à modifier selon le path)
        String url = "jdbc:sqlite:/Users/wassimbenjabria/documents/ece/inge4/pcb/projet/class_attendance_GUI/students.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT * FROM students";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println("ID = " + rs.getInt("ID"));
                System.out.println("LastName = " + rs.getString("LastName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> retunData (){
        String sql = "SELECT * FROM students";
        List<Student> data = new ArrayList<Student>();
        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                // Recuperation base de données
                int ID = rs.getInt("ID");
                String lastName = rs.getString("LastName");
                // Ajout dans la liste
                data.add(new Student("1256777",lastName,"zz","dd", "d"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }


}