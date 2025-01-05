SELECT id_user, id_crypto, SUM(achat) - SUM(vente) AS quantite FROM mtv_crypto GROUP BY iduser, id_crypto;

CREATE OR REPLACE VIEW v_solde_user AS
(
    SELECT id_user, id_crypto, SUM(achat) - SUM(vente) AS quantite FROM mtv_crypto GROUP BY iduser, id_crypto
);