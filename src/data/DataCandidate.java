package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Candidate;

public class DataCandidate {

	public LinkedList<Candidate> getAll() throws SQLException{
		Candidate candidate;
		LinkedList<Candidate> list = new LinkedList<>();
		String query = "select * from candidate";
		PreparedStatement getAllStmt = null;
		ResultSet rs = null;
		try {
			getAllStmt = DbConnector.getInstance().getConn().prepareStatement(query);
			rs = getAllStmt.executeQuery();
			
			while (rs!=null && rs.next()) {
				candidate = new Candidate(rs.getString("name"));
				candidate.setId(rs.getInt("id"));
				candidate.setParty(rs.getString("party"));
				candidate.setPhoto(rs.getString("photo"));
				candidate.setVotes(rs.getInt("votes"));
				candidate.setDescription(rs.getString("description"));
				list.add(candidate);
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
	
	public boolean add(Candidate newCandidate) throws SQLException {
		PreparedStatement addStmt = null;
		boolean addConfirmation = false;
	try {
		 if(candidateExists(newCandidate) || newCandidate.getName().isBlank()){
			addConfirmation = false;
		 } else {
			 addStmt = DbConnector.getInstance().getConn().prepareStatement("insert into candidate (name,votes,photo,party,description) values (?,?,?,?,?)");
			 addStmt.setString(1, newCandidate.getName());
			 addStmt.setInt(2, newCandidate.getVotes());
			  
			 if (!newCandidate.getPhoto().isEmpty() && !newCandidate.getParty().isEmpty()) {
			  addStmt.setString(3, newCandidate.getPhoto());
			  addStmt.setString(4, newCandidate.getParty());
			
			  } else if (!newCandidate.getPhoto().isEmpty()){
			 addStmt.setString(3, newCandidate.getPhoto());
			 addStmt.setNull(4, java.sql.Types.VARCHAR);
			
			  } else if (!newCandidate.getParty().isEmpty()) {
			 addStmt.setString(4, newCandidate.getParty());
		     addStmt.setNull(3, java.sql.Types.VARCHAR);	
			
			  } else {
			 addStmt.setNull(3, java.sql.Types.VARCHAR);
			 addStmt.setNull(4, java.sql.Types.VARCHAR);
			}
			 if (!newCandidate.getDescription().isEmpty()) {
				 addStmt.setString(5, newCandidate.getDescription());
			} else {
				addStmt.setNull(5, java.sql.Types.VARCHAR);
			}
			 
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
	
	public boolean candidateExists(Candidate entry) throws SQLException { //returns true if candidate with the same name as entry.getName() exists in database
		boolean response;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = DbConnector.getInstance().getConn().prepareStatement("select id from candidate where name=?");
			statement.setString(1, entry.getName());
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
	
	public boolean modify(Candidate candidate) throws SQLException { 
	
/* 
 * no need to verify the existence of a candidate, since the admin will be sending a valid and existing candidate to this method;
 * the occurrence of another admin deleting a candidate before the execution of this method is very rare. 
 * if the need of a fully concurrent implementation of this method arises, just including an if(CandidateExists()) should be enough.
 */
		PreparedStatement modifyStmt = null;
		boolean modifyConfirmation = false;
		try {
			modifyStmt = DbConnector.getInstance().getConn().prepareStatement("UPDATE candidate SET name=?, party=?, photo=?, description=? WHERE id=?");
			
			modifyStmt.setString(1, candidate.getName());
			modifyStmt.setString(2, candidate.getParty());
			modifyStmt.setString(3, candidate.getPhoto());
			modifyStmt.setString(4, candidate.getDescription());
			
			modifyStmt.setInt(5, candidate.getId());
			
			modifyStmt.executeUpdate();
			modifyConfirmation = true;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (modifyStmt!=null) {
					modifyStmt.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				throw e2;
			}
		}
	return modifyConfirmation;
	}
	
	public boolean delete(int candidateId) throws SQLException {
		PreparedStatement deleteStmt = null;
		boolean deleteConfirmation = false;
		try {
			deleteStmt = DbConnector.getInstance().getConn().prepareStatement("DELETE FROM candidate WHERE id=?");
			
			deleteStmt.setInt(1, candidateId);
			
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
	
	public boolean increaseVotes(int candidateId) {
		PreparedStatement votesStmt = null;
		boolean completedExecution = false;
		try {
			int votes = getVotes(candidateId);
			if(votes >= 0) {
				votesStmt = DbConnector.getInstance().getConn().prepareStatement("UPDATE candidate SET votes=? WHERE id=?");
				votes++;
				votesStmt.setInt(1, votes);
				votesStmt.setInt(2, candidateId);
			
				votesStmt.executeUpdate(); //should i check the row count?
				completedExecution = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (votesStmt!=null) {
					votesStmt.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	return completedExecution;
	}
	
	public int getVotes(int id) {
		int votes = -1;
		
		PreparedStatement getVotesStmt = null;
		ResultSet rs = null;
		try {
			getVotesStmt = DbConnector.getInstance().getConn().prepareStatement("SELECT votes FROM candidate WHERE id=?");
			getVotesStmt.setInt(1, id);
			rs = getVotesStmt.executeQuery();
			
			if (rs!=null && rs.next()) {
				votes = rs.getInt("votes");
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getVotesStmt!=null) {
					getVotesStmt.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	return votes;
	}
	
}
