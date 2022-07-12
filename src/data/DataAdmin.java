package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Admin;

public class DataAdmin {

	public boolean logIn(Admin credentials) throws SQLException {
		boolean accessConfirmation = false;
		String query = "select id from admin where id=? and password=?";
		PreparedStatement LogInStmt = null;
		ResultSet rs = null;
		try {
			LogInStmt = DbConnector.getInstance().getConn().prepareStatement(query);
			LogInStmt.setInt(1, credentials.getId());
			LogInStmt.setString(2, credentials.getPassword());
			rs = LogInStmt.executeQuery();
			

			accessConfirmation = (rs!=null && rs.next());
			// if resultset has a result it means the login
			//credentials are valid, ergo accessConfirmation is true.

		
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (LogInStmt!=null) {
					LogInStmt.close();
				}
				if (rs!=null) {
					rs.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (Exception e2) {
				throw e2;
			}
		}
		return accessConfirmation;
	}
	
}
