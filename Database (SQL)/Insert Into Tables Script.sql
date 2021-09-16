-- USE PROJETO[BD]

/***************************************************************************************************************/
--INSERÇAO DE DADOS--


--Cidades--

INSERT INTO Cidades (Nome_Cidade) VALUES 
/* Portugal */ ('Viseu'),('Porto'),('Lisboa'),('Braga'),('Guimaraes'),('Portimao'),
/* Espanha */ ('Barcelona'),('Madrid'),('Valencia'),('Sevilha'),
/* França */ ('Paris'),('Marselha'),('Lille'),('Toulouse')

--select * from Cidades--



--Estado_Jogadores--

INSERT INTO Estado_Jogadores (Descricao_Estado) VALUES 
('OK'),
('Expulso'),
('Lesionado')

--select * from Estado_Jogadores--



--Posicoes--

INSERT INTO Posicoes (Descricao_Posicao) VALUES 
('GR'),('DC'),('MC'),('PL')

--select * from Posicoes--



--Paises--

INSERT INTO Paises (Nome_Pais, Codigo_Pais, Indicativo) VALUES 
('Portugal', 'POR', 351),
('Espanha', 'ESP', 34),
('Brasil', 'BRA', 55),
('França', 'FRA', 33),
('Alemanha', 'ALE', 49),
('Italia','ITA',46),
('Argentina','ARG',23)

--select * from Paises--



--Treinadores--

INSERT INTO Treinadores (Nome_Treinador, Data_Nascimento, Id_Pais) VALUES 
('Sérgio Conceição','1946-10-14', 1),
('Bruno Lage ','1971-01-18', 1),
('Custódio', '1976-04-22', 1),
('Ivo Vieira', '1955-05-24', 1),
('Quique Setién', '1989-08-12', 2),
('Zinédine Zidane','1980-05-26',4),
('Albert Celades','1970-02-12',2),
('Julen Lopetegui','1976-06-13',2),
('Thomas Tuchel','1975-11-03',5),
('André Villas Boas','1978-05-06',1),
('Christophe Galtier','1980-09-04',4),
('Denis Zanko','1980-06-26',4)

--select * from Treinadores--



--Estadios--

INSERT INTO Estadios (Nome, Lotacao, Ano_Construcao, Cidade_Estadio) VALUES 
('Dragao', 55560, 1981,  2),
('Luz', 65647, 2003, 3),
('Municipal de Braga', 30560, 1981,  4),
('D. Afonso Henriques',30146, 1965,5),
('Camp Nou',100000, 1957,7),
(' Santiago Bernabeu',82000,1947,8),
('Mestalla',55000,1941,9),
('Ramon Sanchez-Pizjuan',43000,1986,10),
('Parc des Princes',48000,1975,11),
('Vélodrome',67000,2015,12),
('Pierre-Mauroy',55000,2009,13),
('Municipal de Toulouse',33000,1937,14)

--select * from Estadios--


--Clubes--

INSERT INTO Clubes (Nome_Clube, Mascote, Simbolo, Estadio, Treinador_Clube) VALUES 
('Porto', 'Dragão', 'Dragão', 1, 1),
('Benfica', 'Vitoria','Aguia' , 2, 2),
('Braga', 'Guerreiro', 'Escudo',3,3),
('Guimarães', 'Super Afonso', 'Escudo',4,4),
('FC Barcelona', 'L avi del Barça', 'Escudo',5,5),
('Real Madrid', 'Cão', 'Escudo',6,6),
('Valencia', 'Morcego', 'Morcego',7,7),
('Sevilha', 'Leão', 'Escudo',8,8),
('PSG', 'Lince', 'Escudo',9,9),
('Marselha', 'Castor', 'Escudo',10,10),
('Lille', 'Lobo', 'Escudo',11,11),
('Toulouse', 'Cavalo', 'Escudo',12,12)

--select * from Clubes--



--Jogadores--

