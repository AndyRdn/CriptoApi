<%@ page import="java.util.List" %>
<%@ page import="org.main.criptoapi.mvtCrypto.SoldeCryptoDTO" %>
<%@ page import="org.main.criptoapi.crypto.Crypto" %>

<%@ page contentType="text/html;charset=UTF-8" %>


<div class="card">
  <div class="card-header">
    <h5 class="card-title">Portefeuille de cryptos</h5>
  </div>
  <div class="card-datatable table-responsive">
    <table class="datatables-products table">
      <thead class="border-top">
        <tr>
          <th>Crypto</th>
          <th>Valeur</th>
          <th>Quantité</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <%
            List<SoldeCryptoDTO> portefeuille = (List<SoldeCryptoDTO>) request.getAttribute("listCrypto");
            // out.println(portefeuille.size());
            for(SoldeCryptoDTO solde : portefeuille) {
                Crypto c = solde.getCrypto();
                String cName = c.getNom();

                double valeur = solde.getValeur();

                double quantite = solde.getQuantite();

        %>

            <tr>
                <td><%= cName %></td>
                <td><%= valeur %></td>
                <td><%= quantite %></td>
                <td>
                    <form action="/mvt-crypto/sell" method="post">
                        <input type="hidden" name="idUser" value="1" />
                        <input type="hidden" name="idCrypto" value="<%= c.getId() %>" />

                        <div class="input-group">
                            <input type="number" class="form-control" id="" placeholder="Quantite/<%= quantite %>" name="quantite" aria-describedby="button-addon2">
                            <button class="btn btn-outline-primary" type="submit" id="button-addon2">Vendre</button>
                        </div>
                    </form>
                </td>
            </tr>

        <% } %>

      </tbody>
    </table>
  </div>
</div>