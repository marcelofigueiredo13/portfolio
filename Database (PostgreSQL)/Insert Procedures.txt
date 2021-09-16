-------------------------------------
--Tabela Acao Disciplinar
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_acaodisciplinar(jogador integer, jogo integer, tipo varchar, minutos integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.acaodisciplinar(
	j_idjogador, jo_idjogo, ad_tipo, ad_minutos)
	VALUES (jogador, jogo, tipo, minutos);
END;
$$;


-------------------------------------
--Tabela Campeonato
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_campeonato(nome integer, nequipas integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.campeonato(
	c_nome, c_nequipas)
	VALUES (nome, nequipas);
END;
$$;


-------------------------------------
--Tabela Composto
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_composto(campeonato integer, equipa integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.composto(
	c_idcampeonato, eq_idequipa)
	VALUES (campeonato, equipa);
END;
$$;

-------------------------------------
--Tabela Condicionado
-------------------------------------

CREATE OR REPLACE PROCEDURE public.inserir_condicionado(jogador integer, estado integer)
LANGUAGE 'plpgsql'

AS $BODY$
BEGIN
INSERT INTO public.condicionado(
	j_idjogador, ej_idestado)
	VALUES (jogador, estado);
END;
$BODY$;

-------------------------------------
--Tabela Epoca
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_epoca(inicio date, fim date)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.epoca(
	e_inicio, e_fim)
	VALUES (inicio, fim);
END;
$$;

-------------------------------------
--Tabela Equipa
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_equipa(faixaetaria integer, modalidade integer, nome varchar, genero varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.equipa(
	fe_idfaixaetaria, m_idmodalidade, eq_nome, eq_genero)
	VALUES (faixaetaria, modalidade, nome, genero);
END;
$$;

-------------------------------------
--Tabela Espaço Desportivo
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_espacodesportivo(nome varchar, capacidade integer, data date)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.espacodesportivo(
	ed_nome, ed_capacidade, ed_datainauguracao)
	VALUES (nome, capacidade, datainauguracao);
END;
$$;

-------------------------------------
--Tabela Estado Jogador
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_estadojogador(descricao varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.estadojogador(
	ej_descricao)
	VALUES (descricao);
END;
$$;

-------------------------------------
--Tabela Faixa Etaria
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_faixaetaria(nome varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.faixaetaria(
	fe_nome)
	VALUES (nome);
END;
$$;

-------------------------------------
--Tabela Jogador
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_jogador(pais integer, nome varchar, data date, posicao varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.jogador(
	p_idpais, j_nome, j_datanascimento, j_posicao)
	VALUES (pais, nome, data, posicao);
END;
$$;

-------------------------------------
--Tabela Jogador Jogo Equipa
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_jogador_jogo_equipa(equipa integer, jogo integer, jogador integer, tipo varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.jogador_jogo_equipa(
	eq_idequipa, jo_idjogo, j_idjogador, jje_tipo)
	VALUES (equipa, jogo, jogador, tipo);
END;
$$;

-------------------------------------
--Tabela Jogo
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_jogo(campeonato integer, espaco integer, data date, hora time)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.jogo(
	c_idcampeonato, ed_idespaco, jo_data, jo_hora)
	VALUES (campeonato, espaco, data, hora);
END;
$$;

-------------------------------------
--Tabela Modalidade
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_modalidade(nome varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.modalidade(
	m_nome)
	VALUES (nome);
END;
$$;

-------------------------------------
--Tabela País
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_pais(nome varchar, codigo varchar, indicativo integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.pais(
	p_nome, p_codigo, p_indicativo)
	VALUES(nome, codigo, indicativo);
END;
$$;

-------------------------------------
--Tabela Parte
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_parte(resultadocasa integer, resultadofora integer, parte integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.parte(
	p_resultadoequipacasa, p_resultadoequipafora, p_nparte)
	VALUES(resultadocasa, resultadofora, parte);
END;
$$;

-------------------------------------
--Tabela Pontuação
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_pontuacao(jogador integer, parte integer, jogo integer, quantidade integer, minutos integer, tipo varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.pontuacao(
	j_idjogador, p_idparte, jo_idjogo, po_quantidade, po_minutos, po_tipo)
	VALUES(jogador, parte, jogo, quantidade, minutos, tipo);
END;
$$;

-------------------------------------
--Tabela Realizado
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_realizado(campeonato integer, epoca integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.realizado(
	c_idcampeonato, e_idepoca)
	VALUES(campeonato, epoca);
END;
$$;

-------------------------------------
--Tabela Substituição
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_substituicao(jogador integer, jogo integer, tipo varchar, minutos integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.substituicao(
	j_idjogador, jo_idjogo, s_tipo, s_minutos)
	VALUES(jogador, jogo, tipo, minutos);
END;
$$;

-------------------------------------
--Tabela Tem
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_tem(equipa integer, jogador integer)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.tem(
	eq_idequipa, j_idjogador)
	VALUES(equipa, jogador);
END;
$$;

-------------------------------------
--Tabela Treinador
-------------------------------------

CREATE OR REPLACE PROCEDURE inserir_treinador(pais integer, equipa integer, data date, nome varchar)
LANGUAGE 'plpgsql'
AS
$$
BEGIN
INSERT INTO public.jogador(
	p_idpais, eq_idequipa, j_datanascimento, t_nome)
	VALUES (pais, equipa, data, nome);
END;
$$;