create table "Emozione"
(
    "Nome"        text        not null,
    "Utente"      text        not null constraint "Emozione_Utente_null_fk" references "Utente" ("Username"),
    "Canzone"     varchar(18) not null constraint "Emozione_Canzone_null_fk" references "Canzone",
    "Valutazione" integer     not null,
    constraint "Emozione_pk" primary key ("Nome", "Utente", "Canzone")
);

alter table "Emozione"
    owner to postgres;