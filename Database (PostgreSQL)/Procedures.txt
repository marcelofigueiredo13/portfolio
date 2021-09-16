----------------------------------------------------------------------------------------
---------------------------------------1 Procedimento-----------------------------------
----------------------------------------------------------------------------------------


------------------------------------------------------------------------------------
--Funcao que devolve o id de uma equipa dado o seu nome
------------------------------------------------------------------------------------
--select devolve_idequipa('Charleroi')

CREATE OR REPLACE FUNCTION devolve_idequipa(nome_equipa varchar)RETURNS int AS
$BODY$
DECLARE idequipa integer;
BEGIN

SELECT eq_idequipa FROM equipa WHERE eq_nome LIKE nome_equipa INTO idequipa;

IF(idequipa != 0) THEN
	RETURN idequipa;
ELSE
	RAISE EXCEPTION 'EQUIPA NÃO EXISTE';
END IF;

END;
$BODY$
LANGUAGE plpgsql;


------------------------------------------------------------------------------------
--Funcao que devolve o id dos jogadores dado o seu nome
------------------------------------------------------------------------------------
--select devolve_idjogador('Cristiano Ronaldo')

CREATE OR REPLACE FUNCTION devolve_idjogador(nome_jogador varchar)RETURNS int AS
$BODY$
DECLARE idjogador integer;

BEGIN

SELECT j_idjogador FROM jogador WHERE j_nome LIKE nome_jogador INTO idjogador;

IF(idjogador != 0) THEN
	RETURN idjogador;
ELSE
	RAISE NOTICE 'JOGADOR NÃO EXISTE';
END IF;

END;
$BODY$
LANGUAGE plpgsql;


------------------------------------------------------------------------------------
--Funcao que devolve o id da equipa atual de um jogador
------------------------------------------------------------------------------------
--select devolve_equipa_atual(2)

CREATE OR REPLACE FUNCTION devolve_equipa_atual(idjogador integer)RETURNS int AS
$BODY$
DECLARE idequipa integer;
BEGIN
SELECT equipa.eq_idequipa FROM equipa 
join tem on tem.eq_idequipa = equipa.eq_idequipa 
join jogador on jogador.j_idjogador = tem.j_idjogador WHERE jogador.j_idjogador = idjogador INTO idequipa;

IF(idequipa != 0) THEN
	RETURN idequipa;
ELSE
	RAISE EXCEPTION 'O JOGADOR NÃO PERTENCE A UMA EQUIPA';
END IF;

END;
$BODY$
LANGUAGE plpgsql;


------------------------------------------------------------------------------------
--Funcao que devolve os jogos realizados entre duas equipas num determinado espaço temporal
------------------------------------------------------------------------------------
--SELECT devolve_jogos_equipas(1,2,'Futebol','2018-09-12','2020-07-2')

CREATE OR REPLACE FUNCTION public.devolve_jogos_equipas(equipa_a integer, equipa_b integer, modal varchar, data_inicio date, data_fim date)
RETURNS TABLE(idjogo integer) 
LANGUAGE 'plpgsql'    
AS $BODY$
begin
	return query ((select jogador_jogo_equipa.jo_idjogo from jogador_jogo_equipa 
		  		   join jogo
		  		   on jogo.jo_idjogo = jogador_jogo_equipa.jo_idjogo
	              		   join equipa 
				   on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
				   join modalidade
				   on modalidade.m_idmodalidade = equipa.m_idmodalidade
				   where jogador_jogo_equipa.eq_idequipa = equipa_a and data_inicio <= jo_data AND jo_data <= data_fim and m_nome LIKE modal)
		  		   INTERSECT
		  		   (select jogador_jogo_equipa.jo_idjogo from jogador_jogo_equipa 
				   join jogo
		  		   on jogo.jo_idjogo = jogador_jogo_equipa.jo_idjogo
				   join equipa 
				   on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
				   join modalidade
				   on modalidade.m_idmodalidade = equipa.m_idmodalidade
		  		   where jogador_jogo_equipa.eq_idequipa = equipa_b and data_inicio <= jo_data AND jo_data <= data_fim and m_nome LIKE modal));
end;
$BODY$;


------------------------------------------------------------------------------------
--Funcao que devolve o id do jogo mais recente de uma equipa
------------------------------------------------------------------------------------
--select devolve_jogo_mais_recente(1)

