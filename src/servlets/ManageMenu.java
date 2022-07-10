package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ManageMenu", "/managemenu" })
public class ManageMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManageMenu() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("sessionID")!=null && (String)request.getAttribute("sessionID")==request.getSession().getId()) {
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
			request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
			break;
		case "users":
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

}
