create table "Utente"
(
    "CF"        integer not null constraint "Utente_pk" primary key,
    "Nome"      text    not null,
    "Email"     text,
    "Username"  text    not null constraint "Username_pk" unique,
    "Password"  text    not null,
    "Indirizzo" text
);

alter table "Utente"
    owner to postgres;

INSERT INTO public."Utente" ("CF", "Nome", "Email", "Username", "Password", "Indirizzo") VALUES (3, 'Riccardo', 'ricky@lab.it', 'ricky', 'labR', 'r');
INSERT INTO public."Utente" ("CF", "Nome", "Email", "Username", "Password", "Indirizzo") VALUES (0, 'Lorenzo', 'lorenzo@lab.it', 'dange', 'labD', 'd');
INSERT INTO public."Utente" ("CF", "Nome", "Email", "Username", "Password", "Indirizzo") VALUES (1, 'Samuel', 'samuel@lab.it', 'samu', 'labS', 's');
INSERT INTO public."Utente" ("CF", "Nome", "Email", "Username", "Password", "Indirizzo") VALUES (2, 'Gaia', 'gaia@lab.it', 'gaia', 'labG', 'g');
INSERT INTO public."Utente" ("CF", "Nome", "Email", "Username", "Password", "Indirizzo") VALUES (10, 'Simone', 'simo@lab.it', 'simo', 'SimLab', 'sl');
