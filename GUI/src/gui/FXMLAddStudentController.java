package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FXMLAddStudentController {
    @FXML
    private TextField firstNameAdd;
    @FXML
    private TextField lastNameAdd;
    @FXML
    private TextField IDAdd;

    public void handleButtonAction(ActionEvent actionEvent) {
        // add in DB ...........

        //Connexion à la base de données sqlite
        Connexion connexion = new Connexion();
        connexion.connect();

        String FirstNameIn = firstNameAdd.getText();
        String lastNameIn = lastNameAdd.getText();
        String ID1 = IDAdd.getText();
        connexion.addDataDB(ID1, lastNameIn, FirstNameIn);

        // add in tablewiew student
        System.out.println("Student added");
    }

}
