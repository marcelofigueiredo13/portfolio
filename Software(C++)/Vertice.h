#ifndef VERTICE_H
#define VERTICE_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
using namespace std;

class Modelo;

class Vertice
{
    float x;//Abcissa do vértice
    float y;//Ordenada do vértice
    float z;//Cota do vértice
    public:
        Vertice(float x,float y,float z);//Constrói um vértice com 3 valores (x,y,z)
        virtual ~Vertice();
        float Get_x(){return x;}
        float Get_y(){return y;}
        float Get_z(){return z;}
};

#endif // VERTICE_H
