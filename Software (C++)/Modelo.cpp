#include "Modelo.h"

Modelo::Modelo(string _nome)
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    nome = _nome;
}

Modelo::~Modelo()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    if (LV.size() == 0) return;
    for(list<Vertice *>::iterator it = LV.begin(); it != LV.end(); ++it)
    {
        delete (*it);
    }
    LV.clear();
    if (LF.size() == 0) return;
    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)
    {
        delete (*it);
    }
    LF.clear();
    if (LA.size() == 0) return;
    for(list<Aresta *>::iterator it = LA.begin(); it != LA.end(); ++it)
    {
        delete (*it);
    }
    LA.clear();
}

//CARREGAR O FICHEIRO

void Modelo::Load(string nf)
{
    clock_t tempo;//vari�vel to tipo clock para armazenar o resultado
    cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    clock_t start_s = clock();//inicia o relogio
    ifstream F(nf.c_str());
    if(F.is_open())
    {
    char primeiroCarat;
    string linha,nome;
    double x,y,z,V1,V2,V3;
    while ( getline( F, linha ) )//L� a linha toda do ficheiro
    {
        istringstream ss( linha );//Transforma a string e formata para input
        ss >> primeiroCarat;//L� o primeiro carater
        switch(primeiroCarat)
        {
        case 'v':
                if(linha[0]== 'v' && linha[1] == ' ')//Se a primeira posi��o da linha do ficheiro for igual a 'v' e a segunda posi��o for igual a 'espa�o'
                {
                ss>> x;//Carrega a abcissa do v�rtice para a vari�vel x
                ss>> y;//Carrega a ordenada do v�rtice para a vari�vel x
                ss>> z;//Carrega a cota do v�rtice para a vari�vel x
                AddVertice(x,y,z);//Cria um novo v�rtice
                break;
                }
                else
                    break;
        case 'f':
                if(linha[0]== 'f' && linha[1] == ' ')//Se a primeira posi��o da linha do ficheiro for igual a 'f' e a segunda posi��o for igual a 'espa�o'
                {
                ss>> V1;//Carrega o n�mero do primeiro v�rtice da face
                ss>> V2;//Carrega o n�mero do segundo v�rtice da face
                ss>> V3;//Carrega o n�mero do terceiro v�rtice da face
                AddFace(V1,V2,V3);//Cria uma nova face
                break;
                }
                else
                    break;//Se n�o for nenhum dos casos sai
        }
    }
        cout << "Ficheiro carregado com sucesso" << endl;
        F.close();
        cout << "A criar arestas" << endl;
        CriarArestas();
        clock_t stop_s = clock();//para o relogio
        tempo = (stop_s - start_s) / (CLOCKS_PER_SEC/1000);//c�lculo do valor to tempo em segundos
        cout << "Tempo carregamento = " << tempo << "ms" << endl;

    }
    else{
        cout << "Erro ao ler ficheiro" << endl;
    }
}


//V�RTICES

void Modelo::AddVertice(float x,float y,float z)
{
    //cout << "Passei em [" << __FUNCTION__ << "]" <<  endl;
    LV.push_back(new Vertice(x,y,z));//Adiciona um v�rtice � lista de v�rtices
}

int Modelo::ContaVertices()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    int conta=0;
    for(list<Vertice *>::iterator it = LV.begin(); it != LV.end(); ++it)//Percorre a lista de v�rtices e atualiza a vari�vel conta sempre que passa por uma v�rtice
        ++conta;
    cout << "Numero de Vertices = " << conta << endl;
    return conta;
}

void Modelo::MostrarVertices()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;
    int i = 1;
    if(LV.size()== 0) return;
    for(list<Vertice *>::iterator it = LV.begin(); it != LV.end(); ++it)//Percorre a lista de v�rtices e imprime as coordenadas de um v�rtice
    {
        cout << "Vertice [ " << i << " ]" << endl;
        cout << "X = " << (*it)->Get_x() << endl;
        cout << "Y = " << (*it)->Get_y() << endl;
        cout << "Z = " << (*it)->Get_z() << endl << endl;
        ++i;
    }
}


//FACES

Vertice *Modelo::GetVertice(double N)//Inserimos a posi��o(o n�mero) de um v�rtice de uma face e obtemos o seu conte�do
{
    //cout << "Passei em [" << __FUNCTION__ << "]" <<endl;
    list<Vertice *>::iterator it = LV.begin();
    advance(it, (N - 1) );//Avan�a o iterador para a posi��o do v�rtice da face -1 posi��o( Posi��o do v�rtice na lista de v�rtices )
    return (*it);//Retorna o valor do iterador
}