CREATE OR REPLACE FUNCTION devolve_jogo_mais_recente(idequipa integer)RETURNS int AS
$BODY$
DECLARE jogo integer;
BEGIN

SELECT jogo.jo_idjogo FROM jogo
join jogador_jogo_equipa on jogador_jogo_equipa.jo_idjogo = jogo.jo_idjogo
join equipa on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
WHERE equipa.eq_idequipa = idequipa ORDER BY jo_data DESC LIMIT 1 INTO jogo;

IF(jogo != 0) THEN
	RETURN jogo;
ELSE
	RAISE EXCEPTION 'ESTA EQUIPA NÃO TEM JOGOS RECENTES';
END IF;

END;
$BODY$
LANGUAGE plpgsql;


------------------------------------------------------------------------------------
--Devolve a posicao de um jogador dado o seu id
------------------------------------------------------------------------------------
--select devolve_posicao(2)

CREATE OR REPLACE FUNCTION devolve_posicao(idjogador integer) RETURNS varchar
LANGUAGE 'plpgsql'   
AS $BODY$
DECLARE pos varchar;
BEGIN

SELECT jogador.j_posicao FROM jogador WHERE jogador.j_idjogador = idjogador INTO pos;

IF(pos IS NOT NULL) THEN
	RETURN pos;
ELSE
	RAISE EXCEPTION 'O JOGADOR NÃO TEM POSIÇÃO';
END IF;

END;
$BODY$;


------------------------------------------------------------------------------------
--Sequencia que devolve um minuto de jogo, para posteriormente efetuar a substituiçao neste minuto
------------------------------------------------------------------------------------
--select currval('"gera_minuto_jogo"')

CREATE SEQUENCE gera_minuto_jogo START WITH 5 INCREMENT BY 5 MAXVALUE 90 CYCLE;


----------------------------------------------------------------------------------------------------------------------------------------
--Procedimento que substitui os dois jogadores menos substituidos num jogo entre a equipa A e B, de uma certa modalidade na epoca anterior 
----------------------------------------------------------------------------------------------------------------------------------------
--call procedimento1('Club Brugge', 'Kortrijk', 'Futebol', '2018-07-27', '2018-10-31')

CREATE OR REPLACE PROCEDURE public.procedimento1(
	eq_a character varying,
	eq_b character varying,
	modalidade character varying,
	data_inicio date,
	data_fim date)
LANGUAGE 'plpgsql'

AS $BODY$
DECLARE epoca_anterior integer;
id_eqa integer;
id_eqb integer;
idjogo integer;
nome_jogador1 varchar;
nome_jogador2 varchar;
id_jogador1 integer;
id_jogador2 integer;
eq_atual_jogador1 integer;
eq_atual_jogador2 integer;
pos_jogador1 varchar;
pos_jogador2 varchar;
jogador_mais_ad1 varchar;
jogador_mais_ad2 varchar;
jogo_mais_recente_jogador1 integer;
jogo_mais_recente_jogador2 integer;
BEGIN

--Id da equipa A
SELECT devolve_idequipa(EQ_A) INTO id_eqa;

--Id da equipa B
SELECT devolve_idequipa(EQ_B) INTO id_eqb;

--Id do jogo entre as equipas A e B
--Limitar as opçao do utilizador, permitindo apenas selecionar um jogo
IF((SELECT COUNT(*) FROM devolve_jogos_equipas(id_eqa, id_eqb, modalidade, data_inicio, data_fim)) = 1)
THEN idjogo = (SELECT devolve_jogos_equipas(id_eqa, id_eqb, modalidade, data_inicio, data_fim));

ELSE IF((SELECT COUNT(*) FROM devolve_jogos_equipas(id_eqa, id_eqb, modalidade, data_inicio, data_fim)) > 1)
THEN RAISE EXCEPTION 'Existe mais que um jogo entre estas datas';

ELSE IF((SELECT COUNT(*) FROM devolve_jogos_equipas(id_eqa, id_eqb, modalidade, data_inicio, data_fim)) < 1)
THEN RAISE EXCEPTION 'Nao existe nenhum jogo entre estas datas';

END IF;
END IF;
END IF;

