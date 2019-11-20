
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


INSERT INTO tb_eleicao(cargo, unidade_federacao)
VALUES('DEPUTADO ESTADUAL', 'São Paulo');



CREATE TABLE IF NOT EXISTS tb_candidato(

	id_candidato SERIAL PRIMARY KEY , 
	id_partido int NOT NULL REFERENCES tb_partido(id_partido),
        nome_candidato VARCHAR(255) NOT NULL,
        numero_candidato INT,
        id_eleicao int NOT NULL REFERENCES tb_eleicao(id_eleicao)

);


   CREATE TABLE IF NOT EXISTS tb_voto(
	id_voto SERIAL PRIMARY KEY ,
	id_candidato int REFERENCES tb_candidato(id_candidato),
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
	RG) values('Martin', 25, 456789, 11111);

INSERT INTO tb_eleitor(
	nome,
	idade,
	titulo_eleitor,
	RG) values('Lucas', 20, 456789, 22222);


INSERT INTO tb_eleitor(
	nome,
	idade,
	titulo_eleitor,
	RG) values('Gasparzinho', 5, 456789, 33333);




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
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Atletismo',
9101,
3);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Ginástica Artística',
9102, 
3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Boxe',
9103, 3);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Halterofilismo',
9104, 3);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Sertanejo',
9201, 3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Reggae',
9202, 3);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Música Clássica',
9203, 3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Ópera',
9204, 3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Artista',
9301, 3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Operário',
9302, 3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Astronauta',
9303, 3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(select id_partido from tb_partido where numero_partido = 93),
'Cozinheira',
9304,3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Boi-Bumbá',
9401,
3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Peão de Boiadeiro',
9402,
3);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Oktoberfest',
9403,
3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Semana Farroupilha',
9404,
3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Lobisomen',
9501,
3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Cuca',
9502,
3);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Negrinho do Pastoreio',
9503);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Mapinguari',
9504,
3);




INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Basquete',
91001, 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Hipismo',
91002, 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 91),
'Patinação',
91003, 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Frevo',
92001, 
4);

INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Jazz',
92002, 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 92),
'Música Eletrônica',
92003, 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Garçom',
93001, 
4);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Motorista',
93002 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 93),
'Bombeira',
93002, 
4);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Páscoa',
94001, 
4);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Réveillon',
94002, 
4);


INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 94),
'Festa da Uva',
94003, 
4);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Iara',
95001, 
4);




INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Barba-Ruiva',
95002, 
4);



INSERT INTO tb_candidato( 
	id_partido,
	nome_candidato,
        numero_candidato,
        id_eleicao
	)values
(
(select id_partido from tb_partido where numero_partido = 95),
'Bicho-Papão',
95003, 
4);



INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(1, 1, 1);



INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(null, 1, 2);



INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(2, 1, 3);



INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(3, 2, 1);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(21, 1, 4);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(33, 2, 4);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(34, 3, 4);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(30, 4, 4);

INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(30, 5, 4);





INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(4, 2, 2);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(5, 2, 3);



INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(5, 3, 1);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(null, 3, 2);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(6, 3, 3);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(6, 4, 1);

INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(null, 4, 2);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(null, 4, 3);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(15, 5, 1);

INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(16, 5, 2);


INSERT INTO tb_voto (
	id_candidato,
        id_eleitor,
        id_eleicao)values(null, 5, 3);


--SELECT * FROM tb_candidato;
--select * from tb_voto;