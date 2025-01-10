SELECT id_user, id_crypto, SUM(achat) - SUM(vente) AS quantite FROM mtv_crypto GROUP BY id_user, id_crypto;

CREATE OR REPLACE VIEW v_solde_cryptos AS
(
    SELECT id_user, id_crypto, SUM(achat) - SUM(vente) AS quantite FROM mtv_crypto GROUP BY id_user, id_crypto ORDER BY id_user
);