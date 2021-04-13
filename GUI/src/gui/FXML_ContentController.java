/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author vithu
 */
public class FXML_ContentController implements Initializable {
    //public TableView table = new TableView();
    @FXML
    public TableColumn<Student, String> IDColumn;
    public TableColumn<Student, String> LastNameColumn;
    public TableColumn<Student, String> FirstNameColumn;
    public TableColumn<Student, String> DateColumn;
    public TableColumn<Student, String> HourColumn;
    public TableView<Student> tableViewStudents;

    /**
     * Initializes the controller class.
     */

    @FXML
    private void handleButtonFilter(ActionEvent event) throws IOException {
        // TODO
    }

    @FXML
    private void UpdateRows () {
        //ID.setCellValueFactory(new IDProperty("firstName"));
        //ID.setCellValueFactory(new PropertyValueFactory<IDProperty, String>("firstName"));
        //ID.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //ID.setCellValueFactory();
        //TableView<String> tableViewStudents;
        //TableColumn<String, String> ID;
        //final TableView tableView = new TableView();
        //tableView .getItems().setAll("car1", "car2", "car3", "car4");
    }

    @FXML
    private void handleButtonSearch(ActionEvent event) throws IOException {
        try {
            Parent root3 = FXMLLoader.load(getClass().getResource("FXML_search.fxml"));
            Stage stage3 = new Stage();
            stage3.setTitle("ECE class attendance");
            stage3.setScene(new Scene(root3));
            stage3.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        HourColumn.setCellValueFactory(new PropertyValueFactory<>("Hours"));

        tableViewStudents.setEditable(true);

        ObservableList<Student> observableList = FXCollections.observableArrayList(
                new Student("1256777","Kebab","zz","dd", "d"),
                new Student("12737731","ECE","zz","dd", "d")
        );
        tableViewStudents.setItems(observableList);
    }    
    
}