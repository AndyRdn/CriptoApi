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
