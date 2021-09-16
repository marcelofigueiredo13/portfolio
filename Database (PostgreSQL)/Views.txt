-----------------------------------------------------------------------------------------------------
--Vista com a lista dos campeonatos e o seu respetivo valor (valor de todos os jogadores do campeonato)
-----------------------------------------------------------------------------------------------------
--select * from valor_campeonato
--refresh materialized view valor_campeonato

CREATE MATERIALIZED VIEW valor_campeonato
AS
select c_nome as campeonato, CONCAT(to_char(sum(j_valormercado), 'FM999,999,999,999,999,999'), '€') as valortotal 
from campeonato
join composto on composto.c_idcampeonato = campeonato.c_idcampeonato
join equipa on equipa.eq_idequipa = composto.eq_idequipa
join tem on tem.eq_idequipa = equipa.eq_idequipa
join jogador on jogador.j_idjogador = tem.j_idjogador
group by c_nome
order by sum(j_valormercado) desc


----------------------------------------------------------------------------------
--Funcao que devolve uma tabela com o melhor marcador de penalidades de cada equipa
----------------------------------------------------------------------------------
--select * from melhor_marcador_penalidades() order by equipa

CREATE OR REPLACE FUNCTION public.melhor_marcador_penalidades(
	)
    RETURNS table(equipa varchar, jogador varchar)
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE
cur CURSOR FOR SELECT eq_nome
			   From equipa;
		
nome_equipa varchar;
nome_jogador varchar;

i integer;
vista varchar;

BEGIN
	i = 0;
	OPEN cur;
	LOOP
		FETCH NEXT FROM cur INTO nome_equipa;
		IF NOT FOUND THEN EXIT; END IF;
		nome_jogador = (case when (select j_nome
			   FROM equipa
			   join tem on tem.eq_idequipa = equipa.eq_idequipa
			   join jogador on jogador.j_idjogador = tem.j_idjogador
			   join pontuacao on pontuacao.j_idjogador = jogador.j_idjogador
			   where po_tipo LIKE 'penalidade' and eq_nome like nome_equipa 
			   group by j_nome
			   order by count(*) desc limit 1) IN (select j_nome
			   FROM equipa
			   join tem on tem.eq_idequipa = equipa.eq_idequipa
			   join jogador on jogador.j_idjogador = tem.j_idjogador
			   join pontuacao on pontuacao.j_idjogador = jogador.j_idjogador
			   where po_tipo LIKE 'penalidade' and eq_nome like nome_equipa 
			   group by j_nome
			   order by count(*) desc limit 1) THEN (select j_nome
			   FROM equipa
			   join tem on tem.eq_idequipa = equipa.eq_idequipa
			   join jogador on jogador.j_idjogador = tem.j_idjogador
			   join pontuacao on pontuacao.j_idjogador = jogador.j_idjogador
			   where po_tipo LIKE 'penalidade' and eq_nome like nome_equipa 
			   group by j_nome
			   order by count(*) desc limit 1) ELSE 'Esta equipa ainda nao marcou penalidades' END
			);
		--raise notice '% , %', nome_equipa, nome_jogador;
		i = i + 1;
		IF(i = 1) THEN
			CREATE TABLE aux (equipa varchar, jogador varchar);
		END IF;
			INSERT INTO aux (equipa, jogador) VALUES (nome_equipa, nome_jogador);
	END LOOP;
	CLOSE cur;
	
	RETURN QUERY SELECT * FROM aux;
	
	DROP TABLE aux;
END;
$BODY$;


-----------------------------------------------------------
--Criacao da vista com os melhores marcadores de penalidades
-----------------------------------------------------------
create view view1 as select * from melhor_marcador_penalidades() order by equipa;


----------------------------------------------------------------------------------
--Funcao que devolve uma tabela com o melhor marcador de cada equipa
----------------------------------------------------------------------------------
--select * from melhor_marcador() order by equipa

