<%@page import="Model.Account"%>
<% 
        Account auth = (Account)request.getSession().getAttribute("auth");
        if(auth != null){
        response.sendRedirect("books");
        }
    %>
<!DOCTYPE html>
<html>
    <head>

        <title>Book Shopping Application</title>
        <%@include file="include/header.jsp" %>
    </head>
    <body>
        <div align="center" class="container">


            <div class="card" style="width: 18rem">
                <div class="card-header text-center">
                    Login Form User
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/user-login" method="post">
                        <div class="form-group">
                            <label> Username </lable>
                                <input type="text" class="form-control" name="username" placeholder="Enter your username" required />
                        </div>
                        
                        <div class="form-group">
                            <label> password </lable>
                                <input type="password" class="form-control" name="password" placeholder="Enter your password" required />
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-lg btn-primary" value="Submit">Login</button>
                        </div>    
                    </form>
                </div>
            </div>
            <a href="registerForm.jsp">Register</a>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>