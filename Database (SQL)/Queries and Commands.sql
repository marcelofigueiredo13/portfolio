/***************************************************************************************************************/
--CONSULTAS SQL--


--Devole Os Jogadores Que Nasceram Num Fim De Semana--

SELECT Nome_Jogador, Data_Nascimento, Nome_Pais [Nacionalidade], Descricao_Posicao [Posição]
FROM Jogadores
JOIN Paises
ON Paises.Id_Pais = Jogadores.Pais_Jogador
JOIN Posicoes
ON Posicoes.Id_Posicao = Jogadores.Posicao_Jogador
WHERE DATENAME(DW, Jogadores.Data_Nascimento) LIKE '%Sunday' OR DATENAME(DW, Jogadores.Data_Nascimento) LIKE '%Saturday'
ORDER BY Nome_Jogador



--Total De Cartoes Por Jogador--
 
SELECT Nome_Jogador, Sum(Cartao_Amarelo + Cartao_Vermelho) [Cartões]
FROM Jogadores_Jogos INNER JOIN Jogadores
ON Jogadores_Jogos.Id_Jogador=Jogadores.Id_Jogador
GROUP BY Jogadores_Jogos.Id_Jogador,Nome_Jogador
ORDER BY Cartões



--Devole O Nome Do Treinador E A Nacionalidade Dos Treinadores Que O Primeiro Nome Começa Com A, Têm 50 Ou Menos Anos, A Sua Equipa Tem Uma Diferença De Golos Superior A 10 e Têm Percentagem de Vitórias Maior Que 75%

SELECT Nome_Treinador, Nome_Pais [Nacionalidade]
FROM Treinadores JOIN Paises ON Paises.Id_Pais = Treinadores.Id_Pais
WHERE SUBSTRING(Nome_Treinador, 1, 1) = 'A'
UNION
SELECT Nome_Treinador, Nome_Pais [Nacionalidade]
FROM Treinadores JOIN Paises ON Paises.Id_Pais = Treinadores.Id_Pais
WHERE YEAR(Data_Nascimento) < YEAR(GETDATE()) - 50
UNION
SELECT Nome_Treinador, Nome_Pais [Nacionalidade]
FROM Treinadores JOIN Paises ON Paises.Id_Pais = Treinadores.Id_Pais
JOIN Clubes ON Clubes.Treinador_Clube = Treinadores.Id_Treinador
JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Clubes.Id_Clube
WHERE (Golos_Favor - Golos_Contra) > 10
UNION
SELECT Nome_Treinador, Nome_Pais [Nacionalidade]
FROM Treinadores JOIN Paises ON Paises.Id_Pais = Treinadores.Id_Pais
JOIN Clubes ON Clubes.Treinador_Clube = Treinadores.Id_Treinador
JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Clubes.Id_Clube
WHERE (Vitorias/(Vitorias + Empates + Derrotas)) > 0.75



--SubConsulta Que Devole Os Clubes Que Têm Um Total De Cartões Superior À Média De Cartões Por Clube--

SELECT Nome_Clube, SUM(Cartoes_Acumulados) [Cartões Do Clube]
FROM Clubes
JOIN Jogadores 
ON Jogadores.Clube_Jogador = Clubes.Id_Clube
GROUP BY Nome_Clube, Cartoes_acumulados
HAVING Cartoes_acumulados > ((SELECT SUM(Cartoes_Acumulados) FROM Jogadores)/(SELECT COUNT(Id_Clube) FROM Clubes))



--SubConsulta Que Devolve As Top 5 Cidades Onde Houveram Mais Jogos Sem Existirem Jogadores Lesionados

SELECT TOP 5 Nome_Cidade, COUNT(Id_Jogo) [Número de Jogos na Cidade]
FROM Estadios
JOIN Jogos
ON Jogos.Id_Estadio = Estadios.Id_Estadio
JOIN Cidades
ON Cidades.Id_Cidade = Estadios.Cidade_Estadio
WHERE EXISTS (SELECT Id_Estadio FROM Jogos JOIN Jogadores_Jogos ON Jogadores_Jogos.Id_Jogo = Jogos.Id_Jogo JOIN Jogadores ON Jogadores.Id_Jogador = Jogadores_Jogos.Id_Jogador JOIN Estado_Jogadores ON Estado_Jogadores.Id_Estado = Jogadores.Estado_Jogador WHERE Id_Estado != 3)
GROUP BY Nome_Cidade
ORDER BY [Número de Jogos na Cidade] DESC



/***************************************************************************************************************/
--COMANDOS DML--


--Ativa A Função Que Devolve O Número De Jogadores Por Estado De Um Clube
SELECT * FROM Estado_Jogadores_Equipa('Benfica')



--Ativa A Função Que Devolve A Classificação De Um Campeonato--
SELECT * FROM Classificaçao(3) ORDER BY Pontos DESC--



--Ativa A Função Que Devolve O Jogador Mais Substituído De Um Campeonato
SELECT * FROM fn_JogadorMaisSubstituido('Ligue 1')



--Comandos Para Executar O Procedimenteo Que Devolve O Número De Vitórias Em Casa De Um Clube--
DECLARE @Clube VARCHAR(MAX) 
DECLARE @VitoriasCasa VARCHAR(MAX)
SET @Clube = 'Benfica'
EXEC spVitoriasCasa @Clube, @VitoriasCasa



--Comandos Para Executar O Procedimenteo Que Devolve Os Jogadores De Uma Nacionalidade Num Campeonato--
DECLARE @Campeonato VARCHAR(MAX) 
DECLARE @PaisJogador VARCHAR(MAX)
SET @Campeonato = 'Ligue 1'
SET @PaisJogador = 'França'
EXEC sp_JogadoresPaisCampeonato @PaisJogador, @Campeonato



--Ativa O Trigger Que Atualiza O Estado Dos Jogadores Consoante O Número De Cartões(Supenso ou Apto Para Jogar)--
UPDATE Jogadores SET Cartoes_acumulados = Cartoes_acumulados + 4



--Ativa O Trigger Que Atualiza O Estado Do Jogador Para Expulso, Quando Leva Um Cartão Vermelho--
INSERT INTO Jogadores_Jogos (Id_Jogo, Id_Jogador, Cartao_Vermelho, Cartao_Amarelo, Minutos_Jogo, Estado_Jogador) VALUES (13, 2, 1, 1, 90, 1)



--Ativa O Trigger Que Atualiza A Classificação De Um Campeonato Depois De Um Jogo E Também O Trigger Que Atualiza O Resultado Na Tabela Jogos--
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio,Golos_Visitado,Golos_Visitante) VALUES ('2020-06-11  17:30:00', 1, 2, 1, 2, 2)
