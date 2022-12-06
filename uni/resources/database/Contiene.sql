drop table if exists "Contiene";

create table "Contiene"
(
    "fk_Playlist" integer references "Playlist",
    "ID"          varchar(18) references "Canzone"
);

insert into "Contiene"
values (1, 'TRSGHLU128F421DF83')