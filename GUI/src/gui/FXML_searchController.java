/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author vithu
 */

public class FXML_searchController implements Initializable {
    @FXML
    private ImageView img_photo;
    @FXML
    private Label label_lastName;
    @FXML
    private Label label_firstName;
    @FXML
    private Label label_studentID;
   
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
