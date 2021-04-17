package gui;

import com.fazecast.jSerialComm.*;


public class MessageListner  implements SerialPortMessageListener {
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte)0x0B, (byte)0x65 }; }

    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }

    @Override
    public void serialEvent(SerialPortEvent event)
    {
        byte[] delimitedMessage = event.getReceivedData();
        System.out.println("Received the following delimited message: " + delimitedMessage);
    }
}