package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class DataUser {

	public User logIn(User credentials) {
		User userObject = null;
		String query = "select voted from user where id=? and password=?";
		PreparedStatement LogInStmt = null;
		ResultSet rs = null;
		try {
			LogInStmt = DbConnector.getInstance().getConn().prepareStatement(query);
			rs = LogInStmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				userObject = new User(credentials.getId(), credentials.getPassword());
				userObject.setVoted(rs.getBoolean("voted"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (LogInStmt!=null) {
					LogInStmt.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return userObject;
	}
	
}
