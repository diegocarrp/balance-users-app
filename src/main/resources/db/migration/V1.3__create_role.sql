create table role (
    id serial not null,
    name varchar not null,
    primary key (id)
);

insert into role (name) values ('ADMIN');
insert into role (name) values ('CASHIER');