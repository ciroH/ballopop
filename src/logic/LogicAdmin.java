package logic;

import java.sql.SQLException;

import data.DataAdmin;
import entities.Admin;

public class LogicAdmin {

	public boolean validateLogIn(int key, String password) throws SQLException {
		Admin credentials = new  Admin(key, password);
		DataAdmin data = new DataAdmin();
		boolean logInConfirmation = false;
		try {
			logInConfirmation = data.logIn(credentials);
		} catch (SQLException e) {
			throw e;
		}
		
		return logInConfirmation;
	}
	
}
