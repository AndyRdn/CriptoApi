<%@ page import="org.main.criptoapi.mock.SidebarMock" %>
<%

    String url = ((String) request.getAttribute("jakarta.servlet.forward.request_uri"));
%>

<%=SidebarMock.DEFAULT_SIDEBAR.getHtml(url)%>
