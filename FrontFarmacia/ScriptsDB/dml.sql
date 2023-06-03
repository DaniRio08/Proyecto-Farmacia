--Insertar datos en la tabla medicamentos
INSERT INTO medicine(med_id, name, tmax, tmin) VALUES
(1212, "Herceptin (trastuzumab)", 8, 1),
(2323, "Avastin (bevacizumab)", 6, 2),
(5463, "Rituxan (rituximab)", 9, 2),
(3434, "Opdivo (nivolumab)", 5, 1),
(4545, "Adecetris (brentuximab)", -15, -20);

--Insertar datos en la tabla doctores
INSERT INTO doctor(mail, pass, name) VALUES
("dani@gmail.com", "1234", "Daniel Río Arizti"),
("pedrop@gmail.com", "12345", "Pedro Pérez Palomino"),
("felipe1988@gmail.com", "123456", "Felipeti de Caravanchel"),
("rodrigodr@gmail.com", "4321", "Rodrigo Díaz"),
("Sarajj@gmail.com", "54321", "Sara dos Santos"),
("lionel10@gmail.com", "654321", "Lionel Messi Cuccitini"),
("cr7@gmail.com", "cr7", "Cristiano Ronaldo");

--Insertar datos en la tabla pacientes
INSERT INTO patient (mail, name) VALUES
("pedrorodriguez@gmail.com", "Pedro Rodriguez Saavedra"),
("mariagomez@hotmail.com", "Maria Gomez Lopez"),
("juancastro@gmail.com", "Juan Castro Ramirez"),
("lauragonzalez@gmail.com", "Laura Gonzalez Martinez"),
("carloslopez@yahoo.com", "Carlos Lopez Garcia"),
("anaperez@hotmail.com", "Ana Perez Fernandez"),
("robertoortega@gmail.com", "Roberto Ortega Moreno"),
("sofiatorres@yahoo.com", "Sofia Torres Navarro"),
("javierhernandez@gmail.com", "Javier Hernandez Vega"),
("paolacabrera@hotmail.com", "Paola Cabrera Silva"),
("miguelruiz@gmail.com", "Miguel Ruiz Herrera"),
("luciamolina@yahoo.com", "Lucia Molina Jimenez"),
("danielruiz@gmail.com", "Daniel Ruiz Torres"),
("isabelsanchez@hotmail.com", "Isabel Sanchez Lopez"),
("pabloflores@yahoo.com", "Pablo Flores Hernandez"),
("luisacastro@gmail.com", "Luisa Castro Ortiz"),
("jorgesoto@hotmail.com", "Jorge Soto Ramirez"),
("claudiagonzalez@gmail.com", "Claudia Gonzalez Rios"),
("andresdiaz@yahoo.com", "Andres Diaz Martinez"),
("patriciavargas@gmail.com", "Patricia Vargas Silva");

--Insertar datos en la tabla de chips
INSERT INTO xip(xip_id, doctor_mail, id_medicine, patient_mail, end_date) VALUES
(42257783, "dani@gmail.com", 1212, "pedrorodriguez@gmail.com", "2023-07-15"),
(52109090, "dani@gmail.com", 1212, "mariagomez@hotmail.com", "2023-06-15"),
(90228517, "dani@gmail.com", 1212, "juancastro@gmail.com", "2023-07-22"),
(63790427, "dani@gmail.com", 2323, "pedrorodriguez@gmail.com", "2023-05-15"),
(21071485, "dani@gmail.com", 1212, "luisacastro@gmail.com", "2023-07-15"),
(99909633, "dani@gmail.com", 1212, "danielruiz@gmail.com", "2023-07-01"),
(61905917, "dani@gmail.com", 2323, "carloslopez@yahoo.com", "2023-05-26"),
(25851451, "dani@gmail.com", 1212, "javierhernandez@gmail.com", "2023-06-27"),
(23458420, "dani@gmail.com", 2323, "robertoortega@gmail.com", "2023-06-15"),
(64478073, "dani@gmail.com", 5463, "pedrorodriguez@gmail.com", "2023-07-22"),
(66931395, "dani@gmail.com", 5463, "jorgesoto@hotmail.com", "2023-06-01"),
(23822761, "dani@gmail.com", 5463, "sofiatorres@yahoo.com", "2023-07-15"),
(17374511, "dani@gmail.com", 3434, "patriciavargas@gmail.com", "2023-05-25"),
(71491816, "pedrop@gmail.com", 3434, "miguelruiz@gmail.com", "2023-06-17"),
(80229296, "felipe1988@gmail.com", 2323, "lauragonzalez@gmail.com", "2023-06-09"),
(38619223, "felipe1988@gmail.com", 5463, "lauragonzalez@gmail.com", "2023-06-23"),
(39546992, "rodrigodr@gmail.com", 4545, "luciamolina@yahoo.com", "2023-06-22"),
(48976754, "Sarajj@gmail.com", 4545, "anaperez@hotmail.com", "2023-05-29"),
(23626681, "lionel10@gmail.com", 3434, "isabelsanchez@hotmail.com", "2023-06-30"),
(35391825, "lionel10@gmail.com", 3434, "pabloflores@yahoo.com", "2023-07-02"),
(42258786, "cr7@gmail.com", 4545, "andresdiaz@yahoo.com", "2023-07-01");