drop table if exists "Canzone" cascade;

create table "Canzone"
(
    "ID"      varchar(18) primary key,
    "Artista" text    not null,
    "Titolo"  text    not null,
    "Anno"    integer not null
);