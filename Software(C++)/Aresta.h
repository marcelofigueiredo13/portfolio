#ifndef ARESTA_H
#define ARESTA_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
using namespace std;

class Modelo;

class Aresta
{
    int V1;//Número do primeiro vértice de uma aresta
    int V2;//Número do segundo vértice de uma aresta
    public:
        Aresta(int V1,int V2);//Constrói uma aresta com dois vértices
        virtual ~Aresta();
        int Get_V1(){return V1;}
        int Get_V2(){return V2;}
};

#endif // ARESTA_H