void Modelo::AddFace(int V1,int V2,int V3)
{
    //cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    LF.push_back(new Face(V1,V2,V3));//Adiciona uma face � lista de faces
}

int Modelo::ContaFaces()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    int conta=0;
    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista de faces e atualiza a vari�vel conta sempre que passa por uma face
        ++conta;
    cout << "Numero de Faces = " << conta << endl;
    return conta;
}

void Modelo::MostrarFaces()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;
    if(LF.size()== 0) return;
    int i=1;
    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista das faces e imprime as coordenadas dos 3 v�rtices de uma face
    {
        cout << "Face [ " << i << " ]" << endl;
        cout << "V1 : x = " << (GetVertice((*it)->Get_V1()))->Get_x() << " y = " << (GetVertice((*it)->Get_V1()))->Get_y() << " z = " << (GetVertice((*it)->Get_V1()))->Get_z() << endl;
        cout << "V2 : x = " << (GetVertice((*it)->Get_V2()))->Get_x() << " y = " << (GetVertice((*it)->Get_V2()))->Get_y() << " z = " << (GetVertice((*it)->Get_V2()))->Get_z() << endl;
        cout << "V3 : x = " << (GetVertice((*it)->Get_V3()))->Get_x() << " y = " << (GetVertice((*it)->Get_V3()))->Get_y() << " z = " << (GetVertice((*it)->Get_V3()))->Get_z() << endl << endl;
        ++i;
    }
}


//ARESTAS

void Modelo::AddAresta(int V1,int V2)
{
    //cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    LA.push_back(new Aresta(V1,V2));//Adiciona uma aresta � lista de arestas
}

bool Modelo::ComparaArestas(int V1, int V2)
{
    //cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    for(list<Aresta *>::iterator it1=LA.begin(); it1 != LA.end(); ++it1)//Percorre a lista  de arestas
    {
        if((((*it1)->Get_V1()==V2) && ((*it1)->Get_V2()==V1)) || (((*it1)->Get_V1()==V1) && ((*it1)->Get_V2()==V2)))//Se o n�mero do vetor 1 da aresta for igual ao n�mero do vetor 2 e o n�mero do vetor 2 da aresta for igual ao n�mero do vetor 1 da aresta
            return true;                                                                                           //ou se o n�mero do vetor 2 da aresta for igual ao n�mero do vetor 1 e o n�mero do vetor 1 da aresta for igual ao n�mero do vetor 2 da aresta
    }
    return false;
}

void Modelo::CriarArestas()
{
    //cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    if(LF.size()== 0) return;
    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//ERRO SO D� 1/3 DAS ARESTAS DE QQ FICHEIRO
    {
        if(ComparaArestas((*it)->Get_V1(),(*it)->Get_V2())==false)//Se a aresta n�o existir
            AddAresta((*it)->Get_V1(),(*it)->Get_V2());//Cria a (1�) aresta com o primeiro e segundo v�rtice da face
        if(ComparaArestas((*it)->Get_V1(),(*it)->Get_V3())==false)//Se a aresta n�o existir
            AddAresta((*it)->Get_V1(),(*it)->Get_V3());//Cria a (2�) aresta com o primeiro e terceiro v�rtice da face
        if(ComparaArestas((*it)->Get_V2(),(*it)->Get_V3())==false)//Se a aresta n�o existir
                AddAresta((*it)->Get_V2(),(*it)->Get_V3());//Cria a (3�) aresta com o segundo e  terceiro v�rtice da face
    }
}

int Modelo::ContaArestas()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl;
    int conta=0;
    for(list<Aresta *>::iterator it = LA.begin(); it != LA.end(); ++it)//Percorre a lista de arestas e atualiza a vari�vel conta sempre que passa por uma aresta
        ++conta;
    cout << "Numero de Arestas = " << conta << endl;
    return conta;
}

