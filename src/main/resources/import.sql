INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(1, 'Joaquin', 'Navarro', 'alfian1991@gmail.com', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(2, 'Thalia', 'Ravelo dice ella pero es Perez', 'thaly7492@gmail.com', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(3, 'Joaquin1', 'Navarro1', 'alfian1991@gmail.com1', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(4, 'Thalia1', 'Ravelo dice ella pero es Perez1', 'thaly7492@gmail.com1', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(5, 'Joaqui3', 'Navarro3', 'alfian1991@gmail.com3', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(6, 'Thali3', 'Ravelo dice ella pero es Perez3', 'thaly7492@gmail.com3', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(7, 'Joaquin2', 'Navarro2', 'alfian1991@gmail.com2', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(8, 'Thalia2', 'Ravelo dice ella pero es Perez2', 'thaly7492@gmail.com2', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(9, 'Rossana', 'Ravelo', 'rossana@gmail.com', '2019-06-09', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(10, 'Ana', 'Madelein', 'alfian62@gmail.com', '2019-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(11, 'Eduardo', 'Alfian', 'alfian62@gmail.com', '2019-04-02', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(12, 'Anubis', 'Navarro', 'anubis@gmail.com', '2019-03-20', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(13, 'Raydel', 'Douglas', 'rdouglas@gmail.com', '2018-03-02', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(14, 'Raydel', 'Munos', 'rmunos@gmail.com', '2017-02-06', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(15, 'Jose', 'Vicente', 'josevicente@gmail.com', '2019-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(16, 'Abdel', 'LaEscencia', 'abdel@gmail.com', '2019-04-02', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(17, 'Casa', 'Mia', 'micasa@gmail.com', '2019-06-12', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(18, 'Python', 'Django', 'python@django.com', '2017-08-03', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(19, 'Peugeot', '205', '205gt@peugeot.com', '1986-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(20, 'hyunday', 'elantra', 'hyunday@elantra.com', '2013-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(21, 'Bruce', 'Wayne', 'batman@dc.com', '1956-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(22, 'Clark', 'Kent', 'superman@dc.com', '1945-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(23, 'Bruce', 'Banner', 'hulk@marvel.com', '1959-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(24, 'Tony', 'Stark', 'ironman@marvel.com', '1960-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(25, 'Wade', 'Wilson', 'deadpool@marvel.com', '1980-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(26, 'Cleo', 'Patra', 'muyfea@feaegipcia.com', '1000-01-01', '');
INSERT INTO clients (id, name, last_name, email, created_at, image) VALUES(27, 'Edward', 'Norton', 'hystorix@gmail.com', '2000-01-01', '');


/* Populate table products */
INSERT INTO products (name, price, created_at) VALUES('Sony LED 85"', 3500, NOW());
INSERT INTO products (name, price, created_at) VALUES('Iphone 8', 500, NOW());
INSERT INTO products (name, price, created_at) VALUES('PC Gamer Razor', 600, NOW());
INSERT INTO products (name, price, created_at) VALUES('Desktop Chair', 80, NOW());
INSERT INTO products (name, price, created_at) VALUES('LG LED 43"', 600, NOW());
INSERT INTO products (name, price, created_at) VALUES('PS4 3ra gen', 500, NOW());
INSERT INTO products (name, price, created_at) VALUES('Smart Watch', 200, NOW());

/* populate factures */
INSERT INTO facture (description, observation, client_id, created_at) VALUES('Office Equipment', null, 1, NOW());
INSERT INTO factures_items (quantity, facture_id, product_id) VALUES(1, 1, 1);
INSERT INTO factures_items (quantity, facture_id, product_id) VALUES(2, 1, 4);
INSERT INTO factures_items (quantity, facture_id, product_id) VALUES(1, 1, 5);
INSERT INTO factures_items (quantity, facture_id, product_id) VALUES(1, 1, 3);

INSERT INTO facture (description, observation, client_id, created_at) VALUES('Home Equipment', 'Important Note', 1, NOW());
INSERT INTO factures_items (quantity, facture_id, product_id) VALUES(3, 2, 6);
