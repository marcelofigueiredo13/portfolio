------------------------------------------------------------------------------------
--Funcao que devolve uma string com as equipas de um campeonato
------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.equipas_campeonato(
	nome_campeonato character varying,
	idepoca integer)
    RETURNS character varying
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
BEGIN
	return (select array(select equipa.eq_nome from equipa
	join composto on composto.eq_idequipa = equipa.eq_idequipa
	join campeonato on campeonato.c_idcampeonato = composto.c_idcampeonato
	join realizado on realizado.c_idcampeonato = campeonato.c_idcampeonato
	join epoca on epoca.e_idepoca = realizado.e_idepoca
	where c_nome LIKE nome_campeonato AND epoca.e_idepoca = idepoca));
END;
$BODY$;


------------------------------------------------------------------------------------
--Cursor que devolve a classificaçao de um campeonato de futebol
------------------------------------------------------------------------------------
--select * from classificacao('Portugal', 2020,2021) order by pontos desc

CREATE OR REPLACE FUNCTION classificacao(nome_campeonato varchar, ano_inicio integer, ano_fim integer) RETURNS table(equipa varchar, pontos integer, vitorias integer, empates integer, derrotas integer) 
AS
$BODY$
DECLARE
cur CURSOR FOR SELECT eq_nome, jogo.jo_idjogo, jje_tipo,

	(CASE WHEN (SELECT SUM(p_resultadoequipacasa)
		 from parte 
		 inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		 where pontuacao.jo_idjogo=jogo.jo_idjogo)  IN (SELECT SUM(p_resultadoequipacasa)
		 from parte 
		 inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		 where pontuacao.jo_idjogo=jogo.jo_idjogo) THEN (SELECT SUM(p_resultadoequipacasa)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo) ELSE 0 END),
		  		  
	 (CASE WHEN (SELECT SUM(p_resultadoequipafora)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo) IN (SELECT SUM(p_resultadoequipafora)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo) THEN (SELECT SUM(p_resultadoequipafora)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo)ELSE 0 END)
		  
		  
		from jogo
		join jogador_jogo_equipa on jogador_jogo_equipa.jo_idjogo=jogo.jo_idjogo
		join equipa on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
		full join pontuacao on pontuacao.jo_idjogo = jogo.jo_idjogo
		full join parte on parte.p_idparte = pontuacao.p_idparte

		join composto on composto.eq_idequipa = equipa.eq_idequipa
		join campeonato on campeonato.c_idcampeonato = composto.c_idcampeonato
		join realizado on realizado.c_idcampeonato = campeonato.c_idcampeonato
		join epoca on epoca.e_idepoca = realizado.e_idepoca

		where DATE_PART('year',jogo.jo_data) >= ano_inicio AND DATE_PART('year',jogo.jo_data) <= ano_fim and c_nome LIKE nome_campeonato

		group by eq_nome, jogo.jo_idjogo,jje_tipo order by jogo.jo_idjogo;
		
equipa varchar;
jogo integer;
tipo_jogo varchar;
casa integer;
fora integer;

nequipas integer;
equipas varchar[];

nvitorias integer;
nempates integer;
nderrotas integer;
npontos integer;

i integer;

aux integer;
idepoca integer;

BEGIN

i = 1;

--Epoca referente ao ano
idepoca = (SELECT epoca.e_idepoca FROM epoca 
join realizado on realizado.e_idepoca = epoca.e_idepoca
join campeonato on campeonato.c_idcampeonato = realizado.c_idcampeonato
where c_nome LIKE nome_campeonato AND DATE_PART('year',e_inicio) = ano_inicio AND DATE_PART('year',e_fim) = ano_fim);

--RAISE NOTICE 'EPOCA = %',idepoca;

--Descobrir o numero de equipas do campeonato
nequipas = (select count(*) from equipa
join composto on composto.eq_idequipa = equipa.eq_idequipa
join campeonato on campeonato.c_idcampeonato = composto.c_idcampeonato
join realizado on realizado.c_idcampeonato = campeonato.c_idcampeonato
join epoca on epoca.e_idepoca = realizado.e_idepoca
where c_nome LIKE nome_campeonato AND epoca.e_idepoca = idepoca);

--RAISE NOTICE 'N EQUIPAS = %', nequipas;

--Equipas do campeonato
equipas = equipas_campeonato(nome_campeonato, idepoca);
/*LOOP
	if(i > nequipas) then exit; end if;
	raise notice '% - %', i, equipas[i]; 
	i = i + 1;
END LOOP;*/