void Modelo::MostrarArestas()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;
    if(LA.size()== 0) return;
    int i=1;
    for(list<Aresta *>::iterator it = LA.begin(); it != LA.end(); ++it)//Percorre a lista das arestas e imprime as coordenadas dos 2 v�rtices de uma aresta
    {
        cout << "Aresta [ " << i << " ]" << endl;
        cout << "[" << GetVertice((*it)->Get_V1())->Get_x() << " , " << GetVertice((*it)->Get_V1())->Get_y() << " , " << GetVertice((*it)->Get_V1())->Get_z() << " ] ";//Imprime as coordenadas do 1� v�rtice da aresta
        cout << " [" << GetVertice((*it)->Get_V2())->Get_x() << " , " << GetVertice((*it)->Get_V2())->Get_y() << " , " << GetVertice((*it)->Get_V2())->Get_z() << " ]" << endl << endl;//Imprime as coordenadas do 2� v�rtice da aresta
        ++i;
    }
}


//GERAL

bool Modelo::EZero(float PE, float tol)
{
    //cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;
    if(fabs(PE)<tol)
        return true;
    else return false;
}

//INTERSE��O RETA COM O MODELO

void Modelo::NumeroIntersecoes(int x1, int y1, int z1, int x2, int y2, int z2)
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;

    float v1_x,v1_y,v1_z , v2_x,v2_y,v2_z;//Coordenadas de dois vetor do plano, os vetor V1->V2 e V3->V2

    float a ,b ,c ,d ;//Valores do plano

    float x_vetorDiretor ,y_vetorDiretor ,z_vetorDiretor ;//Coordenadas do vetor diretor

    float xN ,yN , zN; //Coordenadas do vetor normal ao plano

    float ProdutoEscalar;

    float t;

    int interseta=0;

    int i=1;

    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista das faces
    {

    //cout << "FACE [" << i << "]" << endl;
    i++;

    //PLANO

    //Coordenadas do Vetor 1 da face (V1-V2)
    v1_x=((GetVertice((*it)->Get_V1()))->Get_x())-((GetVertice((*it)->Get_V2()))->Get_x());
    v1_y=((GetVertice((*it)->Get_V1()))->Get_y())-((GetVertice((*it)->Get_V2()))->Get_y());
    v1_z=((GetVertice((*it)->Get_V1()))->Get_z())-((GetVertice((*it)->Get_V2()))->Get_z());

    //Coordenadas do Vetor 2 da face (V3-V2)
    v2_x=((GetVertice((*it)->Get_V3()))->Get_x())-((GetVertice((*it)->Get_V2()))->Get_x());
    v2_y=((GetVertice((*it)->Get_V3()))->Get_y())-((GetVertice((*it)->Get_V2()))->Get_y());
    v2_z=((GetVertice((*it)->Get_V3()))->Get_z())-((GetVertice((*it)->Get_V2()))->Get_z());

    //C�lculo das coordenadas do vetor normal ao plano
    xN = (v1_x*v2_x);
    yN = (v1_y*v2_y);
    zN = (v1_z*v2_z);

    //Calculo da equa�ao do plano
    a = (v1_y*v2_z)-(v1_z*v2_y);
    b = (v1_z*v2_x)-(v1_x*v2_z);
    c = (v1_x*v2_y)-(v1_y*v2_x);
    d = ( - (a*(GetVertice((*it)->Get_V2()))->Get_x()) - (b*(GetVertice((*it)->Get_V2()))->Get_y()) - (c*(GetVertice((*it)->Get_V2()))->Get_z()));

    //RETA

    //Vetor diretor da reta (vetor = PONTO2-PONTO1)
    x_vetorDiretor = x2-x1;
    y_vetorDiretor = y2-y1;
    z_vetorDiretor = z2-z1;

    //Produto escalar entre o vetor diretor da reta e o vetor normal ao plano (VD(x,y,z).N(x,y,z))
    ProdutoEscalar=(x_vetorDiretor*a)+(y_vetorDiretor*b)+(z_vetorDiretor*c);

    if((EZero(ProdutoEscalar,0.00000001)==false))//Significa que a reta n�o � paralela ao plano, ou seja, que o interseta
    {
            t = (( - d - (a*x1) - (b*y1) - (c*z1))/((a*x_vetorDiretor)+(b*y_vetorDiretor)+(c*z_vetorDiretor)));//Caso intersete o plano calculamos o t

            float x_Int, y_Int, z_Int;//Coordenadas do ponto de interse��o da reta com o plano

            //J� n�o � preciso verificar se o denominador � 0, pois j� sabemos que a reta n�o � paralela

            //C�lculo do ponto de interse��o da reta com a face
            x_Int = x1 + (t*x_vetorDiretor);
            y_Int = y1 + (t*y_vetorDiretor);
            z_Int = z1 + (t*z_vetorDiretor);
            //cout << "Ponto Intersecao" << endl << "[" << x_Int << ";" << y_Int << ";" << z_Int << "]" << endl;

            //Verificar se o ponto pertence ao plano
            float alfa, beta;

            float inv_x, inv_y;

            /*inv_x = (GetVertice((*it)->Get_V1()))->Get_x() - (GetVertice((*it)->Get_V3()))->Get_x();

            inv_y = (GetVertice((*it)->Get_V1()))->Get_y() - (GetVertice((*it)->Get_V3()))->Get_y();

            alfa = ((x_Int * inv_y) - ((GetVertice((*it)->Get_V1()))->Get_x() * inv_y) + (y_Int * inv_x) - ((GetVertice((*it)->Get_V2()))->Get_x() * inv_x)) / ((v1_x * inv_y) + (v1_y * inv_x));

            beta = ((y_Int - (GetVertice((*it)->Get_V2()))->Get_y()) - (alfa * v1_y)) / v2_y;*/

            alfa = ((x_Int - (GetVertice((*it)->Get_V2()))->Get_x())-(((y_Int - (GetVertice((*it)->Get_V2()))->Get_y()) * v2_x)/v2_y)) / (1 - ((v1_y * v2_x) / v2_y));

            beta = ((y_Int - (GetVertice((*it)->Get_V2()))->Get_y()) - (alfa * v1_y)) / v2_y;

            //cout << "ALFA = " << alfa << endl << "BETA = " << beta << endl << endl;
            if((alfa >= 0) && (beta >= 0) && (alfa + beta <= 1))
                interseta++;
    }

    }
    cout << "Numero de intersecoes = " << interseta << endl;
}