INSERT INTO Jogadores (Nome_Jogador, Data_Nascimento, Posicao_Jogador, Pais_Jogador, Clube_Jogador,Estado_Jogador,Cartoes_acumulados)
VALUES
/*Guimaraes*/
('Fábio Santos','1988-02-22',2,1,4,1,1),
('Filipe Soares','2000-06-27',2,1,4,1,1),
('Bruno Loureiro','1989-09-23',3,1,4,1,0),
/*Benfica*/
('Grimaldo','1996-05-24',2,2,2,1,0),
('Carlos Vinícius','1995-03-22',4,3,2,1,1),
('Chiquinho','1995-07-19',3,1,2,1,0),
/*Braga*/
('Bruno Viana','1995-05-3',2,3,3,1,0),
('André Horta','1996-06-12',3,1,3,1,1),
('Raúl Silva','1992-12-04',2,3,3,1,1),
/*Porto*/
('Alex Telles','1994-06-11',2,3,1,1,0),
('Fabio Vieira','1995-09-20',2,1,1,1,1),
('Sérgio Oliveira','1997-12-05',3,1,1,1,0),
/* Valencia*/
('Fabio Szymonek', '1990-05-11', 1, 2, 7,1,2),
('Diogo Goncalves', '1997-07-11', 3, 1, 7,1,0),
('Mehdi Taremi', '1992-07-18', 4, 3, 7,1,0),
/*Real*/
('F. Mendy','1998-09-21',2,4,6,1,0),
('Marcelo','1993-08-25',2,3,6,1,2),
('K. Benzema','1992-02-04',4,4,6,1,0),
/*Barca*/
('Messi','1994-05-06',4,7,5,1,0),
('Dembele','1993-12-26',3,4,5,1,1),
('C. Lenglet','1993-10-08',2,3,5,1,1),
/*sevilha*/
('Diego','1993-09-08',2,3,8,1,0),
('Rony Lopes','1995-11-29',3,1,8,1,1),
('Munir El Haddadi','1997-01-29',4,2,8,1,0),
/* psg*/ 
('Marco Verratti','1992-12-05',3,6,9,1,0),
('Mauro Icardi','1993-02-19',4,7,9,1,0),
('Kylian Mbappé', '1990-12-20',4,4,9,1,0),
/*Marselha*/
('J. Amavi','1995-08-08',4,4,10,1,0),
('Álvaro González','1997-07-08',2,2,10,1,1),
('V. Germain','1994-02-22',4,4,10,1,1),
/*Lille*/
('Gabriel','1997-05-21',2,3,11,1,0),
('Renato Sanches','1998-10-21',3,1,11,1,1),
('J. Bamba','1997-11-20',3,4,11,1,0),
/*Toulouse*/
('J. Antiste','1995-01-23',2,4,12,1,0),
('A. Taoui','1993-11-02',3,4,12,1,1),
('S. Semaoun','1998-10-23',4,4,12,1,1)

--select * from Jogadores--



--Campeonatos--

INSERT INTO Campeonatos (Nome_Campeonato, Data_Inicio, Data_Fim, Pais_Campeonato) VALUES 
('Liga NOS', '2019-08-09 ', '2020-07-19', 1),
('La Liga', '2019-08-12', '2020-07-19', 2),
('Ligue 1', '2019-08-22', '2020-07-05', 3)

--select * from Campeonatos--



--Clubes_Campeonatos--

INSERT INTO Clubes_Campeonatos (Id_Clube, Id_Campeonato,Cartoes_Amarelos, Cartoes_Vermelhos) VALUES
(1, 1,       11, 0 ),
(2, 1,            7, 2 ),
(3, 1,          11, 0),
(4, 1,               13, 1),

(5, 2,          6, 0),
(6, 2,            7, 2),
(7, 2,           10, 0),
(8, 2,              13, 1),

(9, 3,         6, 0),
(10, 3,           7, 2),
(11, 3,         10, 0),
(12, 3,               13, 1)

--select * from Clubes_Campeonatos where Id_Campeonato=1 order by Pontos desc--



--Jogos--

INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-11  17:30:00', 4, 3, 4,2,2)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-11  15:00:00', 1, 2, 1,0,1)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-15  17:30:00', 1, 4, 1,2,0)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-15  15:00:00', 2, 3, 2,2,0)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
--
('2020-06-11  17:30:00', 8, 7, 8,2,1)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-11  15:00:00', 5, 6, 5,0,1)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-15  17:30:00', 5, 8, 5,2,0)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-15  15:00:00', 6, 7, 6,2,0)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 

('2020-06-11  17:30:00', 12, 11, 12,2,1)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-11  15:00:00', 9, 10, 9,0,1)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-15  17:30:00', 9, 12, 9,2,0)
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES 
('2020-06-15  15:00:00', 10, 11, 10,2,0)


---------

INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio) VALUES 

('2020-07-20 17:30:00', 4, 2, 4),
('2020-07-20  15:00:00', 3, 1, 3),

('2020-06-24 17:30:00', 3, 4, 3),
('2020-06-24  15:00:00', 2, 1, 2),

('2020-06-27 17:30:00', 4, 1, 4),
('2020-06-27  15:00:00', 3, 2, 3),

('2020-06-30 17:30:00', 2, 4, 2),
('2020-06-30  15:00:00', 1,3, 1),

---------

('2020-07-20 17:30:00', 8, 6, 8),
('2020-07-20  15:00:00', 7, 5, 7),

('2020-06-24 17:30:00', 7, 8, 7),
('2020-06-24  15:00:00', 6, 5, 6),

('2020-06-27 17:30:00', 8, 5, 8),
('2020-06-27  15:00:00', 7, 6, 7),


