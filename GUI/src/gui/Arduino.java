package gui;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

    public class Arduino implements SerialPortDataListener{
    public List<String> Sdata = new ArrayList<>();
    public List<Student> dta = new ArrayList<Student>();

    public void enable() {
        SerialPort comPort = SerialPort.getCommPort("COM4");
        System.out.println(comPort.getDescriptivePortName());
        comPort.openPort();
        comPort.setBaudRate(9600);
        comPort.addDataListener(this);
        //SerialPortEvent event = new SerialPortEvent();
        //byte[] newData = event.getReceivedData();
        //System.out.println(newData);
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String DataSerial = new String(newData);
        System.out.println(new String(newData));

        Connexion connexion = new Connexion();
        connexion.connect();
        dta = connexion.retunData ();
        Sdata.add(new String(newData));

    }

    public List<String> getData(){
        return Sdata;
    }

    public void removeDate() {
        Sdata.removeAll(Sdata);
    }

}

