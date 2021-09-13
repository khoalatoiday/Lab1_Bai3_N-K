
<%@page import="Model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
        Account auth = (Account)request.getSession().getAttribute("auth");
        if(auth != null){
        request.getSession().setAttribute("auth", auth);
        }else{
        response.sendRedirect("login.jsp");
    }
        
       
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Shopping Application</title>
        <%@include file="include/header.jsp" %>
    </head>
    
    <header>
        <%@include file="include/navbar.jsp" %>
    </header>
    
    <body>
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
                                 <th>Quantity</th>
                                 <th>Thành tiền</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <h3>Total Price: <c:out value="${sum}"/></h3>
                            
                            <c:forEach var="item" items="${cart.bookItems}">

                                <tr>
                                    <td>
                                        <c:out value="${item.book.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.book.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.book.type}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.book.price}" />
                                    </td>
                                     <td>
                                        <c:out value="${item.book.brand}" />
                                    </td>
                                     <td>
                                         <a class="badge badge-primary" href="IncreDecreQuantity?action=insc&id=<c:out value='${item.book.id}'/>">+</a> <c:out value="${item.quantity}" /><a href="IncreDecreQuantity?action=desc&id=<c:out value='${item.book.id}'/>" class="badge badge-danger">-</a>
                                    </td>
                                    <td>
                                        <c:out value="${item.quantity * item.book.price}" />
                                       
                                    </td>
                                    
                                    <td><a href="remove?id=<c:out value='${item.book.id}' />">Loại bỏ</a>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <a  class="btn btn-lg btn-primary" href="order?id=<c:out value='${cart.id}'/>&idCustomer=<c:out value='${cart.customer.id}'/>">Thanh toán</a>

                    </table>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
