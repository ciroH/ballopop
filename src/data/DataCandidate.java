package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		/*	
			if (rs!=null && rs.next()) {
				candidate.setId(rs.getInt("id"));
			}
			
		*/	
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
	return list;
	}
	
	
}
