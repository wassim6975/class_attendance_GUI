/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IDConnection {
    public boolean check(String usr, String pwd) throws FileNotFoundException {
        boolean login = true;
        File file_managerId = new File("./src/gui/Professor.txt");
        //File file_managerId = new File("C:\\Users\\vithu\\OneDrive\\Documents\\INGE4\\S8\\Projets technologiques\\ClassAttendanceProject\\class_attendance_GUI\\GUI\\src\\gui\\Professor.txt");
        Scanner input = new Scanner(file_managerId);
        while (input.hasNext()) {
            if (usr.equals(input.next()) && pwd.equals(input.next())) {
                login = true;
                return login;
            } else {
                login = false;
            }
        }
        return login;
    }
}
