//Sensor PH
#define SensorPin A1           
#define Offset 0.00
int i = 0;
static float pHValue = 0;
static float Voltagem = 0;
static float mediaVoltagem = 0;
static float SumVoltagem = 0;
static float VoltagemFinal = 0;


//Temperatura Exterior
#include "DHT.h"
#define DHTPIN 2 
#define DHTTYPE DHT11 
DHT dht(DHTPIN, DHTTYPE);


//Qualidade do ar
#include <Servo.h>
#include <MQUnifiedsensor.h>
#define placa "Arduino UNO"
#define Voltage_Resolution 5
#define pin A0
#define type "MQ-135"
#define ADC_Bit_Resolution 10
#define RatioMQ135CleanAir 3.6
MQUnifiedsensor MQ135(placa, Voltage_Resolution, ADC_Bit_Resolution, pin, type);
//Lona
Servo servo_12;
int pos = 0;
int lona = 0;

//Ultrassons(Altura da agua)
//Sensor
const int TRIG = 4;
const int ECHO = 5;
long duration; 
long distancia;
//Variaveis auxiliares
long altura;
int profundidade = 11;//Altura do balde
//Altura minima e maxima do nivel da água
int alturaMax = 3;
int alturaMin = 1;
//Bomba/Torneira
Servo servo_10;
Servo servo_11;
int posB = 0;
int posT = 0;
int torneira = 0;
int bomba = 0;

//Humidade do solo
int pino_humidade = A2;
int humidade;


void setup(void)
{
  //Monitor Serie
  Serial.begin(9600);
  
  //Temperatura Exterior
  dht.begin();
  
  //Qualidade do ar
  MQ135.setRegressionMethod(1);
  MQ135.init(); 
  float calcR0 = 0;
  for(int i = 1; i<=10; i ++)
  {
    MQ135.update();
    calcR0 += MQ135.calibrate(RatioMQ135CleanAir);
  }
  MQ135.setR0(calcR0/10);
  //Lona
  servo_12.attach(12);
  
  //Ultrassons
  //Configurações do Sensor
  pinMode(TRIG,OUTPUT);
  pinMode(ECHO,INPUT);
  //Bomba/Torneira
  servo_10.attach(10);
  servo_11.attach(11);
}

void loop(void)
{
  //Serial.println("-----------------------");
  //Serial.println("Sensor de Ultrasssons");
  //Serial.println("-----------------------");
  nivel_agua();
  //Serial.println("-----------------------");
  //Serial.println("Sensor de pH");
  //Serial.println("-----------------------");
  pH();
  //Serial.println("-----------------------");
  //Serial.println("Sensor de Qualidade do Ar");
  //Serial.println("-----------------------");
  qualidade();
  delay(2000);
  //Serial.println("-----------------------");
  //Serial.println("Sensor de Temperatura e Humidade");
  //Serial.println("-----------------------");
  tempExterior();
  //Serial.println("-----------------------");
  //Serial.println("Sensor da Humidade do Solo");
  //Serial.println("-----------------------");
  humidade_solo();
  Serial.println(""); 
}


void nivel_agua()
{
  distancia = lerUltrassons(TRIG,ECHO);
  altura = profundidade - distancia;
  //Serial.print("Nivel da agua:");Serial.print(altura);Serial.print("cm");Serial.println("");
  Serial.print(altura);Serial.print(","); 
  
  if(altura <= alturaMin)
  {
    //Serial.println("Perda de agua detetada!");
    abrir_bomba();    
  }
  else
    fechar_bomba();

  if(altura >= alturaMax)
  {
    //Serial.println("Aumento significativo da agua!");
    abrir_torneira();
  }
  else
    fechar_torneira();
}

int lerUltrassons(int trig, int echo)
{
  digitalWrite(trig, LOW);
  delayMicroseconds(5);
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(trig, LOW);
  pinMode(echo, INPUT);
  duration = pulseIn(echo, HIGH);
 
  //Devolve o tempo em distancia(cm)
  return (duration/2) / 29.1;
} 

void abrir_bomba()
{
  //Liga a bomba para encher a piscina
  if(posB > 0)posB = posB;
  else
  for (posB = 0; posB <=120; posB +=10) {
     servo_10.write(posB);
     delay(50);
    }
    bomba = 1;
}

void fechar_bomba()
{
  //Desliga a bomba para encher a piscina
  if(posB < 120)posB = posB;
  else
    for (posB = 120; posB >= 0; posB -= 10) {
     servo_10.write(posB);
     delay(50);
   } 
   bomba = 0;
}

void abrir_torneira()
{
  //Abre a torneira, pois existe agua a mais
  if(posT > 0)posT = posT;
  else
  for (posT = 0; posT <=120; posT +=10) {
     servo_11.write(posT);
     delay(50);
    }
  torneira = 1;
}

void fechar_torneira()
{
  //Fecha a torneira para manter o nivel de agua
  if(posT < 120)posT = posT;
  else
    for (posT = 120; posT >= 0; posT -= 10) {
     servo_11.write(posT);
     delay(50);
   }
   torneira = 0;
}


void pH()
{
  mediaVoltagem = 0;
  SumVoltagem = 0;
  VoltagemFinal = 0;
  while(i < 5)
  {
    Voltagem = analogRead(SensorPin);
    SumVoltagem += Voltagem;
    i++;
  }
  if(i == 5)
  {
    mediaVoltagem = (SumVoltagem/5);
    VoltagemFinal = mediaVoltagem*5/1024;
    i=0;
  }
  pHValue = 3.5*VoltagemFinal+Offset - 1,1175;
  //Serial.print("pH: ");
  Serial.print(pHValue,2);Serial.print(","); 
}


void qualidade()
{
  MQ135.update();
  MQ135.setA(605.18); MQ135.setB(-3.937);
  float CO = MQ135.readSensor();
  
  MQ135.setA(110.47); MQ135.setB(-2.862);
  float CO2 = MQ135.readSensor();

  //Serial.print("CO:");Serial.print(CO);Serial.println(""); 
  //Serial.print("CO2:");Serial.print(CO2);Serial.println(""); 

  Serial.print(CO);Serial.print(","); 
  Serial.print(CO2);Serial.print(","); 
  
  if(CO > 10 || CO2 > 5)
    fechar_lona();
  else
    abrir_lona();
}

void abrir_lona()
{
  //Abre a torneira, pois existe agua a mais
  if(pos > 0)pos = pos;
  else
  for (pos = 0; pos <=120; pos +=10) {
     servo_12.write(pos);
     delay(50);
    }
    lona = 1;
}

void fechar_lona()
{
  //Fecha a torneira para manter o nivel de agua
  if(pos < 120)pos = pos;
  else
    for (pos = 120; pos >= 0; pos -= 10) {
     servo_12.write(pos);
     delay(50);
   }
   lona = 0;
}


void tempExterior()
{
  float t = dht.readTemperature();
  float h = dht.readHumidity();
  //Serial.print("Temperatura:");Serial.print(t);Serial.println("");
  //Serial.print("Humidade:");Serial.print(h);Serial.println("");
  if(t > 20 && lona == 0)
    abrir_lona();
  Serial.print(t);Serial.print(",");
  Serial.print(h);Serial.print(",");
}


void humidade_solo()
{ 
   humidade = analogRead(pino_humidade);
   humidade = map(humidade,1024,0,0,100);
   //Serial.print("Humidade do solo : ");
   if(humidade > 40 && torneira == 1)
    fechar_torneira();
   Serial.print(humidade);Serial.print(",");
   //Serial.println("%");
}
