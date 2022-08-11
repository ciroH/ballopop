package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Candidate;
import entities.User;
import logic.LogicCandidate;
import logic.LogicUser;

@WebServlet({ "/ManageMenu", "/managemenu" })
public class ManageMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<User> userList = new ArrayList<>();
	LinkedList<Candidate> candidateList = new LinkedList<>();
	
    public ManageMenu() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println((String)request.getAttribute("sessionID"));
		System.out.println(request.getSession().getId());
		
		if (request.getAttribute("sessionID")!=null && (String)request.getAttribute("sessionID")==request.getSession().getId()) { //can implicitly do a null check by inverting the == and replacing it with an .equals(); that way, it won't return NullPointerException.
			doPost(request, response);
		} else {
			request.getSession().invalidate();
			forwardToIndex(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getParameter("destination")) {
		case "menu":
			request.getRequestDispatcher("WEB-INF/mainPanel.jsp").forward(request, response);
			break;
		case "candidates":
			generateCandidateList(request, response);
			request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
			break;
		case "users":
			generateUserList(request, response);
			request.getRequestDispatcher("WEB-INF/usersPanel.jsp").forward(request, response);
			break;
		case "stats":
			
			break;

		default:
			forwardToIndex(request, response);
			break;
		}
	}
	
	private void forwardToIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void generateUserList(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		LogicUser logic = new LogicUser();
		try {
			userList = logic.getAllUsers();
			request.setAttribute("userList", userList);
		} catch (SQLException e) {
			request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
			request.getRequestDispatcher("WEB-INF/mainPanel.jsp").forward(request, response);
		}
	}
	
	protected void generateCandidateList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogicCandidate logic = new LogicCandidate();
		try {
			candidateList = logic.getCandidates();
			request.setAttribute("candidateList", candidateList);
		} catch (SQLException e) {
			request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
			request.getRequestDispatcher("WEB-INF/mainPanel.jsp").forward(request, response);
		}
		
		
	}

}