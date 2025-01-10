CREATE TABLE fonds (
    id SERIAL PRIMARY KEY ,
    idUser INT,
    depot numeric(15,2),
    retrait numeric(15,2),
    daty timestamp
);

CREATE TABLE Crypto(
    id serial primary key ,
    nom varchar(256),
    daty timestamp
);

CREATE TABLE ListCrypto(
    id serial primary key ,
    id_crypto int references Crypto(id),
    daty timestamp
);

CREATE TABLE mtv_crypto(
    id serial primary key ,
    id_crypto int references Crypto(id),
    id_user int,
    achat int,
    vente int,
    daty timestamp,
    valeur numeric(15,2)
);