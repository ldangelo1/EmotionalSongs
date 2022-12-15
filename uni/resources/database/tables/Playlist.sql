drop table if exists "Playlist" cascade;

create table "Playlist"
(
    "Contatore" serial primary key,
    "Nome"      text,
    "CF"        varchar(16) references "Utente" on delete cascade on update cascade
);

insert into "Playlist" ("Nome", "CF")
values ('Growl', 'ABCXYZ00A01A000A');