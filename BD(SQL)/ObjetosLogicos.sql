/***************************************************************************************************************/
--PROCEDIMENTOS PARA INSERIR NAS TABELAS--


--Tabela dos paises--

IF OBJECT_ID ('inserir_pais', 'P') IS NOT NULL  
   DROP PROC inserir_pais;
go

CREATE PROC inserir_pais
 @nomepais_param varchar(15),
 @codigopais_param varchar(4),
 @indicativo_param int
AS
INSERT INTO Paises(Nome_Pais, Codigo_Pais, Indicativo)
VALUES (@nomepais_param, @codigopais_param, @indicativo_param)
go

--Tabela dos treinadores--

IF OBJECT_ID ('inserir_treinador', 'P') IS NOT NULL  
   DROP PROC inserir_treinador;
go

CREATE PROC inserir_treinador
 @nometreinador_param varchar(50),
 @datanascimentotreinador_param date,
 @paistreinador_param int
AS
INSERT INTO Treinadores(Nome_Treinador, Data_Nascimento, Id_Pais)
VALUES (@nometreinador_param, @datanascimentotreinador_param, @paistreinador_param)
go

--Tabela dos clubes--

IF OBJECT_ID ('inserir_clube', 'P') IS NOT NULL  
   DROP PROC inserir_clube;
go

CREATE PROC inserir_clube
 @nomeclube_param varchar(50),
 @mascote_param varchar(20),
 @simbolo_param varchar(20),
 @estadioclube_param int,
 @treinadorclube_param int
AS
INSERT INTO Clubes(Nome_Clube, Mascote, Simbolo, Estadio, Treinador_Clube)
VALUES (@nomeclube_param, @mascote_param, @simbolo_param, @estadioclube_param, @treinadorclube_param)
go

--Tabela das cidades--

IF OBJECT_ID ('inserir_cidade', 'P') IS NOT NULL  
   DROP PROC inserir_cidade;
go

CREATE PROC inserir_cidade
 @nomecidade_param varchar(20)
AS
INSERT INTO Cidades (Nome_Cidade)
VALUES (@nomecidade_param)
go

--Tabela dos estadios--

IF OBJECT_ID ('inserir_estadio', 'P') IS NOT NULL  
   DROP PROC inserir_estadio;
go

CREATE PROC inserir_estadio
 @nomeestadio_param varchar(40),
 @lotacao_param int,
 @anocontrucao_param int,
 @cidadeestadio_param int
AS
INSERT INTO Estadios (Nome, Lotacao, Ano_Construcao, Cidade_Estadio)
VALUES (@nomeestadio_param, @lotacao_param, @anocontrucao_param, @cidadeestadio_param)
go

--Tabela das posicoes--

IF OBJECT_ID ('inserir_posicao', 'P') IS NOT NULL  
   DROP PROC inserir_posicao;
go

CREATE PROC inserir_posicao
 @descricaoposicao_param varchar(20)
AS
INSERT INTO Posicoes (Descricao_Posicao)
VALUES (@descricaoposicao_param)
go

--Tabela dos jogadores--

IF OBJECT_ID ('inserir_jogador', 'P') IS NOT NULL  
   DROP PROC inserir_jogador;
go

CREATE PROC inserir_jogador
 @nomejogador_param varchar(60),
 @datanascimento_param date,
 @posicaojogador_param int,
 @paisjogador_param int,
 @clubejogador_param int,
 @estadojogador_param int
AS
INSERT INTO Jogadores (Nome_Jogador, Data_Nascimento, Posicao_Jogador, Pais_Jogador, Clube_Jogador, Estado_Jogador)
VALUES (@nomejogador_param, @datanascimento_param, @posicaojogador_param, @paisjogador_param, @clubejogador_param, @estadojogador_param)
go

--Tabela dos jogos--

IF OBJECT_ID ('inserir_jogo', 'P') IS NOT NULL  
   DROP PROC inserir_jogo;
go

