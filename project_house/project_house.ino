#include "FirebaseESP8266.h"
#include <ESP8266WiFi.h>
#include <SD.h>

#define FIREBASE_HOST "Colocar tu HOST"
#define FIREBASE_AUTH "Colocar tu AUTH"

#define do_h_pin D1
#define do_a_pin D2
#define ar_l_pin D3
#define pu_l_pin D5
#define pr_l_pin D6
#define ri_l_pin D7
#define le_l_pin D8

FirebaseData firebaseData;

const char* ssid = "Nombre de tu red wifi";
const char* password = "Contraseña";

void setup() {

  initializePin();
  // put your setup code here, to run once:
  WiFi.begin(ssid, password);
  Serial.begin(9600);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500); 
  }
  
  Serial.println("");
  Serial.print("Se conectó al wifi! ");
  Serial.print(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}
String dato1 = "0";
String dato2 = "0";
String dato3 = "0";
String dato4 = "0";
String dato5 = "0";
String dato6 = "0";
String dato7 = "0";

void loop() {

  if(Firebase.getString(firebaseData,"/door_hotel")){
    dato1 = firebaseData.stringData();
      if(dato1 == "1"){
      digitalWrite(do_a_pin, HIGH);
      delay(200);
      digitalWrite(do_a_pin, LOW);
      Firebase.setString(firebaseData,"/door_hotel","0");    
      }
  }else{
    Serial.println(firebaseData.errorReason());
  }

  if(Firebase.getString(firebaseData,"/door_house")){
    dato2 = firebaseData.stringData();
      if(dato2 == "1"){
      digitalWrite(do_h_pin, HIGH);
      delay(200);
      digitalWrite(do_h_pin, LOW);
      Firebase.setString(firebaseData,"/door_house","0");
      }
  }else{
    Serial.println(firebaseData.errorReason());
  }

  if(Firebase.getString(firebaseData,"/area_light")){
    dato3 = firebaseData.stringData();
    if(dato3 == "1"){
    digitalWrite(ar_l_pin, HIGH);
    }else{
    digitalWrite(ar_l_pin, LOW);
    }
  }else{
    Serial.println(firebaseData.errorReason());
  }

  if(Firebase.getString(firebaseData,"/publicity_light")){
    dato4 = firebaseData.stringData();
    if(dato4 == "1"){
    digitalWrite(pu_l_pin, HIGH);
    }else{
    digitalWrite(pu_l_pin, LOW);
    }
  }else{
    Serial.println(firebaseData.errorReason());
  }

  if(Firebase.getString(firebaseData,"/principal_light")){
    dato5 = firebaseData.stringData();
    if(dato5 == "1"){
    digitalWrite(pr_l_pin, HIGH);
    }else{
    digitalWrite(pr_l_pin, LOW);
    }
  }else{
    Serial.println(firebaseData.errorReason());
  }

  if(Firebase.getString(firebaseData,"/right-light")){
    dato6 = firebaseData.stringData();
    if(dato6 == "1"){
    digitalWrite(ri_l_pin, HIGH);
    }else{
    digitalWrite(ri_l_pin, LOW);
    }
  }else{
    Serial.println(firebaseData.errorReason());
  }

  if(Firebase.getString(firebaseData,"/left_light")){
    dato7 = firebaseData.stringData();
    if(dato7 == "1"){
    digitalWrite(le_l_pin, HIGH);
    }else{
    digitalWrite(le_l_pin, LOW);
    }
  }else{
    Serial.println(firebaseData.errorReason());
  }
  
}

void initializePin(){
  
  pinMode(do_h_pin, OUTPUT);
  pinMode(do_a_pin, OUTPUT);
  pinMode(ar_l_pin, OUTPUT);
  pinMode(pu_l_pin, OUTPUT);
  pinMode(pr_l_pin, OUTPUT);
  pinMode(ri_l_pin, OUTPUT);     
  pinMode(le_l_pin, OUTPUT); 

  digitalWrite(do_a_pin, LOW);
  digitalWrite(do_h_pin, LOW);
  digitalWrite(ar_l_pin, LOW);
  digitalWrite(pu_l_pin, LOW);
  digitalWrite(pr_l_pin, LOW);
  digitalWrite(ri_l_pin, LOW);
  digitalWrite(le_l_pin, LOW);
}
