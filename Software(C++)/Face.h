#ifndef FACE_H
#define FACE_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
using namespace std;

class Modelo;

class Face
{
    int V1;//número do primeiro vértice da face x
    int V2;;//número do segundo vértice da face x
    int V3;;//número do terceiro vértice da face x
    public:
        Face(int V1,int V2,int V3);//Constrói uma face com 3 vértices
        virtual ~Face();
        int Get_V1(){return V1;}
        int Get_V2(){return V2;}
        int Get_V3(){return V3;}
};

#endif // FACE_H
