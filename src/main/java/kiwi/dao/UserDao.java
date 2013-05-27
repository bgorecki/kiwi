package kiwi.dao;

import kiwi.model.User;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class UserDao extends GenericDao<User, Long> {

	public UserDao() {
		super(User.class);
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		User user = null;
		
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		
		if(!criteria.list().isEmpty()) {
			user = (User) criteria.list().get(0);
		}
		
		return user;
	}

}
