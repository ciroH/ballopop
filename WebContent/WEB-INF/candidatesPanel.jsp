<%@page import="entities.Candidate"%>
<%@page import="java.util.LinkedList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
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
    <% LinkedList<Candidate> candidateList = (LinkedList<Candidate>)request.getAttribute("candidateList");
       String trigger = (String)request.getAttribute("trigger");
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
                <ul class="navbar-nav">
                     <li class="nav-item">
                    <form action="managecandidate" method="post">
                    	<input type="hidden" name="candidateOption" value="add">
                    	<button class="btn-menu" type="submit" style="color: rgb(184,184,184);">Add</button>
                    </form>
                    </li> <li class="nav-item">
                    <form action="managecandidate" method="post">
                    	<input type="hidden" name="candidateOption" value="modify">
                    	<button class="btn-menu" type="submit" style="color: rgb(184,184,184);">Modify</button>
                    </form>
                    </li> <li class="nav-item">
                    <form action="managecandidate" method="post">
                    	<input type="hidden" name="candidateOption" value="delete">
                    	<button class="btn-menu" type="submit" style="color: rgb(184,184,184);">Delete</button>
                    </form>
                    </li>
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
                                <th>
                                <% 
									if(trigger == null) out.print("Votes");
									else out.print("   ");
                                %>
                                </th>
                            </tr>
                        </thead>
                        <tbody style="color: rgb(83,83,83);">
                           <% if(trigger == null || trigger.equals("delete") || trigger.equals("modify")){ %>
                            <%	for(Candidate candidate : candidateList){  %> 
                            <tr>
                                <th><% out.print(candidate.getName()); %></th>
                                <th><% out.print(candidate.getPhoto()); %></th>
                                <th><% out.print(candidate.getDescription()); %></th>
                                <th><% out.print(candidate.getParty()); %></th>
                                <th>
                                	<% if(trigger == null){ %>
                                	<% out.print(candidate.getVotes()); %>
                                 	<% } else if(trigger.equals("delete")){ %>
                                 		<form action="managecandidate" method="post">
                                 			<input type="hidden" name="candidateOption" value="action">
                                 			<input type="hidden" name="useCase" value="delete">
                                 			<input type="hidden" name="id" value="<%= candidate.getId() %>">
                                 			<button class="btn-main" type="submit" style="color: rgb(184,184,184);">Delete</button>
                                 		</form>
                                 	<% } else /* trigger.equals("modify")*/{ %>	
                               			 <form action="managecandidate" method="post">
                                 			<input type="hidden" name="candidateOption" value="action">
                                 			<input type="hidden" name="useCase" value="modify">
                                 			<input type="hidden" name="id" value="<%= candidate.getId() %>">
                                 			<button class="btn-main" type="submit" style="color: rgb(184,184,184);">Modify</button>
                                 		</form>
                                	<% } %>
                                </th>
                            </tr>
                            <% } %>
                           <% } else if(trigger!=null && trigger.equals("add")){ %>
                           		<form action="managecandidate" method="post">
                           			<th><input name="name" type="text" required ></th>
                           			<th><input name="description" type="text"></th>
                           			<th><input name="party" type="text"></th>
                           			<th><input name="photo" type="text"></th>
                           			<th>
                           				<input type="hidden" name="candidateOption" value="action">
                           				<input type="hidden" name="useCase" value="add">
                           				<button type="submit" class="btn-main" style="color: rgb(184,184,184);">Add</button>
                           			</th>
								</form>
                           	<%	} else if(trigger!=null && trigger.equals("confirmmodify"))/* trigger.equals(confirmmodify) */{ %>
                           		<%	Candidate candidateToModify = (Candidate)request.getAttribute("candidateToModify");
	                           		%>	
                           		<form action="managecandidate" method="post">
                           			<th><input name="name" type="text" required value="<%= candidateToModify.getName() %>" ></th>
                           			<th><input name="description" type="text" value="<%= candidateToModify.getDescription() %>"></th>
                           			<th><input name="party" type="text" value="<%= candidateToModify.getParty() %>"></th>
                           			<th><input name="photo" type="text" value="<%= candidateToModify.getPhoto() %>"></th>
                           			<th>
                           				<input type="hidden" name="candidateOption" value="action">
                           				<input type="hidden" name="id" value="<%= candidateToModify.getId() %>">
                           				<input type="hidden" name="useCase" value="confirmmodify">
                           				<button type="submit" class="btn-main" style="color: rgb(184,184,184);">Confirm</button>
                           			</th>
								</form>
                           	<% } %>
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