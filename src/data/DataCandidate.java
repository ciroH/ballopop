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
		PreparedStatement getAllStatement = null;
		ResultSet rs = null;
		try {
			getAllStatement = DbConnector.getInstance().getConn().prepareStatement(query);
			rs = getAllStatement.executeQuery();
			
			while (rs!=null && rs.next()) {
				candidate = new Candidate(rs.getString("name"));
				candidate.setId(rs.getInt("id"));
				candidate.setParty(rs.getString("party"));
				candidate.setPhoto(rs.getString("photo"));
				candidate.setVotes(rs.getInt("votes"));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (getAllStatement!=null) {
					getAllStatement.close();
				}
				DbConnector.getInstance().releaseConn();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	return list;
	}
	
	
}
