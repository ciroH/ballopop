package entities;

public class Candidate extends BaseEntity{
	String name;
	String party;
	String photo;
	int votes;
	
	public Candidate(String name) {
		setVotes(0);
		this.setName(name);
		this.setParty(null);
		this.setPhoto(null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParty() {
		return party;
	}
	
	public void setParty(String party) {
		this.party = party;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getVotes() {
		return votes;
	}
	
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	
	
}
