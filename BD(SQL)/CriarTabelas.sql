/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     07/06/2020 02:51:58                          */
/*==============================================================*/
/*==============================================================*/

if exists (select 1
            from  sysobjects
           where  id = object_id('[Substituicoes]')
            and   type = 'U')
   drop table [Substituicoes]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Jogadores_Jogos]')
            and   type = 'U')
   drop table [Jogadores_Jogos]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Jogos]')
            and   type = 'U')
   drop table [Jogos]
go


if exists (select 1
            from  sysobjects
           where  id = object_id('[Clubes_Campeonatos]')
            and   type = 'U')
   drop table [Clubes_Campeonatos]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Campeonatos]')
            and   type = 'U')
   drop table [Campeonatos]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Jogadores]')
            and   type = 'U')
   drop table [Jogadores]
go




if exists (select 1
            from  sysobjects
           where  id = object_id('[Clubes]')
            and   type = 'U')
   drop table [Clubes]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Estadios]')
            and   type = 'U')
   drop table [Estadios]
go





if exists (select 1
            from  sysobjects
           where  id = object_id('[Treinadores]')
            and   type = 'U')
   drop table [Treinadores]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Paises]')
            and   type = 'U')
   drop table [Paises]
go
if exists (select 1
            from  sysobjects
           where  id = object_id('[Posicoes]')
            and   type = 'U')
   drop table [Posicoes]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Estado_Jogadores]')
            and   type = 'U')
   drop table [Estado_Jogadores]
go

if exists (select 1
            from  sysobjects
           where  id = object_id('[Cidades]')
            and   type = 'U')
   drop table [Cidades]
go




/*==============================================================*/
/* Table: CIDADES */
/*==============================================================*/
CREATE TABLE [Cidades]
(
 [Id_Cidade]   int IDENTITY (1, 1) NOT NULL ,
 [Nome_Cidade]  varchar(20) NOT NULL ,


 CONSTRAINT PK_Cidades PRIMARY KEY NONCLUSTERED ([Id_Cidade])
);
GO

/*==============================================================*/
/* Table: ESTADO_JOGADORES */
/*==============================================================*/
CREATE TABLE [Estado_Jogadores]
(
 [Id_Estado]   int IDENTITY (1, 1) NOT NULL ,
 [Descricao_Estado] varchar(50) NOT NULL ,


 CONSTRAINT PK_Estado_Jogadores PRIMARY KEY NONCLUSTERED ([Id_Estado])
);
GO

/*==============================================================*/
/* Table: POSICOES */
/*==============================================================*/
CREATE TABLE [Posicoes]
(
 [Id_Posicao]   int IDENTITY (1, 1) NOT NULL ,
 [Descricao_Posicao] varchar(50) NOT NULL ,


 CONSTRAINT PK_Posicoes PRIMARY KEY NONCLUSTERED ([Id_Posicao])
);
GO

/*==============================================================*/
/* Table: PAISES */
/*==============================================================*/
CREATE TABLE [Paises]
(
 [Id_Pais]   int IDENTITY (1, 1) NOT NULL ,
 [Nome_Pais] varchar(50) NOT NULL ,
 [Codigo_Pais] varchar(3) NOT NULL,
 [Indicativo] INT NOT NULL,

 
 CONSTRAINT PK_Paises PRIMARY KEY NONCLUSTERED ([Id_Pais])
);
GO

/*==============================================================*/
/* Table: TREINADORES */
/*==============================================================*/
CREATE TABLE [Treinadores]
(
 [Id_Treinador]    int IDENTITY (1, 1) NOT NULL ,
 [Nome_Treinador]  VARCHAR(50) NOT NULL,
 [Data_Nascimento] date NOT NULL ,
 [Id_Pais]         int NOT NULL ,


 CONSTRAINT PK_Treinadores PRIMARY KEY NONCLUSTERED ([Id_Treinador]),
 CONSTRAINT FK_PAIS_TREINADOR FOREIGN KEY ([Id_Pais])  REFERENCES [Paises]([Id_Pais])
);
GO