CREATE PROC inserir_jogo
 @datahora_param datetime,
 @clubevisitado_param int,
 @clubevisitante_param int,
 @estadiojogo_param int,
 @golosvisitado_param int,
 @golosvisitante_param int
AS
INSERT INTO Jogos (Data_Hora, Clube_Visitado, Clube_Visitante, Id_Estadio, Golos_Visitado, Golos_Visitante)
VALUES (@datahora_param, @clubevisitado_param, @clubevisitante_param, @estadiojogo_param, @golosvisitado_param, @golosvisitante_param)
go

--Tabela dos campeonatos--

IF OBJECT_ID ('inserir_campeonato', 'P') IS NOT NULL  
   DROP PROC inserir_campeonato;
go

CREATE PROC inserir_campeonato
 @nomecampeonato_param varchar(30),
 @datainicio_param date,
 @datafim_param date,
 @paiscampeonato_param int
AS
INSERT INTO Campeonatos (Nome_Campeonato, Data_Inicio, Data_Fim, Pais_Campeonato)
VALUES (@nomecampeonato_param, @datainicio_param, @datafim_param, @paiscampeonato_param)
go

--Tabela dos jogadores_jogos--

IF OBJECT_ID ('inserir_jogadoresjogos', 'P') IS NOT NULL  
   DROP PROC inserir_jogadoresjogos;
go

CREATE PROC inserir_jogadoresjogos
 @jogo_param int,
 @jogador_param int,
 @cartaovermelho_param int,
 @cartaoamarelo_param int,
 @minutosjogo_param int,
 @estadojogador_param int
AS
INSERT INTO Jogadores_Jogos (Id_Jogo, Id_Jogador, Cartao_Vermelho, Cartao_Amarelo, Minutos_Jogo, Estado_Jogador)
VALUES (@jogo_param, @jogador_param, @cartaovermelho_param, @cartaoamarelo_param, @minutosjogo_param, @estadojogador_param)
go

--Tabela das substituicoes--

IF OBJECT_ID ('inserir_substituicao', 'P') IS NOT NULL  
   DROP PROC inserir_substituicao;
go

CREATE PROC inserir_substituicao
 @jogador_param int,
 @minutojogo_param int,
 @substituicao_param varchar(8),
 @jogo_param int
AS
INSERT INTO Substituicoes(Jogador, Minuto_de_jogo, Substituiçao, Jogo)
VALUES (@jogador_param, @minutojogo_param, @substituicao_param, @jogo_param)
go

--Tabela dos estados--

IF OBJECT_ID ('inserir_estado', 'P') IS NOT NULL  
   DROP PROC inserir_estado;
go

CREATE PROC inserir_estado
 @estado_param varchar(15)
AS
INSERT INTO Estado_Jogadores(Descricao_Estado)
VALUES (@estado_param)
go

--Tabela dos clubes_campeonatos--

IF OBJECT_ID ('inserir_clubecampeonato', 'P') IS NOT NULL  
   DROP PROC inserir_clubecampeonato;
go

CREATE PROC inserir_clubecampeonato
 @clube_param int,
 @campeonato_param int,
 @vitorias_param int,
 @derrotas_param int,
 @empates_param int,
 @golosfavor_param int,
 @goloscontra_param int,
 @cartoesamarelos int,
 @cartoesvermelhos int,
 @pontos int
AS
INSERT INTO Clubes_Campeonatos (Id_Clube, Id_Campeonato, Vitorias, Derrotas, Empates, Golos_Favor, Golos_Contra, Cartoes_Amarelos, Cartoes_Vermelhos, Pontos)
VALUES (@clube_param, @campeonato_param, @vitorias_param, @derrotas_param, @empates_param, @golosfavor_param, @goloscontra_param, @cartoesamarelos, @cartoesvermelhos, @pontos)
go

--Devolve O Número De Vitórias Em Casa De Um Clube--

IF OBJECT_ID ('spVitoriasCasa', 'P') IS NOT NULL  
   DROP PROC spVitoriasCasa;
go

