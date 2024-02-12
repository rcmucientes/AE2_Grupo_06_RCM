#INDICACIONES



Para este ejemplo se ha usado una base de datos MySql, concretamente la que viene incluida en el all-in-one del XAMPP


#QUERYS PARA LA CREACION DE LA BASE DE DATOS


CREATE SCHEMA flota; USE flota;

CREATE TABLE coches ( 

id int(11) NOT NULL AUTO_INCREMENT, 

marca varchar(45) DEFAULT NULL, 

modelo varchar(45) DEFAULT NULL, 

fabricacion int(4) DEFAULT NULL, 

kilometros int(6) DEFAULT NULL, 

PRIMARY KEY (id) );


CREATE TABLE pasajeros (

id int (11) NOT NULL AUTO_INCREMENT,

nombre varchar (45) DEFAULT NULL,

edad int (11) DEFAULT NULL,

peso decimal (6,2) DEFAULT NULL,

idcoche int (11) DEFAULT NULL,

PRIMARY KEY (id), 

KEY awef2 (idcoche),

CONSTRAINT awef2 FOREIGN KEY (idcoche) REFERENCES coches (id) ON DELETE NO ACTION ON UPDATE NO ACTION);




#QUERYS PARA CARGAR DATOS 


insert into coches values(0,'Peugeot','206',2001,160000);

insert into coches values(0,'Honda','CRV',2018,110000);

insert into coches values(0,'Opel','Astra',1996,75000);

insert into coches values(0,'Ford','Sierra',1991,270000);

insert into coches values(0,'Renault','Clio',1992,170000);



insert into pasajeros values(0,'Pedro',47,120,null);

insert into pasajeros values(0,'Carlos',65,46,null);

insert into pasajeros values(0,'Ana',55,37,null);

insert into pasajeros values(0,'Rosa',52,70,null);

insert into pasajeros values(0,'Enrique',56,95,null);

