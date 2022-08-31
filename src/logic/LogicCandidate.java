package logic;

import java.sql.SQLException;
import java.util.LinkedList;
import com.mysql.cj.util.StringUtils;
import data.DataCandidate;
import entities.Candidate;

public class LogicCandidate {

	public LinkedList<Candidate> getCandidates() throws SQLException{
	DataCandidate candidateDataAccessObject = new DataCandidate();
	LinkedList<Candidate> candidateList = new LinkedList<>();
	candidateList = candidateDataAccessObject.getAll();
	for (Candidate candidate : candidateList) {
		if(StringUtils.isNullOrEmpty(candidate.getDescription())) candidate.setDescription("empty");
		if(StringUtils.isNullOrEmpty(candidate.getParty())) candidate.setParty("none");
		if(StringUtils.isNullOrEmpty(candidate.getPhoto())) candidate.setPhoto("none");
	}
		return candidateList;
	}
	
	public Candidate get(int id) throws SQLException{
		Candidate candidate = null;
		DataCandidate data = new DataCandidate();
		candidate = data.get(id);
		if (candidate.getDescription() == null) candidate.setDescription("");
		if (candidate.getParty() == null) candidate.setParty("");
		if (candidate.getPhoto() == null) candidate.setPhoto("");
		return candidate;
	}
	
}
