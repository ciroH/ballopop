<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>votingLogIn</title>
    <link rel="stylesheet" href="assets/css/Login-Form_Dark.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.min.css">
    <% String warning = (String)request.getAttribute("warning"); 
       boolean adminPriv = false;
       if(request.getParameter("isAdmin") != null && request.getParameter("isAdmin").equals("admin")){
     		adminPriv = true;
        } %>
</head>

<body>
    <section class="login-dark">
        <form action="login" method="post">
            <h2 class="visually-hidden">Login Form</h2>
            <div class="illustration"><img class="logo" src="assets/img/ballopop-logo.svg"></div>
            <div class="mb-3"><input class="form-control" type="number" name="id" placeholder="DNI" max="99999999" maxlength="8"></div>
            <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Nro. de Trámite"></div>   
       		<input name="isAdmin" type="hidden" <%if(adminPriv){ %>value="admin"<% }else { %> value="user"<% } %>>
            <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Log In</button></div>
            <% if(warning!= null && !warning.isBlank()){ %> <h6 class="alert alert-info text-center"> <% out.print(warning); %> </h6> <% } %>
        </form>
     </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>