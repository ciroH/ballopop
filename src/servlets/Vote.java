package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.LogicUser;

/**
 * Servlet implementation class Vote
 */
@WebServlet({ "/Vote", "/vote" })
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       LogicUser logicUser;
  
    public Vote() {
        super();
        logicUser = new LogicUser();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("warning","invalid server method call");
		request.getRequestDispatcher("voting.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int candidateId = Integer.parseInt(request.getParameter("candidateId"));
		User credentials = new User(Integer.parseInt(request.getParameter("userId")), request.getParameter("userPassword"));
		boolean candidateExists = false;
		boolean userExists = false;
		
		if(request.getParameter("candidateId")!=null && !request.getParameter("candidateId").isBlank()) {
			candidateExists = true;
		}
		if(request.getParameter("userPassword")!=null && !request.getParameter("userPassword").isBlank()) {
			userExists = true;
		}
		if (candidateExists && userExists) {
			if (logicUser.vote(credentials, candidateId)) {
				//TODO: Implement logOut call
				forwardToIndex(request, response);	
			} else {
				request.setAttribute("warning", "server error, try again");
				forwardToIndex(request, response);	
			}
			
		} else {
			request.setAttribute("waning", "invalid user");
			forwardToIndex(request, response);
		}	
	}


	private void forwardToIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