/*==============================================================*/
/* Table: ESTADIOS */
/*==============================================================*/
CREATE TABLE [Estadios]
(
 [Id_Estadio]     int IDENTITY (1, 1) NOT NULL ,
 [Nome]           varchar(50) NOT NULL ,
 [Lotacao]        bigint NOT NULL ,
 [Ano_Construcao] int NOT NULL ,
 [Cidade_Estadio]      int NOT NULL ,


 CONSTRAINT PK_Estadios PRIMARY KEY NONCLUSTERED ([Id_Estadio]),
 CONSTRAINT FK_CIDADE_ESTADIO FOREIGN KEY ([Cidade_Estadio])  REFERENCES [Cidades]([Id_Cidade]),
 
);
GO



/*==============================================================*/
/* Table: CLUBES */
/*==============================================================*/
CREATE TABLE [Clubes]
(
 [Id_Clube]     int IDENTITY (1, 1) NOT NULL ,
 [Nome_Clube]   varchar(50) NOT NULL ,
 [Mascote]      varchar(50) NOT NULL ,
 [Simbolo]      varchar(50) NOT NULL ,
 [Estadio]      int NOT NULL ,
 [Treinador_Clube] int NOT NULL ,


 CONSTRAINT PK_Clubes PRIMARY KEY NONCLUSTERED ([Id_Clube]),
 CONSTRAINT FK_TREINADOR_CLUBE FOREIGN KEY ([Treinador_Clube])  REFERENCES [Treinadores]([Id_Treinador]),
 CONSTRAINT FK_ESTADIO_CLUBE FOREIGN KEY ([Estadio])  REFERENCES [Estadios]([Id_Estadio])
);
GO




/*==============================================================*/
/* Table: JOGADORES */
/*==============================================================*/
CREATE TABLE [Jogadores]
(
 [Id_Jogador]      int IDENTITY (1, 1) NOT NULL ,
 [Nome_Jogador]    varchar(50) NOT NULL ,
 [Data_Nascimento] date NOT NULL ,
 [Posicao_Jogador]         int NOT NULL ,
 [Pais_Jogador]            int NOT NULL ,
 [Clube_Jogador]           int NOT NULL ,
 [Estado_Jogador] int,
 Cartoes_acumulados int default 0,

 CONSTRAINT PK_Jogador PRIMARY KEY NONCLUSTERED ([Id_Jogador]),
 CONSTRAINT FK_POSICAO_JOGADOR FOREIGN KEY ([Posicao_Jogador])  REFERENCES [Posicoes]([Id_Posicao]),
 CONSTRAINT FK_PAIS_JOGADOR FOREIGN KEY ([Pais_Jogador])  REFERENCES [Paises]([Id_Pais]),
 CONSTRAINT FK_CLUBE_JOGADOR FOREIGN KEY ([Clube_Jogador])  REFERENCES [Clubes]([Id_Clube])
);
GO

/*==============================================================*/
/* Table: CAMPEONATOS */
/*==============================================================*/
CREATE TABLE [Campeonatos]
(
 [Id_Campeonato] int IDENTITY (1, 1) NOT NULL ,
 [Nome_Campeonato]          varchar(50) NOT NULL ,
 [Data_Inicio]   date NOT NULL ,
 [Data_Fim]      date NOT NULL ,
 [Pais_Campeonato]       int NOT NULL ,


 CONSTRAINT PK_Campeonatos PRIMARY KEY NONCLUSTERED ([Id_Campeonato]),
 CONSTRAINT FK_PAIS_CAMPEONATO FOREIGN KEY ([Pais_Campeonato])  REFERENCES [Paises]([Id_Pais])
);
GO

