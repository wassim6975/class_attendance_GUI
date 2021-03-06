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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
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
    public List<Student> dataNew = new ArrayList<Student>();
    ObservableList<Student> observableList = FXCollections.observableArrayList(

    );
    public ObservableList<Student> observablePresent = FXCollections.observableArrayList(

    );
    ObservableList<Student> observableAbsent = FXCollections.observableArrayList(

    );    
       public String class_start;

    /**
     * Initializes the controller class.
     */

    public void testSerialCom() {
        //

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

    @FXML
    private void handleButtonAddStudent(ActionEvent event) throws IOException {
        try {
            Parent root3 = FXMLLoader.load(getClass().getResource("FXML_AddStudentController.fxml"));
            Stage stage3 = new Stage();
            stage3.setTitle("ECE class attendance");
            stage3.setScene(new Scene(root3));
            stage3.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    public void handleButtonRefreshStudents(ActionEvent actionEvent) {
        observableList.removeAll(data);
        tableViewStudents.setItems(observableList);

        //Connexion ?? la base de donn??es sqlite
        Connexion connexion = new Connexion();
        connexion.connect();

        data = connexion.retunData ();
        for (int i = 0; i < data.size(); i++) {
            observableList.add(data.get(i));
            System.out.println(data.get(i));
            //tableViewPresent.setItems(observableList);
        }
    }

    public String getHour(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        do{
            class_start = JOptionPane.showInputDialog("Class start at (HH:MM) : ");
            }while(class_start.isEmpty());
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

        //Connexion ?? la base de donn??es sqlite
        Connexion connexion = new Connexion();
        connexion.connect();
        // R??cuperation des donn??es venant de la base de donn??es
        data = connexion.retunData ();
        tableViewPresent.setItems(observablePresent);
        tableViewAbsent.setItems(observableAbsent);
        
        //Remplissage du tableau avec la base de donn??es
       for (int i = 0; i < data.size(); i++) {
           observableList.add(data.get(i));
       }
       tableViewStudents.setItems(observableList);

        Arduino arduino = new Arduino();
        arduino.enable();
        System.out.println(arduino.getListeningEvents());

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                // Toutes secondes
                // Tentative de lecture du port com
                //arduino.getData();
                if (arduino.getData().isEmpty() != true)
                {
                    int i = 0;
                    String dataSerial = "";
                    dataSerial = dataSerial + arduino.getData().get(0);
                    while (dataSerial.length() != 8)
                    {
                        i = i + 1;
                        dataSerial = dataSerial + arduino.getData().get(i);
                    }
                    System.out.println("donn??es tableau : "+dataSerial);
                    // Changement date et heure
                    connexion.changeDB(dataSerial,getDate(),getHour());
                    dataNew = connexion.retunData ();

                    // Recherche d'un ID similaire ?? ceux dans la base de donn??es
                    for (int j = 0; j < dataNew.size(); j++) {
                        String idBD = dataNew.get(j).getID();
                        if (idBD.equals(dataSerial)) {
                            System.out.println(dataNew.get(j).getFirstName()+"ID enregistr?? dans la base de donn??es");
                            if(isLate(class_start,getHour())){
                                observableAbsent.add(dataNew.get(j));
                                tableViewAbsent.setItems(observableAbsent);
                                observablePresent.add(dataNew.get(j));
                                tableViewPresent.setItems(observablePresent);
                            }
                            else{
                                // Ajout dans le tableau de pr??sence
                            observablePresent.add(dataNew.get(j));
                            tableViewPresent.setItems(observablePresent);
                            }
                        } else{
                            System.out.println("Badge/Carte non connu, veuillez l'ajouter");
                        }
                    }
                    // Suppression data
                    arduino.removeDate();
                }

            }
        }, 0, 2000);

    }
    
    public boolean isLate(String startClass,String currentDate) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm");
        try {
        Date startDateObj = sdFormat.parse(startClass);
        Date endDateObj = sdFormat.parse(currentDate);
        
        long timeDiff = endDateObj.getTime() - startDateObj.getTime();
        long minDiff = timeDiff / (1000 * 60);
        if (minDiff > 5)
        {
            JOptionPane.showMessageDialog(null, "You are late");
            return true;
        }
        return false;
       
        } catch (ParseException e) {
            e.printStackTrace();
        }
    return false;
    }
}
