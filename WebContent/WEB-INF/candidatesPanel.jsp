<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>voting</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Animated-List.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body style="background: #000000">
 <nav class="navbar navbar-light navbar-expand-md" style="color: rgb(184,184,184);background: rgba(255,0,0,0);">
        <div class="container-fluid">
		 <form action="ManageMenu" method="post">
        	<input name="destination" type="hidden" value="menu">
        	<button class="btn-menu" class="nav-link" style="color: rgb(184,184,184);filter: blur(0px);">Back</button>
        </form>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-center" id="navcol-1">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="#" style="color: rgb(184,184,184);">Add</a></li>
                    <li class="nav-item"><a class="nav-link" href="#" style="color: rgb(184,184,184);">modify</a></li>
                    <li class="nav-item"><a class="nav-link" href="#" style="color: rgb(184,184,184);">Delete</a></li>
                </ul>
            </div><a class="nav-link" href="#" style="color: rgb(184,184,184);filter: blur(0px);">&nbsp; &nbsp;&nbsp;</a>
        </div>
    </nav>
    <div class="container-fluid" style="background: #000000;">
        <div class="row d-flex justify-content-center">
            <div class="col-md-4" style="width: 214px;"><img class="float-end" src="assets/img/vase-art-gray-cut.jpg"></div>
            <div class="col-md-4 d-xxl-flex my-auto justify-content-xxl-center align-items-xxl-start" style="width: 800px;">
                <div class="table-responsive d-xxl-flex justify-content-xxl-center align-items-xxl-center">
                    <table class="table">
                        <thead style="color: rgb(215,215,215);">
                            <tr>
                                <th>Name</th>
                                <th>Photo</th>
                                <th>Description</th>
                                <th>Party</th>
                                <th>Votes</th>
                            </tr>
                        </thead>
                        <tbody style="color: rgb(83,83,83);">
                            <tr>
                                <td>Marcus Aurelius</td>
                                <td>https://i.imgur.com/vz7vR8h.jpg</td>
                                <td>Last emperor of the Pax Romana. Served as Roman consul in 140, 145, and 161. Holded the Empire together through Roman Empire´s first true pandemic, the Parthian War, and the Germanic Wars.</td>
                                <td>Cell 4</td>
                                <td>Cell 5</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4" style="width: 214px;"><img src="assets/img/vase-art-gray-cut-inverted.jpg"></div>
        </div>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/Animated-List.js"></script>
</body>
</html>