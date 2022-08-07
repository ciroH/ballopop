package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataUser;
import entities.User;
import logic.LogicUser;

@WebServlet({ "/ManageUser", "/manageuser" })
public class ManageUser extends ManageMenu {
	private static final long serialVersionUID = 1L;
	LogicUser logic;
	DataUser data;
   
	public ManageUser() {
        super();
        logic = new LogicUser();
        data = new DataUser();
    }

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("warning", "Invalid access");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userOption = (String)request.getParameter("userOption");
		switch (userOption) {
		case "add":
			request.setAttribute("trigger", "add");
			request.getRequestDispatcher("WEB-INF/usersPanel.jsp").forward(request, response);
			break;
		case "delete":
			request.setAttribute("trigger", "delete");
			request.getRequestDispatcher("WEB-INF/usersPanel.jsp").forward(request, response);
			break;
		case "action":
			String useCase = request.getParameter("useCase");
			if(useCase != null){
				switch (useCase) {
				case "add":
					try {
						int id = Integer.parseInt(request.getParameter("id"));
						String pasword = request.getParameter("password"); 
						User newUser = new User(id, pasword);
						if (!data.add(newUser)) {
							request.setAttribute("warning", "User already exists");
						}
					} catch (SQLException e) {
						request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
					} catch (Exception e2) {
						request.setAttribute("warning", e2.getMessage());
					}
					generateUserList(request, response);
					break;
				case "delete":
					
					break;
				}
			}
			request.getRequestDispatcher("WEB-INF/usersPanel.jsp").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("WEB-INF/usersPanel.jsp").forward(request, response);
			break;
		}
	
	}

}