//FACE COM MAIOR �REA

void Modelo::FaceMaiorArea()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;

    float v1_x,v1_y,v1_z , v2_x,v2_y,v2_z;//Coordenadas de dois vetor do plano, os vetor V1->V2 e V3->V2

    float area;

    float maior_area=0;

    int i=0;

    int j;

    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista das faces
    {
        i++;
        //Coordenadas do Vetor 1 da face (V1-V2)
        v1_x=((GetVertice((*it)->Get_V1()))->Get_x())-((GetVertice((*it)->Get_V2()))->Get_x());
        v1_y=((GetVertice((*it)->Get_V1()))->Get_y())-((GetVertice((*it)->Get_V2()))->Get_y());
        v1_z=((GetVertice((*it)->Get_V1()))->Get_z())-((GetVertice((*it)->Get_V2()))->Get_z());

        //Coordenadas do Vetor 2 da face (V3-V2)
        v2_x=((GetVertice((*it)->Get_V3()))->Get_x())-((GetVertice((*it)->Get_V2()))->Get_x());
        v2_y=((GetVertice((*it)->Get_V3()))->Get_y())-((GetVertice((*it)->Get_V2()))->Get_y());
        v2_z=((GetVertice((*it)->Get_V3()))->Get_z())-((GetVertice((*it)->Get_V2()))->Get_z());

        //AREA FACE

        area = sqrt(pow(((v1_y*v2_z)-(v1_z*v2_y)),2) + pow(((v1_z*v2_x)-(v1_x*v2_z)),2) + pow(((v1_z*v2_y)-(v1_y*v2_x)),2))/2;

        if(area >= maior_area)
        {
            maior_area = area;
            j=i;
        }

    }
    cout << "FACE [" << j << "] = " << maior_area << endl << endl;
}


//AREA DO MODELO

void Modelo::AreaTotalModelo()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;

    float v1_x,v1_y,v1_z , v2_x,v2_y,v2_z;//Coordenadas de dois vetor do plano, os vetor V1->V2 e V3->V2

    float area = 0;

    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista das faces
    {
        //Coordenadas do Vetor 1 da face (V1-V2)
        v1_x=((GetVertice((*it)->Get_V1()))->Get_x())-((GetVertice((*it)->Get_V2()))->Get_x());
        v1_y=((GetVertice((*it)->Get_V1()))->Get_y())-((GetVertice((*it)->Get_V2()))->Get_y());
        v1_z=((GetVertice((*it)->Get_V1()))->Get_z())-((GetVertice((*it)->Get_V2()))->Get_z());

        //Coordenadas do Vetor 2 da face (V3-V2)
        v2_x=((GetVertice((*it)->Get_V3()))->Get_x())-((GetVertice((*it)->Get_V2()))->Get_x());
        v2_y=((GetVertice((*it)->Get_V3()))->Get_y())-((GetVertice((*it)->Get_V2()))->Get_y());
        v2_z=((GetVertice((*it)->Get_V3()))->Get_z())-((GetVertice((*it)->Get_V2()))->Get_z());

        //AREA TOTAL

        area += sqrt(pow(((v1_y*v2_z)-(v1_z*v2_y)),2) + pow(((v1_z*v2_x)-(v1_x*v2_z)),2) + pow(((v1_z*v2_y)-(v1_y*v2_x)),2))/2;

    }
    cout << "Area do modelo = " << area << endl << endl;
}


