<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>voting</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Animated-List.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <% HashMap<String,Integer> votes = (HashMap<String,Integer>)request.getAttribute("votes");
    	%>
</head>
<body style="background: #000000">
 <nav class="navbar navbar-light navbar-expand-md" style="color: rgb(184,184,184);background: rgba(255,0,0,0);">
        <div class="container-fluid">
		 <form action="ManageMenu" method="post">
        	<input name="destination" type="hidden" value="menu">
        	<button class="btn-menu" class="nav-link" style="color: rgb(184,184,184);filter: blur(0px);">Back</button>
        </form>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-center" id="navcol-1">
            </div><a class="nav-link" href="#" style="color: rgb(184,184,184);filter: blur(0px);">&nbsp; &nbsp;&nbsp;</a>
        </div>
    </nav>
    <div class="container-fluid" style="background: #000000;">
        <div class="row d-flex justify-content-center">
            <div class="col-md-4" style="width: 214px;"><img class="float-end" src="assets/img/vase-art-gray-cut.jpg"></div>
            <div>
				<% if(votes.isEmpty()){ %>
					<h4> No Results to show</h4>
				<%} else {%>
					<table>
						<thead>
							<tr>
								<th>Name</th>
								<th>Votes</th>
							</tr>
						</thead>
						<tbody>
							<% for(Map.Entry<String,Integer> candidateEntry = votes){ %>
								<tr>
									<th><% out.print(candidateEntry.getKey()); %></th>
									<th><% out.print(candidateEntry.getValue()); %></th>
								</tr>
							<% } %>
						</tbody>
					</table>
				<% } %>
            </div>
            <div class="col-md-4" style="width: 214px;"><img src="assets/img/vase-art-gray-cut-inverted.jpg"></div>
        </div>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/Animated-List.js"></script>
</body>
</html>