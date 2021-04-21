#include <MFRC522.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <SPI.h>

#define OLED_RESET 4
Adafruit_SSD1306 display(OLED_RESET);

//Affectation des broches 
#define SS_PIN 10
#define RST_PIN 9
 
MFRC522 rfid(SS_PIN, RST_PIN); // Instance of the class

MFRC522::MIFARE_Key key; 

String uidString;
int LED = 8;
int BUZZER = 7;
void setup() {
  
  Serial.begin(9600); //Initialisation du port série
  SPI.begin(); // Initialisation du bus SPI
  rfid.PCD_Init(); // Initialisation du RFID 
  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);  // initialize with the I2C addr 0x3D (for the 128x64)

  pinMode (LED,OUTPUT);
  pinMode (BUZZER, OUTPUT);
  
  // Clear the buffer.
  display.clearDisplay();
  display.display();
  display.setTextColor(WHITE); // or BLACK);
  display.setTextSize(1);
  display.setCursor(10,0); 
  display.print("ECE Paris");
  display.display();
  
}

void loop() {
  if(  rfid.PICC_IsNewCardPresent())
  {
      readRFID();
      digitalWrite(LED,HIGH);
      tone(BUZZER, 1000);
      delay(500);
  }
  noTone(BUZZER);
  digitalWrite(LED,LOW);
}

void readRFID()
{
  
  rfid.PICC_ReadCardSerial();

  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
  clearUID();
   
  printHex(rfid.uid.uidByte, rfid.uid.size);

  uidString = String(rfid.uid.uidByte[0])+" "+String(rfid.uid.uidByte[1])+" "+String(rfid.uid.uidByte[2])+ " "+String(rfid.uid.uidByte[3]);
    
  printUID();

    // Halt PICC
  rfid.PICC_HaltA();

  // Stop encryption on PCD
  rfid.PCD_StopCrypto1();
}

void printHex(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    //Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], HEX);
  }
}

void clearUID()
  { 
    display.setTextColor(BLACK); // or BLACK);
    display.setTextSize(1);
    display.setCursor(30,20);
    display.print(uidString); 
    //display.display();
  }

  void printUID()
  {
    display.setTextColor(WHITE); //Couleur du texte en BLANC
    display.setTextSize(1);      //Taille du texte à 1
    display.setCursor(0,20);     //Postion du curseur (x,y): initialise la position du texte
    display.print("UID: ");      //Affichage de texte
    display.setCursor(30,20);    //Position du curseur: initialise la position du texte
    display.print(uidString);    //Affichage de l'ID
    display.display();           //Afficher le contenu du buffer à l'écran
  }

  
