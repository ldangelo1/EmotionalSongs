create table "Playlist"
(
    "Nome"      text        not null,
    "Utente"    text        not null constraint "Playlist_Utente_null_fk" references "Utente" ("Username"),
    "IDCanzone" varchar(18) not null constraint "Playlist_Canzone_null_fk" references "Canzone",
    constraint "Playlist_pk" primary key ("Nome", "Utente")
);

alter table "Playlist"
    owner to postgres;

INSERT INTO public."Playlist" ("Nome", "Utente", "IDCanzone") VALUES ('growl', 'dange', 'TRSGHLU128F421DF83');