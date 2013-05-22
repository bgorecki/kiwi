package kiwi.dao;

import kiwi.model.User;

public class UserDao extends GenericDao<User, Long> {

	public UserDao() {
		super(User.class);
	}
	
	

}
