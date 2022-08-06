<%@page import="entities.User"%>
<%@page import="java.util.ArrayList"%>
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
    <% 	ArrayList<User> userList = new ArrayList<User>();
    	userList = (ArrayList<User>)request.getAttribute("userList");
    	String warning = (String)request.getAttribute("warning");	
    	String trigger = (String)request.getAttribute("trigger");	
    	boolean deleteMode = false;
    	if(trigger!=null){
    		if(trigger.equals("delete")) deleteMode = true;
    	}
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
                    <form action="manageuser" method="post">
                    	<input type="hidden" name="userOption" value="add">
                    	<button class="btn-menu" type="submit" style="color: rgb(184,184,184);">Add</button>
                    </form>
                    </li>
                    <li class="nav-item">
                    <form action="" method="">
                    <input type="hidden" name="userOption" value="delete">
                    <button class="btn-menu" type="submit" style="color: rgb(184,184,184);">Delete</button>
                    </form>
                    </li>
                </ul>
            </div><a class="nav-link" href="#" style="color: rgb(184,184,184);filter: blur(0px);">&nbsp; &nbsp;&nbsp;</a>
        </div>
    </nav>
    <div class="container" style="background: #000000;">
        <div class="row">
            <div class="col-md-4"><img class="float-end" src="assets/img/vase-art-gray.jpg"></div>
            <div class="col-md-4 d-xxl-flex my-auto justify-content-xxl-center align-items-xxl-start">
                <div class="table-responsive d-xxl-flex justify-content-xxl-center align-items-xxl-center">
                    <table class="table">
                        <thead style="color: rgb(215,215,215);">
                            <tr>
                             <% if(warning==null){ %>
                                <th>ID</th>
                            	<th>
                            	<%if(trigger!=null){
                            		if(trigger.equals("add")){ 
                                		out.print("Password/Nro. Trámite");
                            		} else if(trigger.equals("delete")) {
                                		out.print("Delete");
                            			}
                            	  } else { 
                                		out.print("voted"); //TODO: check on correctness of this, maybe it gotta be outside the if(trigger!=null)
                            		 } %>
                            	</th>
                            <%	  
                            	} else {  %>
                            <th> <% out.print(warning); %> </th>
                            	<% } %>
                             </tr>
                        </thead>
                        <tbody style="color: rgb(83,83,83);">
                        <!-- //TODO: check everything inside this if(), specially line 108  -->       
                         <% if(warning==null){ 	
                         	 if(trigger!= null){
                               	if(trigger.equals("add")){ %> <!-- 	//would've used switch but i was getting JasperExceptions for no apparent reason  -->
                            <form action="manageuser" method="post">
                            	<tr>
                            		<td> <input type="number" name="id" maxlength="" placeholder="ID"> </td>
                            		<td> <input name="password"placeholder="password"> </td>
                            	</tr>
                            	<tr>
                            		<td> 
                            			<input type="hidden" name="useCase" value="add">
                            			<input type="hidden" name="userOption" value="action">
                            		</td>
                            		<td> <button type="submit" class="btn-menu">Add</button> </td>
                            	</tr>
                            </form>
                            <% } else /* if (trigger.equals("delete") || true)*/{ 
                           		  for(User user : userList){ %> 
                            		<tr>  
                               		 <td><% out.print(user.getId()); %></td>
                               		
                               			<td> 
                               			 <form action="manageuser" method="post">
                               			 	<input type="hidden" name="userOption" value="action">
                               			 	<input type="hidden" name="useCase" value="delete">
                               			 	<input type="hidden" name="id" value="<%= user.getId() %>">
                               			 	<button type="submit" class="btn-menu">Delete</button>
                               			 </form>
                               			</td>
                               		 
                            		</tr>
                            	<% } 
                            	} 
                            	%>
                           <% } else{
                            	 for(User user : userList){ %> 
                        		 <tr>  
                           		  <td><% out.print(user.getId()); %></td>
                           		  <% if(user.hasVoted()){%>
                           		  	<td>
                           		  	<% out.print("✓"); %>
                           		 	</td> 
                           		  <% } else { %>
                           		 	<td> 
                           		 	<%out.print("✗"); %> 
                           		 	</td>
                           		   <% }  %>
                           		</tr>
                          <%  	 }
                       	 		} 
                       	 }%>
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