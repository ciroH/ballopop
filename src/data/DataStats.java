package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Candidate;

public class DataStats {

	public HashMap<String, Integer> getVotes() throws SQLException{
		DataCandidate data = new DataCandidate();
		HashMap<String, Integer> votesCount = new HashMap<>();
		ArrayList<Candidate> candidates = new ArrayList<>(data.getAll());
		for (Candidate candidate : candidates) {
			votesCount.put(candidate.getName(), candidate.getVotes());		
		}
		return votesCount;
	}
	
}
