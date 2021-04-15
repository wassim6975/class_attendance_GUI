/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author vithu
 */

public class FXML_searchController implements Initializable {
    public List<String> dataSearch = new ArrayList<String>();
    @FXML
    private Label label_ID;
    @FXML
    private Label label_date;
    @FXML
    private Label label_hours;
    @FXML
    private TextField firstNameSearch;
    @FXML
    private TextField lastNameSearch;
    /**
    /**
     *
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try {
            //Connexion à la base de données sqlite
            Connexion connexion = new Connexion();
            connexion.connect();
            String firstNameIN = firstNameSearch.getText();
            String lastNameIN = lastNameSearch.getText();
            // Récuperation des données venant de la base de données
            dataSearch = connexion.searchStudent(firstNameIN,lastNameIN);
            label_ID.setText(dataSearch.get(0));
            label_date.setText(dataSearch.get(1));
            label_hours.setText(dataSearch.get(2));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
