/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author vithu
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label_date;
    @FXML
    private Label label_hour;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("FXML_Content.fxml"));
            Stage stage1 = new Stage();
            stage1.setTitle("ECE class attendance");
            stage1.setScene(new Scene(root1));
            stage1.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("You clicked me!");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        label_date.setText(getDate());
        label_hour.setText(getHour());
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
}
