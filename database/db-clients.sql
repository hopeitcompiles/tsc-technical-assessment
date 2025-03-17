CREATE TABLE IF NOT EXISTS person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    gender VARCHAR(10),
    age INT,
    identification_number VARCHAR(13),
    address VARCHAR(100),
    phone VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS client (
    id BIGINT PRIMARY KEY,
    password VARCHAR(255),
    status BOOLEAN,
    FOREIGN KEY (id) REFERENCES person(id)
);

INSERT INTO person (name, gender, age, identification_number, address, phone) VALUES
    ('Jose Lema', 'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785'),
    ('Marianela Montalvo', 'Femenino', 28, '0987654321', 'Amazonas y NNUU', '097548965'),
    ('Juan Osorio', 'Masculino', 35, '1122334455', '13 junio y Equinoccial', '098874587');

INSERT INTO client (id, password, status) VALUES
     (1,  '1234', true),
     (2,  '5678', true),
     (3,  '1245', true);