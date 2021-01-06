#ifndef TIPO_H
#define TIPO_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
using namespace std;

class Tipo
{
    string tipo;
    string modelo;
    public:
    Tipo(string tipo, string modelo);
    ~Tipo();
    string Get_Tipo(){return tipo;}
    string Get_Modelo(){return modelo;}
};

#endif // TIPO_H