raise notice 'Ultimo jogo disputado entre as duas equipas : %', idjogo;
--Jogadores da epoca anterior com menos acoes disciplinares no jogo da equipa A com a equipa B
--Foi criada uma tabela auxiliar para extrair o nome dos jogadores
--Uma tabela temporaria desaparece depois de fechar a sessao, ou seja se fizermos esta consulta varias 
--vezes numa sessao, nao compensa criar uma tabela temporaria
CREATE TABLE aux AS (SELECT j_nome, MIN(COUNT(*)) OVER () FROM acaodisciplinar
JOIN jogador on jogador.j_idjogador = acaodisciplinar.j_idjogador
WHERE acaodisciplinar.jo_idjogo = idjogo
GROUP BY j_nome LIMIT 2);


--Extrair o nome dos jogadores menos acoes disciplinares do jogo entre a equipa A e B,
--de uma certa modalidade num certo espaco temporal
nome_jogador1 = (SELECT j_nome from aux order by j_nome asc limit 1);
RAISE NOTICE 'Nome do jogador 1 : %', nome_jogador1;
nome_jogador2 = (SELECT j_nome from aux order by j_nome desc limit 1);
RAISE NOTICE 'Nome do jogador 2 : %', nome_jogador2;


--Obter o id dos jogadores
id_jogador1 = (SELECT devolve_idjogador(nome_jogador1));
id_jogador2 = (SELECT devolve_idjogador(nome_jogador2));


--Obter equipas atuais dos jogadores
eq_atual_jogador1 = (SELECT devolve_equipa_atual(id_jogador1));
eq_atual_jogador2 = (SELECT devolve_equipa_atual(id_jogador2));


--Posicao do jogador1 do jogador2
pos_jogador1 = (SELECT devolve_posicao(id_jogador1));
RAISE NOTICE 'Posicao : %', pos_jogador1;
pos_jogador2 = (SELECT devolve_posicao(id_jogador2));
RAISE NOTICE 'Posicao : %', pos_jogador2;


--Jogador com mais acoes disciplinares na equipa atual do jogador1 e com a mesma posicao
jogador_mais_ad1 = (SELECT j_nome FROM acaodisciplinar
JOIN jogador on jogador.j_idjogador = acaodisciplinar.j_idjogador
JOIN tem on tem.j_idjogador = jogador.j_idjogador
JOIN equipa on equipa.eq_idequipa = tem.eq_idequipa
WHERE equipa.eq_idequipa = eq_atual_jogador1 AND jogador.j_posicao LIKE pos_jogador1 AND jogador.j_idjogador != id_jogador1
GROUP BY j_nome order by count(*) desc limit 1);
RAISE NOTICE 'Jogador mais faltoso da equipa atual : %', jogador_mais_ad1;


--Jogador com mais acoes disciplinares na equipa atual do jogador2 e com a mesma posicao
jogador_mais_ad2 = (SELECT j_nome FROM acaodisciplinar
JOIN jogador on jogador.j_idjogador = acaodisciplinar.j_idjogador
JOIN tem on tem.j_idjogador = jogador.j_idjogador
JOIN equipa on equipa.eq_idequipa = tem.eq_idequipa
WHERE equipa.eq_idequipa = eq_atual_jogador2 AND jogador.j_posicao LIKE pos_jogador2 AND jogador.j_idjogador != id_jogador2
GROUP BY j_nome order by count(*) desc limit 1);
RAISE NOTICE 'Jogador mais faltoso da equipa atual : %', jogador_mais_ad2;


--Jogo mais recente da equipa atual dos jogadores jogador1 e jogador2
jogo_mais_recente_jogador1 = (SELECT devolve_jogo_mais_recente(eq_atual_jogador1));
RAISE NOTICE 'Jogo mais recente : %', jogo_mais_recente_jogador1;
jogo_mais_recente_jogador2 = (SELECT devolve_jogo_mais_recente(eq_atual_jogador2));
RAISE NOTICE 'Jogo mais recente : %', jogo_mais_recente_jogador2;


--Substituir o jogador1 pelo jogador com mais acoes disciplinares da sua equipa atual
INSERT INTO substituicao(j_idjogador, jo_idjogo, s_tipo, s_minutos)
	VALUES (id_jogador1, jogo_mais_recente_jogador1, 'saiu', nextval('"gera_minuto_jogo"'));