('2020-06-30 17:30:00', 6, 8, 6),
('2020-06-30  15:00:00', 5,7, 5),

---------

('2020-07-20 17:30:00', 12, 10, 12),
('2020-07-20  15:00:00', 11, 9, 11),

('2020-06-24 17:30:00', 11, 12, 11),
('2020-06-24  15:00:00', 10, 9, 10),

('2020-06-27 17:30:00', 12, 9, 12),
('2020-06-27  15:00:00', 11, 10, 11),

('2020-06-30 17:30:00', 10, 12, 10),
('2020-06-30  15:00:00', 9,11, 9)

--select * from Jogos--
--select * from Jogadores--



--Jogos_Jogadores--

INSERT INTO Jogadores_Jogos (Id_Jogo, Id_Jogador, Cartao_Vermelho, Cartao_Amarelo, Minutos_Jogo, Estado_Jogador) VALUES 
(1, 1, 0, 1, 90, 1),    /*4-3*/
(1, 2 ,0, 1, 90, 1),
(1, 7, 0, 0, 90, 1),
(1, 8, 0, 1, 70, 1),
(1, 9, 0, 0, 30, 1),

(2, 10, 1, 0, 80, 2),  /*2-1*/ 
(2, 11 ,0, 1, 90, 1),
(2, 4, 0, 0, 90, 1),
(2, 5, 0, 1, 70, 1),
(2, 6, 0, 0, 30, 1),

(3, 12, 0, 0, 90, 1), /* 1-4*/
(3, 11,0, 0, 90, 1),
(3, 1, 0, 1, 70, 1),
(3, 3, 0, 0, 30, 1),

(4, 4, 0, 0, 90, 1), /*2-3*/
(4, 5,0, 0, 90, 1),
(4, 9, 0, 1, 70, 1),
(4, 7, 0, 0, 30, 1),

---------

(5, 22, 0, 0, 90, 1), /* 8-7*/
(5, 23,0, 0, 90, 1),
(5, 13, 0, 1, 70, 1),
(5, 14, 0, 0, 30, 1),

(6, 19, 0, 0, 90, 1),  /*5-6*/ 
(6, 20 ,0, 1, 90, 1),
(6, 21, 1, 0, 80, 2),
(6, 17, 0, 1, 70, 1),
(6, 18, 0, 0, 30, 1),

(7, 19, 0, 0, 90, 1),    /*5-8*/
(7, 21 ,0, 1, 90, 1),
(7, 22, 0, 0, 90, 1),
(7, 23, 0, 1, 70, 1),
(7, 24, 0, 0, 30, 1),

(8, 16, 1, 0, 80, 2),  /*6-7*/ 
(8, 17 ,0, 1, 90, 1),
(8, 18, 0, 0, 90, 1),
(8, 13, 0, 1, 70, 1),
(8, 15, 0, 0, 30, 1),

(9, 25, 0, 0, 90, 1), /* 9-10*/
(9, 26,0, 0, 90, 1),
(9, 28, 0, 1, 70, 1),
(9, 29, 0, 0, 30, 1),

(10, 31, 0, 0, 90, 1), /*12-11*/
(10, 32,0, 0, 90, 1),
(10, 34, 0, 1, 70, 1),
(10, 35, 0, 0, 30, 1),

(11, 26, 0, 0, 90, 1), /* 9-12*/
(11, 27,0, 0, 90, 1),
(11, 35, 0, 1, 70, 1),
(11, 36, 0, 0, 30, 1),

(12, 30, 0, 0, 90, 1),  /*10-11*/ 
(12, 29 ,0, 1, 90, 1),
(12, 28, 1, 0, 80, 2),
(12, 31, 0, 1, 70, 1),
(12, 33, 0, 0, 30, 1)

--select Id_Jogador,SUM(Cartao_amarelo) from Jogadores_Jogos--



--Substituicoes--

INSERT INTO Substituicoes (Jogador, Minuto_de_jogo, Substituiçao, Jogo) VALUES 
(8, 70, 'Saiu', 1),(9, 30,'entrou', 1),
(5, 70, 'Saiu',2),(6, 30, 'entrou',2),
(1,70,'Saiu',3),(3,30,'entrou',3),
(9,70,'Saiu',4),(7,30,'entrou',4),
(13,70,'Saiu',5),(14,30,'entrou',5),
(17,70,'Saiu',6),(18,30,'entrou',6),
(23, 70, 'Saiu', 7),(30, 30, 'entrou', 7),
(13, 70, 'Saiu', 8),(15, 30, 'entrou', 8),
(28,70,'Saiu',9),(29,30,'entrou',9),
(34,70,'Saiu',10),(35,30,'entrou',10),
(35,70,'Saiu',11),(36,30,'entrou',11),
(31,70,'Saiu',12),(33,30,'entrou',12)

--select * from Substituicoes--
