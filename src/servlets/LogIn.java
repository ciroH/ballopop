package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Candidate;
import logic.LogicCandidate;

/*
 * Servlet implementation class LogIn
 */
@WebServlet({ "/LogIn", "/login" })
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       LogicCandidate logicCandidate;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        logicCandidate = new LogicCandidate();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Go back to index, you shouldn't be here");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("isAdmin") != null && request.getParameter("isAdmin").equals("admin")) {
			
		} else if (request.getParameter("isAdmin") != null && request.getParameter("isAdmin").equals("user")) {
		LinkedList<Candidate> candidateList = new LinkedList<>();	
		candidateList = logicCandidate.getCandidates();
		request.setAttribute("candidates", candidateList);
		request.getRequestDispatcher("WEB-INF/voting.jsp").forward(request, response);
		} else {
			//redirect to error message
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
