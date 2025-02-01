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

CREATE TABLE Config(
    id serial primary key ,
    key varchar(255),
    value varchar(255)
);

CREATE TABLE commission(
    id serial primary key ,
    id_crypto int references Crypto(id),
    daty timestamp,
    montant double precision
);

CREATE OR REPLACE VIEW v_solde_cryptos AS
(
    SELECT id_user, id_crypto, SUM(achat) - SUM(vente) AS quantite FROM mtv_crypto GROUP BY id_user, id_crypto ORDER BY id_user
);

create table histo_Crypto(
                             id serial primary key ,
                             id_Crypto int,
                             daty timestamp,
                             valeur decimal(15,2)
);


insert into histo_Crypto values (default, 1, '2024-1-1 10:00:00', 2200.40);
insert into histo_Crypto values (default, 2, '2024-1-1 10:00:00', 5000.99);
insert into histo_Crypto values (default, 3, '2024-1-1 10:00:00', 1500.50);
insert into histo_Crypto values (default, 4, '2024-1-1 10:00:00', 1800.60);
insert into histo_Crypto values (default, 5, '2024-1-1 10:00:00', 3200.75);
insert into histo_Crypto values (default, 6, '2024-1-1 10:00:00', 4100.80);
insert into histo_Crypto values (default, 7, '2024-1-1 10:00:00', 750.30);
insert into histo_Crypto values (default, 8, '2024-1-1 10:00:00', 1200.35);
insert into histo_Crypto values (default, 9, '2024-1-1 10:00:00', 300.25);
insert into histo_Crypto values (default, 10, '2024-1-1 10:00:00', 6500.99);

CREATE OR REPLACE VIEW v_solde_user AS
(
    SELECT iduser, SUM(depot) - SUM(retrait) AS solde FROM fonds GROUP BY iduser
);


INSERT INTO Crypto (nom, daty) VALUES ('Bitcoin', '2023-01-01 10:00:00');
INSERT INTO Crypto (nom, daty) VALUES ('Ethereum', '2023-02-15 11:30:00');
INSERT INTO Crypto (nom, daty) VALUES ('Cardano', '2023-03-20 14:15:00');
INSERT INTO Crypto (nom, daty) VALUES ('Solana', '2023-04-10 09:45:00');
INSERT INTO Crypto (nom, daty) VALUES ('Polkadot', '2023-05-25 16:20:00');
INSERT INTO Crypto (nom, daty) VALUES ('Litecoin', '2023-06-30 12:00:00');
INSERT INTO Crypto (nom, daty) VALUES ('Ripple', '2023-07-18 08:30:00');
INSERT INTO Crypto (nom, daty) VALUES ('Avalanche', '2023-08-22 13:40:00');
INSERT INTO Crypto (nom, daty) VALUES ('Chainlink', '2023-09-05 19:10:00');
INSERT INTO Crypto (nom, daty) VALUES ('Dogecoin', '2023-10-12 21:50:00');

INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (3, 1, 45, 20, '2024-01-04 10:20:30', 1500.50);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (5, 2, 60, 15, '2023-11-28 15:45:10', 3200.75);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (7, 3, 45, 30, '2023-10-15 08:12:00', 750.30);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (2, 4, 80, 50, '2023-12-05 22:30:15', 5000.99);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (9, 5, 10, 5, '2024-01-01 12:00:00', 300.25);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (1, 1, 50, 20, '2023-09-18 18:25:00', 2200.40);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (4, 3, 30, 25, '2023-07-22 14:10:45', 1800.60);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (6, 2, 55, 35, '2023-08-30 09:15:00', 4100.80);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (8, 4, 20, 10, '2024-01-03 20:50:00', 1200.35);
INSERT INTO mtv_crypto (id_crypto, id_user, achat, vente, daty, valeur) VALUES (10, 5, 70, 40, '2023-10-10 16:40:00', 6500.99);

