package data;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Candidate;

public class DataStats {

	public ArrayList<Candidate> getVotes() throws SQLException{
		DataCandidate data = new DataCandidate();
		ArrayList<Candidate> votesCount = new ArrayList<>(data.getAll());
		return votesCount;
	}
	
}
