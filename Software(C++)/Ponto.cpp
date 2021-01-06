#include "Ponto.h"

Ponto::Ponto(float _X, float _Y, float _Z)
{
    //cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
    X = _X;
    Y = _Y;
    Z= _Z;
}

Ponto::~Ponto()
{
    //cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
}