CREATE PROC spVitoriasCasa(@Clube AS VARCHAR(MAX), @VitoriasCasa AS VARCHAR(MAX))
AS
BEGIN
	SELECT Nome_Clube, COUNT(*) [Vitórias Em Casa] FROM Jogos INNER JOIN Clubes ON Clubes.Id_Clube = Jogos.Clube_Visitado GROUP BY Nome_Clube, Golos_Visitado, Golos_Visitante HAVING Nome_Clube = @Clube AND Golos_Visitado > Golos_Visitante
END
go

--Devolve Os Jogadores De Uma Determinada Nacionalidade De Um Determinado Campeonato--

IF OBJECT_ID ('sp_JogadoresPaisCampeonato', 'P') IS NOT NULL  
   DROP PROC sp_JogadoresPaisCampeonato;
go
CREATE PROC sp_JogadoresPaisCampeonato(@Pais AS VARCHAR(MAX), @Campeonato AS VARCHAR(MAX))
AS
BEGIN
	SELECT Nome_Jogador, Descricao_Posicao, Nome_Pais FROM Jogadores JOIN Paises ON Paises.Id_Pais = Jogadores.Pais_Jogador JOIN Posicoes ON Posicoes.Id_Posicao = Jogadores.Posicao_Jogador JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Jogadores.Clube_Jogador JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato 
	WHERE Nome_Pais = @Pais AND Nome_Campeonato = @Campeonato
	ORDER BY Pais_Jogador, Posicao_Jogador
END
go


/***************************************************************************************************************/
--FUNÇÕES--


--Estado Jogadores Por Equipa 

IF OBJECT_ID ('Estado_Jogadores_Equipa', N'IF') IS NOT NULL  
   DROP FUNCTION Estado_Jogadores_Equipa;
go

CREATE FUNCTION Estado_Jogadores_Equipa (@Clube varchar(100))
RETURNS TABLE
RETURN
SELECT 'OK' AS [Estado Jogadores], COUNT(*) AS [Total]
FROM Jogadores inner join Clubes on Jogadores.Clube_Jogador=Clubes.Id_Clube
WHERE Jogadores.Estado_Jogador=1 and Nome_Clube = @Clube
UNION
SELECT 'Expulso' AS [Estado Jogadores], COUNT(*) AS [Total]
FROM Jogadores inner join Clubes on Jogadores.Clube_Jogador=Clubes.Id_Clube
WHERE Jogadores.Estado_Jogador=2 and Nome_Clube = @Clube
UNION
SELECT 'Lesionado' AS [Estado Jogadores], COUNT(*) AS [Total]
FROM Jogadores inner join Clubes on Jogadores.Clube_Jogador=Clubes.Id_Clube
WHERE Jogadores.Estado_Jogador=3 and Nome_Clube = @Clube
go

--Classificaçoes De Um Campeonato (ID)--

IF OBJECT_ID ('Classificaçao', N'IF') IS NOT NULL  
   DROP FUNCTION Classificaçao;
go
CREATE FUNCTION Classificaçao (@Campeonato varchar(100))
RETURNS TABLE
RETURN
	SELECT C.Nome_Clube, Vitorias, Empates, Derrotas, Golos_Favor, Golos_Contra, Cartoes_Amarelos, Cartoes_Vermelhos,Pontos
	FROM Clubes_Campeonatos as CC INNER JOIN Clubes as C
		ON CC.Id_Clube = C.Id_Clube
	WHERE Id_Campeonato = @Campeonato
go	

--Devolve o Jogador Mais Substituido De Um Campeonato--

IF OBJECT_ID ('fn_JogadorMaisSubstituido', N'IF') IS NOT NULL  
   DROP FUNCTION fn_JogadorMaisSubstituido; 
go

CREATE FUNCTION fn_JogadorMaisSubstituido(@Campeonato VARCHAR(MAX))
RETURNS TABLE
AS
RETURN (SELECT TOP 1 Nome_Jogador AS Jogador FROM Jogadores INNER JOIN Substituicoes ON Substituicoes.Jogador = Jogadores.Id_Jogador JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Jogadores.Clube_Jogador JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Substituiçao = 'Saiu' AND Campeonatos.Nome_Campeonato = @Campeonato GROUP BY Nome_Jogador)
GO



