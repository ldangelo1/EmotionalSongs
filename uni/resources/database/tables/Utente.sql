drop table if exists "Utente" cascade;

create table "Utente"
(
    "CF"        varchar(16) primary key,
    "Nome"      text not null,
    "Email"     text not null,
    "Indirizzo" text not null,
    "Username"  text not null unique,
    "Password"  text not null
);

insert into "Utente"
values ('ABCXYZ00A01A000A', 'Lorenzo D''Angelo', 'lorenzo@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'dange',
        'labD');
insert into "Utente"
values ('ABCXYZ00A01A001A', 'Samuel Pedotti', 'samuel@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'samu', 'labS');
insert into "Utente"
values ('ABCXYZ00A01A002A', 'Gaia Irace', 'gaia@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'gaia', 'labG');
insert into "Utente"
values ('ABCXYZ00A01A003A', 'Riccardo', 'ricky@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'ricky', 'labR');