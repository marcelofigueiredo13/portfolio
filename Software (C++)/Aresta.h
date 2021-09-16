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
    int V1;//N�mero do primeiro v�rtice de uma aresta
    int V2;//N�mero do segundo v�rtice de uma aresta
    public:
        Aresta(int V1,int V2);//Constr�i uma aresta com dois v�rtices
        virtual ~Aresta();
        int Get_V1(){return V1;}
        int Get_V2(){return V2;}
};

#endif // ARESTA_H
