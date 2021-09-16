#include "SGestao.h"

SGestao::SGestao()
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
}

SGestao::~SGestao()
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    if(LM.size() == 0)
    {
        cout << "A lista de modelos esta vazia!" << endl;
        return;
    }

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        (*it)->~Modelo();
        delete (*it);
        LM.erase(it);
    }
    LM.clear();
}


//ADICIONA UM MODELO

void SGestao::AddModelo(string nome)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
    LM.push_back(new Modelo(nome));//Adicionamos um modelo � lista de modelos
}


//CARREGA UM MODELO

bool SGestao::LoadModelo(const string &nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
    ifstream F(nf.c_str());
    if(F.is_open())//Se o ficheio existir, adicionamos o modelo
    {
        F.close();//Fechamos, porque n�o precisamos de lhe aceder
        AddModelo(nf);//Adicionamos o modelo com o nome "nf" � lista de modelos
    }
    else
    cout << "Ficheiro nao existe" << endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if((*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
        {
            (*it)->Load(nf);//Carrega um modelo com o nome "nf"
            return true;
        }
    }
    return false;
}


//CONTA UM TIPO DE DADOS

int SGestao::Contar (Tipo T)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if( (*it)->Get_NomeModelo() == T.Get_Modelo())//Cheg�mos ao modelo
        {
            if(T.Get_Tipo() == "vertices")//Se o tipo de dados for vertices
                (*it)->ContaVertices();

            if(T.Get_Tipo() == "faces")//Se o tipo de dados for faces
                (*it)->ContaFaces();

            if(T.Get_Tipo() == "arestas")//Se o tipo de dados for arestas
                (*it)->ContaArestas();


        }
    }
}


//AREA DE UM MODELO

double SGestao::AreaModelo(const string &nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if((*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
        {
            (*it)->AreaTotalModelo();//Calculamos a �rea do modelo atrav�s da utiliza��o do seu m�todo
        }
    }
}


//AREA ENVOLVENTE A UM MODELO

bool SGestao::Envolvente(const string &nf, Ponto &Pmin, Ponto &Pmax)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    float comprimento, largura, altura;//Dimens�es da �rea envolvente

    float area;//Valor da �rea envolvente

    comprimento = (Pmax.Get_XPonto() - Pmin.Get_XPonto());//Xmax - Xmin

    largura = (Pmax.GetYPonto() - Pmin.GetYPonto());//Ymax - Ymin

    altura = (Pmax.GetZPonto() - Pmax.GetZPonto());//Zmax - Zmin

    area = comprimento * largura * altura;

    cout << "Area Envolvente = " << area << endl;
}


//INTERSE��O DE UMA RETA COM UM MODELO

int SGestao::NumInterseccoes(Ponto A, Ponto B)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
//        if((*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
        //{
            (*it)->NumeroIntersecoes(A.Get_XPonto(), A.GetYPonto(), A.GetZPonto(), B.Get_XPonto(), B.GetYPonto(), B.GetZPonto());//Utilizamos a fun��o que calcula o n�mero de interse��es de um determinado modelo
        //}                                                                                                                     //Com as coordenadas dos pontos A e B
    }

}


//REMOVE UM MODELO

bool SGestao::RemoverModelo(const string &nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin();it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if( (*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
        {
            delete (*it);//Apagamos o modelo
            LM.erase(it);//Apagamos o modelo da lista
        }
    }
}


//DETERMINA A MEMORIA OCUPADA POR UM MODELO

Modelo *SGestao::Memoria(const string &nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    double mem = 0;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if( (*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
        {
                mem = (*it)->Get_MemoriaModelo();//Calculamos o tamanho do modelo atrav�s do uso do seu m�todo Get_MemoriaModelo

                cout<<"Tamanho do modelo = "<< mem << endl;
        }
    }
    return 0;
}


//DETERMINA QUAL O MODELO QUE OCUPA MAIS MEM�RIA

Modelo *SGestao::ModeloMaisMemoria()
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    double maximo = 0;

    string modelo;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if(((*it)->Get_MemoriaModelo()) >= maximo)//Se a mem�ria do modelo for maior que o m�ximo
        {
            maximo = (*it)->Get_MemoriaModelo();//M�ximo � igual � mem�ria do modelo
            modelo = (*it)->Get_NomeModelo();//Modelo com mais mem�ria
        }
    }
    cout << "Modelo ["<< modelo << "] = " << maximo << endl;
}


//MEMORIA TOTAL OCUPADA PELO SGESTAO

int SGestao::Memoria()
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    double memoriaTotal = 0;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        memoriaTotal += (*it)->Get_MemoriaModelo();//Incrementamos a mem�ria de um modelo � memoriaTotal
    }
    cout << "Memoria total = " << memoriaTotal << endl;
    return 0;
}


//FACE COM MAIOR AREA

Face *SGestao::FaceMaiorArea(const string &nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if( (*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
        {
            (*it)->FaceMaiorArea();//Calculamos a sua maior face
        }
    }
    return 0;
}


//VERIFICA SE UM DADO MODELO FOI CARREGADO

bool SGestao::ModeloFoiCarregado(const string &nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if( (*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
            return true;
    }
}


//MOSTRA OS MODELOS CARREGADOS

void SGestao::MostrarModelos()
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;

    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        cout << (*it)->Get_NomeModelo() << endl;//Mostra os modelos carregados
    }
}


//AVAN�A AT� UM MODELO

Modelo *SGestao::GetModelo(string nf)
{
    cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
    for(list<Modelo *>::iterator it = LM.begin(); it != LM.end(); ++it)//Percorre a lista de modelos
    {
        if( (*it)->Get_NomeModelo() == nf)//Cheg�mos ao modelo
            return (*it);
    }
}
