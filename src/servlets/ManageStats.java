package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataStats;

/**
 * Servlet implementation class ManageStats
 */
@WebServlet({ "/ManageStats", "/managestats" })
public class ManageStats extends ManageMenu {
	private static final long serialVersionUID = 1L;
	DataStats data;
       
    public ManageStats() {
        super();
        data = new DataStats();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			data.getVotes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
