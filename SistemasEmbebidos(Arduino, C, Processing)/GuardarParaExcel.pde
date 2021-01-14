String DataIn;

import processing.serial.*; 
Serial myPort;    

Table tabela;

int i = 0;

void setup() 
{
  myPort = new Serial(this, "COM6", 9600);

  myPort.bufferUntil(10);
     
  tabela = new Table();
  tabela.addColumn("Altura Agua");
  tabela.addColumn("pH");
  tabela.addColumn("CO");
  tabela.addColumn("CO2");
  tabela.addColumn("Temperatura");
  tabela.addColumn("Humidade");
  tabela.addColumn("Humidade Solo"); 
  
  size(200,200);
}

void serialEvent(Serial p) { 
    DataIn = p.readString(); 
    // println(DataIn);
}

void draw() 
{ 
  TableRow newRow = tabela.addRow();
  delay(3000);
  if(i>3)
  {
    println("Altura da agua(cm)\tpH\tCO(ppm)\tCO2(ppm)\tTemepratura(ºC)\tHumidade(%)\tHumidade Solo(%)");
    //print(DataIn);
    float separa[] = float(split(DataIn, ','));
    //for(int j=0; j < 7; j++)
      //println(separa[j]);
    newRow.setFloat("Altura Agua", separa[0]);
    newRow.setFloat("pH", separa[1]);
    newRow.setFloat("CO", separa[2]);
    newRow.setFloat("CO2", separa[3]);
    newRow.setFloat("Temperatura", separa[4]);
    newRow.setFloat("Humidade", separa[5]);
    newRow.setFloat("Humidade Solo", separa[6]);
    println(separa[0]+"\t\t"+separa[1]+"\t"+separa[2]+"\t"+separa[3]+"\t"+separa[4]+"\t\t"+separa[5]+"\t\t"+separa[6]);
    println("------------------------------------------------------");
    if(separa[0] >= 3)
      println("Aumento significativo da agua! -> Abrir torneira");
    if(separa[0] <= 1)
      println("Perda de agua detetada!  -> Ligar bomba");
    if(separa[1] > 8 && separa[1] < 10)
      println("O pH esta um pouco basico!");
    if(separa[2] > 10 || separa[3] > 5)
      println("Fumo Detetado! -> Fechar a lona");
    if(separa[4] < 14)
      println("Esta um pouco de frio la fora!");
    if(separa[4] > 20)
      println("Esta um otimo dia de calor! -> Abrir Lona");
    if(separa[5] > 40)
      println("Detetada humidade elevada no solo(Indício de fuga de água)! -> Verificar se a torneira esta fechada");
    println("------------------------------------------------------");
  }
  i++;
}

void keyPressed() {
  saveTable(tabela, "arduino.csv");
  exit();
}
