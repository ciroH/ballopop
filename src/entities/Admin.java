package entities;

public class Admin extends BaseEntity {
	
	public Admin(int id, String password) {
		this.setId(id);
		this.setPassword(password);
	}

}