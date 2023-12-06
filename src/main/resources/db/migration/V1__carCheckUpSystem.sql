create table if not exists carinfo(
    id bigint primary key,
    manufacturer varchar(50),
    model varchar(50)
);

create sequence if not exists carinfo_seq;

create table if not exists car(
    id bigint primary key,
    datewhenadded DATE,
    carinfoid bigint constraint car_fk references carinfo(id),
    productionyear INTEGER,
    vin varchar(20));

create sequence if not exists car_seq;

create table if not exists carcheckup(
    id bigint primary key,
    performedat TIMESTAMP,
    worker varchar(50),
    price FLOAT,
    carid bigint constraint carcheckup_fk references car(id));

create sequence if not exists carcheckup_seq;