INSERT INTO substituicao(j_idjogador, jo_idjogo, s_tipo, s_minutos)
	VALUES ((SELECT devolve_idjogador(jogador_mais_ad1)), jogo_mais_recente_jogador1, 'entrou', currval('"gera_minuto_jogo"'));

--Substituir o jogador2 pelo jogador com mais acoes disciplinares da sua equipa atual
INSERT INTO substituicao(j_idjogador, jo_idjogo, s_tipo, s_minutos)
	VALUES (id_jogador2, jogo_mais_recente_jogador2, 'saiu', nextval('"gera_minuto_jogo"'));
INSERT INTO substituicao(j_idjogador, jo_idjogo, s_tipo, s_minutos)
	VALUES ((SELECT devolve_idjogador(jogador_mais_ad2)), jogo_mais_recente_jogador2, 'entrou', currval('"gera_minuto_jogo"'));

	RAISE NOTICE 'INSERT INTO substituicao (%, %, %, %)', nome_jogador1, jogo_mais_recente_jogador1, 'saiu', nextval('"gera_minuto_jogo"');
	RAISE NOTICE 'INSERT INTO substituicao (%, %, %, %)', jogador_mais_ad1, jogo_mais_recente_jogador1, 'entrou', currval('"gera_minuto_jogo"');
	
	RAISE NOTICE 'INSERT INTO substituicao (%, %, %, %)', nome_jogador2, jogo_mais_recente_jogador2, 'saiu', nextval('"gera_minuto_jogo"');
	RAISE NOTICE 'INSERT INTO substituicao (%, %, %, %)', jogador_mais_ad2, jogo_mais_recente_jogador2, 'entrou', currval('"gera_minuto_jogo"');
--Eliminar a tabela auxiliar
DROP TABLE aux;

END;
$BODY$;


----------------------------------------------------------------------------------------
---------------------------------------2 Procedimento-----------------------------------
----------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------
--Procedimento para repor a base de dados
--------------------------------------------------------------------------------------
--CALL procedimento2()

CREATE OR REPLACE PROCEDURE public.procedimento2()
LANGUAGE 'plpgsql'

AS $BODY$
DECLARE 
id int;
queryUser varchar;
tabela varchar;
tipo varchar;
storedQuery varchar;

utilizador varchar;

invertedQuery varchar;

coluna_id varchar;
coluna_aux varchar;

valor_coluna varchar;
valor_aux varchar;

valores_eliminados varchar;
valores_inseridos varchar;
json_para_array varchar[];
tamanho_array integer;
i integer;