LOOP
	IF(i > nequipas) THEN EXIT; END IF;
	nvitorias = 0;
	nempates = 0;
	nderrotas = 0;
	npontos = 0;
	OPEN cur;
	LOOP
		FETCH NEXT FROM cur INTO equipa, jogo, tipo_jogo, casa, fora;
		IF NOT FOUND THEN EXIT; END IF;
		
		IF(equipa = equipas[i]) THEN
		--RAISE NOTICE '% - % - % - % - % - %', aux, equipa, jogo, tipo_jogo, casa, fora;
			IF(tipo_jogo LIKE 'casa') THEN
				IF(casa > fora) THEN
					npontos = npontos + 3;
					nvitorias = nvitorias + 1;
				ELSIF (casa = fora) THEN
					npontos = npontos + 1;
					nempates = nempates + 1;
				ELSE nderrotas = nderrotas + 1;
				END IF;
			ELSIF(tipo_jogo LIKE 'fora') THEN
				IF(casa < fora) THEN
					npontos = npontos + 3;
					nvitorias = nvitorias + 1;
				ELSIF (casa = fora) THEN
					npontos = npontos + 1;
					nempates = nempates + 1;
				ELSE nderrotas = nderrotas + 1;
				END IF;
			END IF;	
		END IF;
	
	END LOOP;
	CLOSE cur;
	--raise notice '% | % | % | % | %', equipas[i], nvitorias, nempates, nderrotas, npontos;
	IF(i = 1) THEN
		CREATE TABLE aux (equipa varchar, pontos integer, vitorias integer, empates integer, derrotas integer);
	END IF;
	
	INSERT INTO aux (equipa, pontos, vitorias, empates, derrotas) VALUES (equipas[i], npontos, nvitorias, nempates, nderrotas);
	equipa := equipas[i];
	vitorias := nvitorias;
	empates := nempates;
	derrotas := nderrotas;
	pontos := npontos;
	i = i + 1;
END LOOP;
	return query SELECT * FROM aux;
	drop table aux;
END;
$BODY$
LANGUAGE plpgsql;



------------------------------------------------------------------------------------------------------
--Cursor que devolve a percentagem de vitoria dos treinadores de um campeonato numa determinada epoca
------------------------------------------------------------------------------------------------------
--select * from treinador('Belgium',2018,2019)

CREATE OR REPLACE FUNCTION public.treinador(
	nome_campeonato character varying,
	ano_inicio integer,
	ano_fim integer)
    RETURNS TABLE(n_treinador varchar, winrate character varying) 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
    
AS $BODY$
DECLARE
cur CURSOR FOR SELECT eq_nome, jje_tipo,
		(CASE WHEN (SELECT SUM(p_resultadoequipacasa)
		from parte 
		inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		where pontuacao.jo_idjogo=jogo.jo_idjogo)  IN (SELECT SUM(p_resultadoequipacasa)
		from parte 
		inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		where pontuacao.jo_idjogo=jogo.jo_idjogo) THEN (SELECT SUM(p_resultadoequipacasa)
		from parte 
		inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		where pontuacao.jo_idjogo=jogo.jo_idjogo) ELSE 0 END),	  
	    (CASE WHEN (SELECT SUM(p_resultadoequipafora)
		from parte 
		inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		where pontuacao.jo_idjogo=jogo.jo_idjogo) IN (SELECT SUM(p_resultadoequipafora)
		from parte 
		inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		where pontuacao.jo_idjogo=jogo.jo_idjogo) THEN (SELECT SUM(p_resultadoequipafora)
		from parte 
		inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		where pontuacao.jo_idjogo=jogo.jo_idjogo)ELSE 0 END)
		from jogo
		join jogador_jogo_equipa on jogador_jogo_equipa.jo_idjogo=jogo.jo_idjogo
		join equipa on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
		full join pontuacao on pontuacao.jo_idjogo = jogo.jo_idjogo
		full join parte on parte.p_idparte = pontuacao.p_idparte
		join composto on composto.eq_idequipa = equipa.eq_idequipa
		join campeonato on campeonato.c_idcampeonato = composto.c_idcampeonato
		join realizado on realizado.c_idcampeonato = campeonato.c_idcampeonato
		join epoca on epoca.e_idepoca = realizado.e_idepoca
		where DATE_PART('year',jogo.jo_data) >= ano_inicio AND DATE_PART('year',jogo.jo_data) <= ano_fim and c_nome LIKE nome_campeonato
		group by eq_nome, jje_tipo, jogo.jo_idjogo order by jogo.jo_idjogo;
		
nome_equipa varchar;
tipo_jogo varchar;
casa integer;
fora integer;

nequipas integer;
equipas varchar[];

nvitorias integer;

i integer;

idepoca integer;

njogos integer;

