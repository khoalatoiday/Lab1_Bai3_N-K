<%@page import="Model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
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
            <h3 class="text-center">Order</h3>
            <hr>
            <div class="container text-left">
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>

                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Thành tiền</th>

                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="item" items="${cart.bookItems}">

                        <tr>

                            <td>
                                <c:out value="${item.book.name}" />
                            </td>

                            <td>
                                <c:out value="${item.book.price}" />
                            </td>

                            <td>
                                <c:out value="${item.quantity}" />
                            </td>
                            <td>
                                <c:out value="${item.quantity * item.book.price}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>


            </table>

            <form action="choosePaymentServlet" method="get">
                Lựa chọn phương thức thanh toán
                <select name="payment">
                    <c:forEach items="${listPayment}" var="payment">
                        <option value="${payment.id}">${payment.type}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Chọn" />
            </form>
            <p>
                Hình thức giao dịch của bạn là:<strong> <c:out value="${nameOfPayment}"/></strong>
            </p>
            <p>
                Bạn đã nhận được khuyến mãi giảm:<strong> <c:out value="${discount}" />%</strong> tổng giá trị đơn hàng
            </p>

            <form action="chooseShipmentServlet" method="get">
                Lựa chọn nơi nhận hàng
                <select name="shipment">
                    <c:forEach items="${listShipment}" var="shipment">
                        <option value="${shipment.id}">${shipment.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Chọn" />

            </form>
            <p>Bạn nhận hàng tại:  
             <strong>   <c:out value="${address}" /></strong>
            </p>
            <p>Phí giao hàng của bạn là:
               <strong> <c:out value="${priceOfShipment}"/></strong>
            </p>
            <h4>Tổng số tiền bạn phải trả là: <strong> <c:out value="${sum - sum*discount/100 + priceOfShipment}"/> </strong></h4>
            <a  class="btn btn-lg btn-primary" href="finishOrder">Thanh toán</a>
            <%@include file="include/footer.jsp" %>
    </body>
</html>
