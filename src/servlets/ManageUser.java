package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ManageUser", "/manageuser" })
public class ManageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManageUser() {
        super();
    }

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("warning", "Invalid access");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userOption = (String)request.getAttribute("userOption");
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
