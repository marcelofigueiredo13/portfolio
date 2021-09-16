------------------------------------------------------------------------------------
--Adicionar coluna cidade à tabela dos espaços desportivos
------------------------------------------------------------------------------------
ALTER TABLE espacodesportivo
ADD COLUMN cidade varchar;


------------------------------------------------------------------------------------
--Sequência que devolve um número inteiro
------------------------------------------------------------------------------------
CREATE SEQUENCE numero_cidade INCREMENT BY 1 START WITH 1 MINVALUE 1;


------------------------------------------------------------------------------------
--Fazer inserts na tabela dos espaços desportivos utilizando a sequência
------------------------------------------------------------------------------------
UPDATE espacodesportivo 
SET cidade = CONCAT('cidade',CAST(nextval('"numero_cidade"') AS VARCHAR))