create table accounts(

id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,

customer_id UUID NOT NULL,

balance DECIMAL (19,2) NOT NULL,
status varchar (20) not null,

CONSTRAINT fk_account_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id)

)