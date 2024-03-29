<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Menu</title>
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/Animated-List.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <% request.setAttribute("sessionID", request.getSession().getId()); %>
</head>
<body style="background: #000000">
    <nav class="navbar navbar-light navbar-expand-md" style="color: rgb(184,184,184);background: rgba(255,0,0,0);">
        <div class="container-fluid">
        <form action="LogOut" method="post">
        	<input name="destination" type="hidden" value="menu">
        	<button class="btn-menu" class="nav-link" style="color: rgb(184,184,184);filter: blur(0px);">Back</button>
        </form>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-center" id="navcol-1">
                <ul class="navbar-nav">
                    <li class="nav-item"></li>
                    <li class="nav-item"></li>
                </ul>
            </div><a class="nav-link" href="#" style="color: rgb(184,184,184);filter: blur(0px);">&nbsp; &nbsp;&nbsp;</a>
        </div>
    </nav>
    <div class="container" style="background: #000000;">
        <div class="row">
            <div class="col-md-4"><img class="float-end" src="assets/img/vase-art-gray.jpg"></div>
            <div class="col-md-4 d-xxl-flex my-auto justify-content-xxl-center align-items-xxl-start">
                <div class="table-responsive d-xxl-flex justify-content-xxl-center align-items-xxl-center" style="border-width: 1px;border-color: rgb(72,72,72);">
                    <table class="table">
                        <thead style="color: rgb(215,215,215);">
                            <tr>
                                <th style="text-align: center;color: rgba(215,215,215,0.5);">&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody style="color: rgb(83,83,83);">
                            <tr>
                                <td style="text-align: center;">
                                <form action="ManageMenu" method="post">
                                	<input name="destination" type="hidden" value="candidates">
                                	<button class="btn-menu" type="submit">Candidates</button>
                                </form>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">
								<form action="ManageMenu" method="post">
                                	<input name="destination" type="hidden" value="users">
                                	<button class="btn-menu" type="submit">Users</button>
                                </form>
								</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;">
								<form action="ManageMenu" method="post">
                                	<input name="destination" type="hidden" value="stats">
                                	<button class="btn-menu" type="submit">Stats</button>
                                </form>
								</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4"><img src="assets/img/vase-art-gray-inverted.jpg"></div>
        </div>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/Animated-List.js"></script>

</body>
</html>