drop table if exists "Utente";

create table "Utente"
(
    "CF"        integer primary key,
    "Nome"      text not null,
    "Email"     text not null,
    "Indirizzo" text not null,
    "Username"  text not null unique,
    "Password"  text not null
);

insert into "Utente"
values (0, 'Lorenzo D''Angelo', 'lorenzo@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'dange', 'labD');
insert into "Utente"
values (1, 'Samuel Pedotti', 'samuel@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'samu', 'labS');
insert into "Utente"
values (2, 'Gaia Irace', 'gaia@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'gaia', 'labG');
insert into "Utente"
values (3, 'Riccardo', 'ricky@lab.it', 'via Laboratorio 0, Varese (VA) 22000', 'ricky', 'labR');