/***************************************************************************************************************/
--CURSORES--


--Classificação de Todos os Campeonatos E Mostra O Campeonato Com Mais Cartões, Golos e Substiuições, Bem Como O Respetivo Jogador Mais Faltoso, Clube Mais Marcador e Também Jogador Mais Substituído--


IF CURSOR_STATUS('global','Cursor_Classificacoes')>=-1
BEGIN
 DEALLOCATE Cursor_Classificacoes
END
go

DECLARE Cursor_Classificacoes CURSOR 
FOR
SELECT Nome_Campeonato, Nome_Clube, Vitorias, Empates, Derrotas, Golos_Favor, Golos_Contra, Cartoes_Amarelos, Cartoes_Vermelhos
FROM Campeonatos
INNER JOIN Clubes_Campeonatos
ON Clubes_Campeonatos.Id_Campeonato = Campeonatos.Id_Campeonato
INNER JOIN Clubes
ON Clubes.Id_Clube = Clubes_Campeonatos.Id_Clube

GROUP BY Nome_Campeonato, Nome_Clube, Vitorias, Empates, Derrotas, Golos_Favor, Golos_Contra, Cartoes_Amarelos, Cartoes_Vermelhos

--Variaveis a Inserir no Cursor
DECLARE @Campeonato VARCHAR(20), @Clube VARCHAR(30), @Vitorias INT, @Empates INT, @Derrotas INT, @Pontos INT, @GolosMarcados INT, @GolosSofridos INT, @CartoesA INT, @CartoesV INT

--Estatisticas a Calcular
DECLARE @CampeonatoMaisCartoes VARCHAR(20), @CampeonatoMaisGolos VARCHAR(20), @CampeonatoMaisSubstituicoes VARCHAR(20), @JogadorMaisCartoes VARCHAR(20), @ClubeMaisGolos VARCHAR(20), @JogarMaisSubstiuido VARCHAR(20)

--Variaveis Auxiliares
DECLARE @CampeonatoNovo VARCHAR(20), @DfGolos INT, @TotalCartoes INT, @MaxCartoes INT, @TotalGolos INT, @MaxGolos INT, @TotalSubstituicoes INT, @MaxSubstituicoes INT
DECLARE @Aux INT

OPEN Cursor_Classificacoes
FETCH NEXT FROM Cursor_Classificacoes INTO @Campeonato, @Clube, @Vitorias, @Empates, @Derrotas, @GolosMarcados, @GolosSofridos, @CartoesA, @CartoesV

SET @MaxCartoes = 0
SET @MaxGolos = 0
SET @MaxSubstituicoes = 0