BEGIN
	--Nome do ultimo utilizador a realizar um query na base de dados
	utilizador = (SELECT historico.utilizador FROM historico ORDER BY timestamp_acao DESC LIMIT 1);
	
	--Cursor para imprimir os querys que o ultimo utilizador realizou
	DECLARE cur CURSOR FOR SELECT id_acao, historico.utilizador, table_name, acao, query FROM historico;
	
	BEGIN
	OPEN cur;
	LOOP
		FETCH NEXT FROM cur INTO id, queryUser, tabela, tipo, storedQuery;
		--Se o query tiver sido realizado pelo ultimo utilizador a realizar um query
		IF(queryUser LIKE utilizador) THEN
		
			--Se o query for do tipo insert
			IF(tipo LIKE 'insert') THEN 
				--Transformar os valores inseridos numa string
				valores_inseridos = (SELECT CAST(new_values AS varchar) FROM historico WHERE id_acao = id);
				--Converter a string dos valores eliminados num array
				json_para_array = (SELECT string_to_array(valores_inseridos, ','));
				--Obter o lado esquerdo do elemento do array, ou seja, o nome de uma das colunas
				coluna_id = (SELECT left(json_para_array[1], strpos(json_para_array[1], ':')));
				--Remover os caracteres {, : e ", para ficar so com o nome da coluna
				coluna_id = (SELECT REPLACE(coluna_id, '{', ''));
				coluna_id = (SELECT REPLACE(coluna_id, '"', ''));
				coluna_id = (SELECT REPLACE(coluna_id, ':', ''));
				--Selecionar os valores que estavam nas colunas
				valor_coluna = (SELECT substring(json_para_array[1] from strpos(json_para_array[1], ':')));
				--Remover os caracteres } e :, para ficar so com o nome da coluna
				valor_coluna = (SELECT REPLACE(valor_coluna, ':', ''));
				valor_coluna = (SELECT REPLACE(valor_coluna, '}', ''));
				--Cria o query "invertido" do insert, ou seja, vai remover o registo que foi adicionado
				invertedQuery = (SELECT CONCAT('DELETE FROM ', tabela , ' WHERE ', coluna_id, ' = ', valor_coluna, ';'));
				RAISE NOTICE '%', invertedQuery;			
				EXECUTE invertedQuery;
				
			--Se o query for do tipo uptade
			ELSIF(tipo LIKE 'update') THEN 
				i = 1;
				--Comecar o query "invertido"
				invertedQuery = (SELECT CONCAT('UPDATE ', tabela, ' SET '));
				--Transformar os valores eliminados numa string
				valores_eliminados = (SELECT CAST(old_values AS varchar) FROM historico WHERE id_acao = id);
				--Converter a string dos valores eliminados num array
				json_para_array = (SELECT string_to_array(valores_eliminados, ','));
				--Obter o tamanho do array, ou seja, o numero de colunas da tabela da qual foi eliminada um registo
				tamanho_array = (SELECT array_length(json_para_array, 1));
				--Loop para adicionar as colunas ao query "invertido"
				LOOP
					--Terminha o loop, se o iterador for maior que o tamanho do array
					IF(i > tamanho_array) THEN EXIT; END IF;
					--Obter o lado esquerdo do elemento do array, ou seja, o nome de uma das colunas
					coluna_id = (SELECT left(json_para_array[i], strpos(json_para_array[i], ':')));
					--Remover os caracteres {, : e ", para ficar so com o nome da coluna
					coluna_id = (SELECT REPLACE(coluna_id, '{', ''));
					coluna_id = (SELECT REPLACE(coluna_id, '"', ''));
					coluna_id = (SELECT REPLACE(coluna_id, ':', ''));
					
					--Armazenar o nome da primeira coluna, ou seja, da coluna "id" da tabela que sofreu update
					IF( i = 1) THEN coluna_aux = coluna_id; END IF;
					
					--Adiciona o nome da coluna ao query "invertido"
					IF( i > 1) THEN
						invertedQuery = (SELECT CONCAT(invertedQuery, coluna_id, ' = ')); 
					END IF;
					
					--Selecionar os valores que estavam nas colunas
					valor_coluna = (SELECT substring(json_para_array[i] from strpos(json_para_array[i], ':')));
					--Remover os caracteres } e :, para ficar so com o nome da coluna
					valor_coluna = (SELECT REPLACE(valor_coluna, ':', ''));
					valor_coluna = (SELECT REPLACE(valor_coluna, '}', ''));
					--Substituir "" por '', porque os valores que tiverem "" sao strings
					valor_coluna = (SELECT REPLACE(valor_coluna, '"', ''''));
					--Se for a ultima coluna, nao adiciona virgula
					IF( i = tamanho_array) THEN
						invertedQuery = (SELECT CONCAT(invertedQuery, valor_coluna));
					--Se for a primeira coluna, armazena o valor
					ELSIF (i = 1) THEN
						valor_aux = valor_coluna;
					ELSE
						invertedQuery = (SELECT CONCAT(invertedQuery, valor_coluna, ','));
					END IF;
					i = i + 1;
				END LOOP;
				--Acabar o query invertido com os valores armazenados
				invertedQuery = (SELECT CONCAT(invertedQuery, ' WHERE ', coluna_aux, ' = ', valor_aux));
				RAISE NOTICE '%', invertedQuery;
				EXECUTE invertedQuery;
				
			--Se o query for do tipo delete
			ELSIF(tipo LIKE 'delete') THEN 
				i = 1;
				--Comecar o query "invertido"
				invertedQuery = (SELECT CONCAT('INSERT INTO ', tabela, ' ('));
				--Transformar os valores eliminados numa string
				valores_eliminados = (SELECT CAST(old_values AS varchar) FROM historico WHERE id_acao = id);
				--Converter a string dos valores eliminados num array
				json_para_array = (SELECT string_to_array(valores_eliminados, ','));
				--Obter o tamanho do array, ou seja, o numero de colunas da tabela da qual foi eliminada um registo
				tamanho_array = (SELECT array_length(json_para_array, 1));
				
				--Loop para adicionar as colunas ao query "invertido"
				LOOP
					--Terminha o loop, se o iterador for maior que o tamanho do array
					IF(i > tamanho_array) THEN EXIT; END IF;
					--Obter o lado esquerdo do elemento do array, ou seja, o nome de uma das colunas
					coluna_id = (SELECT left(json_para_array[i], strpos(json_para_array[i], ':')));
					--Remover os caracteres {, : e ", para ficar so com o nome da coluna
					coluna_id = (SELECT REPLACE(coluna_id, '{', ''));
					coluna_id = (SELECT REPLACE(coluna_id, '"', ''));
					coluna_id = (SELECT REPLACE(coluna_id, ':', ''));
				
					--Adicionamos o nome da coluna ao query procedido de uma virgula
					IF(i < tamanho_array) THEN
						invertedQuery = (SELECT CONCAT(invertedQuery, coluna_id, ',')); 
					--Mecanismo para nao adicionar , apos o nome da ultima coluna
					ELSE 
						invertedQuery = (SELECT CONCAT(invertedQuery, coluna_id)); 
					END IF;
					i = i + 1;
				END LOOP;
				
				--Preparar o query para receber os valores eliminados
				invertedQuery = (SELECT CONCAT(invertedQuery,') VALUES ('));
				--Dar reset ao incremento do loop
				i = 1;
				
				--Loop para adicionar o valor das colunas ao query "invertido"
				LOOP
					--Terminha o loop, se o iterador for maior que o tamanho do array
					IF(i > tamanho_array) THEN EXIT; END IF;
					--Selecionar os valores que estavam nas colunas
					valor_coluna = (SELECT substring(json_para_array[i] from strpos(json_para_array[i], ':')));
					--Remover os caracteres } e :, para ficar so com o nome da coluna
					valor_coluna = (SELECT REPLACE(valor_coluna, ':', ''));
					valor_coluna = (SELECT REPLACE(valor_coluna, '}', ''));
					--Substituir "" por '', porque os valores que tiverem "" sao strings
					valor_coluna = (SELECT REPLACE(valor_coluna, '"', ''''));
					--Caso o valor do incremento do loop seja igual ao numero de variaveis(tamanho do array)
					IF(i = tamanho_array) THEN
						--Adicionar o valor da ultima coluna precedida de um )
						invertedQuery = (SELECT CONCAT(invertedQuery, valor_coluna, ');'));
					ELSE
						--Adicionar o valor da ultima coluna precedida de uma ,
						invertedQuery = (SELECT CONCAT(invertedQuery, valor_coluna, ','));
					END IF;
					i = i + 1;
				END LOOP;
				RAISE NOTICE '%', invertedQuery;
				EXECUTE invertedQuery;		
			END IF;			
		END IF;
		IF NOT FOUND THEN EXIT;
		END IF;
	END LOOP;
	END;
END;
$BODY$;


--------------------------------------------------------------------------------------------------------------------------
--Procedimento que imprime o valor medio de um jogador cujo o seu numero esta no top5 dos mais utilizados em todas as ligas
--------------------------------------------------------------------------------------------------------------------------
--call valor_medio_numero()

CREATE OR REPLACE PROCEDURE public.valor_medio_numero()
LANGUAGE 'plpgsql'

AS $BODY$
DECLARE cur CURSOR FOR SELECT numero,total FROM top5numeros();

numero int;
njogadores bigint;

i integer;

media bigint;
valortotal bigint;

BEGIN
	OPEN cur;
	raise notice 'Numero		N Jogadores		ValorTotal		Valor Medio Jogador';
	LOOP
		FETCH NEXT FROM cur INTO numero, njogadores;
		IF NOT FOUND THEN EXIT; END IF;
		valortotal = (select sum(j_valormercado) from jogador where j_numerocamisola = numero);
		media = (valortotal/njogadores);
		raise notice '%		%			%€		%€', numero, njogadores, to_char(valortotal, 'FM999,999,999,999,999,999'), to_char(media, 'FM999,999,999,999,999,999');
	END LOOP;
END;
$BODY$;
