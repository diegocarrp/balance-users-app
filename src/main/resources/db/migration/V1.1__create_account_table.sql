create table account
(
    id serial not null,
    rut varchar not null,
    email varchar not null,
    names varchar not null,
    lastname1 varchar not null,
    lastname2 varchar not null,
    cellphone varchar not null,
    password VARCHAR not null,
    role_id numeric not null,
    PRIMARY KEY (id)
);