
CREATE TABLE IF NOT EXISTS tb_partido(
	id_partido INT AUTO_INCREMENT NOT NULL,
	nomeCompleto VARCHAR(255) NOT NULL,
	sigla VARCHAR(10),
	PRIMARY KEY(id_partido)
);

CREATE TABLE IF NOT EXISTS tb_eleitor(

	id_eleitor INT AUTO_INCREMENT NOT NULL,
	nome VARCHAR(255) NOT NULL,
	idade INT NOT NULL,
	tituloEleitor int not null,
	RG INT NOT NULL,

	PRIMARY KEY(id_eleitor)


);

   CREATE TABLE IF NOT EXISTS tb_eleicao(
	id_eleicao AUTO_INCREMENT PRIMARY KEY , 
        cargo varchar(255),
        unidadeFederacao varchar(10)
);



CREATE TABLE IF NOT EXISTS tb_candidato(
	id_candidato int AUTO_INCREMENT NOT NULL, 
	id_partido int NOT NULL,
        numero_candidato INT,
	nome VARCHAR(255) NOT NULL,
	estadoFederacao VARCHAR(2) NOT NULL,
	
	PRIMARY KEY (id_candidato),
	FOREIGN KEY (id_partido) REFERENCES tb_partido(id_partido)
	
);


   CREATE TABLE IF NOT EXISTS tb_eleicao_candidato(
	
        FOREIGN KEY(id_eleicao) int REFERENCES tb_eleicao(id_eleicao) , 
        FOREIGN KEY(id_candidato) int REFERENCES tb_candidato(id_candidato)
);



 CREATE TABLE IF NOT EXISTS tb_voto(
	id_voto INT AUTO_INCREMENT,
        PRIMARY KEY (id_voto),
	FOREIGN KEY (id_candidato) int  REFERENCES tb_candidato(id_candidato),
        FOREIGN KEY (id_eleitor) int  REFERENCES tb_eleitor(id_eleitor),
        FOREIGN KEY (id_eleicao) int REFERENCES tb_eleicao(id_eleicao)
	
);
    