INSERT INTO fonds (idUser, depot, retrait, daty)
VALUES
    (1, 15000.00, 0.00, '2024-02-15 10:30:00'),
    (2, 0.00, 12000.00, '2024-03-08 14:15:00'),
    (3, 8000.00, 0.00, '2024-04-20 09:45:00'),
    (4, 0.00, 5000.00, '2024-05-12 16:20:00'),
    (5, 18000.00, 0.00, '2024-06-18 11:00:00'),
    (1, 0.00, 7000.00, '2024-07-05 17:30:00'),
    (2, 10000.00, 0.00, '2024-08-10 08:45:00'),
    (3, 0.00, 15000.00, '2024-09-22 13:15:00'),
    (4, 12000.00, 0.00, '2024-10-11 15:00:00'),
    (5, 0.00, 9000.00, '2024-11-04 10:30:00'),
    (1, 20000.00, 0.00, '2024-12-16 14:00:00'),
    (2, 0.00, 8000.00, '2024-02-28 18:00:00'),
    (3, 6000.00, 0.00, '2024-03-15 11:30:00'),
    (4, 0.00, 10000.00, '2024-04-25 17:15:00'),
    (5, 15000.00, 0.00, '2024-05-20 09:00:00'),
    (1, 0.00, 12000.00, '2024-06-28 13:45:00'),
    (2, 18000.00, 0.00, '2024-07-12 15:30:00'),
    (3, 0.00, 7000.00, '2024-08-24 10:15:00'),
    (4, 9000.00, 0.00, '2024-09-05 16:00:00'),
    (5, 0.00, 15000.00, '2024-10-18 11:30:00'),
    (1, 10000.00, 0.00, '2024-11-11 17:00:00'),
    (2, 0.00, 5000.00, '2024-12-22 12:30:00'),
    (3, 12000.00, 0.00, '2024-01-15 09:45:00'),
    (4, 0.00, 8000.00, '2024-02-01 14:15:00'),
    (5, 7000.00, 0.00, '2024-03-02 10:30:00');

insert into config values
    (default,'comsAchat', '20'),
    (default,'comsVente', '25');


INSERT INTO commission (id_crypto, daty, montant) VALUES (1, '2024-01-05 00:00:00', 120.50);
INSERT INTO commission (id_crypto, daty, montant) VALUES (2, '2024-01-10 00:00:00', 250.75);
INSERT INTO commission (id_crypto, daty, montant) VALUES (3, '2024-01-15 00:00:00', 98.30);
INSERT INTO commission (id_crypto, daty, montant) VALUES (4, '2024-01-20 00:00:00', 180.45);
INSERT INTO commission (id_crypto, daty, montant) VALUES (5, '2024-01-25 00:00:00', 305.60);
INSERT INTO commission (id_crypto, daty, montant) VALUES (6, '2024-01-08 00:00:00', 75.20);
INSERT INTO commission (id_crypto, daty, montant) VALUES (7, '2024-01-12 00:00:00', 420.90);
INSERT INTO commission (id_crypto, daty, montant) VALUES (8, '2024-01-18 00:00:00', 150.00);
INSERT INTO commission (id_crypto, daty, montant) VALUES (9, '2024-01-22 00:00:00', 210.35);
INSERT INTO commission (id_crypto, daty, montant) VALUES (10, '2024-01-28 00:00:00', 99.99);
INSERT INTO commission (id_crypto, daty, montant) VALUES (1, '2024-01-03 00:00:00', 60.75);
INSERT INTO commission (id_crypto, daty, montant) VALUES (2, '2024-01-07 00:00:00', 130.40);
INSERT INTO commission (id_crypto, daty, montant) VALUES (3, '2024-01-13 00:00:00', 345.80);
INSERT INTO commission (id_crypto, daty, montant) VALUES (4, '2024-01-17 00:00:00', 220.90);
INSERT INTO commission (id_crypto, daty, montant) VALUES (5, '2024-01-21 00:00:00', 110.25);
INSERT INTO commission (id_crypto, daty, montant) VALUES (6, '2024-01-26 00:00:00', 185.50);
INSERT INTO commission (id_crypto, daty, montant) VALUES (7, '2024-01-29 00:00:00', 275.80);
INSERT INTO commission (id_crypto, daty, montant) VALUES (8, '2024-01-30 00:00:00', 325.45);
INSERT INTO commission (id_crypto, daty, montant) VALUES (9, '2024-01-31 00:00:00', 490.20);
INSERT INTO commission (id_crypto, daty, montant) VALUES (10, '2024-01-02 00:00:00', 210.10);

