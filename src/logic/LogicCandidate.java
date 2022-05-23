package logic;

import java.util.LinkedList;

import data.DataCandidate;
import entities.Candidate;

public class LogicCandidate {

	public LinkedList<Candidate> getCandidates(){
	DataCandidate candidateDataAccessObject = new DataCandidate();
	LinkedList<Candidate> candidateList = new LinkedList<>();
	candidateList = candidateDataAccessObject.getAll();
		return candidateList;
	}
	
}
