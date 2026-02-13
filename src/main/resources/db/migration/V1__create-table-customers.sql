CREATE TABLE customers(


id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,

name varchar(255) not null ,
cpf varchar(11) not null unique,
date_of_birth date not null,
email varchar(255) not null,
telephone varchar (20),
cellphone varchar (20),
zip_code varchar (10),
public_place varchar (255),
number varchar (20),
complement varchar (255)
);

