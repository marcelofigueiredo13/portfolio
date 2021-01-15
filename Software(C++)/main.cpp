#include <iostream>
#include <fstream>
#include <list>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>
using namespace std;

#include "SGestao.h"
#include "Modelo.h"

int main()
{
    int menu;

    SGestao X;

    while(1)
    {
    system("CLS");
    cout << "1 - Carregar Modelo\n2 - Lista Modelos\n3 - Aceder Modelo\n4 - Modelo Mais Memoria\n5 - Memoria Total Sistema Gestao\n6 - Remover Modelo\n0 - Sair" << endl;
    cin >> menu;
    switch(menu)
    {
        case 1:
        {
            system("CLS");
            string nf;
            cout << "Nome do modelo?" << endl;
            cin >> nf;
            X.LoadModelo(nf);
            system("pause");
            break;
        }
        case 2:
        {
            system("CLS");
            X.MostrarModelos();
            system("pause");
            break;
        }
        case 3:
        {
            system("CLS");
            string nf1;
            cout << "Nome do modelo?" << endl;
            cin >> nf1;
            if(X.ModeloFoiCarregado(nf1) == false)
            {
                cout << "Modelo Nao Carregado" << endl;
                system("pause");
                break;
            }
            system("CLS");
            int op;

            cout << "1 - Contar Vertices\n2 - Contar Faces\n3 - Contar Arestas\n4 - Area Modelo\n5 - Envolvente\n6 - Memoria Ocupada\n7 - Intersecao Com Reta\n8 - Face maior\n0 - Sair" << endl;
            cin >> op;
            switch(op)
            {
                case 1:
                {
                    system("CLS");
                    Tipo *V = new Tipo("vertices",nf1);
                    X.Contar(*V);
                    system("pause");
                    break;
                }
                case 2:
                {
                    system("CLS");
                    Tipo *F = new Tipo("faces",nf1);
                    X.Contar(*F);
                    system("pause");
                    break;
                }
                case 3:
                {
                    system("CLS");
                    Tipo *A = new Tipo("arestas",nf1);
                    X.Contar(*A);
                    system("pause");
                    break;
                }
                case 4:
                {
                    X.AreaModelo(nf1);
                    system("pause");
                    break;
                }
                case 5:
                {
                    system("CLS");
                    Ponto *Max = Modelo(nf1).MaiorPonto();
                    Ponto *Min = Modelo(nf1).MenorPonto();
                    X.Envolvente(nf1, *Min, *Max);
                    system("pause");
                    break;
                }
                case 6:
                {
                    system("CLS");
                    X.Memoria(nf1);
                    system("pause");
                    break;
                }
                case 7:
                {
                    system("CLS");
                    float x1, y1, z1, x2, y2, z2;
                    cout << "Coordenadas do Ponto 1" << endl;
                    cin >> x1;
                    cin >> y1;
                    cin >> z1;
                    cout << "Coordenadas do Ponto 2" << endl;
                    cin >> x2;
                    cin >> y2;
                    cin >> z2;
                    Ponto *A = new Ponto(x1,y1,z1);
                    Ponto *B = new Ponto(x2,y2,z2);
                    X.GetModelo(nf1);
                    X.NumInterseccoes(*A, *B);
                    system("pause");
                    break;
                }
                case 8:
                {
                    system("CLS");
                    X.FaceMaiorArea(nf1);
                    system("pause");
                    break;
                }
                case 0: break;break;

                default: cout << "Opcao Invalida" << endl;break;
            }

            break;
        }
        case 4:
        {
            system("CLS");
            X.ModeloMaisMemoria();
            system("pause");
            break;
        }
        case 5:
        {
            system("CLS");
            X.Memoria();
            system("pause");
            break;
        }
        case 6:
        {
            system("CLS");
            string nf2;
            cout << "Nome do modelo ?" << endl;
            cin >> nf2;
            system("CLS");
            X.RemoverModelo(nf2);
            system("pause");
            break;
        }
        case 0:X.~SGestao();exit(1);break;

        default: cout << "Opcao Invalida" << endl;break;
    }
    }

}
