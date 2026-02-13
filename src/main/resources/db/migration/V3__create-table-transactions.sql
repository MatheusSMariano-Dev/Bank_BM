create table transactions(

id UUID default random_uuid() primary key,

account_id UUID NOT NULL,

amount decimal (19,2) not null,

type varchar (20) not null,

created_at TIMESTAMP NOT NULL,

constraint fk_transactions_account
    foreign key (account_id)
    references accounts (id)


)