nome_treinador varchar;
treinador_max_wr varchar;
treinador_min_wr varchar;

wr float;
max_wr float;
min_wr float;
total_wr float;

BEGIN

i = 1;

max_wr = 0;
min_wr = 9999;
total_wr = 0;

--Epoca referente ao ano
idepoca = (SELECT epoca.e_idepoca FROM epoca 
join realizado on realizado.e_idepoca = epoca.e_idepoca
join campeonato on campeonato.c_idcampeonato = realizado.c_idcampeonato
where c_nome LIKE nome_campeonato AND DATE_PART('year',e_inicio) = ano_inicio AND DATE_PART('year',e_fim) = ano_fim);

--RAISE NOTICE 'EPOCA = %',idepoca;

--Descobrir o numero de equipas do campeonato
nequipas = (select count(*) from equipa
join composto on composto.eq_idequipa = equipa.eq_idequipa
join campeonato on campeonato.c_idcampeonato = composto.c_idcampeonato
join realizado on realizado.c_idcampeonato = campeonato.c_idcampeonato
join epoca on epoca.e_idepoca = realizado.e_idepoca
where c_nome LIKE nome_campeonato AND epoca.e_idepoca = idepoca);

--RAISE NOTICE 'N EQUIPAS = %', nequipas;

--Equipas do campeonato
equipas = equipas_campeonato(nome_campeonato, idepoca);

LOOP
	IF(i > nequipas) THEN EXIT; END IF;
	nvitorias = 0;

	OPEN cur;
	LOOP
		FETCH NEXT FROM cur INTO nome_equipa, tipo_jogo, casa, fora;
		IF NOT FOUND THEN EXIT; END IF;
		
		IF(nome_equipa = equipas[i]) THEN
		--RAISE NOTICE '%', nome_equipa;
			IF(tipo_jogo LIKE 'casa') THEN
				IF(casa > fora) THEN
					nvitorias = nvitorias + 1;
				END IF;
			ELSIF(tipo_jogo LIKE 'fora') THEN
				IF(casa < fora) THEN
					nvitorias = nvitorias + 1;
				END IF;
			END IF;	
		END IF;
	
	END LOOP;
	CLOSE cur;
	--raise notice '% - %', treinador, nvitorias;
	IF(i = 1) THEN
		CREATE TABLE aux (n_treinador varchar, winrate varchar);
	END IF;
	
	nome_treinador = (SELECT t_nome from treinador where eq_idequipa = (select devolve_idequipa(equipas[i])));
	--raise notice '%', nome_treinador;
	njogos = (select count(distinct jogador_jogo_equipa.jo_idjogo)
			  from jogador_jogo_equipa
			  join jogo on jogo.jo_idjogo = jogador_jogo_equipa.jo_idjogo
			  join equipa on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
			  join composto on composto.eq_idequipa = equipa.eq_idequipa
			  join campeonato on campeonato.c_idcampeonato = composto.c_idcampeonato
			  join realizado on realizado.c_idcampeonato = campeonato.c_idcampeonato
			  join epoca on epoca.e_idepoca = realizado.e_idepoca
			  join treinador on treinador.eq_idequipa = equipa.eq_idequipa
			  where equipa.eq_idequipa = (select devolve_idequipa(equipas[i])) and DATE_PART('year',jogo.jo_data) >= ano_inicio AND DATE_PART('year',jogo.jo_data) <= ano_fim and t_nome like nome_treinador);
			  
	--raise notice 'njogos = %', njogos;
	--raise notice 'nvitorias = %', nvitorias;	
	
	IF(njogos > 0) THEN
	wr = (select CAST(nvitorias as decimal)/njogos * 100);
	--raise notice '%', to_char(wr,'FM999999999.00');
		IF(wr > max_wr) THEN
			max_wr = wr;
			treinador_max_wr = nome_treinador;
		END IF;
		
		IF(wr < min_wr) THEN
			min_wr = wr;
			treinador_min_wr = nome_treinador;
		END IF;
		
		total_wr = total_wr + wr;
	END IF;
	
	IF(nome_treinador = '') IS NOT NULL THEN
		INSERT INTO aux(n_treinador, winrate) VALUES (nome_treinador, to_char(wr,'FM999999999.00'));	
	END IF;
	
	i = i + 1;
END LOOP;
	return query SELECT * FROM aux;
	drop table aux;
	raise notice 'Media percentagem vitoria por treinador: %', to_char((total_wr/i),'FM999999999.00');
	raise notice 'Treinador com maior percentagem de vitoria: % com %', treinador_max_wr, max_wr;
	raise notice 'Treinador com maior percentagem de vitoria: % com %', treinador_min_wr, min_wr;
END;
$BODY$;