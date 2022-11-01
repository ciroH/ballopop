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
				/*	if the ResultSet is not null, and there's a next
				 *  position for the rs cursor to point at, the cursor will move to that
				 *  position (from -1 to 0 in this case), and the bpe will check that the credential's
				 *  plain text password matches the DB password after digesting operations. 
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