//ENVOLVENTE
//MAIOR PONTO

Ponto *Modelo::MaiorPonto()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;

    float maior_x, maior_y, maior_z;


    //cout << "V1 " << (GetVertice((*it)->Get_V1()))->Get_x() << endl << "V2 " << (GetVertice((*it)->Get_V2()))->Get_x() << "V3 " << (GetVertice((*it)->Get_V1()))->Get_x() << endl;

    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista das faces
    {
        //Maior X
        if((GetVertice((*it)->Get_V1()))->Get_x() > maior_x)
            maior_x = (GetVertice((*it)->Get_V1()))->Get_x();
        if((GetVertice((*it)->Get_V2()))->Get_x() > maior_x)
            maior_x = (GetVertice((*it)->Get_V2()))->Get_x();
        if((GetVertice((*it)->Get_V3()))->Get_x() < maior_x)
            maior_x = (GetVertice((*it)->Get_V3()))->Get_x();

        //Maior Y
        if((GetVertice((*it)->Get_V2()))->Get_y() < maior_y)
            maior_y = (GetVertice((*it)->Get_V2()))->Get_y();
        if((GetVertice((*it)->Get_V3()))->Get_y() < maior_y)
            maior_y = (GetVertice((*it)->Get_V3()))->Get_y();

        //Maior Z
        if((GetVertice((*it)->Get_V2()))->Get_z() > maior_z)
            maior_z = (GetVertice((*it)->Get_V2()))->Get_z();
        if((GetVertice((*it)->Get_V3()))->Get_z() > maior_z)
            maior_z = (GetVertice((*it)->Get_V3()))->Get_z();;
    }
    Ponto *Max = new Ponto(maior_x,maior_y,maior_z);
    cout << "[" << maior_x << "," << maior_y << "," << maior_z << "]" << endl;
    return Max;
}


//MENOR PONTO

Ponto *Modelo::MenorPonto()
{
    cout << "Passei em [" << __FUNCTION__ << "]" << endl << endl;

    float menor_x, menor_y, menor_z;

    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)//Percorre a lista das faces
    {
        //Menor X
        if((GetVertice((*it)->Get_V2()))->Get_x() < menor_x)
            menor_x = (GetVertice((*it)->Get_V2()))->Get_x();
        if((GetVertice((*it)->Get_V3()))->Get_x() < menor_x)
            menor_x = (GetVertice((*it)->Get_V3()))->Get_x();

        //Menor Y
        if((GetVertice((*it)->Get_V2()))->Get_y() < menor_y)
            menor_y = (GetVertice((*it)->Get_V2()))->Get_y();
        if((GetVertice((*it)->Get_V3()))->Get_y() < menor_y)
            menor_y = (GetVertice((*it)->Get_V3()))->Get_y();

        //Menor Z
        if((GetVertice((*it)->Get_V2()))->Get_z() > menor_z)
            menor_z = (GetVertice((*it)->Get_V2()))->Get_z();
        if((GetVertice((*it)->Get_V3()))->Get_z() > menor_z)
            menor_z = (GetVertice((*it)->Get_V3()))->Get_z();
    }
    Ponto *Min = new Ponto(menor_x,menor_y,menor_z);
    cout << "[" << menor_x << "," << menor_y << "," << menor_z << "]" << endl;
    return Min;
}


//MEMORIA DO MODELO

double Modelo::Get_MemoriaModelo()
{
    double tamanho = 0;

    for(list<Vertice *>::iterator it = LV.begin(); it != LV.end(); ++it)
    {
        tamanho += 3*sizeof(this);
    }

    for(list<Face *>::iterator it = LF.begin(); it != LF.end(); ++it)
    {
        tamanho += 3*sizeof(this);
    }

    for(list<Aresta *>::iterator it = LA.begin(); it != LA.end(); ++it)
    {
        tamanho += 2*sizeof(this);
    }
    return tamanho;
}

