/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     23/01/2021 19:45:38                          */
/*==============================================================*/
/*

drop index ACONTECE_FK;

drop index ALVO_FK;

drop index ACAODISCIPLINAR_PK;

drop table ACAODISCIPLINAR;

drop index CAMPEONATO_PK;

drop table CAMPEONATO;

drop index COMPOSTO2_FK;

drop index COMPOSTO_FK;

drop index COMPOSTO_PK;

drop table COMPOSTO;

drop index CONDICIONADO2_FK;

drop index CONDICIONADO_FK;

drop index CONDICIONADO_PK;

drop table CONDICIONADO;

drop index EPOCA_PK;

drop table EPOCA;

drop index PERTENCE_FK;

drop index PRATICA_FK;

drop index EQUIPA_PK;

drop table EQUIPA;

drop index ESPACODESPORTIVO_PK;

drop table ESPACODESPORTIVO;

drop index ESTADOJOGADOR_PK;

drop table ESTADOJOGADOR;

drop index FAIXAETARIA_PK;

drop table FAIXAETARIA;

drop index NASCE_FK;

drop index JOGADOR_PK;

drop table JOGADOR;

drop index PARTICIPA_FK;

drop index COMPARECE_FK;

drop index INCLUI_FK;

drop index JOGADOR_JOGO_EQUIPA_PK;

drop table JOGADOR_JOGO_EQUIPA;

drop index RECEBE_FK;

drop index ALOJAM_FK;

drop index JOGO_PK;

drop table JOGO;

drop index MODALIDADE_PK;

drop table MODALIDADE;

drop index PAIS_PK;

drop table PAIS;

drop index PARTE_PK;

drop table PARTE;

drop index REFERENTE_FK;

drop index PONTUA_FK;

drop index POSSUI_FK;

drop index PONTUACAO_PK;

drop table PONTUACAO;

drop index REALIZADO2_FK;

drop index REALIZADO_FK;

drop index REALIZADO_PK;

drop table REALIZADO;

drop index OCORRE_FK;

drop index OBJETO_FK;

drop index SUBSTITUICAO_PK;

drop table SUBSTITUICAO;

drop index TEM2_FK;

drop index TEM_FK;

drop index TEM_PK;

drop table TEM;

drop index NATURAL_FK;

drop index TREINAM_FK;

drop index TREINADOR_PK;

drop table TREINADOR;
*/
/*==============================================================*/
/* Table: ACAODISCIPLINAR                                       */
/*==============================================================*/
create table ACAODISCIPLINAR (
   AD_IDACAO            SERIAL,
   J_IDJOGADOR          INT4                 null,
   JO_IDJOGO            INT4                 null,
   AD_TIPO              TEXT                 not null,
   AD_MINUTOS           NUMERIC              not null,
   constraint PK_ACAODISCIPLINAR primary key (AD_IDACAO)
);

/*==============================================================*/
/* Index: ACAODISCIPLINAR_PK                                    */
/*==============================================================*/
create unique index ACAODISCIPLINAR_PK on ACAODISCIPLINAR (
AD_IDACAO
);

/*==============================================================*/
/* Index: ALVO_FK                                               */
/*==============================================================*/
create  index ALVO_FK on ACAODISCIPLINAR (
J_IDJOGADOR
);

/*==============================================================*/
/* Index: ACONTECE_FK                                           */
/*==============================================================*/
create  index ACONTECE_FK on ACAODISCIPLINAR (
JO_IDJOGO
);

/*==============================================================*/
/* Table: CAMPEONATO                                            */
/*==============================================================*/
create table CAMPEONATO (
   C_IDCAMPEONATO       SERIAL,
   C_NOME               TEXT                 not null,
   C_NEQUIPAS           NUMERIC              not null,
   constraint PK_CAMPEONATO primary key (C_IDCAMPEONATO)
);

/*==============================================================*/
/* Index: CAMPEONATO_PK                                         */
/*==============================================================*/
create unique index CAMPEONATO_PK on CAMPEONATO (
C_IDCAMPEONATO
);

