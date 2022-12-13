drop table if exists "Emozione";

create table "Emozione"
(
    "Tipo"        text,
    "CF"          integer references "Utente" ("CF"),
    "ID"          varchar(18) references "Canzone" ("ID"),
    "Valutazione" integer not null,
    primary key ("Tipo", "CF", "ID")
);

insert into "Emozione"
values ('Happiness', 0, 'TRSGHLU128F421DF83', 4);