WHILE(@@FETCH_STATUS = 0)
BEGIN
	IF(@Campeonato = @CampeonatoNovo)
		BEGIN
			SET @Pontos = (@Vitorias * 3) + @Empates 
			SET @DfGolos = @GolosMarcados - @GolosSofridos
			SET @TotalCartoes = @CartoesA + @CartoesV
			SET @TotalGolos = (SELECT SUM(Golos_Favor) FROM Clubes_Campeonatos JOIN Clubes ON Clubes.Id_Clube = Clubes_Campeonatos.Id_Clube JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Campeonatos.Nome_Campeonato = @Campeonato)
			SET @TotalSubstituicoes = (SELECT COUNT(Substituiçao) FROM Substituicoes JOIN Jogadores ON Jogadores.Id_Jogador = Substituicoes.Jogador JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Jogadores.Clube_Jogador JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Campeonatos.Nome_Campeonato = @Campeonato)

			PRINT '|' + CONVERT(VARCHAR,@Clube) + '	|	' + CONVERT(VARCHAR,@Vitorias) + '	|	' + CONVERT(VARCHAR,@Empates) + '	|	' + CONVERT(VARCHAR,@Derrotas) + '	|	' + CONVERT(VARCHAR,@Pontos) + '	|	' + CONVERT(VARCHAR,@DfGolos) + '	|'
			
			IF(@TotalCartoes > @MaxCartoes)
				BEGIN
					SET @MaxCartoes = @TotalCartoes
					SET @CampeonatoMaisCartoes = @Campeonato
				END

			IF(@TotalGolos > @MaxGolos)
				BEGIN
					SET @MaxGolos = @TotalGolos
					SET @CampeonatoMaisGolos = @Campeonato
				END

			IF(@TotalSubstituicoes > @MaxSubstituicoes)
			BEGIN
				SET @MaxSubstituicoes = @TotalSubstituicoes
				SET @CampeonatoMaisSubstituicoes = @Campeonato
			END

			FETCH NEXT FROM Cursor_Classificacoes INTO @Campeonato, @Clube, @Vitorias, @Empates, @Derrotas, @GolosMarcados, @GolosSofridos, @CartoesA, @CartoesV
		END
	ELSE
		BEGIN
			PRINT 'Campeonato: ' + CONVERT(VARCHAR,@Campeonato)
			PRINT '|	Clube	|	V	|	E	|	D	|	PT	|	DG	|'

			SET @Pontos = (@Vitorias * 3) + @Empates 
			SET @DfGolos = @GolosMarcados - @GolosSofridos			
			SET @TotalCartoes = @CartoesA + @CartoesV
			SET @TotalGolos = (SELECT SUM(Golos_Favor) FROM Clubes_Campeonatos JOIN Clubes ON Clubes.Id_Clube = Clubes_Campeonatos.Id_Clube JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Campeonatos.Nome_Campeonato = @Campeonato)			
			SET @TotalSubstituicoes = (SELECT COUNT(Substituiçao) FROM Substituicoes JOIN Jogadores ON Jogadores.Id_Jogador = Substituicoes.Jogador JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Jogadores.Clube_Jogador JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Campeonatos.Nome_Campeonato = @Campeonato)

			PRINT '|' + CONVERT(VARCHAR,@Clube) + '	|	' + CONVERT(VARCHAR,@Vitorias) + '	|	' + CONVERT(VARCHAR,@Empates) + '	|	' + CONVERT(VARCHAR,@Derrotas) + '	|	' + CONVERT(VARCHAR,@Pontos) + '	|	' + CONVERT(VARCHAR,@DfGolos) + '	|'

			IF(@TotalCartoes > @MaxCartoes)
				BEGIN
					SET @MaxCartoes = @TotalCartoes
					SET @CampeonatoMaisCartoes = @Campeonato
				END
			
			IF(@TotalGolos > @MaxGolos)
				BEGIN
					SET @MaxGolos = @TotalGolos
					SET @CampeonatoMaisGolos = @Campeonato
				END
			
			IF(@TotalSubstituicoes > @MaxSubstituicoes)
			BEGIN
				SET @MaxSubstituicoes = @TotalSubstituicoes
				SET @CampeonatoMaisSubstituicoes = @Campeonato
			END

			FETCH NEXT FROM Cursor_Classificacoes INTO @CampeonatoNovo, @Clube, @Vitorias, @Empates, @Derrotas, @GolosMarcados, @GolosSofridos, @CartoesA, @CartoesV
		END
