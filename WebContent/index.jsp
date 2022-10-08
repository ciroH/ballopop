<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>votingLogIn</title>
    <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.min.css">
    <% String warning = (String)request.getAttribute("warning"); 
       String userType = (String)request.getAttribute("userType");
       boolean adminPriv = false;
       if(userType != null && userType.equals("admin")){
     		adminPriv = true;
        } %>
</head>

<body>
    <section class="login-dark">
     <nav class="navbar navbar-light navbar-expand fixed-top">
            <div class="container-fluid">
                <div class="collapse navbar-collapse d-xxl-flex justify-content-end justify-content-xxl-end" id="navcol-1">
                    <ul class="navbar-nav">
                        <li class="nav-item" style="color: rgb(255,255,255);">
          					<a class="nav-link" <% if(!adminPriv){ %> href="login?userType=user" <% }else { %> href="login?userType=admin" <% } %> style="background: rgba(0,0,0,0.52);color: rgb(242,242,242);">
                       	 <% if(!adminPriv){ %>Admin Login <% }else { %> User Login <% } %></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <form action="login" method="post" accept-charset="utf-8">
            <h2 class="visually-hidden">Login Form</h2>
			<div class="illustration"><img class="logo" <% if(!adminPriv){ %> src="assets/img/ballopop-logo.svg" <% }else{ %> src="assets/img/ballopop-logo-admin.svg" <% } %>></div>
            <div class="mb-3"><input class="form-control no-wheel" type="number" name="id" <% if(adminPriv){ %>placeholder="ID"<% }else { %>placeholder="DNI" <% } %> min="0" max="99999999" maxlength="8" autofocus></div>
            <div class="mb-3"><input class="form-control" type="password" name="password" <% if(adminPriv){ %>placeholder="Password" <% } else{ %>  placeholder="Nro. de Trámite" <% } %> ></div>   
       		<input name="userType" type="hidden" <%if(adminPriv){ %>value="admin"<% }else { %> value="user"<% } %>>
            <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Log In</button></div>
            <% if(warning!= null && !warning.isBlank()){ %> <h6 class="alert alert-info text-center"> <% out.print(warning); %> </h6> <% } %>
        </form>
     </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>