package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Candidate;
import entities.User;
import logic.LogicAdmin;
import logic.LogicCandidate;
import logic.LogicUser;

/*
 * Servlet implementation class LogIn
 */
@WebServlet({ "/LogIn", "/login" })
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       LogicCandidate logicCandidate;
       LogicUser logicUser;
       LogicAdmin logicAdmin;
    public LogIn() {
        super();
        logicCandidate = new LogicCandidate();
        logicUser = new LogicUser();
        logicAdmin = new LogicAdmin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("userType")!=null) {
			String userType = "";
			if (request.getParameter("userType").equals("user")) {
				userType = "admin";
			} else if(request.getParameter("userType").equals("admin")) {
				userType = "user";
			}			
			request.setAttribute("userType", userType);
		}
		
		forwardToIndex(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("userType") != null && request.getParameter("userType").equals("admin")) {
			if(!request.getParameter("id").isBlank() && !request.getParameter("password").isEmpty()) {
				int key = Integer.parseInt(request.getParameter("id"));
				String password = request.getParameter("password");
				String exceptionMessage = null;
				boolean acceptedCredentials = false;
				try {
				acceptedCredentials = logicAdmin.validateLogIn(key, password);
				} catch (SQLException e) {
				exceptionMessage = e.getMessage();
				} finally {
					if (exceptionMessage != null) {
						request.setAttribute("warning", exceptionMessage);
						request.setAttribute("userType", "admin");
						forwardToIndex(request, response);
					} else if(acceptedCredentials){
						request.setAttribute("adminID", key);
						request.getRequestDispatcher("WEB-INF/mainPanel.jsp").forward(request, response);
					} else {
						request.setAttribute("warning", "invalid credentials");
						request.setAttribute("userType", "admin");
						forwardToIndex(request, response);
					}
					
				}
			} else forwardToIndex(request, response);
		} else if (request.getParameter("userType") != null && request.getParameter("userType").equals("user")) {
		
		if(!request.getParameter("id").isBlank() && !request.getParameter("password").isEmpty()) {
			int key = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			User user = logicUser.validateLogIn(key, password);
			if(user != null && !user.hasVoted()) {
				LinkedList<Candidate> candidateList = new LinkedList<>();	
				try {
					candidateList = logicCandidate.getCandidates();
				} catch (SQLException e) {
					request.setAttribute("warning",e.getSQLState() + " : " + e.getMessage());
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				request.setAttribute("candidates", candidateList);
				request.setAttribute("credentials", user);
				request.getRequestDispatcher("WEB-INF/voting.jsp").forward(request, response);
			} else if(user == null) {
				request.setAttribute("warning","invalid user");
				forwardToIndex(request, response);
				} else if (user.hasVoted()) {
					request.setAttribute("warning","user has already voted");
					forwardToIndex(request, response);
					} else {
						forwardToIndex(request, response);
					}
		 } else forwardToIndex(request, response);
		}
		
	}

	private void forwardToIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
