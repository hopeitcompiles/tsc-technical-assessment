CREATE TABLE IF NOT EXISTS account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_type VARCHAR(50),
    account_number VARCHAR(50) UNIQUE,
    initial_balance DOUBLE,
    status BOOLEAN,
    client_id BIGINT
);

CREATE TABLE IF NOT EXISTS transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATETIME,
    transaction_type VARCHAR(50),
    amount DOUBLE,
    balance DOUBLE,
    account_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES account(id)
);