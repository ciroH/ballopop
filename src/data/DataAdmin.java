package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jasypt.util.password.BasicPasswordEncryptor;

import entities.Admin;

public class DataAdmin {

	public boolean logIn(Admin credentials) throws SQLException {
		boolean accessConfirmation = false;
		String query = "select password from admin where id=?";
		PreparedStatement LogInStmt = null;
		ResultSet rs = null;
		BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();
		try {
			LogInStmt = DbConnector.getInstance().getConn().prepareStatement(query);
			LogInStmt.setInt(1, credentials.getId());
			rs = LogInStmt.executeQuery();

			if (rs!=null && rs.next()) {
				/*	
				 * 
				 */
				accessConfirmation = bpe.checkPassword(credentials.getPassword(), rs.getString("password"));	
			}
		
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