/*==============================================================*/
/* Table: CLUBES_CAMPEONATOS */
/*==============================================================*/
CREATE TABLE [Clubes_Campeonatos]
(
 [Id_Clube]          int NOT NULL ,
 [Id_Campeonato]     int NOT NULL ,
 [Vitorias]           int default 0 ,
 [Derrotas]          int default 0,
 [Empates]           int  default 0,
 [Golos_Favor]       int default 0 ,
 [Golos_Contra]      int default 0,
 [Cartoes_Amarelos]  int NOT NULL ,
 [Cartoes_Vermelhos] int NOT NULL ,
 [Pontos] int default 0


 CONSTRAINT PK_Clubes_Campeonatos PRIMARY KEY NONCLUSTERED ([Id_Clube], [Id_Campeonato]),
 CONSTRAINT FK_CLUBE_CAMPEONATO FOREIGN KEY ([Id_Clube])  REFERENCES [Clubes]([Id_clube]),
 CONSTRAINT FK_ID_CAMPEONATO FOREIGN KEY ([Id_Campeonato])  REFERENCES [Campeonatos]([Id_Campeonato])
);
GO

/*==============================================================*/
/* Table: JOGOS */
/*==============================================================*/
CREATE TABLE [Jogos]
(
 [Id_Jogo]         int IDENTITY (1, 1) NOT NULL ,
 [Data_Hora]       datetime NOT NULL ,
  [Clube_Visitado]  int NOT NULL ,
 [Clube_Visitante] int NOT NULL ,
 [Id_Estadio]      int NOT NULL ,
 [Golos_Visitado]int default NULL,
 [Golos_Visitante] int default NULL,
 [Resultado] varchar(20) default '-:-'

 CONSTRAINT PK_Jogos PRIMARY KEY NONCLUSTERED ([Id_Jogo]),
 CONSTRAINT FK_VISITANTE_JOGO FOREIGN KEY ([Clube_Visitante])  REFERENCES [Clubes]([Id_Clube]),
 CONSTRAINT FK_VISITADO_JOGO FOREIGN KEY ([Clube_Visitado])  REFERENCES [Clubes]([Id_Clube]),
 CONSTRAINT FK_ESTADIO_JOGO FOREIGN KEY ([Id_Estadio])  REFERENCES [Estadios]([Id_Estadio])
);
GO

/*==============================================================*/
/* Table: JOGADORES_JOGOS */
/*==============================================================*/
CREATE TABLE [Jogadores_Jogos]
(
 [Id_Jogo]        int NOT NULL ,
 [Id_Jogador]       int NOT NULL ,
 [Cartao_Vermelho] int,
 [Cartao_Amarelo]  int,
 [Minutos_Jogo]     int NOT NULL ,
 [Estado_Jogador] int,

 CONSTRAINT PK_Jogadores_Jogos PRIMARY KEY NONCLUSTERED ([Id_Jogo], [Id_Jogador]),
 CONSTRAINT FK_ID_JOGADOR_JOGADORJOGO FOREIGN KEY ([Id_Jogador])  REFERENCES [Jogadores]([Id_Jogador]),
 CONSTRAINT FK_ESTADIO_JOGADOR_JOGO FOREIGN KEY ([Estado_Jogador])  REFERENCES [Estado_Jogadores]([Id_Estado]),
);
GO

/*==============================================================*/
/* Table: SUBSTITUICOES */
/*==============================================================*/
CREATE TABLE [Substituicoes]
(
 
 [Jogador]    int NOT NULL ,
 [Minuto_de_jogo] int NOT NULL ,
 [Substituiçao]  varchar(20) ,
 [Jogo]           int NOT NULL ,


 CONSTRAINT PK_Substituicoes PRIMARY KEY NONCLUSTERED ([Jogador] ASC, [Jogo] ASC),
 CONSTRAINT FK_SAIU_SUBSTITUICAO FOREIGN KEY ([Jogador])  REFERENCES [Jogadores]([Id_Jogador]),
 CONSTRAINT FK_JOGO_SUBSTITUICAO FOREIGN KEY ([Jogo])  REFERENCES [Jogos]([Id_jogo])
);



