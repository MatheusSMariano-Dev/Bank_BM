CREATE TABLE users (
    id UUID PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE,
    account_number VARCHAR(20) UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    active BOOLEAN DEFAULT TRUE
);