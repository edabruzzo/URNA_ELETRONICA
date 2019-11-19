
DROP TABLE IF EXISTS tb_eleicao_candidato;
DROP TABLE IF EXISTS tb_voto;
DROP TABLE IF EXISTS tb_candidato;
DROP TABLE IF EXISTS tb_eleitor;
DROP TABLE IF EXISTS tb_partido;
DROP TABLE IF EXISTS tb_eleicao;


/*

http://www.postgresqltutorial.com/postgresql-serial/

SERIAL NO POSTGRESL É O EQUIVALENTE AO AUTO-INCREMENT DO MYSQL

*/


CREATE TABLE IF NOT EXISTS tb_partido(
	id_partido SERIAL PRIMARY KEY,
	nomeCompleto VARCHAR(255) NOT NULL,
        numero_partido INT,
	sigla VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS tb_eleitor(

	id_eleitor SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	idade INT NOT NULL,
	titulo_eleitor int not null,
	RG INT NOT NULL


);


   CREATE TABLE IF NOT EXISTS tb_eleicao(
	id_eleicao SERIAL PRIMARY KEY , 
        cargo varchar(255),
        unidade_federacao varchar(10)
);

INSERT INTO tb_eleicao(cargo, unidade_federacao)
VALUES('PRESIDENTE', 'NACIONAL');

INSERT INTO tb_eleicao(cargo, unidade_federacao)
VALUES('GOVERNADOR', 'SP');

INSERT INTO tb_eleicao(cargo, unidade_federacao)
VALUES('DEPUTADO FEDERAL', 'São Paulo');


CREATE TABLE IF NOT EXISTS tb_candidato(

	id_candidato SERIAL PRIMARY KEY , 
	id_partido int NOT NULL REFERENCES tb_partido(id_partido),
	nome_candidato VARCHAR(255) NOT NULL,
        numero_candidato INT

);


   CREATE TABLE IF NOT EXISTS tb_eleicao_candidato(
	
        id_eleicao int REFERENCES tb_eleicao(id_eleicao) , 
        id_candidato int REFERENCES tb_candidato(id_candidato)
);



   CREATE TABLE IF NOT EXISTS tb_voto(
	id_voto SERIAL PRIMARY KEY ,
	id_candidato int NOT NULL REFERENCES tb_candidato(id_candidato),
        id_eleitor int NOT NULL REFERENCES tb_eleitor(id_eleitor),
        id_eleicao int NOT NULL REFERENCES tb_eleicao(id_eleicao)
	
);
    

INSERT INTO tb_eleitor(
	nome,
	idade,
	titulo_eleitor,
	RG) values('Emmanuel', 35, 123456, 123456);


INSERT INTO tb_eleitor(
	nome,
	idade,
	titulo_eleitor,
	RG) values('Welington', 35, 456789, 456789);


INSERT INTO tb_eleitor(
	nome,
	idade,
	titulo_eleitor,
	RG) values('Martin', 25, 456789, 456789);

INSERT INTO tb_eleitor(
	nome,
	idade,
	titulo_eleitor,
	RG) values('Lucas', 20, 456789, 456789);



INSERT INTO tb_partido(

	nomeCompleto,
	numero_partido,
	sigla

) values('PARTIDO DOS ESPORTES', 91, 'PEsp');


INSERT INTO tb_partido(

	nomeCompleto,
        numero_partido,
	sigla

) values('PARTIDO DOS RITMOS MUSICAIS', 92, 'PMus');


INSERT INTO tb_partido(

	nomeCompleto,
        numero_partido,
	sigla

) values('PARTIDO DAS PROFISSOES', 93, 'PProf');

INSERT INTO tb_partido(

	nomeCompleto,
        numero_partido,
	sigla

) values('PARTIDO DAS FESTAS POPULARES', 94, 'PFest');


INSERT INTO tb_partido(

	nomeCompleto,
        numero_partido,
	sigla

) values('PARTIDO DO FOLCLORE', 95, 'PFolc');



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Atletismo',
9101);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Ginástica Artística',
9102);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Boxe',
9103);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Halterofilismo',
9104);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Sertanejo',
9201);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Reggae',
9202);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Música Clássica',
9203);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Ópera',
9204);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Artista',
9301);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Operário',
9302);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Astronauta',
9303);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Cozinheira',
9304);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Boi-Bumbá',
9401);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Peão de Boiadeiro',
9402);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Oktoberfest',
9403);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Semana Farroupilha',
9404);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Lobisomen',
9501);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Cuca',
9502);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Negrinho do Pastoreio',
9503);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Mapinguari',
9504);
--SELECT * FROM tb_candidato;
--select * from tb_voto;