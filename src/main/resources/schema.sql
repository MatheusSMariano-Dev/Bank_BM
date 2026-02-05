CREATE TABLE cliente
id UUID varchar(255) not null primary key,
nome varchar (150) NOT NULL,
cpf char (11) NOT NULL,
data_nascimento DATE NOT NULL,

email varchar (255) NOT NULL,
telefone varchar (20),

cep varchar (8),
logradouro varchar (150),
numero varchar (20),
complemento varchar (100),
bairro varchar (100),
cidade varchar (100),
estado char (2),

status varchar (20) NOT NULL,

criado_em TIMESTAMP NOT NULL,
atualizado_em TIMESTAMP NOT NULL,

CONSTRAINT pk_cliente PRIMARY KEY (id),
CONSTRAINT uk_client_cpf UNIQUE (cpf),
CONSTRAINT uk_client_email UNIQUE (email),