package logic;

import data.DataUser;
import entities.User;

public class LogicUser {

	public User validateLogIn(int key, String password) {
		DataUser data = new DataUser();
		User validatedUser = null;
		User credentialsResponse = data.logIn(new User(key, password));
		if (credentialsResponse != null) {
			validatedUser = credentialsResponse;
		}
		return validatedUser;
	}

	public boolean vote(User credentials, int candidateId) {
		DataUser dataUser = new DataUser();
		
		return dataUser.markVote(credentials, candidateId);
		
	}	
}