END
		--Uma vez que alguns dados são NULL, como por exemplo os golos, tive que criar dados "fictícios" para os prints aparecerem, mas depois qd tivermos golos, podemos apagar
		--SET @CampeonatoMaisGolos = 'Liga NOS'
 
		SET @JogadorMaisCartoes = (SELECT TOP 1 Nome_Jogador FROM Jogadores WHERE Cartoes_acumulados = (SELECT MAX(Cartoes_Acumulados) FROM Jogadores JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Jogadores.Clube_Jogador JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Campeonatos.Nome_Campeonato = @CampeonatoMaisCartoes))
		SET @ClubeMaisGolos = (SELECT TOP 1 Nome_Clube FROM Clubes JOIN Clubes_Campeonatos ON Clubes_Campeonatos.Id_Clube = Clubes.Id_Clube JOIN Campeonatos ON Campeonatos.Id_Campeonato = Clubes_Campeonatos.Id_Campeonato WHERE Campeonatos.Nome_Campeonato = @CampeonatoMaisGolos AND Golos_Favor =(SELECT MAX(Golos_Favor) FROM Clubes_Campeonatos))
		SET @JogarMaisSubstiuido = (SELECT * FROM fn_JogadorMaisSubstituido(@CampeonatoMaisSubstituicoes))
		
		PRINT 'Campeonato Com Mais Cartões: ' + @CampeonatoMaisCartoes
		PRINT 'Número de Cartões: ' + CONVERT(VARCHAR,@MaxCartoes)
		PRINT 'Jogador Mais Faltoso: ' + @JogadorMaisCartoes + CHAR(13)

		PRINT 'Campeonato Com Mais Golos: ' + @CampeonatoMaisGolos
		PRINT 'Número de Golos: ' + CONVERT(VARCHAR,@MaxGolos)
		PRINT 'Clube Com Mais Golos: ' + @ClubeMaisGolos + CHAR(13)

		PRINT 'Campeonato Com Mais Substituições: ' + @CampeonatoMaisSubstituicoes
		PRINT 'Número de Substituições: ' + CONVERT(VARCHAR,@MaxSubstituicoes)
		PRINT 'Jogador Mais Vezes Substituido: ' + @JogarMaisSubstiuido + CHAR(13)

CLOSE Cursor_Classificacoes
DEALLOCATE Cursor_Classificacoes
go



/***************************************************************************************************************/
--TRIGGERS--


--Atualiza Os Cartões Acumulados De Um Jogador-- ESTA VERIFICADO

IF OBJECT_ID ('CartoesAcumulados', 'TR') IS NOT NULL  
   DROP TRIGGER CartoesAcumulados;
go

CREATE TRIGGER CartoesAcumulados
ON Jogadores_jogos
AFTER INSERT
AS
DECLARE @Id_jogo int, @Cartoes int, @Id_Jogador int, @Cartao_Vermelho int, @Cartao_Amarelo int, @Minutos_Jogo int, @Estado_Jogador int
	
SELECT @Id_jogo=Id_jogo, @Id_Jogador=Id_Jogador, @Cartao_Amarelo=Cartao_Amarelo, @Cartao_Vermelho=Cartao_Vermelho, @Minutos_Jogo=Minutos_Jogo, @Estado_Jogador=Estado_Jogador

FROM Inserted
		
IF(@Cartao_Amarelo > 0)	
BEGIN
	UPDATE Jogadores
	SET Cartoes_acumulados = Cartoes_acumulados + @Cartao_Amarelo
	WHERE Id_Jogador=@Id_Jogador
END
IF(@Cartao_Vermelho > 0)	
BEGIN
	UPDATE Jogadores
	SET Cartoes_acumulados = Cartoes_acumulados + @Cartao_Vermelho, Estado_Jogador = 2
	WHERE Id_Jogador=@Id_Jogador
END
go


--Atualiza O Estado Do Jogador Após Um Jogo, De Acordo Com Os Cartões-- ESTA VERIFICADO

IF OBJECT_ID ('EstadoJogador', 'TR') IS NOT NULL  
   DROP TRIGGER EstadoJogador;
go

CREATE TRIGGER EstadoJogador
ON Jogadores
FOR UPDATE
AS
DECLARE @Id_Jogador int, @Cartoes_acumulados int, @Estado int

SELECT @Id_Jogador=Id_Jogador, @Cartoes_acumulados=Cartoes_acumulados, @Estado = Estado_Jogador

FROM Inserted

IF(@Estado = 2)--O estado=2, vem do trigger anterior, que é quando o jogador leva um cartão vermelho num jogo
BEGIN
	PRINT 'Jogador Suspenso!'
	PRINT 'O jogador ' + CAST(@Id_Jogador as varchar) +' fica de fora no próximo jogo!'
