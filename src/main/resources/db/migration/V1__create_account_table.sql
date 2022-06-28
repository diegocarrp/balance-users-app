CREATE TABLE account
(
    id SERIAL NOT NULL,
    rut varchar(12) NOT NULL,
    email varchar(255) NOT NULL,
    names varchar(255) NOT NULL,
    lastname1 varchar(255) NOT NULL,
    lastname2 varchar(255) NOT NULL,
    cellphone varchar(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role varchar(255) NOT NULL,
    PRIMARY KEY (id)
);