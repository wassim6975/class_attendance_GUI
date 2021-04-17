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
import java.util.*;
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
import com.fazecast.jSerialComm.*;

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
    public TableColumn<Student, String> IDColumnP;
    public TableColumn<Student, String> LastNameColumnP;
    public TableColumn<Student, String> FirstNameColumnP;
    public TableColumn<Student, String> DateColumnP;
    public TableColumn<Student, String> HourColumnP;
    public TableColumn<Student, String> IDColumnA;
    public TableColumn<Student, String> LastNameColumnA;
    public TableColumn<Student, String> FirstNameColumnA;
    public TableColumn<Student, String> DateColumnA;
    public TableColumn<Student, String> HourColumnA;
    public TableView<Student> tableViewStudents;
    public TableView<Student> tableViewPresent;
    public TableView<Student> tableViewAbsent;
    public List<Student> data = new ArrayList<Student>();

    ObservableList<Student> observableList = FXCollections.observableArrayList(
            new Student("1855637","Castex","Jean","24/03/2021", "15:21"),
            new Student("1256777","Emmanuel","Macron","13/04/2021", "17:33")
    );
    ObservableList<Student> observablePresent = FXCollections.observableArrayList(
            new Student("1855637","LEE","Tar","24/03/2021", "15:21"),
            new Student("1256777","Marie","Sofie","13/04/2021", "17:33")
    );
    ObservableList<Student> observableAbsent = FXCollections.observableArrayList(
            new Student("1855637","Lancelot","Bob","24/03/2021", "15:21"),
            new Student("1256777","King","Rafael","13/04/2021", "17:33")
    );


    /**
     * Initializes the controller class.
     */


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

    @FXML
    private void handleButtonAddStudent(ActionEvent event) throws IOException {
        try {
            Parent root3 = FXMLLoader.load(getClass().getResource("FXML_AddStudentController.fxml"));
            Stage stage3 = new Stage();
            stage3.setTitle("ECE class attendance");
            stage3.setScene(new Scene(root3));
            stage3.show();
        } catch (Exception e) {
        }
    }
    public void handleButtonRefreshStudents(ActionEvent actionEvent) {
        observableList.removeAll(data);
        tableViewStudents.setItems(observableList);

        //Connexion à la base de données sqlite
        Connexion connexion = new Connexion();
        connexion.connect();

        data = connexion.retunData ();
        for (int i = 0; i < data.size(); i++) {
            observableList.add(data.get(i));
            System.out.println(data.get(i));
            tableViewPresent.setItems(observableList);
        }
    }

    private void serialData()  {
        String ID = "";
        System.out.println(SerialPort.getCommPorts()[0]);
        SerialPort ComPort = SerialPort.getCommPorts()[0];
        ComPort.setBaudRate(9600);
        ComPort.openPort();
        System.out.println("Connected:" + ComPort.getDescriptivePortName());

        byte[] newData = new byte[ComPort.bytesAvailable()];
        // lecture des données
        int numRead = ComPort.readBytes(newData, newData.length);
        if (numRead > 0) {
            System.out.println("Read " + numRead + " bytes: " + new String(newData));

            // conversion bytes to String
            ID = new String(newData);

            tableViewPresent.setItems(observablePresent);

            //Connexion à la base de données sqlite
            Connexion connexion = new Connexion();
            connexion.connect();
            data = connexion.retunData ();

            /*if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN)
                System.out.println("All bytes were successfully transmitted!");
            }*/

            // Recherche d'un ID similaire à ceux dans la base de données
            for (int i = 0; i < data.size(); i++) {
                String idBD = data.get(i).getID();
                if (idBD.equals(ID)) {
                    System.out.println(data.get(i).getFirstName()+"ID enregistré dans la base de données");
                    // Ajout dans le tableau de présence
                    observablePresent.add(data.get(i));
                    tableViewPresent.setItems(observablePresent);
                    //
                } else{
                    System.out.println("Badge/Carte non connu, veuillez l'ajouter");
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        HourColumn.setCellValueFactory(new PropertyValueFactory<>("Hours"));

        IDColumnP.setCellValueFactory(new PropertyValueFactory<>("ID"));
        LastNameColumnP.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        FirstNameColumnP.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        DateColumnP.setCellValueFactory(new PropertyValueFactory<>("Date"));
        HourColumnP.setCellValueFactory(new PropertyValueFactory<>("Hours"));

        IDColumnA.setCellValueFactory(new PropertyValueFactory<>("ID"));
        LastNameColumnA.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        FirstNameColumnA.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        DateColumnA.setCellValueFactory(new PropertyValueFactory<>("Date"));
        HourColumnA.setCellValueFactory(new PropertyValueFactory<>("Hours"));

        tableViewStudents.setEditable(true);
        tableViewPresent.setEditable(true);
        tableViewAbsent.setEditable(true);

        //Connexion à la base de données sqlite
        Connexion connexion = new Connexion();
        connexion.connect();
        // Récuperation des données venant de la base de données
        data = connexion.retunData ();


        tableViewPresent.setItems(observablePresent);
        tableViewAbsent.setItems(observableAbsent);

        //Remplissage du tableau avec la base de données
       for (int i = 0; i < data.size(); i++) {
           observableList.add(data.get(i));
       }
       tableViewStudents.setItems(observableList);


       // test .........................................
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.setBaudRate(9600);
        comPort.openPort();
        MessageListner listener = new MessageListner();
        comPort.addDataListener(listener);
        try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
        byte[] newData = new byte[comPort.bytesAvailable()];
        System.out.println(new String(newData));
        // fin test
        //comPort.removeDataListener();
        //comPort.closePort();


        Timer t = new Timer();
        /*t.schedule(new TimerTask() {
            public void run() {
                // Toutes les 0.5 secondes
                // Tentative de lecture port com
                //serialData();

                SerialPort comPort = SerialPort.getCommPorts()[0];
                comPort.openPort();
                MessageListner listener = new MessageListner();
                comPort.addDataListener(listener);
                try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
                comPort.removeDataListener();
                comPort.closePort();

                //System.out.println("Tentative de lecture de carte");
            }
        }, 0, 500);*/
        // test

    }
}