--Script per a la creació de la base de dades del projecte de Entorns de Desenvolupament--

CREATE DATABASE farmacia;
USE farmacia;

--Taula Doctor--
CREATE TABLE IF NOT EXISTS doctor 
(mail varchar(50),
pass varchar(100) NOT NULL,
name varchar(100) NOT NULL,
last_log date,
session bigint(10),
PRIMARY KEY(mail));

--Taula Pacient--
CREATE TABLE IF NOT EXISTS patient
(mail varchar(50),
name varchar(100) NOT NULL,
PRIMARY KEY(mail));

--Taula Medicament--
CREATE TABLE IF NOT EXISTS medicine
(med_id int AUTO_INCREMENT,
name varchar(50) NOT NULL,
tmax int NOT NULL,
tmin int NOT NULL,
PRIMARY KEY(med_id));

--Taula Xip--
CREATE TABLE IF NOT EXISTS xip
(xip_id int(10),
doctor_mail varchar(50),
id_medicine int,
patient_mail varchar(50),
end_date date NOT NULL,
PRIMARY KEY(xip_id),
FOREIGN KEY (doctor_mail) REFERENCES doctor (mail)
ON UPDATE cascade,
FOREIGN KEY (id_medicine) REFERENCES medicine (med_id)
ON UPDATE cascade,
FOREIGN KEY (patient_mail) REFERENCES patient (mail)
ON UPDATE cascade);