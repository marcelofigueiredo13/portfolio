#ifndef SGESTAO_H
#define SGESTAO_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
using namespace std;

#include "Modelo.h"
#include "Tipo.h"
#include "Ponto.h"

class SGestao
{
    list<Modelo *>LM;
    public:
        SGestao();
        virtual ~SGestao();

        void AddModelo(string nome);
        bool ModeloFoiCarregado(const string &fich);

        bool LoadModelo(const string &fich);
        int Contar(Tipo T);
        double AreaModelo(const string &fich);
        bool Envolvente(const string &fich, Ponto &Pmin, Ponto &Pmax);
        int Memoria();
        int NumInterseccoes(Ponto A, Ponto B);
        bool RemoverModelo(const string &nf);
        Modelo *Memoria(const string &fich);
        Modelo *ModeloMaisMemoria();
        Face *FaceMaiorArea(const string &fich);

        Modelo *GetModelo(string nome);
        void MostrarModelos();
};

#endif
