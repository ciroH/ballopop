package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataCandidate;
import entities.Candidate;

public class LogicCandidate {

	public LinkedList<Candidate> getCandidates() throws SQLException{
	DataCandidate candidateDataAccessObject = new DataCandidate();
	LinkedList<Candidate> candidateList = new LinkedList<>();
	candidateList = candidateDataAccessObject.getAll();
	for (Candidate candidate : candidateList) {
		if(candidate.getDescription() == null) candidate.setDescription("none");
		if(candidate.getParty() == null) candidate.setParty("none");
		if(candidate.getPhoto() == null) candidate.setPhoto("none");
	}
		return candidateList;
	}
	
}
