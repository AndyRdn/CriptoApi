SELECT iduser, SUM(depot) - SUM(retrait) AS solde FROM fonds GROUP BY iduser;

CREATE OR REPLACE VIEW v_solde_user AS
(
    SELECT iduser, SUM(depot) - SUM(retrait) AS solde FROM fonds GROUP BY iduser
);