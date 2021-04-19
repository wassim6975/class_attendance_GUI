package gui;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class Arduino implements SerialPortDataListener{

    public void enable() {
        SerialPort comPort = SerialPort.getCommPort("COM4");
        System.out.println(comPort.getDescriptivePortName());
        comPort.openPort();
        comPort.setBaudRate(9600);
        comPort.addDataListener(this);
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        System.out.println(newData);
    }
}