END
ELSE
BEGIN
	IF(@Cartoes_acumulados % 5=0)
		BEGIN
			UPDATE Jogadores
			SET Estado_Jogador=2
			WHERE Id_Jogador=@Id_Jogador
			PRINT 'O jogador ' + CAST(@Id_Jogador as varchar) +' fica de fora no próximo jogo!'
		END
	ELSE 
		BEGIN
			UPDATE Jogadores
			SET Estado_Jogador=1
			WHERE Id_Jogador = @Id_Jogador
			PRINT 'O jogador ' + CAST(@Id_Jogador as varchar) + ' está apto para o próximo jogo!'
		END
END
go


--Atualiza Classificacao--

IF OBJECT_ID ('Classificacao', 'TR') IS NOT NULL  
   DROP TRIGGER Classificacao;
go

CREATE TRIGGER Classificacao
on Jogos
AFTER INSERT
AS
DECLARE	@Golos_Visitante int, @Golos_Visitado int, @Clube_Visitado int,	@Clube_Visitante int

SELECT @Golos_Visitante=Golos_Visitante, @Golos_Visitado=Golos_Visitado, @Clube_Visitado=Clube_Visitado, @Clube_Visitante=Clube_Visitante
		
FROM Inserted

IF(@Golos_Visitado>@Golos_Visitante)
BEGIN
	UPDATE Clubes_Campeonatos
	SET Pontos=Pontos+3, Vitorias=Vitorias+1,Golos_Contra=Golos_Contra+@Golos_Visitante,Golos_Favor=Golos_Favor+@Golos_Visitado
	WHERE Id_Clube=@Clube_Visitado
	UPDATE Clubes_Campeonatos
	SET Derrotas=Derrotas+1, Golos_Contra=Golos_Contra+@Golos_Visitado,  Golos_Favor=Golos_Favor+@Golos_Visitante
	WHERE Id_Clube=@Clube_Visitante
END

IF (@Golos_Visitante=@Golos_Visitado)
BEGIN 
	UPDATE Clubes_Campeonatos
	SET Pontos=Pontos+1, Empates=Empates+1,Golos_Contra=Golos_Contra+@Golos_Visitado,Golos_Favor=Golos_Favor+@Golos_Visitado
	WHERE Id_Clube=@Clube_Visitante
	UPDATE Clubes_Campeonatos
	SET Pontos=Pontos+1, Empates=Empates+1,Golos_Contra=Golos_Contra+@Golos_Visitado,Golos_Favor=Golos_Favor+@Golos_Visitado
	WHERE Id_Clube=@Clube_Visitado
END

IF(@Golos_Visitante>@Golos_Visitado)
BEGIN
	UPDATE Clubes_Campeonatos
	SET Pontos=Pontos+3, Vitorias=Vitorias+1,Golos_Contra=Golos_Contra+@Golos_Visitado,Golos_Favor=Golos_Favor+@Golos_Visitante
	WHERE Id_Clube=@Clube_Visitante
	UPDATE Clubes_Campeonatos
	SET Derrotas=Derrotas+1, Golos_Contra=Golos_Contra+@Golos_Visitante,  Golos_Favor=Golos_Favor+@Golos_Visitado
	WHERE Id_Clube=@Clube_Visitado
END
go


--Atualiza Resultado Na Tabela Dos Jogos--

IF OBJECT_ID ('Resultado', 'TR') IS NOT NULL  
   DROP TRIGGER Resultado;
go

CREATE TRIGGER Resultado
on Jogos
for INSERT
AS
DECLARE @Golos_Visitante int, @Golos_Visitado int, @Id_Jogo int
SELECT @Golos_Visitante=Golos_Visitante, @Golos_Visitado=Golos_Visitado, @Id_Jogo=Id_Jogo

FROM Inserted
	
IF(@Golos_Visitante is NOT NULL AND @Golos_Visitado is  NOT NULL)
	UPDATE Jogos 
	SET Resultado=(''+CAST(@Golos_Visitado as varchar)+':'+CAST(@Golos_Visitante as varchar)+'')
	WHERE @Id_Jogo=Id_Jogo 
go


