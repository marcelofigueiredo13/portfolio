#ifndef MODELO_H
#define MODELO_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <cmath>
#include <time.h>
using namespace std;

#include "Vertice.h"
#include "Face.h"
#include "Aresta.h"
#include "Ponto.h"

class SGestao;

class Modelo
{
    list<Vertice *> LV;
    list<Face *> LF;
    list<Aresta *>LA;

    string nome;
    public:
        Modelo(string nome);
        virtual ~Modelo();

        string Get_NomeModelo(){return nome;}

        void Load(string nome);

        bool EZero(float X, float tol);

        void NumeroIntersecoes(int x1, int y1, int z1, int x2, int y2, int z2);

        void FaceMaiorArea();

        void AreaTotalModelo();

        Ponto *MaiorPonto();
        Ponto *MenorPonto();

        double Get_MemoriaModelo();

        void AddVertice(float x,float y,float z);
        int ContaVertices();
        void MostrarVertices();

        Vertice *GetVertice(double N);
        void AddFace(int V1,int V2,int V3);
        int ContaFaces();
        void MostrarFaces();

        void AddAresta(int V1,int V2);
        bool ComparaArestas(int V1, int V2);
        void CriarArestas();
        int ContaArestas();
        void MostrarArestas();
};

#endif // MODELO_H
