CREATE DATABASE IF NOT EXISTS db_exames;

USE db_exames;

CREATE TABLE IF NOT EXISTS tb_funcionario(
	id_funcionario INT AUTO_INCREMENT,
	nm_funcionario VARCHAR(100) NOT NULL,
	PRIMARY KEY (id_funcionario)
);

CREATE TABLE IF NOT EXISTS tb_exame(
	id_exame INT AUTO_INCREMENT,
	nm_exame VARCHAR(100) NOT NULL,
	PRIMARY KEY (id_exame)
);

CREATE TABLE IF NOT EXISTS tb_exame_funcionario(
	id_exame_funcionario INT AUTO_INCREMENT,
	id_funcionario INT NOT NULL,
	id_exame INT NOT NULL,
	dt_exame_funcionario DATE NOT NULL,
	PRIMARY KEY (id_exame_funcionario),
	CONSTRAINT fk_funcionario FOREIGN KEY (id_funcionario) REFERENCES tb_funcionario (id_funcionario),
	CONSTRAINT fk_exame FOREIGN KEY (id_exame) REFERENCES tb_exame (id_exame)
);

INSERT INTO tb_exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');
INSERT INTO tb_funcionario(nm_funcionario) VALUES ('Carlos'), ('Bruno');
INSERT INTO tb_exame_funcionario (id_funcionario,id_exame,dt_exame_funcionario) 
VALUES (1,1,"2024-08-20"), (2,2,"2024-08-25");