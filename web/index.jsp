<%@page import="Model.Cart"%>
<%@page import="CustomerDAO.CustomerDAOImpl"%>
<%@page import="Model.Customer"%>
<%@page import="Model.ChoosenBookItem"%>
<%@page import="BookItemDAO.BookItemImpl"%>
<%@page import="CardDAO.CartDAOImpl"%>
<%@page import="AccountDAO.AccountDAOImpl"%>
<%@page import="Model.BookItem"%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
        AccountDAOImpl accountDAOImpl = new AccountDAOImpl();
        int customerId = accountDAOImpl.sellectCustomerIdById(auth.getUsername());
        CartDAOImpl cartDAOImpl = new CartDAOImpl();
        int cartId = cartDAOImpl.sellectCartIdByCustomerId(customerId);
        float sum = 0;
        if (cartId != -1) {
            BookItemImpl bookItemImpl = new BookItemImpl();
            CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
            Cart cart = cartDAOImpl.selectCartById(cartId);
            
                ArrayList<ChoosenBookItem> choosenBookItems = bookItemImpl.selectChoosenBookItemsByCartId(cartId);
                Customer customer = customerDAOImpl.sellectCustomerById(customerId);
                cart.setId(cartId);
                cart.setCustomer(customer);
                cart.setBookItems(choosenBookItems);
                request.getSession().setAttribute("cart", cart);
                for(ChoosenBookItem choosenBookItem: cart.getBookItems()){
                    sum += (choosenBookItem.getPrice() * choosenBookItem.getQuantity());
                }
                request.getSession().setAttribute("sum", sum);
            }
        }else{
        request.getSession().removeAttribute("cart");
    }
    
%>
<html>

    <head>
        <title>Book Shopping Application</title>
        <%@include file="include/header.jsp" %>
    </head>

    <body>

        <header>
            <%@include file="include/navbar.jsp" %>
        </header>
        <br>

        <div class="row container">

            <div class="container">
                <h3 class="text-center">List of Books</h3>
                <hr>
                <div class="container text-left">
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Price</th>
                            <th>Brand</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="book" items="${listBooks}">

                            <tr>
                                <td>
                                    <c:out value="${book.id}" />
                                </td>
                                <td>
                                    <c:out value="${book.name}" />
                                </td>
                                <td>
                                    <c:out value="${book.type}" />
                                </td>
                                <td>
                                    <c:out value="${book.price}" />
                                </td>
                                <td>
                                    <c:out value="${book.brand}" />
                                </td>

                                <td><a href="buy?id=<c:out value='${book.id}' />">Mua Hàng</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="addToCart?id=<c:out value='${book.id}' />">Thêm vào giỏ hàng</a></td>
                            </tr>
                        </c:forEach>

                    </tbody>

                </table>
                <form action="searchByName" method="POST">
                    <input name="txt" type="text" value="${txtS}">
                    <button type="submit">Search Your Book</button>
                </form>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>       
    </body>

</html>