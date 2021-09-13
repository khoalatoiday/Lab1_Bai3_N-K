<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="books">Books</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/books">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="cart">Cart<span class="badge badge-danger"><c:out value="${cart.bookItems.size()}"/></span></a>
        </li>
      
        
        <%
          if(auth != null){ %>
          <li class="nav-item">
          <a class="nav-link" href="#">Order</a>
        </li>
            <li class="nav-item">
          <a class="nav-link" href="logout">Logout</a>
        </li>
            <%}else{ %>
              <li class="nav-item">
          <a class="nav-link" href="login.jsp">Login</a>
        </li>
        <%}
        %>
        
      </ul>
    </div>
  </div>
</nav>