/*==============================================================*/
/* Table: COMPOSTO                                              */
/*==============================================================*/
create table COMPOSTO (
   C_IDCAMPEONATO       SERIAL,
   EQ_IDEQUIPA          INT4                 not null,
   constraint PK_COMPOSTO primary key (C_IDCAMPEONATO, EQ_IDEQUIPA)
);

/*==============================================================*/
/* Index: COMPOSTO_PK                                           */
/*==============================================================*/
create unique index COMPOSTO_PK on COMPOSTO (
C_IDCAMPEONATO,
EQ_IDEQUIPA
);

/*==============================================================*/
/* Index: COMPOSTO_FK                                           */
/*==============================================================*/
create  index COMPOSTO_FK on COMPOSTO (
C_IDCAMPEONATO
);

/*==============================================================*/
/* Index: COMPOSTO2_FK                                          */
/*==============================================================*/
create  index COMPOSTO2_FK on COMPOSTO (
EQ_IDEQUIPA
);

/*==============================================================*/
/* Table: CONDICIONADO                                          */
/*==============================================================*/
create table CONDICIONADO (
   J_IDJOGADOR          INT4                 not null,
   EJ_IDESTADO          INT4                 not null,
   constraint PK_CONDICIONADO primary key (J_IDJOGADOR, EJ_IDESTADO)
);

/*==============================================================*/
/* Index: CONDICIONADO_PK                                       */
/*==============================================================*/
create unique index CONDICIONADO_PK on CONDICIONADO (
J_IDJOGADOR,
EJ_IDESTADO
);

/*==============================================================*/
/* Index: CONDICIONADO_FK                                       */
/*==============================================================*/
create  index CONDICIONADO_FK on CONDICIONADO (
J_IDJOGADOR
);

/*==============================================================*/
/* Index: CONDICIONADO2_FK                                      */
/*==============================================================*/
create  index CONDICIONADO2_FK on CONDICIONADO (
EJ_IDESTADO
);

/*==============================================================*/
/* Table: EPOCA                                                 */
/*==============================================================*/
create table EPOCA (
   E_IDEPOCA            SERIAL,
   E_INICIO             DATE                 not null,
   E_FIM                DATE                 not null,
   constraint PK_EPOCA primary key (E_IDEPOCA)
);

/*==============================================================*/
/* Index: EPOCA_PK                                              */
/*==============================================================*/
create unique index EPOCA_PK on EPOCA (
E_IDEPOCA
);

/*==============================================================*/
/* Table: EQUIPA                                                */
/*==============================================================*/
create table EQUIPA (
   EQ_IDEQUIPA          SERIAL,
   FE_IDFAIXAETARIA     INT4                 null,
   M_IDMODALIDADE       INT4                 null,
   EQ_NOME              TEXT                 not null,
   EQ_GENERO            TEXT                 not null,
   constraint PK_EQUIPA primary key (EQ_IDEQUIPA)
);

/*==============================================================*/
/* Index: EQUIPA_PK                                             */
/*==============================================================*/
create unique index EQUIPA_PK on EQUIPA (
EQ_IDEQUIPA
);

/*==============================================================*/
/* Index: PRATICA_FK                                            */
/*==============================================================*/
create  index PRATICA_FK on EQUIPA (
M_IDMODALIDADE
);

/*==============================================================*/
/* Index: PERTENCE_FK                                           */
/*==============================================================*/
create  index PERTENCE_FK on EQUIPA (
FE_IDFAIXAETARIA
);

/*==============================================================*/
/* Table: ESPACODESPORTIVO                                      */
/*==============================================================*/
create table ESPACODESPORTIVO (
   ED_IDESPACO          SERIAL,
   ED_NOME              TEXT                 not null,
   ED_CAPACIDADE        NUMERIC              not null,
   ED_DATAINAUGURACAO   DATE                 not null,
   constraint PK_ESPACODESPORTIVO primary key (ED_IDESPACO)
);

/*==============================================================*/
/* Index: ESPACODESPORTIVO_PK                                   */
/*==============================================================*/
create unique index ESPACODESPORTIVO_PK on ESPACODESPORTIVO (
ED_IDESPACO
);