INSERT INTO commission (id_crypto, daty, montant) VALUES (1, '2024-02-03 00:00:00', 135.75);
INSERT INTO commission (id_crypto, daty, montant) VALUES (2, '2024-02-07 00:00:00', 260.30);
INSERT INTO commission (id_crypto, daty, montant) VALUES (3, '2024-02-12 00:00:00', 110.90);
INSERT INTO commission (id_crypto, daty, montant) VALUES (4, '2024-02-16 00:00:00', 190.60);
INSERT INTO commission (id_crypto, daty, montant) VALUES (5, '2024-02-22 00:00:00', 310.40);
INSERT INTO commission (id_crypto, daty, montant) VALUES (6, '2024-02-05 00:00:00', 95.20);
INSERT INTO commission (id_crypto, daty, montant) VALUES (7, '2024-02-11 00:00:00', 405.80);
INSERT INTO commission (id_crypto, daty, montant) VALUES (8, '2024-02-14 00:00:00', 160.00);
INSERT INTO commission (id_crypto, daty, montant) VALUES (9, '2024-02-19 00:00:00', 220.35);
INSERT INTO commission (id_crypto, daty, montant) VALUES (10, '2024-02-25 00:00:00', 109.99);
INSERT INTO commission (id_crypto, daty, montant) VALUES (1, '2024-02-06 00:00:00', 70.80);
INSERT INTO commission (id_crypto, daty, montant) VALUES (2, '2024-02-08 00:00:00', 140.50);
INSERT INTO commission (id_crypto, daty, montant) VALUES (3, '2024-02-15 00:00:00', 360.70);
INSERT INTO commission (id_crypto, daty, montant) VALUES (4, '2024-02-18 00:00:00', 230.85);
INSERT INTO commission (id_crypto, daty, montant) VALUES (5, '2024-02-23 00:00:00', 120.15);
INSERT INTO commission (id_crypto, daty, montant) VALUES (6, '2024-02-27 00:00:00', 195.50);
INSERT INTO commission (id_crypto, daty, montant) VALUES (7, '2024-02-28 00:00:00', 285.80);
INSERT INTO commission (id_crypto, daty, montant) VALUES (8, '2024-02-29 00:00:00', 335.45);
INSERT INTO commission (id_crypto, daty, montant) VALUES (9, '2024-02-02 00:00:00', 500.20);
INSERT INTO commission (id_crypto, daty, montant) VALUES (10, '2024-02-04 00:00:00', 220.10);

INSERT INTO commission (id_crypto, daty, montant) VALUES (5, '2025-02-23 00:00:00', 120.15);
INSERT INTO commission (id_crypto, daty, montant) VALUES (6, '2025-02-27 00:00:00', 195.50);
INSERT INTO commission (id_crypto, daty, montant) VALUES (7, '2025-02-28 00:00:00', 285.80);
INSERT INTO commission (id_crypto, daty, montant) VALUES (8, '2025-02-29 00:00:00', 335.45);
INSERT INTO commission (id_crypto, daty, montant) VALUES (9, '2025-02-02 00:00:00', 500.20);
INSERT INTO commission (id_crypto, daty, montant) VALUES (10, '2025-02-04 00:00:00', 220.10);


