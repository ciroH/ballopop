package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import entities.Candidate;

public class DataCandidate {

	public LinkedList<Candidate> getAll(){
		Candidate candidate;
		LinkedList<Candidate> list = new LinkedList<>();
		String query = "select * from candidates";
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
				list.add(candidate);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
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
				e2.printStackTrace();
			}
		}
		
	return list;
	}
	
	public boolean add(Candidate newCandidate) {
		PreparedStatement addStmt = null;
	/*
	 * if(!verifyCandidate(newCandidate)){
	 * 	return false;
	 * }	
	 */
	try {
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		
	}
		
	return true;
	}
	
}