/*==============================================================*/
/* Table: ESTADOJOGADOR                                         */
/*==============================================================*/
create table ESTADOJOGADOR (
   EJ_IDESTADO          SERIAL,
   EJ_DESCRICAO         TEXT                 not null,
   constraint PK_ESTADOJOGADOR primary key (EJ_IDESTADO)
);

/*==============================================================*/
/* Index: ESTADOJOGADOR_PK                                      */
/*==============================================================*/
create unique index ESTADOJOGADOR_PK on ESTADOJOGADOR (
EJ_IDESTADO
);

/*==============================================================*/
/* Table: FAIXAETARIA                                           */
/*==============================================================*/
create table FAIXAETARIA (
   FE_IDFAIXAETARIA     SERIAL,
   FE_NOME              TEXT                 not null,
   constraint PK_FAIXAETARIA primary key (FE_IDFAIXAETARIA)
);

/*==============================================================*/
/* Index: FAIXAETARIA_PK                                        */
/*==============================================================*/
create unique index FAIXAETARIA_PK on FAIXAETARIA (
FE_IDFAIXAETARIA
);

/*==============================================================*/
/* Table: JOGADOR                                               */
/*==============================================================*/
create table JOGADOR (
   J_IDJOGADOR          SERIAL,
   P_IDPAIS             INT4                 null,
   J_NOME               TEXT                 not null,
   J_DATANASCIMENTO     DATE                 not null,
   J_POSICAO            TEXT                 not null,
   J_NUMEROCAMISOLA	INT4		     not null,
   J_VALORMERCADO	INT4		     not null,
   constraint PK_JOGADOR primary key (J_IDJOGADOR)
);

/*==============================================================*/
/* Index: JOGADOR_PK                                            */
/*==============================================================*/
create unique index JOGADOR_PK on JOGADOR (
J_IDJOGADOR
);

/*==============================================================*/
/* Index: NASCE_FK                                              */
/*==============================================================*/
create  index NASCE_FK on JOGADOR (
P_IDPAIS
);

/*==============================================================*/
/* Table: JOGADOR_JOGO_EQUIPA                                   */
/*==============================================================*/
create table JOGADOR_JOGO_EQUIPA (
   JJE_ID               SERIAL,
   EQ_IDEQUIPA          INT4                 null,
   JO_IDJOGO            INT4                 null,
   J_IDJOGADOR          INT4                 null,
   JJE_TIPO             TEXT                 not null,
   constraint PK_JOGADOR_JOGO_EQUIPA primary key (JJE_ID)
);

/*==============================================================*/
/* Index: JOGADOR_JOGO_EQUIPA_PK                                */
/*==============================================================*/
create unique index JOGADOR_JOGO_EQUIPA_PK on JOGADOR_JOGO_EQUIPA (
JJE_ID
);

/*==============================================================*/
/* Index: INCLUI_FK                                             */
/*==============================================================*/
create  index INCLUI_FK on JOGADOR_JOGO_EQUIPA (
JO_IDJOGO
);

/*==============================================================*/
/* Index: COMPARECE_FK                                          */
/*==============================================================*/
create  index COMPARECE_FK on JOGADOR_JOGO_EQUIPA (
J_IDJOGADOR
);

/*==============================================================*/
/* Index: PARTICIPA_FK                                          */
/*==============================================================*/
create  index PARTICIPA_FK on JOGADOR_JOGO_EQUIPA (
EQ_IDEQUIPA
);

/*==============================================================*/
/* Table: JOGO                                                  */
/*==============================================================*/
create table JOGO (
   JO_IDJOGO            SERIAL,
   C_IDCAMPEONATO       INT4                 null,
   ED_IDESPACO          INT4                 null,
   JO_DATA              DATE                 not null,
   JO_HORA              TIME                 not null,
   constraint PK_JOGO primary key (JO_IDJOGO)
);

/*==============================================================*/
/* Index: JOGO_PK                                               */
/*==============================================================*/
create unique index JOGO_PK on JOGO (
JO_IDJOGO
);

/*==============================================================*/
/* Index: ALOJAM_FK                                             */
/*==============================================================*/
create  index ALOJAM_FK on JOGO (
C_IDCAMPEONATO
);

/*==============================================================*/
/* Index: RECEBE_FK                                             */
/*==============================================================*/
create  index RECEBE_FK on JOGO (
ED_IDESPACO
);

