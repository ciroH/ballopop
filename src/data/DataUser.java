package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.util.password.BasicPasswordEncryptor;

import entities.User;

public class DataUser {

	public User logIn(User credentials) {
		User userObject = null;
		String query = "select voted,password from user where id=?";
		PreparedStatement LogInStmt = null;
		ResultSet rs = null;
		BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();
		try {
			LogInStmt = DbConnector.getInstance().getConn().prepareStatement(query);
			LogInStmt.setInt(1, credentials.getId());
			rs = LogInStmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				if(bpe.checkPassword(credentials.getPassword(), rs.getString("password"))) {
				userObject = new User(credentials.getId(), credentials.getPassword());
				userObject.setVoted(rs.getBoolean("voted"));
				}
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
	
	public boolean markVote(User credentials, int candidateId) {
		DataCandidate dataCandidate = new DataCandidate();
		boolean voteConfirmation = false;
		String query = "update user set voted=1 where id=?";
		PreparedStatement voteStatement = null;
		if(this.logIn(credentials) != null){
			try {
				if(dataCandidate.increaseVotes(candidateId)) {
				voteStatement = DbConnector.getInstance().getConn().prepareStatement(query);
				voteStatement.setInt(1, credentials.getId());
				voteStatement.executeUpdate();
				voteConfirmation = true;
				}
			} catch (SQLException e) {
				voteConfirmation = false;
				e.printStackTrace();
			} finally {
				try {
					if(voteStatement != null){
						voteStatement.close();
					}
					DbConnector.getInstance().releaseConn();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}	
		return voteConfirmation;
	}
	
	public boolean add(User newUser) throws SQLException {
		PreparedStatement addStmt = null;
		boolean addConfirmation = false;
		BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();
	try {
		 if(userExists(newUser) || newUser.getPassword().isBlank()){
			addConfirmation = false;
		 } else {
			 addStmt = DbConnector.getInstance().getConn().prepareStatement("insert into user (id,password) values (?,?)");
			 addStmt.setInt(1, newUser.getId());
			 addStmt.setString(2, bpe.encryptPassword(newUser.getPassword()));
			 
			addStmt.executeUpdate();
			addConfirmation = true;
		 }		
	} catch (SQLException e) {
		throw e;
	} finally {
		try {
			if (addStmt!=null) {
				addStmt.close();
			}
			DbConnector.getInstance().releaseConn();
		} catch (SQLException e2) {
			throw e2;
		}
	}
		
	return addConfirmation;
	}

	public boolean userExists(User entry) throws SQLException { //returns true if candidate with the same name as entry.getName() exists in database
		boolean response;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = DbConnector.getInstance().getConn().prepareStatement("select id from user where id=? and password=?");
			statement.setInt(1,entry.getId());
			statement.setString(2,entry.getPassword());
			rs = statement.executeQuery();
			
			if (rs!=null && rs.next()) {
				response = true;
			} else {
				response = false;
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (statement!=null) {
					statement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				throw e2;
			}
		}		
	return response;
	}

	public boolean delete(int userId) throws SQLException {
		PreparedStatement deleteStmt = null;
		boolean deleteConfirmation = false;
		try {
			deleteStmt = DbConnector.getInstance().getConn().prepareStatement("DELETE FROM user WHERE id=?");
			
			deleteStmt.setInt(1, userId);
			
			deleteStmt.executeUpdate();
			deleteConfirmation = true;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (deleteStmt!=null) {
					deleteStmt.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				throw e2;
			}
		}
	return deleteConfirmation;
	}
	
	public ArrayList<User> getAll() throws SQLException{
		User user;
		ArrayList<User> list = new ArrayList<>();
		String query = "select * from user";
		PreparedStatement getAllStmt = null;
		ResultSet rs = null;
		try {
			getAllStmt = DbConnector.getInstance().getConn().prepareStatement(query);
			rs = getAllStmt.executeQuery();
			while (rs!=null && rs.next()) {
				user = new User(rs.getInt("id"), "");
				if (rs.getBoolean("voted")) {
					user.setVoted(true);
				}
				list.add(user);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getAllStmt!=null) {
					getAllStmt.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				throw e2;
			}
		}
		return list;
	}

	
}
