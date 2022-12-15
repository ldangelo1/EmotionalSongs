drop table if exists "Emozione" cascade;

create table "Emozione"
(
    "Tipo"        text,
    "CF"          varchar(16) references "Utente" on delete cascade on update cascade,
    "ID"          varchar(18) references "Canzone" on delete cascade on update cascade,
    "Valutazione" integer not null,
    primary key ("Tipo", "CF", "ID")
);

insert into "Emozione"
values ('Happiness', 'ABCXYZ00A01A000A', 'TRSGHLU128F421DF83', 4);