<%@ page import="java.util.List" %>
<%@ page import="org.main.criptoapi.mvtCrypto.MtvCrypto" %>

<div class="card">
  <h5 class="card-header">Liste des achats et ventes</h5>
  <div class="table-responsive text-nowrap">
    <table class="table">
      <thead>
        <tr>
          <th>Utilisateur</th>
          <th>Date</th>
          <th>Crypto</th>
          <th>Achat</th>
          <th>Vente</th>
        </tr>
      </thead>
      <tbody class="table-border-bottom-0">
      <%
      
      List<MtvCrypto> mouvements = (List<MtvCrypto>) request.getAttribute("listeAchatVente");
      for(MtvCrypto m : mouvements) { %>
        <tr>
          <td><%= m.getIdUser() %></td>
          <td><%= m.getDaty().toString() %></td>
          <td><%= m.getIdCrypto().getNom() %></td>
          <td><%= m.getAchat() %></td>
          <td><%= m.getVente() %></td>
        </tr>

      <% }
      
      %>
      </tbody>
    </table>
  </div>
</div>