/*==============================================================*/
/* Table: MODALIDADE                                            */
/*==============================================================*/
create table MODALIDADE (
   M_IDMODALIDADE       SERIAL,
   M_NOME               TEXT                 not null,
   constraint PK_MODALIDADE primary key (M_IDMODALIDADE)
);

/*==============================================================*/
/* Index: MODALIDADE_PK                                         */
/*==============================================================*/
create unique index MODALIDADE_PK on MODALIDADE (
M_IDMODALIDADE
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS (
   P_IDPAIS             SERIAL,
   P_NOME               TEXT                 not null,
   P_CODIGO             TEXT                 not null,
   P_INDICATIVO         NUMERIC(8)           not null,
   constraint PK_PAIS primary key (P_IDPAIS)
);

/*==============================================================*/
/* Index: PAIS_PK                                               */
/*==============================================================*/
create unique index PAIS_PK on PAIS (
P_IDPAIS
);

/*==============================================================*/
/* Table: PARTE                                                 */
/*==============================================================*/
create table PARTE (
   P_IDPARTE             SERIAL,
   P_RESULTADOEQUIPACASA INT4                 not null,
   P_RESULTADOEQUIPAFORA INT4                 not null,
   P_NPARTE             NUMERIC              null,
   constraint PK_PARTE primary key (P_IDPARTE)
);

/*==============================================================*/
/* Index: PARTE_PK                                              */
/*==============================================================*/
create unique index PARTE_PK on PARTE (
P_IDPARTE
);

/*==============================================================*/
/* Table: PONTUACAO                                             */
/*==============================================================*/
create table PONTUACAO (
   PO_IDPONTUACAO       SERIAL,
   J_IDJOGADOR          INT4                 null,
   P_IDPARTE            INT4                 null,
   JO_IDJOGO            INT4                 null,
   PO_QUANTIDADE        NUMERIC              not null,
   PO_MINUTOS           NUMERIC              not null,
   PO_TIPO              TEXT                 not null,
   constraint PK_PONTUACAO primary key (PO_IDPONTUACAO)
);

/*==============================================================*/
/* Index: PONTUACAO_PK                                          */
/*==============================================================*/
create unique index PONTUACAO_PK on PONTUACAO (
PO_IDPONTUACAO
);

/*==============================================================*/
/* Index: POSSUI_FK                                             */
/*==============================================================*/
create  index POSSUI_FK on PONTUACAO (
JO_IDJOGO
);

/*==============================================================*/
/* Index: PONTUA_FK                                             */
/*==============================================================*/
create  index PONTUA_FK on PONTUACAO (
J_IDJOGADOR
);

/*==============================================================*/
/* Index: REFERENTE_FK                                          */
/*==============================================================*/
create  index REFERENTE_FK on PONTUACAO (
P_IDPARTE
);

/*==============================================================*/
/* Table: REALIZADO                                             */
/*==============================================================*/
create table REALIZADO (
   C_IDCAMPEONATO       INT4                 not null,
   E_IDEPOCA            INT4                 not null,
   constraint PK_REALIZADO primary key (C_IDCAMPEONATO, E_IDEPOCA)
);

/*==============================================================*/
/* Index: REALIZADO_PK                                          */
/*==============================================================*/
create unique index REALIZADO_PK on REALIZADO (
C_IDCAMPEONATO,
E_IDEPOCA
);

/*==============================================================*/
/* Index: REALIZADO_FK                                          */
/*==============================================================*/
create  index REALIZADO_FK on REALIZADO (
C_IDCAMPEONATO
);

/*==============================================================*/
/* Index: REALIZADO2_FK                                         */
/*==============================================================*/
create  index REALIZADO2_FK on REALIZADO (
E_IDEPOCA
);

/*==============================================================*/
/* Table: SUBSTITUICAO                                          */
/*==============================================================*/
create table SUBSTITUICAO (
   S_IDSUBSTITUICAO     SERIAL,
   J_IDJOGADOR          INT4                 null,
   JO_IDJOGO            INT4                 null,
   S_TIPO               TEXT                 not null,
   S_MINUTOS            NUMERIC              not null,
   constraint PK_SUBSTITUICAO primary key (S_IDSUBSTITUICAO)
);

/*==============================================================*/
/* Index: SUBSTITUICAO_PK                                       */
/*==============================================================*/
create unique index SUBSTITUICAO_PK on SUBSTITUICAO (
S_IDSUBSTITUICAO
);

/*==============================================================*/
/* Index: OBJETO_FK                                             */
/*==============================================================*/
create  index OBJETO_FK on SUBSTITUICAO (
J_IDJOGADOR
);

/*==============================================================*/
/* Index: OCORRE_FK                                             */
/*==============================================================*/
create  index OCORRE_FK on SUBSTITUICAO (
JO_IDJOGO
);

/*==============================================================*/
/* Table: TEM                                                   */
/*==============================================================*/
create table TEM (
   EQ_IDEQUIPA          INT4                 not null,
   J_IDJOGADOR          INT4                 not null,
   constraint PK_TEM primary key (EQ_IDEQUIPA, J_IDJOGADOR)
);

/*==============================================================*/
/* Index: TEM_PK                                                */
/*==============================================================*/
create unique index TEM_PK on TEM (
EQ_IDEQUIPA,
J_IDJOGADOR
);

/*==============================================================*/
/* Index: TEM_FK                                                */
/*==============================================================*/
create  index TEM_FK on TEM (
EQ_IDEQUIPA
);

/*==============================================================*/
/* Index: TEM2_FK                                               */
/*==============================================================*/
create  index TEM2_FK on TEM (
J_IDJOGADOR
);

/*==============================================================*/
/* Table: TREINADOR                                             */
/*==============================================================*/
create table TREINADOR (
   T_IDTREINADOR        SERIAL,
   P_IDPAIS             INT4                 null,
   EQ_IDEQUIPA          INT4                 null,
   T_DATANASCIMENTO     DATE                 not null,
   T_NOME               TEXT                 null,
   constraint PK_TREINADOR primary key (T_IDTREINADOR)
);

/*==============================================================*/
/* Index: TREINADOR_PK                                          */
/*==============================================================*/
create unique index TREINADOR_PK on TREINADOR (
T_IDTREINADOR
);

/*==============================================================*/
/* Index: TREINAM_FK                                            */
/*==============================================================*/
create  index TREINAM_FK on TREINADOR (
EQ_IDEQUIPA
);

/*==============================================================*/
/* Index: NATURAL_FK                                            */
/*==============================================================*/
create  index NATURAL_FK on TREINADOR (
P_IDPAIS
);

alter table ACAODISCIPLINAR
   add constraint FK_ACAODISC_ACONTECE_JOGO foreign key (JO_IDJOGO)
      references JOGO (JO_IDJOGO)
      on delete restrict on update restrict;

alter table ACAODISCIPLINAR
   add constraint FK_ACAODISC_ALVO_JOGADOR foreign key (J_IDJOGADOR)
      references JOGADOR (J_IDJOGADOR)
      on delete restrict on update restrict;

alter table COMPOSTO
   add constraint FK_COMPOSTO_COMPOSTO_CAMPEONA foreign key (C_IDCAMPEONATO)
      references CAMPEONATO (C_IDCAMPEONATO)
      on delete restrict on update restrict;

alter table COMPOSTO
   add constraint FK_COMPOSTO_COMPOSTO2_EQUIPA foreign key (EQ_IDEQUIPA)
      references EQUIPA (EQ_IDEQUIPA)
      on delete restrict on update restrict;

alter table CONDICIONADO
   add constraint FK_CONDICIO_CONDICION_JOGADOR foreign key (J_IDJOGADOR)
      references JOGADOR (J_IDJOGADOR)
      on delete restrict on update restrict;

alter table CONDICIONADO
   add constraint FK_CONDICIO_CONDICION_ESTADOJO foreign key (EJ_IDESTADO)
      references ESTADOJOGADOR (EJ_IDESTADO)
      on delete restrict on update restrict;

alter table EQUIPA
   add constraint FK_EQUIPA_PERTENCE_FAIXAETA foreign key (FE_IDFAIXAETARIA)
      references FAIXAETARIA (FE_IDFAIXAETARIA)
      on delete restrict on update restrict;

alter table EQUIPA
   add constraint FK_EQUIPA_PRATICA_MODALIDA foreign key (M_IDMODALIDADE)
      references MODALIDADE (M_IDMODALIDADE)
      on delete restrict on update restrict;

alter table JOGADOR
   add constraint FK_JOGADOR_NASCE_PAIS foreign key (P_IDPAIS)
      references PAIS (P_IDPAIS)
      on delete restrict on update restrict;

alter table JOGADOR_JOGO_EQUIPA
   add constraint FK_JOGADOR__COMPARECE_JOGADOR foreign key (J_IDJOGADOR)
      references JOGADOR (J_IDJOGADOR)
      on delete restrict on update restrict;

alter table JOGADOR_JOGO_EQUIPA
   add constraint FK_JOGADOR__INCLUI_JOGO foreign key (JO_IDJOGO)
      references JOGO (JO_IDJOGO)
      on delete restrict on update restrict;

alter table JOGADOR_JOGO_EQUIPA
   add constraint FK_JOGADOR__PARTICIPA_EQUIPA foreign key (EQ_IDEQUIPA)
      references EQUIPA (EQ_IDEQUIPA)
      on delete restrict on update restrict;

alter table JOGO
   add constraint FK_JOGO_ALOJAM_CAMPEONA foreign key (C_IDCAMPEONATO)
      references CAMPEONATO (C_IDCAMPEONATO)
      on delete restrict on update restrict;

alter table JOGO
   add constraint FK_JOGO_RECEBE_ESPACODE foreign key (ED_IDESPACO)
      references ESPACODESPORTIVO (ED_IDESPACO)
      on delete restrict on update restrict;

alter table PONTUACAO
   add constraint FK_PONTUACA_PONTUA_JOGADOR foreign key (J_IDJOGADOR)
      references JOGADOR (J_IDJOGADOR)
      on delete restrict on update restrict;

alter table PONTUACAO
   add constraint FK_PONTUACA_POSSUI_JOGO foreign key (JO_IDJOGO)
      references JOGO (JO_IDJOGO)
      on delete restrict on update restrict;

alter table PONTUACAO
   add constraint FK_PONTUACA_REFERENTE_PARTE foreign key (P_IDPARTE)
      references PARTE (P_IDPARTE)
      on delete restrict on update restrict;

alter table REALIZADO
   add constraint FK_REALIZAD_REALIZADO_CAMPEONA foreign key (C_IDCAMPEONATO)
      references CAMPEONATO (C_IDCAMPEONATO)
      on delete restrict on update restrict;

alter table REALIZADO
   add constraint FK_REALIZAD_REALIZADO_EPOCA foreign key (E_IDEPOCA)
      references EPOCA (E_IDEPOCA)
      on delete restrict on update restrict;

alter table SUBSTITUICAO
   add constraint FK_SUBSTITU_OBJETO_JOGADOR foreign key (J_IDJOGADOR)
      references JOGADOR (J_IDJOGADOR)
      on delete restrict on update restrict;

alter table SUBSTITUICAO
   add constraint FK_SUBSTITU_OCORRE_JOGO foreign key (JO_IDJOGO)
      references JOGO (JO_IDJOGO)
      on delete restrict on update restrict;

alter table TEM
   add constraint FK_TEM_TEM_EQUIPA foreign key (EQ_IDEQUIPA)
      references EQUIPA (EQ_IDEQUIPA)
      on delete restrict on update restrict;

alter table TEM
   add constraint FK_TEM_TEM2_JOGADOR foreign key (J_IDJOGADOR)
      references JOGADOR (J_IDJOGADOR)
      on delete restrict on update restrict;

alter table TREINADOR
   add constraint FK_TREINADO_NATURAL_PAIS foreign key (P_IDPAIS)
      references PAIS (P_IDPAIS)
      on delete restrict on update restrict;

alter table TREINADOR
   add constraint FK_TREINADO_TREINAM_EQUIPA foreign key (EQ_IDEQUIPA)
      references EQUIPA (EQ_IDEQUIPA)
      on delete restrict on update restrict;

