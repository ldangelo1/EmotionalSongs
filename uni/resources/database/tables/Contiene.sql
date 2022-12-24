drop table if exists "Contiene" cascade;

create table "Contiene"
(
    "fk_Playlist" integer references "Playlist" on delete cascade on update cascade,
    "ID"          varchar(18) references "Canzone" on delete cascade on update cascade
);

insert into "Contiene"
values (1, 'TRSGHLU128F421DF83')