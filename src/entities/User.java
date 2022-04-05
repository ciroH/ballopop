package entities;

public class User extends BaseEntity{
	boolean voted;
	
	public User(int id, String password) {
		this.setId(id);
		this.setPassword(password);
		this.setVoted(false);
	}

	public boolean hasVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	
}
