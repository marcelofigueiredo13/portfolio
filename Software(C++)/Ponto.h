#ifndef PONTO_H
#define PONTO_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
using namespace std;

class Ponto
{
    float X;
    float Y;
    float Z;
    public:
        Ponto(float x,float y,float z);
        virtual ~Ponto();
        float Get_XPonto(){return X;}
        float GetYPonto(){return Y;}
        float GetZPonto(){return Z;}
};

#endif // PONTO_H