CREATE OR REPLACE FUNCTION public.melhor_marcador(
	)
    RETURNS table(equipa varchar, jogador varchar)
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE
cur CURSOR FOR SELECT eq_nome
			   From equipa;
		
nome_equipa varchar;
nome_jogador varchar;

i integer;
vista varchar;

BEGIN
	i = 0;
	OPEN cur;
	LOOP
		FETCH NEXT FROM cur INTO nome_equipa;
		IF NOT FOUND THEN EXIT; END IF;
		
		nome_jogador = (case when (select j_nome
		FROM equipa
		join tem on tem.eq_idequipa = equipa.eq_idequipa
		join jogador on jogador.j_idjogador = tem.j_idjogador
		join pontuacao on pontuacao.j_idjogador = jogador.j_idjogador
		where eq_nome like nome_equipa
		group by j_nome order by sum(po_quantidade) desc limit 1) IN (select j_nome
		FROM equipa
		join tem on tem.eq_idequipa = equipa.eq_idequipa
		join jogador on jogador.j_idjogador = tem.j_idjogador
		join pontuacao on pontuacao.j_idjogador = jogador.j_idjogador
		where eq_nome like nome_equipa
		group by j_nome order by sum(po_quantidade) desc limit 1) THEN (select j_nome
		FROM equipa
		join tem on tem.eq_idequipa = equipa.eq_idequipa
		join jogador on jogador.j_idjogador = tem.j_idjogador
		join pontuacao on pontuacao.j_idjogador = jogador.j_idjogador
		where eq_nome like nome_equipa
		group by j_nome order by sum(po_quantidade) desc limit 1) ELSE 'Esta equipa ainda nao marcou golos' END
			);
		--raise notice '% , %', nome_equipa, nome_jogador;
		i = i + 1;
		IF(i = 1) THEN
			CREATE TABLE aux (equipa varchar, jogador varchar);
		END IF;
			INSERT INTO aux (equipa, jogador) VALUES (nome_equipa, nome_jogador);
	END LOOP;
	CLOSE cur;
	
	RETURN QUERY SELECT * FROM aux;
	
	DROP TABLE aux;
END;
$BODY$;


--------------------------------------------
--Criacao da vista com os melhores marcadores
--------------------------------------------
create view view2 as select * from melhor_marcador() order by equipa;


--------------------------------------------
--Criacao da vista dos requisitos minimos
--------------------------------------------
--select * from requisitos

create view requisitos as
SELECT eq_nome, jogo.jo_idjogo, jje_tipo,
	(CASE WHEN (SELECT SUM(p_resultadoequipacasa)
		 from parte 
		 inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		 where pontuacao.jo_idjogo=jogo.jo_idjogo)  IN (SELECT SUM(p_resultadoequipacasa)
		 from parte 
		 inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		 where pontuacao.jo_idjogo=jogo.jo_idjogo) THEN (SELECT SUM(p_resultadoequipacasa)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo) ELSE 0 END) as casa ,
	 (CASE WHEN (SELECT SUM(p_resultadoequipafora)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo) IN (SELECT SUM(p_resultadoequipafora)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo) THEN (SELECT SUM(p_resultadoequipafora)
		  from parte 
		  inner join pontuacao on parte.p_idparte=pontuacao.p_idparte 
		  where pontuacao.jo_idjogo=jogo.jo_idjogo)ELSE 0 END) as fora,
	  view2.jogador as melhor_marcador, view1.jogador as melhor_marcador_penaltis
from jogo
join jogador_jogo_equipa on jogador_jogo_equipa.jo_idjogo=jogo.jo_idjogo
join equipa on equipa.eq_idequipa = jogador_jogo_equipa.eq_idequipa
full join pontuacao on pontuacao.jo_idjogo = jogo.jo_idjogo
full join parte on parte.p_idparte = pontuacao.p_idparte
join view1 on view1.equipa = equipa.eq_nome
join view2 on view2.equipa = equipa.eq_nome
group by eq_nome, jogo.jo_idjogo,jje_tipo,view2.jogador,view1.jogador order by jo_data, jo_idjogo













