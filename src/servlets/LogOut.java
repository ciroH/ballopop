package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class LogOut
 */
@WebServlet({ "/LogOut", "/logout", "/Logout" })
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogOut() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Illegal Access");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().invalidate();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
