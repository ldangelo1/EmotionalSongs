drop table if exists "Playlist";

create table "Playlist"
(
    "Contatore" serial primary key,
    "Nome"      text,
    "CF"        integer references "Utente" ("CF")
);

insert into "Playlist" ("Nome", "CF")
values ('Growl', 0);